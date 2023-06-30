package com.example.radius.data.remote

import com.example.radius.data.util.FACILITIES_URL
import com.example.radius.domain.models.Facilities
import retrofit2.http.GET

interface FacilitiesAPI {

    @GET(FACILITIES_URL)
    suspend fun getFacilities(): Facilities

}
