package com.eggtart.eggtart.common

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.eggtart.eggtart.common.ui.BaseSideEffectPopup
import com.eggtart.eggtart.common.ui.EggtartTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 *  Created by wonjin on 2024/03/21
 **/
abstract class BaseActivity : ComponentActivity() {
    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()

        setContent {
            EggtartTheme {
                Scaffold(topBar = { AppBar() }, bottomBar = { NavigationBar() }) { paddingValues ->
                    Surface(modifier = Modifier.padding(paddingValues)) {
                        Body()

                        val sideEffectPopup = (viewModel as BaseViewModel<*, *>).collectAsState().value.sideEffectPopup

                        if (sideEffectPopup != null) {
                            BaseSideEffectPopup(sideEffectPopup = sideEffectPopup, (viewModel as BaseViewModel<*, *>)::dismissSideEffectPopup)
                        }
                    }
                }

                (viewModel as BaseViewModel<*, *>).collectSideEffect {
                    when (it) {
                        is BaseSideEffect.ShowToast -> {
                            Toast.makeText(this, it.text, Toast.LENGTH_SHORT).show()
                        }

                        is BaseSideEffect.ShowPopup -> {
                            (viewModel as BaseViewModel<*, *>).showSideEffectPopup(it)
                        }
                    }
                }
            }
        }
    }

    abstract fun initData()

    @Composable
    protected open fun Body() {
    }

    @Composable
    protected open fun AppBar() {
    }

    @Composable
    protected open fun NavigationBar() {
    }
}