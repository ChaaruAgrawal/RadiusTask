package com.example.radius.data.repository

import com.example.radius.domain.models.Facilities
import com.example.radius.util.Resource
import kotlinx.coroutines.flow.Flow

interface FacilityRepository {

    suspend fun getFacilities(): Flow<Resource<Facilities>>
}
