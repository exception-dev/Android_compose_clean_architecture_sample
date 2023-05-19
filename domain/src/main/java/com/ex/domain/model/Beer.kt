package com.ex.domain.model

data class Beer (
    val id:Long,
    var name: String = "",
    var tagline: String = "",
    var firstBrewed: String = "",
    var description: String = "",
    var imageUrl: String? = "",
    var abv: String? = "",
    var ibu: String? = "",
    var targetFg: String? = "",
    var targetOg: String? = "",
    var ebc: String? = "",
    var srm: String? = "",
    var ph: String? = "",
    var attenustionLevel: Float = 0f,
    var volume: ValueModel?,
    var boilVolume: ValueModel?,
    var method : MethodModel?,
    var ingredients: Ingredient?,
    var foodPairing: List<String>?,
    var brewersTips: String = "",
    var contributedBy : String = ""
)