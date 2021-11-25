package kr.co.lee.retrofitexample

import java.text.DateFormat
import java.text.SimpleDateFormat

fun getDate(dateString: String): String {
    return try {
        // 주어진 Pattern을 사용하는 SimpleDateFormat 객체 생성
        val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        // 주어진 String을 이용해 date 날짜 생성
        val date = format1.parse(dateString)
        val sdf = SimpleDateFormat("MMM-d, yyyy")
        // 주어진 날짜를 해당하는 패턴으로 format
        sdf.format(date)
    } catch (ex: Exception) {
        ex.printStackTrace()
        "xx"
    }
}

fun getTime(dateString: String?): String {
    return try {
        val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        val date = format1.parse(dateString)
        val sdf: DateFormat = SimpleDateFormat("h:mm a")
        sdf.format(date)
    } catch (ex: java.lang.Exception) {
        ex.printStackTrace()
        "xx"
    }
}