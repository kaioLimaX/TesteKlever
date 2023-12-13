package com.example.projetomvvmcleanhilt.di

import android.content.Context
import com.example.projetomvvmcleanhilt.data.local.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseHelper( @ApplicationContext context: Context): DatabaseHelper {
        return DatabaseHelper(context)
    }
}