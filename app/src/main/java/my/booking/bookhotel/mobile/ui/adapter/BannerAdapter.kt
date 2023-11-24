package my.booking.bookhotel.mobile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.booking.bookhotel.databinding.AdItemBinding
import my.booking.bookhotel.mobile.utils.loadFromUrl

class BannerAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currentList = ArrayList<String>()


    fun submitList(newList: List<String>) {
        currentList.clear()
        currentList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BannerViewHolder(
            AdItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BannerViewHolder).onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}

class BannerViewHolder(
    private val binding: AdItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(link:  String) {
        binding.appCompatImageView.loadFromUrl(link)
    }
}
