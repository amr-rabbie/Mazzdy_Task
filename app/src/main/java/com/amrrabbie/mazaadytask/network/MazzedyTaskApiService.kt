package com.amrrabbie.mazaadytask.network

import com.amrrabbie.mazaadytask.model.categoriesModel.CategoriesResponse
import com.amrrabbie.mazaadytask.model.propetiesModel.PropertiesResponse
import com.amrrabbie.mazaadytask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MazzedyTaskApiService {

    @GET(Constants.Categories_Url)
    suspend fun getCategories(

    ):Response<CategoriesResponse>

    @GET(Constants.Properites_Url)
    suspend fun getProperties(
        @Query("cat") cat:Int
    ):Response<PropertiesResponse>
}