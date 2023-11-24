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
import my.booking.bookhotel.mobile.ui.adapter.PeculiarityAdapter
import my.booking.bookhotel.mobile.ui.home.HomeViewModel
import my.booking.bookhotel.mobile.utils.formatNumberWithCurrency
import my.booking.bookhotel.mobile.utils.hide
import my.booking.bookhotel.mobile.utils.show

@AndroidEntryPoint
class MainFragment : Fragment() {
   private lateinit var binding: FragmentMainBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter: PeculiarityAdapter by lazy { PeculiarityAdapter() }

    //    var sampleImages = intArrayOf(
//        R.drawable.image_1,
//        R.drawable.image_2,
//        R.drawable.image_3,
//    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val imageListener =
//            ImageListener { position, imageView -> imageView.setImageResource(sampleImages[position]) }
//        binding.carouselView.pageCount = sampleImages.size
//        binding.carouselView.setImageListener(imageListener)

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
            binding.tvPriceTourAmount.text = "от ${formatNumberWithCurrency(hotel.minimalPrice)}"
            binding.tvPriceTourInclusive.text = hotel.priceForIt
//            binding.tvPeculiaritiesOne.text = hotel.peculiarities[0]
//            binding.tvPeculiaritiesTwo.text = hotel.peculiarities[1]
//            binding.tvPeculiaritiesThree.text = hotel.peculiarities[2]
//            binding.tvPeculiaritiesFour.text = hotel.peculiarities[3]
            val layoutManager = FlexboxLayoutManager(context)
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            binding.rvPeculiarities.layoutManager = layoutManager
            adapter.submitList(hotel.peculiarities)
            binding.rvPeculiarities.adapter = adapter
        })

        binding.llBtnToChooseRoom.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToBookRoomFragment())
        }



    }
}