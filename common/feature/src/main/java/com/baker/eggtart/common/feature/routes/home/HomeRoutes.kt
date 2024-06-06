package com.baker.eggtart.common.feature.routes.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.baker.eggtart.common.feature.types.DrawableResource
import com.baker.eggtart.common.feature.types.StringResource

/**
 *  Created by wonjin on 2024/04/04
 **/

sealed class HomeRoutes(val route: String, @StringRes val labelId: Int = -1, @DrawableRes val unselectedIconId: Int = -1, @DrawableRes val selectedIconId: Int = -1) {
    data object Mandalart : HomeRoutes("mandalart", StringResource.nav_mandalart, DrawableResource.ic_grid_n, DrawableResource.ic_grid_s)
    data object Calendar : HomeRoutes("calendar", StringResource.nav_calendar, DrawableResource.ic_calendar_n, DrawableResource.ic_calendar_s)
    data object Settings : HomeRoutes("settings", StringResource.nav_settings, DrawableResource.ic_settings_n, DrawableResource.ic_settings_s)
}