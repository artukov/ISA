import React, {useState, useEffect} from 'react';
import { CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
// import { usePharmaDerma } from '../../hooks/loadPharmacyDermatologists';
import { urlDeleteDermaPharmacy, urlGetPhramacyDermatologists } from '../../services/UrlService';
import DermatologistDetails from '../dermatologist/DermatologistDetails';


const DermatologistList = ({pharmacyID}) => {
    
    const [dermatologists, setDermatologists] = useState([]);

    // const fetchDermatologists = usePharmaDerma(pharmacyID);

    useEffect(() => {
        // console.log('dermatologists list', pharmacyID);
        const loadDermatologists = async (id) =>{
            try{
                const resault = await axiosConfig.get(urlGetPhramacyDermatologists + id);
                // console.log('dermatologists \n', resault.data);
                setDermatologists(resault.data);
            }
            catch(err){
                console.log(err.response);
            }
        }

        // setDermatologists(fetchDermatologists);

        if(pharmacyID !== undefined){
            loadDermatologists(pharmacyID);
        }

    }, [pharmacyID]);

    const deleteDermatologist = async (id) =>{
        try{
            const resault = await axiosConfig.delete(urlDeleteDermaPharmacy + id + "/" + pharmacyID);
            console.log(resault);

            
            setDermatologists(dermatologists.filter(dermatologist => dermatologist.id !== id));
        }
        catch(err){
            alert(err.response.data);
        }        
    }
    
    return ( 
    <div>
        <CardDeck >
        {
            dermatologists.length ? dermatologists.map(dermatologist => {
                return (    
                    <DermatologistDetails key={dermatologist.email} dermatologist = {dermatologist} 
                    deleteDermatologistFromPharmacy = {deleteDermatologist}/>
                )
            }) : <p>no dermatologists</p>
        }         
        </CardDeck>
        
    </div> 
    );
}
 
export default DermatologistList;