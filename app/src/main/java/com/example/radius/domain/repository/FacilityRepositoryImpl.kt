package com.example.radius.domain.repository

import com.example.radius.data.datasource.local.RealmDataSource
import com.example.radius.data.datasource.remote.RemoteDataSource
import com.example.radius.data.repository.FacilityRepository
import com.example.radius.data.util.REMOTE
import com.example.radius.domain.models.Facilities
import com.example.radius.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FacilityRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val realmDataSource: RealmDataSource
) : FacilityRepository {

    override suspend fun getFacilities(): Flow<Resource<Facilities>> {
        return flow {
            if (REMOTE) {
                val facilities = remoteDataSource.getFacilities()
                facilities.data?.let {
                    realmDataSource.refreshFacilities(it)
                    emit(Resource.Success(it))
                }
                emit(Resource.Error("SOMETHING_WENT_WRONG"))
            } else
                emit(realmDataSource.getFacilities())
        }
    }
}
