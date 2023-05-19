package com.ex.punk.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ex.punk.ui.beer.BeerDetailScreen
import com.ex.punk.ui.beer.BeerListScreen

@Composable
fun NavigationScreen(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BeerList.route
    ) {
        composable(BeerList.route) {
            BeerListScreen{ beer ->
                println("**********item click**************")
                println("beer $it")


                navController.navigate(
                    "${BeerDetail.route}/${beer.id}"
                )
            }
        }

        composable(
            route = BeerDetail.routeWithArgs,
            arguments = BeerDetail.arguments
        ) { navBackStackEntry ->

            println("navBackStackEntry : $navBackStackEntry")

            println("navBackStackEntry.arguments : ${navBackStackEntry.arguments}")
            val beerId =
                navBackStackEntry.arguments?.getInt(BeerDetail.beerArg)

            println("beerId : $beerId")
            BeerDetailScreen()

        }


    }
}