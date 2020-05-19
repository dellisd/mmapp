package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.FeatureCollection
import io.github.dellisd.spatialk.geojson.Geometry
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource as MapboxGeoJsonSource


actual class GeoJsonSource actual constructor(id: String) : MapboxGeoJsonSource(id) {
    actual val sourceId: String
        get() = id

    actual fun setGeoJson(feature: Feature) {
        setGeoJson(feature.toMapbox())
    }

    actual fun setGeoJson(geometry: Geometry) {
        setGeoJson(geometry.toMapbox())
    }

    actual fun setGeoJson(featureCollection: FeatureCollection) {
        setGeoJson(featureCollection.toMapbox())
    }
}