package com.eggtart.eggtart.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eggtart.eggtart.data.entity.local.MandalartSheetEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by 노원진 on 2024.03.27
 */

@Dao
interface MandalartSheetDao {
    @Insert
    suspend fun insertSheet(vararg sheet: MandalartSheetEntity)

    @Delete
    suspend fun deleteSheet(vararg sheet: MandalartSheetEntity)

    @Update
    suspend fun updateSheet(vararg sheet: MandalartSheetEntity)

    @Query("SELECT * FROM mandalartSheet")
    suspend fun getSheets(): Flow<List<MandalartSheetEntity>>
}