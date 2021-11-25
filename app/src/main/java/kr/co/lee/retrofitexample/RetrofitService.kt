package kr.co.lee.retrofitexample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


// Retrofit 인터페이스 
interface RetrofitService {
    // HTTP method는 GET 방식
    // baseUrl 뒤에 /v2/everything를 붙인다
    @GET("/v2/everything")
    // 추상 메서드 정의
    // @Query 어노테이션을 사용해 질의 문자열 지정
    // 반환값은 Call 객체(이 객체에 네트워킹일을 시키는 구조)
    fun getList(@Query("q") q: String
                ,@Query("apiKey") apiKey: String
                ,@Query("page") page: Long
                ,@Query("pageSize") pageSize: Int): Call<PageListModel>
}