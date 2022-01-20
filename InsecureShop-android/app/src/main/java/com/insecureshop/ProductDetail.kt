package com.insecureshop

data class ProductDetail(
        var id: Int,
        var name: String,
        var imageUri: Int,
        var price: String,
        var rating: Int,
        var url: String = "https://www.kiwa.com",
        var qty: Int = 0
)
