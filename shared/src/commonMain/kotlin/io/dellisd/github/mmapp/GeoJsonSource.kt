package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.FeatureCollection
import io.github.dellisd.spatialk.geojson.GeoJson
import io.github.dellisd.spatialk.geojson.Geometry

expect class GeoJsonSource(sourceId: String, features: FeatureCollection?) {

    val sourceId: String

    fun setGeoJson(feature: Feature)
    
    fun setGeoJson(geometry: Geometry)

    fun setGeoJson(featureCollection: FeatureCollection)

    fun setGeoJson(json: String)
}

fun makeTestSource(id: String): GeoJsonSource {
    val source = GeoJsonSource("test", null)
    source.setGeoJson(feature)
    return source
}
