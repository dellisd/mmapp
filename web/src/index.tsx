import React from "react";
import { io } from "mmapp-shared";
import ReactDOM from "react-dom";

// Aliased namespace import
import GeoJsonSource = io.dellisd.github.mmapp.GeoJsonSource;

import { Hello } from "./components/Hello";
import {Map} from "./components/Map";

console.log(GeoJsonSource);
console.log(new io.dellisd.github.mmapp.GeoJsonSource("no"));
console.log(io.dellisd.github.mmapp.testSource);
console.log(io.dellisd.github.mmapp.feature);

ReactDOM.render(<Map />, document.getElementById("root"));
