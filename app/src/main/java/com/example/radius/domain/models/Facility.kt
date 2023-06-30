package com.example.radius.domain.models

data class Facility(
    val facility_id: Int,
    val name: String,
    val options: List<Option> = ArrayList()
)

data class Facilities(
    val facilities: List<Facility> = ArrayList(),
    val exclusions: List<List<Exclusion>> = ArrayList()
)

data class Exclusion(
    val facility_id: Int,
    val options_id: Int
)

data class Option(
    val id: Int,
    val name: String,
    val icon: String
)
