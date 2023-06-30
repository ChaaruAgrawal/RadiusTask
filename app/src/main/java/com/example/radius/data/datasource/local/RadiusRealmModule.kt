package com.example.radius.data.datasource.local

import com.example.radius.data.realmModels.*
import io.realm.annotations.RealmModule

@RealmModule(classes = [Facilities::class, Facility::class, Option::class, ExclusionList::class, Exclusion::class])
class RadiusRealmModule
