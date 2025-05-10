package com.example.littlelemon

import androidx.room.*

@Entity(tableName = "menu")
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu")
    suspend fun getAll(): List<MenuItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MenuItemEntity>)
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "little_lemon_db"
                ).build().also { instance = it }
            }
        }
    }
}
