package my.booking.bookhotel.mobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import my.booking.bookhotel.mobile.ui.home.HomeAdapter

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    fun provideHomeAdapter() = HomeAdapter()

}