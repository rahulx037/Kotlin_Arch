package dragor.international.ui.base.multiadapter.models

import dragor.international.util.Constants


data class HorizontalImageListModel(val Images : ArrayList<HorizontalImageModel>,
                                    val type : Int = Constants.HORIZONTAL_LIST,
                                    val id : Int = Constants.HORIZONTAL_LIST,
                                    var title : String = "") {
}