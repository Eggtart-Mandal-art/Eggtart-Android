package com.eggtart.eggtart.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eggtart.eggtart.model.local.MandalartSheetModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by 노원진 on 2024.03.27
 */

@Dao
interface MandalartSheetDao {
    @Insert
    fun insert(vararg sheet: MandalartSheetModel)

    @Query("SELECT * FROM mandalartSheet")
    fun getSheets(): Flow<List<MandalartSheetModel>>
}