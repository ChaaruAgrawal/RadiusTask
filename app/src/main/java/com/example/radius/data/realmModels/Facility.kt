package com.example.radius.data.realmModels

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Facility : RealmModel {
    @PrimaryKey
    var facilityId: Int = 0

    var name: String = ""

    var options: RealmList<Option> = RealmList()
}
