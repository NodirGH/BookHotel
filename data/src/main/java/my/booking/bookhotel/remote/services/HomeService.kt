package my.booking.bookhotel.remote.services

import my.booking.bookhotel.remote.Home
import my.booking.bookhotel.remote.responses.HotelDetailsResponse
import my.booking.bookhotel.remote.responses.RoomDetailsResponse
import retrofit2.http.GET

interface HomeService {

    @GET(Home.getHotelDetails)
    suspend fun getHotelDetails(): HotelDetailsResponse

    @GET(Home.getRoomDetails)
    suspend fun getRoomDetails(): RoomDetailsResponse
}