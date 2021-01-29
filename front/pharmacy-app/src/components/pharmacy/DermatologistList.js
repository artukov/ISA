import React, {useState, useEffect, useContext} from 'react';
import { CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
// import dermatologistReducer from '../../reducer/dermatologistReducer';
// import { usePharmaDerma } from '../../hooks/loadPharmacyDermatologists';
import { urlDeleteDermaPharmacy, urlGetPhramacyDermatologists } from '../../services/UrlService';
import DermatologistDetails from '../dermatologist/DermatologistDetails';
import { DermatologistContext } from '../dermatologist/DermatologistContext';


const DermatologistList = ({pharmacyID,reload}) => {
    
    // const [dermatologists, setDermatologists] = useState([]);
    // const [state, dispatch] = useReducer(dermatologistReducer, dermatologists)

    // useEffect(() => {
    //     // console.log('dermatologists list', pharmacyID);
    //     const loadDermatologists = async (id) =>{
    //         try{
    //             const resault = await axiosConfig.get(urlGetPhramacyDermatologists + id);
    //             console.log('dermatologists \n', resault.data);
    //             setDermatologists(resault.data);
    //         }
    //         catch(err){
    //             console.log(err.response);
    //         }
    //     }

    //     // setDermatologists(fetchDermatologists);

    //     if(pharmacyID !== undefined){
    //         loadDermatologists(pharmacyID);
    //     }

    // }, [pharmacyID, reload]);

    const {state} = useContext(DermatologistContext);

    // const deleteDermatologist = async (id) =>{
    //     try{
    //         // const resault = 
    //         await axiosConfig.delete(urlDeleteDermaPharmacy + id + "/" + pharmacyID);
    //         // console.log(resault);

    //         // setDermatologists(dermatologists.filter(dermatologist => dermatologist.id !== id));
    //     }
    //     catch(err){
    //         alert(err.response.data);
    //     }        
    // }
    
    return ( 
    <div>
        <CardDeck >
        {
            state.employed ? state.employed.map(dermatologist => {
                return (    
                    <DermatologistDetails key={dermatologist.email} dermatologist = {dermatologist} />
                )
            }) : <p>no dermatologists</p>
        }         
        </CardDeck>
        
    </div> 
    );
}
 
export default DermatologistList;