package com.example.compose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.compose.data.Chat
import com.example.compose.data.Contact
import com.example.compose.data.Message

@Database(entities = arrayOf(Chat::class, Message::class, Contact::class), version = 1, exportSchema = false)
@TypeConverters(
    value = [Converters::class]
)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
    abstract fun contactDao(): ContactDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ChatDatabase? = null

        fun getDatabase(context: Context): ChatDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    "chat_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert Anonymous user on creation of database.
                        db.execSQL("INSERT INTO Contact VALUES (1, 'Steve', 'Doe', '0624789090', 'https://picsum.photos/300/300')")
                        db.execSQL("INSERT INTO Contact VALUES (2, 'John', 'Doe', '0624789090', 'https://picsum.photos/300/300')")
                    }
                }).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
