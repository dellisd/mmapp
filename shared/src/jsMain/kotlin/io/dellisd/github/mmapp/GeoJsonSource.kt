package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.FeatureCollection
import io.github.dellisd.spatialk.geojson.Geometry
import io.github.dellisd.spatialk.geojson.dsl.geometryCollection

@JsExport
actual class GeoJsonSource actual constructor(val id: String) {
    val type = "geojson"
    actual val sourceId: String get() = id
    var data: dynamic = null

    actual fun setGeoJson(feature: Feature) {
        data = JSON.parse(feature.json)
    }

    actual fun setGeoJson(geometry: Geometry) {
        data = JSON.parse(geometry.json)
    }

    actual fun setGeoJson(featureCollection: FeatureCollection) {
        data = JSON.parse(featureCollection.json)
    }

    actual fun setGeoJson(json: String) {
        data = JSON.parse(json)
    }

}