
import React, { useState, useEffect} from 'react';
import { Button, CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
// import userProfile from '../../reducer/userProfileReducer';
// import { usePharmaPharmacists } from '../../hooks/loadPharmacyPharmacists';
import { urlDeletePharmacist, urlGetPharmacyPharmacists, urlNewPharmacist } from '../../services/UrlService';
import PharmacistDetails from '../pharmacist/PharmacistsDetails';
import AddPharmacist from '../pharmacyAdmin/AddPharmacist';

// const initialState = {};

const PharmacistList = ({pharmacyID}) => {
    
    const [pharmacists, setPharmacists] = useState([]);
    const [showAddForm, setShowAddForm] = useState(false);

    const [reload, setReload] = useState(false);

    const openAddForm = () => setShowAddForm(true);
    const closeAddForm = () => setShowAddForm(false);

    // const [state, dispatch] = useReducer(userProfile, initialState);



    // const fetchPharmacists = usePharmaPharmacists(pharmacyID)

    useEffect(() => {
        const loadPharmacist = async (id) =>{
            try{
                const resault = await axiosConfig.get(urlGetPharmacyPharmacists + pharmacyID);
                setPharmacists( resault.data);
            }
            catch(err){
                console.log(err.response);
            }
        }
        if(pharmacyID !== undefined){
            loadPharmacist(pharmacyID);
        }

    }, [pharmacyID, reload]);

    const addPharmacist = async (pharmacist) =>{
        //console.log(pharmacist);
        if(pharmacist === null){
            closeAddForm();
            return;
        }
        pharmacist.ratings = [];
  
        pharmacist.pharmacyID = pharmacyID;
        pharmacist.address_id = pharmacist.address;

        try{
            await axiosConfig.post(urlNewPharmacist, pharmacist);
           
            setReload(!reload);//because of the key in the list, the list needs to be reloaded not only reset
        }
        catch(err){
            console.log(err.response);
        }
        closeAddForm();
    };

    const deletePharmacist = async (id) =>{
        console.log(id);
        try{
            await axiosConfig.delete(urlDeletePharmacist + id);
            setPharmacists(
                pharmacists.filter(temp => temp.id !== id)
            );
        }
        catch(err){
            if(err.response !== undefined){
                alert(err.response.data);
            }
            // console.log(err.response);
        }
        closeAddForm();
    };

    return (  
        <div>
            <Button variant = "dark" onClick ={()=>openAddForm()}>Add new pharmacist</Button>
            {
                showAddForm ? (
                    <AddPharmacist addPharmacist = {addPharmacist} ></AddPharmacist>
                ) : null
            }
            <CardDeck>
            { pharmacists.length ? pharmacists.map(pharmacist => 
                                    <PharmacistDetails pharmacist = {pharmacist} key={pharmacist.id}
                                        deletePharmacist = {deletePharmacist}
                                    ></PharmacistDetails>) 
            : <p>no pharmacists</p>
            }
            </CardDeck>
            
        </div>
    );
}
 
export default PharmacistList;