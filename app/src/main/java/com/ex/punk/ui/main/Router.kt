package com.ex.punk.ui.main

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Router {
    val route: String
}

object BeerList : Router {
    override val route = "beer_list"
}

object BeerDetail : Router {
    override val route = "beer_detail"
    const val beerArg = "beerId"
    val routeWithArgs = "${route}/{${beerArg}}"
    val arguments = listOf(
        navArgument(beerArg) { type = NavType.IntType }
    )
}
