package my.booking.bookhotel.repository

import my.booking.bookhotel.dto.HotelDetailsDto
import my.booking.bookhotel.remote.services.HomeService
import javax.inject.Inject
import javax.inject.Singleton

interface HomeRepository {
    suspend fun getHotelDetails() : HotelDetailsDto
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
}