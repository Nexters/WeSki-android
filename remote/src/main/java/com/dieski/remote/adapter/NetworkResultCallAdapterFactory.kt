package com.dieski.remote.adapter

import com.dieski.remote.model.network.NetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    // getRawType - 기저 타입
    // List<String>과 같은 ParameterizedType이 있는 경우 getRawType은 List.class를 반환합니다.

    // getParameterUpperBound - 제네릭 타입의 인자를 반환합니다.
    // List<String> -> getParameterUpperBound(0, returnType) -> String.class
    // Map<String, Int> -> getParameterUpperBound(1, returnType) -> Int.class
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // 먼저 리턴 타입의 로우 타입이 Call인지 확인한다.
        if (getRawType(returnType) != Call::class.java) return null

        // 리턴 타입은 Call<NetworkResult<Foo>>가 돼야 한다.
        require(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResult<Foo>> or Call<NetworkResult<out Foo>>"
        }

        // 리턴 타입에서 첫 번째 제네릭 인자를 얻는다.
        val responseType = getParameterUpperBound(0, returnType)

        // 기대한 것처럼 동작하기 위해선 추출한 제네릭 인자가 NetworkResult 타입이어야 한다. ex) NetworkResult<Foo>
        if (getRawType(responseType) != NetworkResult::class.java) return null

        // NetworkResult 클래스가 제네릭 인자를 가지는지 확인한다. 제네릭 인자로는 응답을 변환할 클래스를 받아야 한다.
        require(responseType is ParameterizedType) {
            "Response must be parameterized as NetworkResult<Foo> or NetworkResult<out Foo>"
        }

        // 마지막으로 Result의 제네릭 인자를 얻어서 CallAdapter를 생성한다.
        val resultType = getParameterUpperBound(0, responseType)

        return NetworkResultCallAdapter(resultType)
    }

    companion object {
        fun create(): NetworkResultCallAdapterFactory = NetworkResultCallAdapterFactory()
    }

    private class NetworkResultCallAdapter(
        private val resultType: Type
    ) : CallAdapter<Type, Call<NetworkResult<Type>>> {

        override fun responseType(): Type = resultType

        override fun adapt(call: Call<Type>) = NetworkResultCall(call)
    }
}