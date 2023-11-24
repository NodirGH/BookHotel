package my.booking.bookhotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import my.booking.bookhotel.databinding.FragmentMainBinding
import my.booking.bookhotel.dto.HotelDetailsDto
import my.booking.bookhotel.mobile.ui.adapter.BannerAdapter
import my.booking.bookhotel.mobile.ui.adapter.PeculiarityAdapter
import my.booking.bookhotel.mobile.ui.home.HomeViewModel
import my.booking.bookhotel.mobile.utils.formatNumberWithCurrency
import my.booking.bookhotel.mobile.utils.hide
import my.booking.bookhotel.mobile.utils.setAutoScroll
import my.booking.bookhotel.mobile.utils.show

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter: PeculiarityAdapter by lazy { PeculiarityAdapter() }
    private lateinit var bannerAdapter: BannerAdapter
    private var hotelName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        bannerAdapter = BannerAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainFragmentHotelsPager.adapter = bannerAdapter
        viewModel.getHotelDetails()

        viewModel.getHotelDetails.observe(requireActivity()) { hotel ->
            setHotelDetails(hotel)
        }

        binding.rlBtnToChooseRoom.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToBookRoomFragment(hotelName))
        }
    }

    private fun setHotelDetails(hotel: HotelDetailsDto){
        if (hotel.name == "UnknownHostException") {
            binding.rlBtnToChooseRoom.hide()
            binding.scrollView.hide()
            binding.shimmerFrameLayout.hide()
            binding.tvUnexpectedError.text = "Сервер недоступен"
            binding.tvUnexpectedError.show()
            return
        } else if (hotel.name.isEmpty()) {
            binding.rlBtnToChooseRoom.hide()
            binding.scrollView.hide()
            binding.shimmerFrameLayout.hide()
            binding.tvUnexpectedError.show()
            return
        }
        binding.shimmerFrameLayout.hide()
        binding.rlBtnToChooseRoom.show()
        binding.scrollView.show()
        binding.tvHotelAddress.text = hotel.address
        binding.tvHotelDetails.text = hotel.description
        binding.tvHotelName.text = hotel.name
        binding.tvRatingNumber.text = hotel.rating.toString()
        binding.tvRatingText.text = hotel.ratingName
        binding.tvPriceTourAmount.text = "от ${formatNumberWithCurrency(hotel.minimalPrice)}"
        binding.tvPriceTourInclusive.text = hotel.priceForIt

        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        binding.rvPeculiarities.layoutManager = layoutManager
        adapter.submitList(hotel.peculiarities)
        binding.rvPeculiarities.adapter = adapter

        bannerAdapter.submitList(hotel.imageUrls)
        binding.mainFragmentHotelsPager.setAutoScroll(5000, hotel.imageUrls.size, 1000)
        hotelName = hotel.name
    }
}