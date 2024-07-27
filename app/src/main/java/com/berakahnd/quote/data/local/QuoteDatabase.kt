package com.berakahnd.quote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuoteItem::class], version = 1)
abstract class QuoteDatabase : RoomDatabase(){
    abstract fun getDao() : QuoteDao
}