package kr.co.lee.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var retrofitService: RetrofitService? = null

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView 객체 초기화 및 Layout Manager 지정
        recyclerView = findViewById(R.id.main_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        retrofitService()
    }

    // 네트워킹을 하는 메서드
    private fun retrofitService() {
        // Retrofit 객체 획득
        val retrofit = RetrofitFactory.getInstance()
        // Retrofit 객체에 Service 전달하여 RetrofitService 객체 획득
        retrofitService = retrofit.create(RetrofitService::class.java)

        // Call 객체 획득
        val call: Call<PageListModel>? = retrofitService?.getList(QUERY, API_KEY, 1, 5)

        // 네트워크 관련된 일이기에 개발자 스레드에서 적용
        Runnable {
            call?.enqueue(object : Callback<PageListModel> {
                    // 서버에서 정상적으로 결과 받은 경우
                    override fun onResponse(
                        call: Call<PageListModel>,
                        response: Response<PageListModel>
                    ) {
                        // 응답 성공
                        if(response.isSuccessful) {
                            // RecyclerView Adapter 설정
                            val adapter = RecyclerAdapter(response.body()?.articles!!, this@MainActivity)
                            recyclerView.adapter = adapter
                            Log.d(TAG, "response - successful")
                        } else {
                            // 에러가 발생 한 경우
                            Log.d(TAG, "response - ${response.errorBody()}")
                            Log.d(TAG, "response - ${response.code()}")
                        }
                    }

                    // 서버 연동에 실패한 경우
                    override fun onFailure(call: Call<PageListModel>, t: Throwable) {
                        Log.d(TAG, "failure..")
                    }

                })
        }.run()
    }

    // 상수
    companion object {
        const val QUERY: String = "travel"
        // Api Key는 https://newsapi.org 에서 획득
        const val API_KEY: String = "~~~~~~"
        const val TAG = "MainActivity"
    }
}