package com.eggtart.eggtart.features.main

import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eggtart.eggtart.common.BaseActivity
import com.eggtart.eggtart.common.ui.EggtartTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by wonjin on 2024/03/21
 **/

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun initData() {
    }

    override fun body(): @Composable (paddingValues: PaddingValues) -> Unit = {
        Button(onClick = { viewModel.testSideEffectPopup() }) {
            
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun appBar(): @Composable () -> Unit = {}

    override fun navigationBar(): @Composable () -> Unit = {}
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