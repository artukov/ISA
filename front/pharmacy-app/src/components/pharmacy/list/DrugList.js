import React, { useState, useEffect } from 'react';
import { CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../../config/AxiosConfig';
import { urlGetPharmacyDrugs } from '../../../services/UrlService';
import PharmacyDrugDetails from '../details/DrugDetails';

const PharmacyDrugList = ({pharmacyID}) => {
    const [drugs, setDrugs] = useState([]);
    useEffect(() => {
        const loadDrugs = async (id) =>{
            try{
                const resault = await axiosConfig.get(urlGetPharmacyDrugs + id);
                setDrugs(resault.data);
            }
            catch(err){
                console.log(err.response);
            }
        }

        if(pharmacyID !==undefined){
            loadDrugs(pharmacyID);
        }
        
    }, [pharmacyID]);

    return (  
        <div>
            <CardDeck>
                {
                    drugs.length ? (
                        drugs.map(drug => 
                                <PharmacyDrugDetails key={drug.id} drug={drug} pharmacyID={pharmacyID}></PharmacyDrugDetails>
                            )
                    ) : null
                }
            </CardDeck>

        </div>
    );
}
 
export default PharmacyDrugList;