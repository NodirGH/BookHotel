package my.booking.bookhotel.remote.responses

import com.squareup.moshi.Json
import my.booking.bookhotel.dto.BookingDto

data class BookingResponse(
    @Json(name = "id") val id: Int?,
    @Json(name = "hotel_name") val hotelName: String?,
    @Json(name = "hotel_adress") val hotelAddress: String?,
    @Json(name = "horating") val horating: Int?,
    @Json(name = "rating_name") val ratingName: String?,
    @Json(name = "departure") val departure: String?,
    @Json(name = "arrival_country") val arrivalCountry: String?,
    @Json(name = "tour_date_start") val tourDateStart: String?,
    @Json(name = "tour_date_stop") val tourDateStop: String?,
    @Json(name = "number_of_nights") val numberOfNights: Int?,
    @Json(name = "room") val room: String?,
    @Json(name = "nutrition") val nutrition: String?,
    @Json(name = "tour_price") val tourPrice: Int?,
    @Json(name = "fuel_charge") val fuelCharge: Int?,
    @Json(name = "service_charge") val serviceCharge: Int?
) {
    fun mapToBookingDto(): BookingDto{
        return BookingDto(
            id = id ?: 0,
            hotelName = hotelName ?: "",
            hotelAddress = hotelAddress ?: "",
            horating = horating ?: 0,
            ratingName = ratingName ?: "",
            departure = departure ?: "",
            arrivalCountry = arrivalCountry ?: "",
            tourDateStart = tourDateStart ?: "",
            tourDateStop = tourDateStop ?: "",
            numberOfNights = numberOfNights ?: 0,
            room = room ?: "",
            nutrition = nutrition ?: "",
            tourPrice = tourPrice ?: 0,
            fuelCharge = fuelCharge ?: 0,
            serviceCharge = serviceCharge ?: 0
        )
    }
}