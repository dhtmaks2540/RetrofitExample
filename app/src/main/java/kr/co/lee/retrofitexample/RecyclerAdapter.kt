package kr.co.lee.retrofitexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// RecyclerView Adapter
class RecyclerAdapter(val itemList: List<ItemModel>, val context: Context): RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {
    // View 생성 후 ViewHolder에 전달
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lab3, parent, false)

        return ItemViewHolder(view)
    }

    // 하나의 아이템 데이터 셋팅
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: ItemModel = itemList[position]

        val author: String = item.author ?: "Anonymous"
        val titleString = "$author - ${item.title}"

        holder.titleView.text = titleString
        holder.timeView.text = "${getDate(item.publishedAt)} at ${getTime(item.publishedAt)}"
        holder.descView.text = item.description
        Glide.with(context).load(item.urlToImage).override(250, 200).into(holder.imageView)
    }

    // 아이템 개수 확인
    override fun getItemCount(): Int = itemList.size

    // ViewHolder 클래스
    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById<TextView>(R.id.item_title)
        val timeView = view.findViewById<TextView>(R.id.item_time)
        val descView = view.findViewById<TextView>(R.id.item_desc)
        val imageView = view.findViewById<ImageView>(R.id.item_image)
    }
}