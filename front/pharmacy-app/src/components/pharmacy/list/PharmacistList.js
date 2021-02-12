import React, { useState, useEffect } from 'react';
import { CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../../config/AxiosConfig';
import { urlGetPharmacyPharmacists } from '../../../services/UrlService';
import PharmacyPharmacistDetails from '../details/PharmacistDetails';

const PharmacyPharmacistList = ({pharmacyID}) => {

    const [pharmacists, setPharmacists] = useState([]);

    useEffect(() => {
        const loadPharmacist = async (id) =>{
            try{
                const result = await axiosConfig.get(urlGetPharmacyPharmacists + pharmacyID);
             
                setPharmacists(result.data);
            }
            catch(err){
                console.log(err.response);
            }
        }
        if(pharmacyID !== undefined){
            loadPharmacist(pharmacyID);
        }

    }, [pharmacyID]);

    return ( 
        <div>
            <CardDeck>
                {
                    pharmacists.length ? (
                        pharmacists.map(pharmacist => 
                            <PharmacyPharmacistDetails key={pharmacist.id} pharmacist={pharmacist} ></PharmacyPharmacistDetails>
                            )
                    ) : null
                }
            </CardDeck>

        </div>
     );
}
 
export default PharmacyPharmacistList;