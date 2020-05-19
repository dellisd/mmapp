package io.dellisd.github.mmapp

import io.github.dellisd.spatialk.geojson.Feature.Companion.toFeature

val feature = """{
      "type": "Feature",
      "properties": {},
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          [
            [
              -75.68932056427,
              45.383622119671905
            ],
            [
              -75.69580078125,
              45.39230231271719
            ],
            [
              -75.69756031036376,
              45.39317628606501
            ],
            [
              -75.7005214691162,
              45.391126947939874
            ],
            [
              -75.70060729980469,
              45.38943920192204
            ],
            [
              -75.69970607757568,
              45.38612383980515
            ],
            [
              -75.69966316223145,
              45.38410438758857
            ],
            [
              -75.70086479187012,
              45.38232600426025
            ],
            [
              -75.70189476013184,
              45.38090928598363
            ],
            [
              -75.70172309875488,
              45.37795516347998
            ],
            [
              -75.69734573364258,
              45.38130114778196
            ],
            [
              -75.69339752197266,
              45.38262742910067
            ],
            [
              -75.68932056427,
              45.383622119671905
            ]
          ]
        ]
      }
    }""".toFeature()