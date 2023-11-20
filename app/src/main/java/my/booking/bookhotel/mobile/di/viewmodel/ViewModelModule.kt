package my.booking.bookhotel.mobile.di.viewmodel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import my.booking.bookhotel.mobile.ui.home.HomeUseCase
import my.booking.bookhotel.mobile.ui.home.HomeViewModel

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideHomeViewModel(useCase: HomeUseCase) = HomeViewModel(useCase)
}