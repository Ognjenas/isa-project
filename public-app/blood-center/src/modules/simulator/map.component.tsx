import { Flex } from "@chakra-ui/layout"

import React, { useEffect, useRef } from "react";

import { YMaps, Map, Circle, Placemark, ZoomControl, GeolocationControl } from "react-yandex-maps"
export interface Location {
    latitude: number,
    longitude: number
}

interface Props {
    locator: Location
}
const center = {
    lat: -3.745,
    lng: -38.523
};

const containerStyle = {
    width: '400px',
    height: '400px'
};

export const MapSimulator = ({ locator }: Props) => {
    const mapRef: any = useRef();
    useEffect(() => {
        console.log("Locator changed", locator)
    }, [locator])


    return (
        <Flex id="map" width="100%" height="100%" alignItems="center" justifyContent="center">
            <YMaps>
                <div>
                    <Map defaultState={{ center: [locator.latitude, locator.longitude], zoom: 18 }}
                        instanceRef={mapRef}
                        width="500px"
                        height="500px">
                        <Placemark
                            geometry={[locator.latitude, locator.longitude]}
                            options={{
                                preset: 'islands#geolocationIcon',
                                iconColor: 'green',
                            }}
                        />
                        <ZoomControl />
                        <GeolocationControl options={{ visible: false }} />
                    </Map>
                </div>
            </YMaps>
        </Flex>
    )
}