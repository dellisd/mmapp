import React from "react";
import { io } from "mmapp-shared";
import ReactDOM from "react-dom";

// Aliased namespace import
import GeoJsonSource = io.dellisd.github.mmapp.GeoJsonSource;
import makeTestSource = io.dellisd.github.mmapp.makeTestSource;

import { Hello } from "./components/Hello";
import {Map} from "./components/Map";

console.log(GeoJsonSource);
console.log(new io.dellisd.github.mmapp.GeoJsonSource("no", null));
console.log(makeTestSource("test"));

ReactDOM.render(<Map />, document.getElementById("root"));
