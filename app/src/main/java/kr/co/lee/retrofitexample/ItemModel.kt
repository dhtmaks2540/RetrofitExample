package kr.co.lee.retrofitexample

data class ItemModel(
    val id: Long,
    val author: String,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String
)
