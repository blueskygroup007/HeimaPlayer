package com.bluesky.heimaplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.heimaplayer.model.YueDanVideoResult
import com.bluesky.heimaplayer.widget.YueDanItemView

/**
 * 悦单
 */
class YueDanAdapter : RecyclerView.Adapter<YueDanAdapter.YueDanHolder>() {

    var data = ArrayList<YueDanVideoResult>()

    fun updateData(data: List<YueDanVideoResult>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun loadMoreData(data: List<YueDanVideoResult>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class YueDanHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
        return YueDanHolder(YueDanItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {
        val itemData=data.get(position)
        val itemView=holder.itemView as YueDanItemView
        itemView.setData(itemData)
    }
}