package com.amrrabbie.mazaadytask.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrrabbie.mazaadytask.model.propetiesModel.PropertiesResponse
import com.amrrabbie.mazaadytask.repostry.PropetiesRepostry
import com.rugovit.eventlivedata.MutableEventLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropetiesViewModel
@Inject
constructor(private val propetiesRepostry: PropetiesRepostry) : ViewModel(){

    var propetiesListData= MutableEventLiveData<PropertiesResponse>()

    fun getProperties(cat:Int)=
        viewModelScope.launch {
            propetiesRepostry.getProperties(cat).let { response ->
                if(response.isSuccessful){
                    propetiesListData.postValue(response.body())
                }else{
                    Log.e("TAG", "getProperties: " + response.message(), )
                }
            }
        }
}