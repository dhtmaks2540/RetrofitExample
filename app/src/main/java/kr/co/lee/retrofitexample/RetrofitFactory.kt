package kr.co.lee.retrofitexample

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 객체 선언 방식(싱글턴)
object RetrofitFactory {
    // 기본적으로 적용되는 서버 URL
    private const val BASE_URL = "https://newsapi.org"
    private var instance: Retrofit? = null

    fun getInstance(): Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder() // Builder 패턴
                .baseUrl(BASE_URL)
                // 컨버터 지정(Gson Converter 사용)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return instance!!
    }
}