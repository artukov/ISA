import React, { useState, useEffect } from 'react';

const DermatologistDetails = ({dermatologist}) => {
    return (  
        <div>
            <h6>Dermatologists details component</h6>
            <p>{dermatologist.email}</p>
            <p>{dermatologist.firstname}</p>
            <p>{dermatologist.lastname}</p>
            <p>{dermatologist.phoneNumber}</p>
            <p>{dermatologist.role}</p>
            <p>{dermatologist.hours}</p>
            <p>{dermatologist.start_hour}</p>
        </div>
    );
}
 
export default DermatologistDetails;