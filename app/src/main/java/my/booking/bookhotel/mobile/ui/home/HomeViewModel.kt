package my.booking.bookhotel.mobile.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    fun getHotelDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = homeUseCase.getHotelDetails()
            val check = result
        }
    }


}