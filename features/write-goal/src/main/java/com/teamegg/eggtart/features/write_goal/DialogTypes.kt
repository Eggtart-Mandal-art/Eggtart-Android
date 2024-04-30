package com.teamegg.eggtart.features.write_goal

import com.teamegg.eggtart.common.util.ServerErrorModel

/**
 *    Created by 노원진 on 2024/04/29
 **/

sealed class DialogTypes {
    data object UnSaveFinish : DialogTypes()

    data object DeleteCell : DialogTypes()

    data class ServerError(val error: ServerErrorModel?) : DialogTypes()
}