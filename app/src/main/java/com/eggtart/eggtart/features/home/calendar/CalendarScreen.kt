package com.eggtart.eggtart.features.home.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by 노원진 on 2024.03.24
 */

@Composable
fun CalendarScreen(homePaddingValues: PaddingValues = PaddingValues()) {
    Box(modifier = Modifier.padding(homePaddingValues).fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Calendar")
    }
}