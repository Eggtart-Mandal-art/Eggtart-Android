package com.eggtart.eggtart.common

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
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
                Scaffold(topBar = appBar(), bottomBar = navigationBar()) { paddingValues ->
                    Surface(modifier = Modifier.padding(paddingValues)) {
                        body()

                        val sideEffectPopup = ((viewModel as BaseViewModel<*, *>).collectAsState().value as BaseState).sideEffectPopup

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
    abstract fun body(): @Composable (PaddingValues) -> Unit
    abstract fun appBar(): @Composable () -> Unit
    abstract fun navigationBar(): @Composable () -> Unit
}