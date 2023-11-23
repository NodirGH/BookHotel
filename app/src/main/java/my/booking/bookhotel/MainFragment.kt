package my.booking.bookhotel

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.booking.bookhotel.databinding.FragmentMainBinding
import my.booking.bookhotel.mobile.ui.home.HomeViewModel
import my.booking.bookhotel.mobile.utils.hide
import my.booking.bookhotel.mobile.utils.loadFromUrl
import my.booking.bookhotel.mobile.utils.show

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

        viewModel.getHotelDetails()

        viewModel.getHotelDetails.observe(requireActivity(), Observer { hotel ->
            binding.shimmerFrameLayout.hide()
            binding.llBtnToChooseRoom.show()
            binding.scrollView.show()
            binding.tvHotelAddress.text = hotel.address
            binding.tvHotelDetails.text = hotel.description
            binding.tvHotelName.text = hotel.name
            binding.tvRatingNumber.text = hotel.rating.toString()
            binding.tvRatingText.text = hotel.ratingName
            binding.tvPriceTourAmount.text = hotel.minimalPrice.toString()
            binding.tvPriceTourInclusive.text = hotel.priceForIt
//            if (hotel.imageUrls.isNotEmpty()){
//            binding.ivCarousel.loadFromUrl(hotel.imageUrls[0])
//            }
            binding.ivCarousel.setImageResource(R.drawable.img_food)
        })

        binding.llBtnToChooseRoom.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToBookRoomFragment())
        }

    }
}