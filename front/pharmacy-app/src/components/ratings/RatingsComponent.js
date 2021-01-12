import React, { useEffect } from 'react';

const RatingsComponent = ({ratings}) => {

    useEffect(() =>{},[ratings]);

    if(ratings === null || ratings === undefined){
        return 'null';
    }
    else {
        let avgRating = 0.0;
        ratings.map(rating =>{
            avgRating += rating;
            return rating;
        });

        avgRating /= ratings.length;

        return ( 
            <div>
                <h4>RatingsComponent</h4>
                { avgRating }
            </div>
         );
    }
}
 
export default RatingsComponent;