package my.booking.bookhotel.remote.responses

import com.squareup.moshi.Json
import my.booking.bookhotel.dto.HotelDetailsDto

data class HotelDetailsResponse(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "adress") val address: String?,
    @Json(name = "minimal_price") val minimalPrice: Int?,
    @Json(name = "price_for_it") val priceForIt: String?,
    @Json(name = "rating") val rating: Int?,
    @Json(name = "rating_name") val ratingName: String?,
    @Json(name = "image_urls") val imageUrls: List<String>?,
    @Json(name = "about_the_hotel") val aboutHotels: AboutHotelResponse?
) {
    fun mapToHotelDetailsDto(): HotelDetailsDto {
        return HotelDetailsDto(
            id = id ?: 0,
            name = name ?: "",
            address = address ?: "",
            minimalPrice = minimalPrice ?: 0,
            priceForIt = priceForIt ?: "",
            rating = rating ?: 0,
            ratingName = ratingName ?: "",
            imageUrls = imageUrls ?: emptyList(),
            description = aboutHotels?.description ?: "",
            peculiarities = aboutHotels?.peculiarities ?: emptyList()
        )
    }
}

data class AboutHotelResponse(
    @Json(name = "description") val description: String?,
    @Json(name = "peculiarities") val peculiarities: List<String>?
)