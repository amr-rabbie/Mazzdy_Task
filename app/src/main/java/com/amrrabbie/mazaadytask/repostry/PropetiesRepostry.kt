package com.amrrabbie.mazaadytask.repostry

import com.amrrabbie.mazaadytask.network.MazzedyTaskApiService
import javax.inject.Inject

class PropetiesRepostry
@Inject
constructor(private val mazzedyTaskApiService: MazzedyTaskApiService){

    suspend fun getProperties(cat:Int)=
        mazzedyTaskApiService.getProperties(cat)
}