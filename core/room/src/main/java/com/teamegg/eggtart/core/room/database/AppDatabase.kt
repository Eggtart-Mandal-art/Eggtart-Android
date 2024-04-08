package com.teamegg.eggtart.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.teamegg.eggtart.core.room.database.dao.MandalartCellDao
import com.teamegg.eggtart.core.room.database.dao.MandalartSheetDao
import com.google.gson.Gson
import com.teamegg.eggtart.core.room.entity.MandalartCellEntity
import com.teamegg.eggtart.core.room.entity.MandalartSheetEntity

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