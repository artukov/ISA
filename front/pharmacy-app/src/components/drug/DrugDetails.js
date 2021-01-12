import React, { useState, useEffect } from 'react'

const DrugDetails = ({drug}) => {
    return ( 
        <div>
            <h6>DrugDetails component</h6>
            <p>{drug.code}</p>
            <p>{drug.name}</p>
            <p>{drug.manufacturer}</p>
            <p>{drug.receipt}</p>
            <p>{drug.shape}</p>
            <p>{drug.type}</p>
            <p>amount : {drug.amount}</p>
        </div>
     );
}
 
export default DrugDetails;