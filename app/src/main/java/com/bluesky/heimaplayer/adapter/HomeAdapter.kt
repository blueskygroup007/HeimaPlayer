package com.bluesky.heimaplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.heimaplayer.model.HaoKanVideoBean
import com.bluesky.heimaplayer.widget.HomeItemView
import com.bluesky.heimaplayer.widget.ProgressItemView

/**
 *
 * @author BlueSky
 * @date 24.3.24
 * Description:
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    val TYPE_NORMAL_ITEM: Int = 0
    val TYPE_PROGRESS_ITEM: Int = 1

    private var datas = ArrayList<HaoKanVideoBean>()

    fun updateList(datas: List<HaoKanVideoBean>) {
        this.datas.clear()
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun loadMore(datas: List<HaoKanVideoBean>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemViewType(position: Int): Int {
        if (position == datas.size) {
            return TYPE_PROGRESS_ITEM
        } else {
            return TYPE_NORMAL_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        if (viewType == TYPE_NORMAL_ITEM) {
            return HomeHolder(HomeItemView(parent.context))
        } else {
            return HomeHolder(ProgressItemView(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return datas.size + 1
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        if (position < datas.size) {
            val itemData = datas.get(position)
            //kotlin中的强转
            val itemView = holder.itemView as HomeItemView
            //item刷新
            itemView.setData(itemData)
        }
    }
}