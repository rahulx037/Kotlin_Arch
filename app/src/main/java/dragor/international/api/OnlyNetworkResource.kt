package dragor.international.api

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

abstract class OnlyNetworkResource<RequestType> {
    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        result.postValue(Resource.loading(null))
        fetchFromNetwork()
    }

    @MainThread
    open fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) result.postValue(newValue)
    }

     private fun fetchFromNetwork() {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
         result.addSource(apiResponse) { response ->
             result.removeSource(apiResponse)
             response.apply {
                 if (response.isResponseSuccess()){
                     setValue(Resource.success(processResponse(response)))

                 }else{
                     onFetchFailed()
                     response.status?.let { Resource.error(it, null) }?.let { setValue(it) }
                 }
             }
        }
    }


    protected open fun onFetchFailed() {}

    open fun asLiveData(): LiveData<Resource<RequestType>> {
        return result
    }

    @WorkerThread
    protected open fun processResponse(response: ApiResponse<RequestType>): RequestType {
        return response.data!!
    }

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}