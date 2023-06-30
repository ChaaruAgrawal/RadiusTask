package com.example.radius.data.datasource.remote

import com.example.radius.domain.models.Facilities
import com.example.radius.util.Resource

interface RemoteDataSource {

    suspend fun getFacilities() : Resource<Facilities>
}
