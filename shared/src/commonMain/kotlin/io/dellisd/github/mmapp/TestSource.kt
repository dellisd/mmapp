package io.dellisd.github.mmapp

import kotlin.js.JsExport

@JsExport
fun makeTestSource(id: String): GeoJsonSource {
    val source = GeoJsonSource("test", null)
    source.setGeoJson(feature)
    return source
}