package com.example.radius.data.realmModels

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class ExclusionList : RealmModel {
    var exclusion: RealmList<Exclusion> = RealmList()
}
