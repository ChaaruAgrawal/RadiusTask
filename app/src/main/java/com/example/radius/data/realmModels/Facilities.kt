package com.example.radius.data.realmModels

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Facilities : RealmModel {
    var facilities: RealmList<Facility> = RealmList()
    var exclusions: RealmList<ExclusionList> = RealmList()
}
