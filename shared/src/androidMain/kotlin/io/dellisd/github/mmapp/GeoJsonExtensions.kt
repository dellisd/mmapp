package io.dellisd.github.mmapp

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.github.dellisd.spatialk.geojson.*
import com.mapbox.geojson.Point as MapboxPoint
import com.mapbox.geojson.BoundingBox as MapboxBoundingBox
import com.mapbox.geojson.MultiPoint as MapboxMultiPoint
import com.mapbox.geojson.LineString as MapboxLineString
import com.mapbox.geojson.MultiLineString as MapboxMultiLineString
import com.mapbox.geojson.Polygon as MapboxPolygon
import com.mapbox.geojson.MultiPolygon as MapboxMultiPolygon
import com.mapbox.geojson.Geometry as MapboxGeometry
import com.mapbox.geojson.GeometryCollection as MapboxGeometryCollection
import com.mapbox.geojson.Feature as MapboxFeature
import com.mapbox.geojson.FeatureCollection as MapboxFeatureCollection

fun BoundingBox.toMapbox(): MapboxBoundingBox = when (this.northeast.altitude) {
    null -> MapboxBoundingBox.fromLngLats(
        southwest.longitude,
        southwest.latitude,
        northeast.longitude,
        northeast.latitude
    )
    else -> MapboxBoundingBox.fromLngLats(
        southwest.longitude,
        southwest.latitude,
        southwest.altitude!!,
        northeast.longitude,
        northeast.latitude,
        northeast.altitude!!
    )
}

fun Position.toMapbox(): MapboxPoint = when (val alt = altitude) {
    null -> MapboxPoint.fromLngLat(longitude, latitude)
    else -> MapboxPoint.fromLngLat(longitude, latitude, alt)
}

fun Point.toMapbox(): MapboxPoint = when (val alt = altitude) {
    null -> MapboxPoint.fromLngLat(longitude, latitude, bbox?.toMapbox())
    else -> MapboxPoint.fromLngLat(longitude, latitude, alt, bbox?.toMapbox())
}

fun MultiPoint.toMapbox(): MapboxMultiPoint =
    MapboxMultiPoint.fromLngLats(coordinates.map(Position::toMapbox), bbox?.toMapbox())

fun LineString.toMapbox(): MapboxLineString =
    MapboxLineString.fromLngLats(coordinates.map(Position::toMapbox), bbox?.toMapbox())

fun MultiLineString.toMapbox(): MapboxMultiLineString = MapboxMultiLineString.fromLngLats(
    coordinates.map { it.map(Position::toMapbox) },
    bbox?.toMapbox()
)

fun Polygon.toMapbox(): MapboxPolygon = MapboxPolygon.fromLngLats(
    coordinates.map { it.map(Position::toMapbox) },
    bbox?.toMapbox()
)

fun MultiPolygon.toMapbox(): MapboxMultiPolygon = MapboxMultiPolygon.fromLngLats(
    coordinates.map { polygon -> polygon.map { ring -> ring.map(Position::toMapbox) } },
    bbox?.toMapbox()
)

fun GeometryCollection.toMapbox(): MapboxGeometryCollection =
    MapboxGeometryCollection.fromGeometries(geometries.map(Geometry::toMapbox), bbox?.toMapbox())

fun Geometry.toMapbox(): MapboxGeometry = when (this) {
    is Point -> this.toMapbox()
    is MultiPoint -> this.toMapbox()
    is LineString -> this.toMapbox()
    is MultiLineString -> this.toMapbox()
    is Polygon -> this.toMapbox()
    is MultiPolygon -> this.toMapbox()
    is GeometryCollection -> this.toMapbox()
}

fun Feature.toMapbox(): MapboxFeature {
    val props = JsonObject()
    properties.forEach { (key, value) ->
        when {
            value.isNull -> props.add(key, null)
            value.primitive.booleanOrNull != null -> props.addProperty(key, value.primitive.boolean)
            value.primitive.contentOrNull != null -> props.addProperty(key, value.primitive.content)
            value.primitive.doubleOrNull != null -> props.addProperty(key, value.primitive.double)
            value.primitive.longOrNull != null -> props.addProperty(key, value.primitive.long)
            value.primitive.floatOrNull != null -> props.addProperty(key, value.primitive.float)
            value.primitive.intOrNull != null -> props.addProperty(key, value.primitive.int)
        }
    }
    return MapboxFeature.fromGeometry(geometry?.toMapbox(), props, id, bbox?.toMapbox())
}

fun FeatureCollection.toMapbox(): MapboxFeatureCollection =
    MapboxFeatureCollection.fromFeatures(features.map(Feature::toMapbox), bbox?.toMapbox())