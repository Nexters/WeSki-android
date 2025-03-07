package com.dieski.remote.adapter

import com.dieski.remote.model.network.NetworkResult
import com.dieski.remote.model.network.NetworkResult as Result
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class NetworkResultCall<T : Any>(
    private val call: Call<T>
) : Call<NetworkResult<T>> {

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        val networkCallback = object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResult = response.toNetworkResult()
                callback.onResponse(
                    this@NetworkResultCall,
                    Response.success(networkResult)
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val errorResponse = t.toErrorResult<T>()
                return callback.onResponse(
                    this@NetworkResultCall,
                    Response.success(errorResponse)
                )
            }
        }

        call.enqueue(networkCallback)
    }

    override fun clone(): Call<Result<T>> = NetworkResultCall(call.clone())

    override fun execute(): Response<Result<T>> {
        throw UnsupportedOperationException("Weski는 execute()를 지원하지 않습니다. enqueue()를 사용해주세요.")
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}