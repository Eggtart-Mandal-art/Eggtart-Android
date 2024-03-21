package com.eggtart.eggtart.features.main

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.eggtart.eggtart.common.BaseActivity
import com.eggtart.eggtart.common.BaseSideEffect
import com.eggtart.eggtart.common.BaseState
import com.eggtart.eggtart.common.BaseViewModel
import com.eggtart.eggtart.common.EggtartTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 *  Created by wonjin on 2024/03/21
 **/

class MainActivity : BaseActivity() {
    override val viewModel by viewModels<MainViewModel>()
    override fun initData() {

    }

    override fun body(paddingValues: PaddingValues) = {}

    override fun appBar() = {}

    override fun navigationBar() = {}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EggtartTheme {
        Greeting("Android")
    }
}