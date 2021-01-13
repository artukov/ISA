import React, {useState, useEffect} from 'react';
import { usePharmaDerma } from '../../hooks/loadPharmacyDermatologists';
import DermatologistDetails from '../dermatologist/DermatologistDetails';


const DermatologistList = ({pharmacyID}) => {
    
    const [dermatologists, setDermatologists] = useState([]);

    const fetchDermatologists = usePharmaDerma(pharmacyID);

    useEffect(() => {
        setDermatologists(fetchDermatologists);
       
    }, [fetchDermatologists])
    
    return ( 
    <div>
        <h3>Dermatologists component</h3>
        {
            dermatologists.length ? dermatologists.map(dermatologist => {
                return (
                    <DermatologistDetails key={dermatologist.email} dermatologist = {dermatologist}/>
                )
            }) : <p>no dermatologists</p>
        }
    </div> 
    );
}
 
export default DermatologistList;