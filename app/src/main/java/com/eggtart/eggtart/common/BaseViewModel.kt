package com.eggtart.eggtart.common

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

/**
 *  Created by wonjin on 2024/03/21
 **/
abstract class BaseViewModel<STATE : Any, SIDE_EFFECT : Any> : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {

}