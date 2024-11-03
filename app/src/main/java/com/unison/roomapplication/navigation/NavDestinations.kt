package com.unison.roomapplication.navigation


sealed class NavDestinations (val route: String) {

    object Home: NavDestinations("home")
    object ProductsForm: NavDestinations("products_form")
    object ProductsEdit: NavDestinations("products_edit")
    object ProudctsList: NavDestinations("products_list")
    object PresentationCard: NavDestinations("presentation_card")

}


