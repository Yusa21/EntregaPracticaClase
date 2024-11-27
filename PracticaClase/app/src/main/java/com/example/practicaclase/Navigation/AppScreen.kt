package com.example.navegacion.Navigation

sealed class AppScreen(val route: String){
    object FirstScreen: AppScreen("FirstScreen")
    object SecondScreen: AppScreen("SecondScreen")
}