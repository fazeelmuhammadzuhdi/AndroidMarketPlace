package com.example.marketplace.core.data.repository

import android.util.Log
import com.example.marketplace.core.data.source.local.LocalDataSource
import com.example.marketplace.core.data.source.remote.RemoteDataSource
import com.example.marketplace.core.data.source.remote.network.Resource
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository(val local:LocalDataSource, val remote:RemoteDataSource) {

    fun login(data: LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    emit(Resource.success(body?.data))
                    Log.d("TAG", "Sukses:" + body.toString())
                }else{
                    emit(Resource.error(it.body()?.message?: "Error", null))
                    Log.d("TAG", "Error:" + "keterangan error")
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
            Log.d("TAG", "Error:" + e.message)
        }
    }
}
