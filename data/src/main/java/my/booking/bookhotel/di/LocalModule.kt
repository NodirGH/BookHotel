package my.booking.bookhotel.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.booking.bookhotel.local.AppPreference
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideAppPreference(@ApplicationContext applicationContext: Context): AppPreference{
        return AppPreference(applicationContext)
    }
}