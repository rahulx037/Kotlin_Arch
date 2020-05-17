package com.ehomosepian.club.ui.base.multiadapter.models

import com.ehomosepian.club.util.Constants

class VerticalImageListModel(val Images : ArrayList<VeriticalImageInnerModel>,
                             val type : Int = Constants.VERTICAL_LIST,
                             val id : Int = Constants.VERTICAL_LIST,
                             var title : String = "") {
}