package com.berakahnd.quote.di

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.room.Room
import androidx.work.WorkerFactory
import com.berakahnd.quote.data.local.QuoteDao
import com.berakahnd.quote.data.local.QuoteDatabase
import com.berakahnd.quote.data.remote.QuoteApi
import com.berakahnd.quote.data.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuoteModule {

    @Provides
    @Singleton
    fun provideQuoteRepository(
        application: Application,
        api : QuoteApi,
        dao : QuoteDao
    ): QuoteRepository {
        return QuoteRepository(application, api, dao)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://bible-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : QuoteApi {
        return retrofit.create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteDatabase(@ApplicationContext context : Context) : QuoteDatabase {
        return Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "quoteItem.db"
        ).build()
    }

    @Provides
    fun provideQuoteDao(db: QuoteDatabase) : QuoteDao {
        return db.getDao()
    }

}