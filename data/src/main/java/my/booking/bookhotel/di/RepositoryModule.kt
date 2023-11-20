package my.booking.bookhotel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.booking.bookhotel.remote.services.HomeService
import my.booking.bookhotel.repository.HomeRepository
import my.booking.bookhotel.repository.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        service: HomeService
    ): HomeRepository {
        return HomeRepositoryImpl(service)
    }
}