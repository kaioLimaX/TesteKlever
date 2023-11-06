package com.example.projetomvvmcleanhilt.di

import com.example.projetomvvmcleanhilt.data.remote.DummyAPI
import com.example.projetomvvmcleanhilt.data.repository.UserRepositoryImpl
import com.example.projetomvvmcleanhilt.domain.repository.UserRepository
import com.example.projetomvvmcleanhilt.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.internal.Util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideDummyAPI( retrofit: Retrofit) : DummyAPI {
        return retrofit.create(DummyAPI::class.java)
    }

    @Provides
    fun provideUserRepository( dummyAPI: DummyAPI) : UserRepository {
        return UserRepositoryImpl(dummyAPI)
    }

}