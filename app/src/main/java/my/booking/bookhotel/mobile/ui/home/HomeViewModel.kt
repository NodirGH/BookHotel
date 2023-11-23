package my.booking.bookhotel.mobile.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.booking.bookhotel.dto.BookingDto
import my.booking.bookhotel.dto.HotelDetailsDto
import my.booking.bookhotel.dto.RoomDetailsDto
import my.booking.bookhotel.mobile.utils.LiveEvent
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    var getHotelDetails = LiveEvent<HotelDetailsDto>()
    var getRoomsDetails = LiveEvent<List<RoomDetailsDto>>()
    var getBookingDetails = LiveEvent<BookingDto>()

    fun getHotelDetails(){
        viewModelScope.launch {
            val result = homeUseCase.getHotelDetails()
            getHotelDetails.postValue(result)
        }
    }

    fun getRoomDetails(){
        viewModelScope.launch {
            val result = homeUseCase.getRoomDetails()
            getRoomsDetails.postValue(result)
        }
    }

    fun booking(){
        viewModelScope.launch {
            val result = homeUseCase.booking()
            getBookingDetails.postValue(result)
        }
    }
}