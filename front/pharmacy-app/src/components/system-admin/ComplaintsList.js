import React, { useState, useEffect } from 'react';
import { CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import ComplaintDetails from './ComplaintDetails';

const ComplaintsList = () => {
    const [complaints, setComplaints] = useState([]);

    useEffect(() => {
        const loadUnansweredComplaints = async () => {
            try{
                const result = await axiosConfig.get('/complaint/findUnanswered');
                setComplaints(result.data);
            }
            catch(err){
                console.log(err.response);
            }
        }
        loadUnansweredComplaints();
    }, []);

    const saveComplaint = async (complaint) => {
        // console.table(complaint);
        try{
            setComplaints(complaints.filter(iter => iter.id !== complaint.id));
            await axiosConfig.put('/complaint/answer',complaint);            
        }
        catch(err){
            console.log(err.response);
            /**
             * TODO
             * if sending an mail or something cause an error
             * reset the page
             * */
        }
    }

    return (  
        <div>
            <CardDeck>
            {
                complaints.length ? (
                    complaints.map(complaint =>
                            <ComplaintDetails key={complaint.id} complaint={complaint} saveComplaint={saveComplaint}></ComplaintDetails>
                        )
                ) : null
            }
            </CardDeck>
        </div>
    );
}
 
export default ComplaintsList;