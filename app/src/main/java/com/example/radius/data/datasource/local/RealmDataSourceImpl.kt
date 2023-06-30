package com.example.radius.data.datasource.local

import com.example.radius.data.realmModels.*
import com.example.radius.util.Resource
import io.realm.Realm
import javax.inject.Inject

class RealmDataSourceImpl @Inject constructor(): RealmDataSource {

    private val realm = Realm.getDefaultInstance()

    override fun getFacilities(): Resource<com.example.radius.domain.models.Facilities> {
        val realmFacilities = realm.where(Facilities::class.java).findAll().toList().first()

        val facilitiesList = ArrayList<com.example.radius.domain.models.Facility>()
        realmFacilities.facilities.forEach { facility ->
            val optionList: ArrayList<com.example.radius.domain.models.Option> = ArrayList()
            facility.options.forEach { option ->
                optionList.add(com.example.radius.domain.models.Option(option.id, option.name, option.icon))
            }
            facilitiesList.add(com.example.radius.domain.models.Facility(facility.facilityId, facility.name, optionList))
        }

        val exclusionsList = ArrayList<ArrayList<com.example.radius.domain.models.Exclusion>>()
        realmFacilities.exclusions.forEach { exclusionList ->
            val exclusionListItem = ArrayList<com.example.radius.domain.models.Exclusion>()
            exclusionList.exclusion.forEach { exclusion ->
                exclusionListItem.add(com.example.radius.domain.models.Exclusion(exclusion.facilityId, exclusion.optionsId))
            }
            exclusionsList.add(exclusionListItem)
        }

        return Resource.Success(com.example.radius.domain.models.Facilities(facilitiesList, exclusionsList))
    }

    override fun refreshFacilities(facilities: com.example.radius.domain.models.Facilities) {
        clearDatabase()
        realm.executeTransaction { realmDb: Realm ->
            val realmFacilities = realmDb.createObject(Facilities::class.java)
            facilities.facilities.forEach { facility ->
                val realmFacility = realmDb.createObject(Facility::class.java, facility.facility_id)
                realmFacility.name = facility.name
                facility.options.forEach { option ->
                    val realmOption = realmDb.createObject(Option::class.java, option.id)
                    realmOption.name = option.name
                    realmOption.icon = option.icon
                    realmFacility.options.add(realmOption)
                }
                realmFacilities.facilities.add(realmFacility)
            }
            facilities.exclusions.forEach { exclusionList ->
                val realmExclusionList = realmDb.createObject(ExclusionList::class.java)
                exclusionList.forEach { exclusion ->
                    val realmExclusion = realmDb.createObject(Exclusion::class.java)
                    realmExclusion.facilityId = exclusion.facility_id
                    realmExclusion.optionsId = exclusion.options_id
                    realmExclusionList.exclusion.add(realmExclusion)
                }
                realmFacilities.exclusions.add(realmExclusionList)
            }

            realm.insertOrUpdate(realmFacilities)
        }
    }

    override fun clearDatabase() {
        realm.executeTransaction { realmDb: Realm ->
            realmDb.delete(Facilities::class.java)
            realmDb.delete(Facility::class.java)
            realmDb.delete(Option::class.java)
            realmDb.delete(ExclusionList::class.java)
            realmDb.delete(Exclusion::class.java)
        }
    }

}
