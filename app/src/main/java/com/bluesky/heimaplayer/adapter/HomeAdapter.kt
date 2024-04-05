package com.bluesky.heimaplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.heimaplayer.model.HomeItemResult
import com.bluesky.heimaplayer.widget.HomeItemView

/**
 *
 * @author BlueSky
 * @date 24.3.24
 * Description:
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    private var datas = ArrayList<HomeItemResult>()

    fun updateList(datas: List<HomeItemResult>) {
        this.datas.clear()
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(HomeItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val itemData=datas.get(position)
        //kotlin中的强转
        val itemView=holder.itemView as HomeItemView
        //item刷新
        itemView.setData(itemData)
    }
}