package com.eggtart.eggtart.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eggtart.eggtart.database.dao.MandalartCellDao
import com.eggtart.eggtart.database.dao.MandalartSheetDao

/**
 * Created by 노원진 on 2024.03.27
 */

@Database(entities = [MandalartCellDao::class, MandalartSheetDao::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mandalartCellDao(): MandalartCellDao

    abstract fun mandalartSheetDao(): MandalartSheetDao
}