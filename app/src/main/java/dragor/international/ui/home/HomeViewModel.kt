package dragor.international.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dragor.international.repository.Repository
import dragor.international.ui.base.BaseViewModel

class HomeViewModel(private val repo: Repository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}