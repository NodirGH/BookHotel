package my.booking.bookhotel.mobile.di.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.booking.bookhotel.mobile.ui.home.HomeUseCase
import my.booking.bookhotel.mobile.ui.home.HomeUseCaseImpl
import my.booking.bookhotel.repository.HomeRepository

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideHomeUseCase(repository: HomeRepository): HomeUseCase = HomeUseCaseImpl(repository)
}