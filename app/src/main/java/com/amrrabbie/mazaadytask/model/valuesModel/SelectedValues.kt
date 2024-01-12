package com.amrrabbie.mazaadytask.model.valuesModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


class SelectedValues (var category:String?,var subcategory:String?,
                     var processtype:String?,var brand:String?,
                     var transmissiontype:String?,var fueltype:String?,
                     var condition:String?,var color:String?
): Serializable