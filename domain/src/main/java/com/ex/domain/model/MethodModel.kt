package com.ex.domain.model


data class MethodModel(
    val mashTemp:List<MethodItem>,
    val fermentation:MethodItem?,
    val twist:String? = ""

)