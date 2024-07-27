package com.example.myrcipeapp

import okhttp3.Route

sealed class Screens (val route: String) {
    object RepiceScreen : Screens("recipescreen")
    object DetailScreen : Screens("detailscreen")
}