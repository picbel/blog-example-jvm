package com.blog.be.example.by.delegate

import org.junit.jupiter.api.Test

internal class RestaurantTest{

    private val restaurant: Restaurant = Restaurant(
        name = "테스트토랑",
        address = AddressModel(
            zipCode = "01234",
            address1 = "지구",
            address2 = "어딘가"
        )
    )

    @Test
    fun test(){
        println(restaurant.fullAddress())
        println(restaurant.zipCode)
        println(restaurant.address1)
        println(restaurant.address2)
        println(restaurant.name)
    }
}

