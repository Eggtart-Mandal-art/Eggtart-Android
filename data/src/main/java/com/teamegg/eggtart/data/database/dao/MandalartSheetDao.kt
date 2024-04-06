package com.teamegg.eggtart.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.teamegg.eggtart.data.entity.local.MandalartSheetEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by 노원진 on 2024.03.27
 */

@Dao
interface MandalartSheetDao {
    @Insert
    fun insertSheet(vararg sheet: MandalartSheetEntity)

    @Delete
    fun deleteSheet(vararg sheet: MandalartSheetEntity)

    @Update
    fun updateSheet(vararg sheet: MandalartSheetEntity)

    @Query("SELECT * FROM mandalartSheet")
    fun getSheets(): Flow<List<MandalartSheetEntity>>
}