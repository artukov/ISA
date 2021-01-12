import React, { useState, useEffect } from 'react';
import { usePharmacyDrugs } from '../../hooks/loadPharmacyDrugs';
import DrugDetails from '../drug/DrugDetails';

const DrugList = ({pharmacyID}) => {

    const [drugs, setDrugs] = useState([]);

    const fetchDrugs = usePharmacyDrugs(pharmacyID);

    useEffect(() => {
        setDrugs(fetchDrugs);
        return () => {  
        }
    }, [fetchDrugs]);


    return ( 
    <div>
        <h4>DrugList component</h4>
        { drugs.length ? drugs.map(drug => <DrugDetails drug = {drug} key={drug.id}></DrugDetails>) 
            : <p>no drugs</p>
        }

    </div> );
}
 
export default DrugList;