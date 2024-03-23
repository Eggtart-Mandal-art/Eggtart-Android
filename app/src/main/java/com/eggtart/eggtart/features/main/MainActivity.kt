package com.eggtart.eggtart.features.main

import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.eggtart.eggtart.R
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
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EggtartTheme {
        Text(
            text = "Hello ${LocalContext.current.resources.getString(R.string.app_name)}!"
        )
    }
}