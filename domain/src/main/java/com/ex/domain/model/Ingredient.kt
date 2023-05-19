package com.ex.domain.model


data class Ingredient(
    val malt:List<IngredientItem>?,
    val hops:List<IngredientItem>?,
    val yeast:String? = ""
)