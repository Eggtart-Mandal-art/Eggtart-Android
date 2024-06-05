package com.baker.eggtart.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.baker.eggtart.core.room.database.dao.MandalartCellDao
import com.baker.eggtart.core.room.database.dao.MandalartSheetDao
import com.baker.eggtart.core.room.entity.MandalartCellEntity
import com.baker.eggtart.core.room.entity.MandalartSheetEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
        fun listToJson(value: List<MandalartCellEntity>): String = Json.encodeToString(value)

        @TypeConverter
        fun JsonToList(value: String): List<MandalartCellEntity> = Json.decodeFromString<List<MandalartCellEntity>>(value)

        @TypeConverter
        fun cellToJson(value: MandalartCellEntity?): String = Json.encodeToString(value)

        @TypeConverter
        fun jsonToCell(value: String): MandalartCellEntity? = Json.decodeFromString(value)
    }
}