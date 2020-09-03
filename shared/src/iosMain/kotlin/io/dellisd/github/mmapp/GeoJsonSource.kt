package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.FeatureCollection
import io.github.dellisd.spatialk.geojson.Geometry
import cocoapods.Mapbox.MGLShapeSource
import cocoapods.Mapbox.MGLShapeSourceMeta
import cocoapods.Mapbox.MGLSource
import cocoapods.Mapbox.MGLShape

actual class GeoJsonSource actual constructor(actual val id: String, features: FeatureCollection?)
    : MGLShapeSource(id, features?.toMapbox() as? MGLShape, null) {

    //val source = MGLShapeSource(id, null, null)

    actual fun setGeoJson(json: String) {
        TODO("Not implemented")
    }

    actual fun setGeoJson(feature: Feature) {
        shape = feature.toMapbox() as MGLShape
    }

    actual fun setGeoJson(geometry: Geometry) {
        shape = feature.toMapbox() as MGLShape
    }

    actual fun setGeoJson(featureCollection: FeatureCollection) {
        shape = feature.toMapbox() as MGLShape
    }
}

