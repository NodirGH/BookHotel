package my.booking.bookhotel.dto

data class RoomDetailsDto(
    val id: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val pricePer: String = "",
    val peculiarities: List<String> = emptyList(),
    val imageUrls: List<String> = emptyList()
)