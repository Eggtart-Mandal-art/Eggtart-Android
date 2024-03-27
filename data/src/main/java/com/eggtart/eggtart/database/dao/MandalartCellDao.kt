package com.eggtart.eggtart.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eggtart.eggtart.model.local.MandalartCellModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by 노원진 on 2024.03.27
 */

@Dao
interface MandalartCellDao {

    @Insert
    fun insert(vararg cell: MandalartCellModel)

    @Query("SELECT * FROM mandalartCell WHERE sheetId=:sheetId AND depth=:depth")
    fun getCell(sheetId: Long, depth: Int = 0): Flow<MandalartCellModel?>
}