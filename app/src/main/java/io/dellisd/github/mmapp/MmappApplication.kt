package io.dellisd.github.mmapp

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox

class MmappApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Mapbox.getInstance(applicationContext, "pk.eyJ1IjoiZGVsbGlzZCIsImEiOiJjam9obzZpMDQwMGQ0M2tsY280OTh2M2o5In0.XtnbkAMU7nIMkq7amsiYdw")
    }
}