
import React, { useState, useEffect, useReducer} from 'react';
import { Button, CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
// import userProfile from '../../reducer/userProfileReducer';
// import { usePharmaPharmacists } from '../../hooks/loadPharmacyPharmacists';
import { urlDeletePharmacist, urlGetPharmacyPharmacists, urlNewPharmacist } from '../../services/UrlService';
import PharmacistDetails from '../pharmacist/PharmacistsDetails';
import PharmacistSearchFilter from '../pharmacist/PharmacistSearchFilter';
import AddPharmacist from '../pharmacyAdmin/AddPharmacist';
import pharmacistListReducer, {SET_PHARMACISTS, ADD_PHARMACIST} from '../pharmacy/pharmacistListReducer';

// const initialState = {};

const PharmacistList = ({pharmacyID}) => {
    
    const [state, dispatch] = useReducer(pharmacistListReducer, {
        pharmacists : []
    });

    const [pharmacists, setPharmacists] = useState([]);
    const [showAddForm, setShowAddForm] = useState(false);

    const [reload, setReload] = useState(false);

    const openAddForm = () => setShowAddForm(true);
    const closeAddForm = () => setShowAddForm(false);

    const [sortHoursOrder, setSortHoursOrder] = useState(false);
    const [sortRatingOrder, setSortRatingOrder] = useState(false);

    useEffect(() => {
        const loadPharmacist = async (id) =>{
            try{
                const result = await axiosConfig.get(urlGetPharmacyPharmacists + pharmacyID);
                dispatch({type : SET_PHARMACISTS, pharmacists : result.data});
                setPharmacists(result.data);
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
        pharmacist.address = {};

        try{
            await axiosConfig.post(urlNewPharmacist, pharmacist);
           
            // setReload(!reload);//because of the key in the list, the list needs to be reloaded not only reset
            dispatch({type : ADD_PHARMACIST, pharmacist});
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

    const searchPharmacist = (firstname, lastname) =>{
        dispatch({type : SET_PHARMACISTS, pharmacists : pharmacists});
        if(firstname === '' && lastname === ''){
            dispatch({type : SET_PHARMACISTS, pharmacists : pharmacists});
            return;
        }
        if(firstname === '' || lastname === ''){
            alert('Both firstname and last name must be inserted');
            return;
        } 

        const searchResult = state.pharmacists.filter(pharmacist => 
                pharmacist.firstname.toUpperCase() === firstname.toUpperCase() 
                && pharmacist.lastname.toUpperCase() === lastname.toUpperCase()
            );
        dispatch({type : SET_PHARMACISTS, pharmacists : searchResult});

    }
    const sortByRatings = () => {
        let sortedByRatings = null;
        if(sortRatingOrder)
             sortedByRatings = state.pharmacists.sort((a,b) => {
                 let aAvg = 0;
                 if(a.ratings.length > 0)
                        aAvg = a.ratings.reduce((r1,r2) => r1 + r2) / a.ratings.length;
                 let bAvg = 0
                 if(b.ratings.length > 0)
                     bAvg = b.ratings.reduce((r1, r2) => r1 + r2) / b.ratings.length;
                 if(aAvg > bAvg)
                    return 1;
                return -1;
             });
        else
            sortedByRatings = state.pharmacists.sort((a, b) => {
                let aAvg = 0;
                 if(a.ratings.length > 0)
                        aAvg = a.ratings.reduce((r1,r2) => r1 + r2) / a.ratings.length;
                 let bAvg = 0
                 if(b.ratings.length > 0)
                     bAvg = b.ratings.reduce((r1, r2) => r1 + r2) / b.ratings.length;
                 if(bAvg > aAvg)
                    return 1;
                return -1;
            });
        dispatch({type : SET_PHARMACISTS, pharmacists : sortedByRatings});
        setSortRatingOrder(!sortRatingOrder);

    }
    const sortByHours = () => {
        let sortedByHours = null;
        if(sortHoursOrder)
            sortedByHours = state.pharmacists.sort((a, b) => a.hours - b.hours);
        else
            sortedByHours = state.pharmacists.sort((a, b) => b.hours - a.hours);
        dispatch({type : SET_PHARMACISTS, pharmacists : sortedByHours});
        setSortHoursOrder(!sortHoursOrder);
    }

    return (  
        <div>
            <Button variant = "dark" onClick ={()=>openAddForm()}>Add new pharmacist</Button>
            {
                showAddForm ? (
                    <AddPharmacist addPharmacist = {addPharmacist} ></AddPharmacist>
                ) : null
            }
            <PharmacistSearchFilter searchPharmacist={searchPharmacist}
                                    sortByRatings={sortByRatings}
                                    sortByHours={sortByHours}>
            </PharmacistSearchFilter>
            <CardDeck>
            { state.pharmacists.length ? state.pharmacists.map((pharmacist,index) => 
                                    <PharmacistDetails pharmacist = {pharmacist} key={index}
                                        deletePharmacist = {deletePharmacist}
                                    ></PharmacistDetails>) 
            : <p>no pharmacists</p>
            }
            </CardDeck>
            
        </div>
    );
}
 
export default PharmacistList;