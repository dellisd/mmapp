package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.FeatureCollection
import io.github.dellisd.spatialk.geojson.Geometry

actual class GeoJsonSource actual constructor(actual val sourceId: String) {
    actual fun setGeoJson(feature: Feature) {}

    actual fun setGeoJson(geometry: Geometry) {}

    actual fun setGeoJson(featureCollection: FeatureCollection) {}

    actual fun setGeoJson(json: String) {}
    
}