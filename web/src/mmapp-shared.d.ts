declare module "mmapp-shared" {
    type Nullable<T> = T | null | undefined
    namespace io.dellisd.github.mmapp {
        class GeoJsonSource {
            constructor(id: string, features: Nullable<any>)
            readonly sourceId: string;
            readonly id: string;
            data: string;
            readonly type: string;
            setGeoJson(feature: any): void
            setGeoJson(geometry: any): void
            setGeoJson(featureCollection: any): void
            setGeoJson(json: string): void
        }

        function makeTestSource(id: string): GeoJsonSource
    }
}