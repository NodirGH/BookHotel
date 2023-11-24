package my.booking.bookhotel.mobile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.booking.bookhotel.databinding.ItemPeculiarityBinding

class PeculiarityAdapter : RecyclerView.Adapter<PeculiarityAdapter.PeculiarityViewHolder>() {

    private var peculiarityList: List<String> = emptyList()

    fun submitList(peculiarities: List<String>) {
        peculiarityList = peculiarities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeculiarityViewHolder {
        return PeculiarityViewHolder(
            ItemPeculiarityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PeculiarityViewHolder, position: Int) {
        holder.bindView(peculiarityList[position])
    }

    override fun getItemCount(): Int {
        return peculiarityList.size
    }

    inner class PeculiarityViewHolder(private val binding: ItemPeculiarityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(peculiarities: String){
            binding.tvPeculiarities.text = peculiarities
        }
    }
}