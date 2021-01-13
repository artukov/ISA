import React, { useState, useEffect } from 'react'
import { usePharmacyExaminations } from '../../hooks/loadPharmacyExaminations';
import ExaminationDetails from '../appointment/ExaminationDetails';

const ExaminationList = ({pharmacyID}) => {
    const [examinations, setExaminations] = useState([]);

    const fetchExaminations = usePharmacyExaminations(pharmacyID)

    useEffect(() => {
        
        setExaminations(fetchExaminations);
        
    }, [fetchExaminations])


    return ( <div>
        {
            examinations.map(examination => {
                return (
                    <ExaminationDetails key={examination.id} examination = {examination} ></ExaminationDetails>
                )
            })
        }
    </div> );
        
   

}
    
 
export default ExaminationList;
