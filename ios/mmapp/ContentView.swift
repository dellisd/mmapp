//
//  ContentView.swift
//  mmapp
//
//  Created by Derek Ellis on 2020-06-09.
//  Copyright © 2020 Derek Ellis. All rights reserved.
//

import SwiftUI
import Mapbox

struct ContentView: View {
    var body: some View {
        MapView().centerCoordinate(.init(latitude: 45.3846, longitude: -75.7003)).zoomLevel(16)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
