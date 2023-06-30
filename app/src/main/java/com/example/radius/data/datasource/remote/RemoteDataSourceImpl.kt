package com.example.radius.data.datasource.remote

import com.example.radius.data.remote.FacilitiesAPI
import com.example.radius.domain.models.Facilities
import com.example.radius.util.Resource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val facilitiesAPI: FacilitiesAPI) :
    RemoteDataSource {

    override suspend fun getFacilities(): Resource<Facilities> {
        val facilities = facilitiesAPI.getFacilities()
        return if (facilities.facilities.isNotEmpty())
            Resource.Success(facilities)
        else Resource.Error("SOMETHING WENT WRONG")
    }
}
