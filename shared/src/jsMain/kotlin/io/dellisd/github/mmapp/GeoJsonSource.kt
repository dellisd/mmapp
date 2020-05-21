package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.FeatureCollection
import io.github.dellisd.spatialk.geojson.Geometry

@JsExport
actual class GeoJsonSource actual constructor(id: String) {
    actual val sourceId: String
        get() = TODO("Not yet implemented")

    actual fun setGeoJson(feature: Feature) {
    }

    actual fun setGeoJson(geometry: Geometry) {
    }

    actual fun setGeoJson(featureCollection: FeatureCollection) {
    }

    actual fun setGeoJson(json: String) {
    }

}