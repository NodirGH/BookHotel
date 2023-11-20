package my.booking.bookhotel.dto

data class HotelDetailsDto(
    val id: Int = 0,
    val name  :String = "",
    val address : String = "",
    val minimalPrice : Int = 0,
    val priceForIt : String = "",
    val rating : Int = 0,
    val ratingName : String = "",
    val imageUrls : List<String> = emptyList(),
    val description: String = "",
    val peculiarities: List<String> = emptyList()
)