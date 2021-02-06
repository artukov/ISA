import React, { useState, useEffect } from 'react';
import {GoogleApiWrapper, Map, Marker, InfoWindow} from 'google-maps-react'

export const MapContainer = (props) => {
    const [selectedPlace, setSelectedPlace] = useState({ name : ''});
    const selectedLocation = (e) =>{
        console.log(e);
    }

    return ( 
        <Map google={props.google} zoom={14} onClick={(e) => selectedLocation(e)}
            style={ {
                width: '100%',
                height: '100%'
              }}
              center={{
                lat: 40.854885,
                lng: -88.081807
              }}
            >
            <Marker></Marker>
            <InfoWindow >
            <div>
              <h1>{selectedPlace.name}</h1>
            </div>
        </InfoWindow>

        </Map>
     );
}
 
// export default MapContainer;

export default GoogleApiWrapper({
    apiKey : ('Pharmacy-app key')
})(MapContainer)