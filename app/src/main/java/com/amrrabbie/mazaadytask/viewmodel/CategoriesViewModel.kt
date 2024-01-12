package com.amrrabbie.mazaadytask.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrrabbie.mazaadytask.model.categoriesModel.CategoriesResponse
import com.amrrabbie.mazaadytask.repostry.CategoriesRepostry
import com.rugovit.eventlivedata.MutableEventLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel
@Inject
constructor(private val categoriesRepostry: CategoriesRepostry) : ViewModel(){

    var categoriesListData= MutableEventLiveData<CategoriesResponse>()

    fun getCategories()=
        viewModelScope.launch {
            categoriesRepostry.getCategories().let { response ->
                if(response.isSuccessful){
                    categoriesListData.postValue(response.body())
                }else{
                    Log.e("TAG", "getCategories: ${response.message()}", )
                }
            }
        }
}