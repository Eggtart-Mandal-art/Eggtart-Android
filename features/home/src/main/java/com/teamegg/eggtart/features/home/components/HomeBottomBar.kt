package com.teamegg.eggtart.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.teamegg.eggtart.common.feature.theme.Typography
import com.teamegg.eggtart.navigation.home.HomeRoute

/**
 *  Created by wonjin on 2024/04/04
 **/

@Composable
fun HomeBottomBar(navHostController: NavHostController) {
    val items = remember {
        listOf(HomeRoute.Mandalart, HomeRoute.Calendar, HomeRoute.Settings)
    }

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomVisibilityState = currentRoute in items.map { it.route }

    AnimatedVisibility(visible = bottomVisibilityState, enter = slideInVertically { it } + fadeIn(), exit = slideOutVertically { it } + fadeOut()) {
        Column {
            HorizontalDivider()
            NavigationBar(containerColor = MaterialTheme.colorScheme.background, tonalElevation = 0.dp) {

                items.forEach {
                    Column(modifier = Modifier
                        .weight(1f)
                        .height(80.dp)
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = rememberRipple(bounded = false)
                        ) {
                            navHostController.navigate(it.route) {
                                launchSingleTop = true

                                popUpTo(HomeRoute.Mandalart.route)
                            }
                        }
                        .padding(top = 12.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                        Icon(
                            painter = painterResource(id = if (currentRoute == it.route) it.selectedIconId else it.unselectedIconId),
                            contentDescription = stringResource(id = it.labelId),
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(id = it.labelId),
                            style = com.teamegg.eggtart.common.feature.theme.Typography.labelSmall,
                            color = if (currentRoute == it.route) MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
                        )
                    }
                }
            }
        }
    }
}