import React, { useState, useEffect } from 'react';

const PharmacistDetails = ({pharmacist}) => {
    return ( 
    <div>
        <h6>PharmacistDetails component</h6>
        <p>{pharmacist.email}</p>
        <p>{pharmacist.firstname}</p>
        <p>{pharmacist.lastname}</p>
        <p>{pharmacist.phoneNumber}</p>
        <p>{pharmacist.role}</p>
        <p>{pharmacist.hours}</p>
        <p>{pharmacist.start_hour}</p>
    </div> );
}
 
export default PharmacistDetails;