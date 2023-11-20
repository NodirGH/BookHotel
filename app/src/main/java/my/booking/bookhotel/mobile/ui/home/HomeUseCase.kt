package my.booking.bookhotel.mobile.ui.home

import my.booking.bookhotel.dto.HotelDetailsDto
import my.booking.bookhotel.repository.HomeRepository
import javax.inject.Inject

interface HomeUseCase {
    suspend fun getHotelDetails() : HotelDetailsDto
}

class HomeUseCaseImpl @Inject constructor(private val repository: HomeRepository): HomeUseCase {

    override suspend fun getHotelDetails(): HotelDetailsDto {
        return repository.getHotelDetails()
    }
}