package com.example.radius.data.datasource.local

import com.example.radius.domain.models.Facilities
import com.example.radius.util.Resource

interface RealmDataSource {

    fun getFacilities(): Resource<Facilities>

    fun refreshFacilities(facilities: Facilities)

    fun clearDatabase()
}
