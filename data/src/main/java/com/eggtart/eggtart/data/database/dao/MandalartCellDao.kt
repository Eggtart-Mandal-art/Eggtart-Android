package com.eggtart.eggtart.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eggtart.eggtart.data.entity.local.MandalartCellEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by 노원진 on 2024.03.27
 */

@Dao
interface MandalartCellDao {

    @Insert
    suspend fun insertCell(vararg cell: MandalartCellEntity)

    @Delete
    suspend fun deleteCell(vararg cell: MandalartCellEntity)

    @Update
    suspend fun updateCell(vararg cell: MandalartCellEntity)

    @Query("SELECT * FROM mandalartCell WHERE sheetId=:sheetId AND depth=:depth")
    suspend fun getCell(sheetId: Long, depth: Int = 0): Flow<MandalartCellEntity?>
}