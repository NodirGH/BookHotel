package my.booking.bookhotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.booking.bookhotel.databinding.FragmentMainBinding
import my.booking.bookhotel.mobile.ui.home.HomeViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {
   private lateinit var binding: FragmentMainBinding
   private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBookRoom.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToBookRoomFragment())
        }

        binding.btnRequestHotelDetails.setOnClickListener {
            viewModel.getHotelDetails()
        }

        binding.btnRequestRoomDetails.setOnClickListener {
            viewModel.getRoomDetails()
        }

        binding.btnRequestBooking.setOnClickListener {

        }
    }
}