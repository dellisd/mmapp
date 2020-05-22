declare module "mmapp-shared" {
    type Nullable<T> = T | null | undefined
    namespace io.dellisd.github.mmapp {
        class GeoJsonSource {
            constructor(id: string)
            readonly sourceId: string;
            readonly id: string;
            data: string;
            readonly type: string;
            setGeoJson(feature: any): void
            setGeoJson(geometry: any): void
            setGeoJson(featureCollection: any): void
            setGeoJson(json: string): void
        }

        const feature: any;
        const testSource: GeoJsonSource;
    }
}