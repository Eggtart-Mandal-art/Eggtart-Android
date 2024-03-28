package com.eggtart.eggtart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.eggtart.eggtart.data.database.dao.MandalartCellDao
import com.eggtart.eggtart.data.database.dao.MandalartSheetDao
import com.eggtart.eggtart.data.entity.local.MandalartCellEntity
import com.eggtart.eggtart.data.entity.local.MandalartSheetEntity
import com.google.gson.Gson

/**
 * Created by 노원진 on 2024.03.27
 */

@Database(entities = [MandalartCellEntity::class, MandalartSheetEntity::class], version = 1, exportSchema = true)
@TypeConverters(AppDatabase.MandalartCellTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mandalartCellDao(): MandalartCellDao

    abstract fun mandalartSheetDao(): MandalartSheetDao

    class MandalartCellTypeConverter {
        @TypeConverter
        fun listToJson(value: List<MandalartCellEntity>): String = Gson().toJson(value)

        @TypeConverter
        fun JsonToList(value: String): List<MandalartCellEntity> = Gson().fromJson(value, Array<MandalartCellEntity>::class.java).toList()

        @TypeConverter
        fun cellToJson(value: MandalartCellEntity?): String = Gson().toJson(value) ?: ""

        @TypeConverter
        fun jsonToCell(value: String): MandalartCellEntity? = Gson().fromJson(value, MandalartCellEntity::class.java)
    }
}