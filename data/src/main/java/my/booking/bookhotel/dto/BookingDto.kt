package my.booking.bookhotel.dto

data class BookingDto(
    val id: Int = 0,
    val hotelName: String = "",
    val hotelAddress: String = "",
    val horating: Int = 0,
    val ratingName: String = "",
    val departure: String = "",
    val arrivalCountry: String = "",
    val tourDateStart: String = "",
    val tourDateStop: String = "",
    val numberOfNights: Int = 0,
    val room: String = "",
    val nutrition: String = "",
    val tourPrice: Int = 0,
    val fuelCharge: Int = 0,
    val serviceCharge: Int = 0
)