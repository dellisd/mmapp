package io.dellisd.github.mmapp

import cocoapods.Mapbox.MGLShape
import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.FeatureCollection
import io.github.dellisd.spatialk.geojson.Geometry
import cocoapods.Mapbox.MGLShapeSource
import cocoapods.Mapbox.MGLShapeSourceMeta
import cocoapods.Mapbox.MGLSource
import kotlinx.cinterop.ObjCObjectBase
import platform.Foundation.NSURL

actual class GeoJsonSource actual constructor(actual val sourceId: String, features: FeatureCollection?) {

    val source = MGLShapeSource(sourceId, null, null)

    actual fun setGeoJson(feature: Feature) {
        source.shape = feature.toMapbox() as MGLShape
    }

    actual fun setGeoJson(geometry: Geometry) {
        source.shape = feature.toMapbox() as MGLShape
    }

    actual fun setGeoJson(featureCollection: FeatureCollection) {
        source.shape = feature.toMapbox() as MGLShape
    }

    actual fun setGeoJson(json: String) {
        TODO("Not implemented")
    }

}