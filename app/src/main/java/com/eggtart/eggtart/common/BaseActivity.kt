package com.eggtart.eggtart.common

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
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
                Scaffold(topBar = appBar(), bottomBar = navigationBar(), content = body())

                (viewModel as BaseViewModel<*, *>).collectSideEffect {
                    when (it) {
                        is BaseSideEffect.ShowToast -> {
                            Toast.makeText(this, it.text, Toast.LENGTH_SHORT).show()
                        }

                        is BaseSideEffect.ShowPopup -> {
                            addContentView(
                                ComposeView(this).apply {

                                    setContent {
                                        Dialog(onDismissRequest = { /*TODO*/ }) {
                                            Text(text = "abcdefg")
                                        }
                                    }
                                }, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                            )
                        }

                        is BaseSideEffect.ShowSnackBar -> {
                            addContentView(
                                ComposeView(this).apply {
                                    setContent {
                                        SnackbarHost(hostState = SnackbarHostState(), snackbar = { snackbarData ->
                                            Snackbar(snackbarData = snackbarData)
                                        })
                                    }
                                },
                                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                            )
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