package my.booking.bookhotel.remote.responses

import com.squareup.moshi.Json
import my.booking.bookhotel.dto.HotelDetailsDto
import my.booking.bookhotel.dto.RoomDetailsDto

data class RoomDetailsResponse(
    @Json(name = "rooms") val rooms: List<RoomDto>
)

data class RoomDto(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "price") val price: Int?,
    @Json(name = "price_per") val pricePer: String?,
    @Json(name = "peculiarities") val peculiarities: List<String>?,
    @Json(name = "image_urls") val imageUrls: List<String>?
)  {
    fun mapToRoomDetailsDto(): RoomDetailsDto {
        return RoomDetailsDto(
            id = id ?: 0,
            name = name ?: "",
            price = price ?: 0,
            pricePer = pricePer ?: "",
            peculiarities = peculiarities ?: emptyList(),
            imageUrls = imageUrls ?: emptyList(),
        )
    }
}