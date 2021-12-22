package com.example.compose.di

import android.app.Application
import com.example.compose.database.ChatDao
import com.example.compose.database.ChatDatabase
import com.example.compose.database.ContactDao
import com.example.compose.database.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class appModule {
    @Provides
    @Singleton
    fun localDatabase(application: Application): ChatDatabase = ChatDatabase.getDatabase(application)

    @Provides
    @Singleton
    fun chatDao(localDatabase: ChatDatabase): ChatDao = localDatabase.chatDao()

    @Provides
    @Singleton
    fun messageDao(localDatabase: ChatDatabase): MessageDao = localDatabase.messageDao()

    @Provides
    @Singleton
    fun contactDao(localDatabase: ChatDatabase): ContactDao = localDatabase.contactDao()
}
