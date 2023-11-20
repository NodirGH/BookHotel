package my.booking.bookhotel.remote.services

import my.booking.bookhotel.remote.Home
import my.booking.bookhotel.remote.responses.HotelDetailsResponse
import retrofit2.http.GET

interface HomeService {

    @GET(Home.getHotelDetails)
    fun getHotelDetails(): HotelDetailsResponse
}