package com.amrrabbie.mazaadytask.model.propetiesModel

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PropertiesResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
) : Parcelable

@Parcelize
data class OptionsItem(

	@field:SerializedName("parent")
	val parent: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("child")
	val child: Boolean? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("parent")
	val parent: String? = null,

	@field:SerializedName("other_value")
	val otherValue: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("options")
	val options: List<OptionsItem?>? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("list")
	val list: Boolean? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable
