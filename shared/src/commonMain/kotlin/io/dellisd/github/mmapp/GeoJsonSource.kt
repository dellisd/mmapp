package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.FeatureCollection
import io.github.dellisd.spatialk.geojson.Geometry

expect class GeoJsonSource(id: String) {

    val sourceId: String

    fun setGeoJson(feature: Feature)

    fun setGeoJson(geometry: Geometry)

    fun setGeoJson(featureCollection: FeatureCollection)

    fun setGeoJson(json: String)
}

val testSource = GeoJsonSource("test").also { it.setGeoJson(feature) }