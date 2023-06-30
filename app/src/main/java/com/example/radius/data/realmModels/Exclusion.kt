package com.example.radius.data.realmModels

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Exclusion : RealmModel {
    var facilityId: Int = 0

    var optionsId: Int = 0
}
