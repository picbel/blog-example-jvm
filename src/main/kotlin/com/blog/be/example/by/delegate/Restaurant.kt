package com.blog.be.example.by.delegate


interface Address {
    val zipCode: String
    val address1: String
    val address2: String

    fun fullAddress() = "$zipCode $address1 $address2"
}

data class AddressModel(
    override val zipCode: String,
    override val address1: String,
    override val address2: String
) : Address

data class Restaurant(
    val name: String,
    private val address: Address
) : Address by address
