package my.booking.bookhotel.mobile.ui.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.booking.bookhotel.R
import my.booking.bookhotel.databinding.ItemRoomBinding
import my.booking.bookhotel.dto.RoomDetailsDto
import my.booking.bookhotel.mobile.utils.loadFromUrl

class BookRoomAdapter : RecyclerView.Adapter<BookRoomAdapter.BookRoomViewHolder>() {

    private lateinit var listener: HotelActionListener

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
            binding.ivCarousel.loadFromUrl(roomDetailsDto.imageUrls[0])
            binding.tvHotelName.text = roomDetailsDto.name
            binding.tvPriceRoom.text = roomDetailsDto.price.toString()
            binding.tvPriceInclusive.text = roomDetailsDto.pricePer
            binding.ivCarousel.setImageResource(R.drawable.img_food)
            binding.llBtnChooseRoom.setOnClickListener { listener.onHotelRoomClick(roomDetailsDto) }
        }
    }
}
