import React from "react";
import ReactMapGL, { ViewState, Source, Layer } from "react-map-gl";
import { io } from "mmapp-shared";
import testSource = io.dellisd.github.mmapp.testSource;
import { AutoSizer } from "react-virtualized";
import "mapbox-gl/dist/mapbox-gl.css";

export const Map = () => {
  const [viewport, setViewport] = React.useState<ViewState>({
    longitude: -75.6701,
    latitude: 45.3599,
    zoom: 11,
    bearing: -30,
  });

  return (
    <AutoSizer>
      {({ width, height }: { width: number; height: number }) => (
        <ReactMapGL
          width={width}
          height={height}
          viewState={viewport}
          onViewportChange={setViewport}
          mapboxApiAccessToken="pk.eyJ1IjoiZGVsbGlzZCIsImEiOiJjam9obzZpMDQwMGQ0M2tsY280OTh2M2o5In0.XtnbkAMU7nIMkq7amsiYdw"
        >
          <Source {...testSource}>
            <Layer id="test-fill" type="fill" paint={{}} />
          </Source>
        </ReactMapGL>
      )}
    </AutoSizer>
  );
};
