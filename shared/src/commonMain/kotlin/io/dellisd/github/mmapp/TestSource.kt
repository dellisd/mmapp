package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.dsl.featureCollection
import kotlin.js.JsExport

@JsExport
fun makeTestSource(id: String): GeoJsonSource {
    val source = GeoJsonSource("test", null)
    source.setGeoJson(feature)
    return source
}