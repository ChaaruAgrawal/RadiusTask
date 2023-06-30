package com.example.radius

import android.app.Application
import com.example.radius.data.datasource.local.RadiusRealmModule
import com.example.radius.di.AppComponent
import com.example.radius.di.AppModule
import com.example.radius.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class RadiusApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule)
            .build()

        Realm.init(this)

        val configuration = RealmConfiguration.Builder()
            .name("facilities.db")
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .schemaVersion(0)
            .modules(RadiusRealmModule())
            .build()

        Realm.setDefaultConfiguration(configuration)
    }

}
