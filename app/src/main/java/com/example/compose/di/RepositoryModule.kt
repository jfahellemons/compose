package com.example.compose.di

import com.example.compose.database.ChatDao
import com.example.compose.database.MessageDao
import com.example.compose.repositories.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMessageRepository(
        messageRepositoryImpl: MessageRepositoryImpl
    ): MessageRepository

    @Binds
    abstract fun bindChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository

    @Binds
    abstract fun bindContactRepository(
        contactRepositoryImpl: ContactsRepositoryImpl
    ): ContactsRepository
}
