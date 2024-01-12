package com.amrrabbie.mazaadytask.repostry

import com.amrrabbie.mazaadytask.network.MazzedyTaskApiService
import javax.inject.Inject

class CategoriesRepostry
@Inject
constructor(private val mazzedyTaskApiService: MazzedyTaskApiService){
    suspend fun getCategories()=
        mazzedyTaskApiService.getCategories()
}