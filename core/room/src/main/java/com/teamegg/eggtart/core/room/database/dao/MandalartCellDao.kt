package com.teamegg.eggtart.core.room.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.teamegg.eggtart.core.room.entity.MandalartCellEntity

/**
 * Created by 노원진 on 2024.03.27
 */

@Dao
interface MandalartCellDao {

    @Insert
    fun insertCell(vararg cell: MandalartCellEntity)

    @Delete
    fun deleteCell(vararg cell: MandalartCellEntity)

    @Update
    fun updateCell(vararg cell: MandalartCellEntity)

    @Query("SELECT * FROM mandalartCell WHERE sheetId=:sheetId AND step=:step")
    fun getCell(sheetId: Long, step: Int = 0): List<MandalartCellEntity>
}