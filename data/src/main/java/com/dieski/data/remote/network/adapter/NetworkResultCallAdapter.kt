package com.dieski.data.remote.network.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResultCallAdapter(
    private val responseType: Type
) : CallAdapter<Type, Call<Result<Type>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<Type>): Call<Result<Type>> = NetworkResultCall(call)
}