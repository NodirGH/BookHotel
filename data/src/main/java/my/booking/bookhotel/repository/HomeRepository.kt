package my.booking.bookhotel.repository

import my.booking.bookhotel.dto.BookingDto
import my.booking.bookhotel.dto.HotelDetailsDto
import my.booking.bookhotel.dto.RoomDetailsDto
import my.booking.bookhotel.remote.services.HomeService
import javax.inject.Inject
import javax.inject.Singleton

interface HomeRepository {
    suspend fun getHotelDetails() : HotelDetailsDto
    suspend fun getRoomDetails() : List<RoomDetailsDto>
    suspend fun booking() : BookingDto
}

@Singleton
class HomeRepositoryImpl @Inject constructor(private val service: HomeService): HomeRepository {

    override suspend fun getHotelDetails(): HotelDetailsDto {
        return try {
            val result = service.getHotelDetails().mapToHotelDetailsDto()
            result
        } catch (e: Exception) {
            val error = e
            HotelDetailsDto()
        }
    }

    override suspend fun getRoomDetails(): List<RoomDetailsDto> {
        return try {
            val result = service.getRoomDetails().rooms.map { it.mapToRoomDetailsDto() }
            result
        } catch (e: Exception){
            val error = e
            emptyList()
        }
    }

    override suspend fun booking(): BookingDto {
        return try {
            val result = service.booking().mapToBookingDto()
            result
        } catch (e: Exception){
            val error = e
            BookingDto()
        }
    }
}