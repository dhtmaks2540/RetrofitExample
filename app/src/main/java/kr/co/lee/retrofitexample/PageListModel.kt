package kr.co.lee.retrofitexample

data class PageListModel(
    val id: Long,
    val totalResults: Long,
    val articles: List<ItemModel>
)
