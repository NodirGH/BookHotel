package my.booking.bookhotel.mobile.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.booking.bookhotel.local.AppPreference
import my.booking.bookhotel.mobile.base.SecurityService
import my.booking.bookhotel.remote.interceptors.JsonParseInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

//    @Singleton
//    @Provides
//    fun provideSecurityService(
//        application: Application,
//        appPreferences: AppPreference
//    ): SecurityService {
//        return SecurityService(application, appPreferences)
//    }
//
//    @Singleton
//    @Provides
//    fun provideAuthSecurityListener(securityService: SecurityService): JsonParseInterceptor.Listener =
//        object : JsonParseInterceptor.Listener {
//            override fun openHomeWithClearStack() {
////                securityService.openMainActivityWithClearStack()
//            }
//        }
}