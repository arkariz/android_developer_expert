package com.arrkariz.capstoneprojectmade.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arrkariz.capstoneprojectmade.R
import com.arrkariz.capstoneprojectmade.databinding.ItemListBinding
import com.arrkariz.core.domain.model.DetailGame
import com.bumptech.glide.Glide

class FavoriteAdapter :RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {

    private var listData = ArrayList<DetailGame>()
    var onItemClick: ((DetailGame) -> Unit)? = null

    fun setData(newListData: List<DetailGame>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: DetailGame) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .into(imgPoster)
                tvItemTitle.text = data.name
                tvRating.text = data.rating.toString()
                tvItemReleased.text = data.released
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}