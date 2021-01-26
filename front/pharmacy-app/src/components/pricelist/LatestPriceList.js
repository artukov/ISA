import React, { useState, useEffect, useContext } from 'react';
import { PriceListContext } from './PriceListContext';
import PriceListDetails from './PriceListDetails';

const LatestPriceList = () => {

    const {latestPL} = useContext(PriceListContext);

    return (  
        <div>
            <PriceListDetails priceList ={latestPL}></PriceListDetails>
        </div>
    );
}
 
export default LatestPriceList;