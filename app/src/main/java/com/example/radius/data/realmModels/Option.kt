package com.example.radius.data.realmModels

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Option : RealmModel {
    @PrimaryKey
    var id: Int = 0

    var name: String = ""

    var icon: String = ""
}
