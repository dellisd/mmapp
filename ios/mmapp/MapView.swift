//
//  MapView.swift
//  mmapp
//
//  Created by Derek Ellis on 2020-06-12.
//  Copyright © 2020 Derek Ellis. All rights reserved.
//

import SwiftUI
import Mapbox
import Shared

struct MapView: UIViewRepresentable {
    private let mapView: MGLMapView = MGLMapView(frame: .zero, styleURL: MGLStyle.lightStyleURL)
    
    func makeUIView(context: UIViewRepresentableContext<MapView>) -> MGLMapView {
        mapView.delegate = context.coordinator
        return mapView
    }
    
    func updateUIView(_ uiView: MGLMapView, context: UIViewRepresentableContext<MapView>) {

    }
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    func styleURL(_ styleURL: URL) -> MapView {
        mapView.styleURL = styleURL
        return self
    }
    
    func centerCoordinate(_ centerCoordinate: CLLocationCoordinate2D) -> MapView {
        mapView.centerCoordinate = centerCoordinate
        return self
    }
    
    func zoomLevel(_ zoomLevel: Double) -> MapView {
        mapView.zoomLevel = zoomLevel
        return self
    }
    
    final class Coordinator: NSObject, MGLMapViewDelegate {
        var control: MapView
        
        init(_ control: MapView) {
            self.control = control
        }
        
        func mapView(_ mapView: MGLMapView, didFinishLoading style: MGLStyle) {
            
            
            let shapeSource = TestSourceKt.makeTestSource(id: "test")
            mapView.style?.addSource(shapeSource)
            
            let fillLayer = MGLFillStyleLayer(identifier: "buildingFillLayer", source: shapeSource)
            fillLayer.fillColor = NSExpression(forConstantValue: UIColor.blue)
            fillLayer.fillOpacity = NSExpression(forConstantValue: 0.5)

            mapView.style?.addLayer(fillLayer)
            
            let coordinates = [
            CLLocationCoordinate2D(latitude: 37.791329, longitude: -122.396906),
            CLLocationCoordinate2D(latitude: 37.791591, longitude: -122.396566),
            CLLocationCoordinate2D(latitude: 37.791147, longitude: -122.396009),
            CLLocationCoordinate2D(latitude: 37.790883, longitude: -122.396349),
            CLLocationCoordinate2D(latitude: 37.791329, longitude: -122.396906),
        ]
        
        let buildingFeature = MGLPolygonFeature(coordinates: coordinates, count: 5)
            //shapeSource.shape = buildingFeature
        /*let shapeSource = MGLShapeSource(identifier: "buildingSource", features: [buildingFeature], options: nil)
        mapView.style?.addSource(shapeSource)
        
        let fillLayer = MGLFillStyleLayer(identifier: "buildingFillLayer", source: shapeSource)
        fillLayer.fillColor = NSExpression(forConstantValue: UIColor.blue)
        fillLayer.fillOpacity = NSExpression(forConstantValue: 0.5)
        
        mapView.style?.addLayer(fillLayer)*/

        }
        
        func mapView(_ mapView: MGLMapView, viewFor annotation: MGLAnnotation) -> MGLAnnotationView? {
            return nil
        }
         
        func mapView(_ mapView: MGLMapView, annotationCanShowCallout annotation: MGLAnnotation) -> Bool {
            return true
        }
        
    }
}

#if DEBUG
struct MapView_Previews: PreviewProvider {
    static var previews: some View {
        MapView()
    }
}
#endif
