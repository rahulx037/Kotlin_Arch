package dragor.international.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dragor.international.R
import dragor.international.ui.MainActivity
import dragor.international.ui.base.multiadapter.FeedAdapter
import dragor.international.ui.base.multiadapter.FeedItemBinder
import dragor.international.ui.base.multiadapter.FeedItemClass
import dragor.international.ui.base.multiadapter.models.*
import dragor.international.ui.base.multiadapter.viewholder.HorizontalImagesListViewBinder
import dragor.international.ui.base.multiadapter.viewholder.SearchViewBinder
import dragor.international.ui.base.multiadapter.viewholder.VerticalImagesListViewBinder
import dragor.international.ui.base.multiadapter.viewholder.VerticalImagesViewBinder
import dragor.international.util.Constants
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : Fragment() {

    val arrayListImages : ArrayList<Any> = ArrayList()
    val arrayListImagesNatureVertical : ArrayList<VeriticalImageInnerModel> = ArrayList()
    val arrayListHorizontalImages : ArrayList<HorizontalImageModel> = ArrayList()
    val arrayListFoodVerticalImages : ArrayList<VeriticalImageModel> = ArrayList()
    val arrayListCoupleVerticalImages : ArrayList<HorizontalImageModel> = ArrayList()
    private var adapter: FeedAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_discover, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addData()
    }


    //region Add images

    private fun addData() {

        for (i in 1..6) {
            val veriticalImageModel =
                VeriticalImageInnerModel(getVerticalImages(i), Constants.VERTICAL)
            arrayListImagesNatureVertical.add(veriticalImageModel)
        }

        for (i in 1..6) {
            val horizontalImageModel  =
                HorizontalImageModel(getHorizontalImages(i),Constants.HORIZONTAL)
            arrayListHorizontalImages.add(horizontalImageModel)
        }

        for (i in 1..6) {
            val horizontalImageModel  = VeriticalImageModel(getFoodVerticalImages(i),Constants.VERTICAL)
            arrayListFoodVerticalImages.add(horizontalImageModel)
        }

        for (i in 1..4) {
            val horizontalImageModel  =
                HorizontalImageModel(getCoupleImages(i),Constants.VERTICAL_LIST)
            arrayListCoupleVerticalImages.add(horizontalImageModel)
        }

        val searchString = SearchModel("Search..",Constants.SEARCH)

        val verticalImageListModel  = VerticalImageListModel(arrayListImagesNatureVertical,Constants.VERTICAL_LIST)
        verticalImageListModel.title = "Doctors"

        val verticalImageListModel2  = VerticalImageListModel(arrayListImagesNatureVertical,Constants.VERTICAL_LIST)
        verticalImageListModel2.title = "University"

        val horizontalImageListModel1  = HorizontalImageListModel(arrayListCoupleVerticalImages,Constants.HORIZONTAL_LIST)
        horizontalImageListModel1.title = "Insurence"

        // Adding two couple horizontal images list
        val horizontalImageListModel  = HorizontalImageListModel(arrayListCoupleVerticalImages,Constants.HORIZONTAL_LIST)
        horizontalImageListModel.title = "Near Me"

        // Adding two food vertical images
        for (i in 0..6) {
            when (i){
               0 ->{
                   arrayListImages.add(searchString)
                   arrayListImages.add(verticalImageListModel)
                }
                1 ->{
                    arrayListImages.add(horizontalImageListModel1)
                }
                2 ->{
                    arrayListImages.add(horizontalImageListModel)
                    arrayListImages.add(verticalImageListModel2)

                }
                3->{
                    arrayListImages.add(arrayListFoodVerticalImages[i])
                }
                4-> {

                    arrayListImages.add(arrayListFoodVerticalImages[i])
                }
                5->{
                    arrayListImages.add(arrayListFoodVerticalImages[i])
                    arrayListImages.add(arrayListFoodVerticalImages[i])

                }

            }

        }

        showFeedItems(vertical_recyclerview,arrayListImages)

    }

    //endregion

    //region gettring images from reosurces
    private fun getVerticalImages(numer : Int) : Int{
        return when(numer){
            1 -> R.drawable.avt1
            2 -> R.drawable.avt2
            3 -> R.drawable.avt3
            4 -> R.drawable.avt4
            else -> R.drawable.avt1
        }
    }

    private fun getHorizontalImages(numer : Int) : Int{
        return when(numer){
            1 -> R.drawable.avt1
            2 -> R.drawable.avt2
            3 -> R.drawable.avt3
            4 -> R.drawable.avt4
            5 -> R.drawable.avt5
            6 -> R.drawable.avt1
            else -> R.drawable.avt5
        }
    }

    private fun getFoodVerticalImages(numer : Int) : Int{
        return when(numer){
            1 -> R.drawable.avt1
            2 -> R.drawable.avt1
            3 -> R.drawable.avt1
            4 -> R.drawable.avt1
            5 -> R.drawable.avt1
            6 -> R.drawable.avt1
            else -> R.drawable.avt1
        }
    }

    private fun getCoupleImages(numer : Int) : Int{
        return when(numer){
            1 -> R.drawable.avt1
            2 -> R.drawable.avt2
            3 -> R.drawable.avt3
            4 -> R.drawable.avt4
            5 -> R.drawable.avt5
            6 -> R.drawable.avt2
            else -> R.drawable.avt5
        }
    }

    //endregion

    fun searchClick(data : SearchModel){

    }
    //region adapter

    fun verticalImageClick(data : VeriticalImageModel){
//        Toast.makeText(this@MainActivity,"",Toast.LENGTH_SHORT).show()
    }

    fun verticalImageInnerClick(data : VeriticalImageInnerModel){
//        Toast.makeText(this@MainActivity,"",Toast.LENGTH_SHORT).show()
    }

    fun horizontalImageClick(data : HorizontalImageModel){

    }

    private fun showFeedItems(recyclerView: RecyclerView, list: ArrayList<Any>?) {

        if (adapter == null) {
            val viewBinders = mutableMapOf<FeedItemClass, FeedItemBinder>()

            val searchViewBinder=SearchViewBinder {data : SearchModel ->
                 searchClick(data)}

            val verticalImagesViewBinder = VerticalImagesViewBinder { data : VeriticalImageModel ->
                verticalImageClick(data)}

            val verticalImagesListViewBinder = VerticalImagesListViewBinder { data : VeriticalImageInnerModel ->
                verticalImageInnerClick(data)}

            val horizontalImagesViewBinder = HorizontalImagesListViewBinder { data : HorizontalImageModel ->
                horizontalImageClick(data)}

            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                searchViewBinder.modelClass,
                searchViewBinder as FeedItemBinder)
            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                verticalImagesViewBinder.modelClass,
                verticalImagesViewBinder as FeedItemBinder)

            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                horizontalImagesViewBinder.modelClass,
                horizontalImagesViewBinder as FeedItemBinder)

            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                verticalImagesListViewBinder.modelClass,
                verticalImagesListViewBinder as FeedItemBinder)

            adapter = FeedAdapter(viewBinders)
        }



        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            ViewCompat.setNestedScrollingEnabled(recyclerView,true);
            recyclerView.setRecycledViewPool(recycledViewPool);
        }
        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter

        }
        (recyclerView.adapter as FeedAdapter).submitList(list ?: emptyList())
    }


}
