package my.booking.bookhotel.mobile.ui.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import my.booking.bookhotel.R
import my.booking.bookhotel.databinding.ItemRoomBinding
import my.booking.bookhotel.dto.RoomDetailsDto
import my.booking.bookhotel.mobile.ui.adapter.BannerAdapter
import my.booking.bookhotel.mobile.ui.adapter.PeculiarityAdapter
import my.booking.bookhotel.mobile.utils.formatNumberWithCurrency
import my.booking.bookhotel.mobile.utils.loadFromUrl
import my.booking.bookhotel.mobile.utils.setAutoScroll

class BookRoomAdapter : RecyclerView.Adapter<BookRoomAdapter.BookRoomViewHolder>() {

    private lateinit var listener: HotelActionListener
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var peculiarityAdapter: PeculiarityAdapter

    fun setPeculiarityAdapter(peculiarityAdapter: PeculiarityAdapter){
        this.peculiarityAdapter = peculiarityAdapter
    }

    fun setBannerAdapter(bannerAdapter: BannerAdapter){
        this.bannerAdapter = bannerAdapter
    }

    fun setOnActionListener(listener: HotelActionListener) {
        this.listener = listener
    }

    interface HotelActionListener {
        fun onHotelRoomClick(roomDetailsDto: RoomDetailsDto)
    }

    private var hotelRoomList: List<RoomDetailsDto> = emptyList()

    fun submitList(roomDto: List<RoomDetailsDto>) {
        hotelRoomList = roomDto
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookRoomViewHolder {
        return BookRoomViewHolder(
            ItemRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookRoomViewHolder, position: Int) {
        holder.bindView(
            hotelRoomList[holder.bindingAdapterPosition]
        )
    }

    override fun getItemCount() = hotelRoomList.size

    inner class BookRoomViewHolder(private val binding: ItemRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(
            roomDetailsDto: RoomDetailsDto
        ) {

            binding.mainFragmentRoomsPager

            val layoutManager = FlexboxLayoutManager(binding.root.context)
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            binding.rvRoomPeculiarities.layoutManager = layoutManager
            peculiarityAdapter.submitList(roomDetailsDto.peculiarities)
            binding.rvRoomPeculiarities.adapter = peculiarityAdapter

            binding.tvHotelName.text = roomDetailsDto.name
            binding.tvPriceRoom.text = formatNumberWithCurrency(roomDetailsDto.price)
            binding.tvPriceInclusive.text = roomDetailsDto.pricePer
            binding.llBtnChooseRoom.setOnClickListener { listener.onHotelRoomClick(roomDetailsDto) }

            binding.mainFragmentRoomsPager.adapter = bannerAdapter
            bannerAdapter.submitList(roomDetailsDto.imageUrls)
            binding.mainFragmentRoomsPager.setAutoScroll(5000, roomDetailsDto.imageUrls.size, 1000)
        }
    }
}
