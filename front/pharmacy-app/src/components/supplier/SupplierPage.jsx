
import React, { useState, useEffect } from 'react';
import { Tabs, Tab } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlCurrentUser } from '../../services/UrlService';
import AllOrdersContextProvider from './all-orders/AllOrdersContext';
import AllOrdersList from './all-orders/AllOrdersList';
import IncomingOrdersContextProvider from './incoming-orders/IncomingOrdersContext';
import IncomingOrdersList from './incoming-orders/IncomingOrdersList';

const SupplierPage = () => {

    const [supplier, setSupplier] = useState({});

    useEffect(() => {
        const loadCurrentUser = async () =>{
            try{
                const result = await axiosConfig.get(urlCurrentUser);
                // console.log(result.data);
                setSupplier(result.data);
            }
            catch(err){
                console.log(err.response);
            }
        }

        loadCurrentUser();
       
    }, [])

    return ( 
    <div>
        <Tabs defaultActiveKey="all" onSelect={() => {}}>
            <Tab eventKey="incomming" title="Incomming orders">
                <IncomingOrdersContextProvider supplierID = {supplier.id}>
                    <IncomingOrdersList></IncomingOrdersList>
                </IncomingOrdersContextProvider>
            </Tab>
            <Tab eventKey="all" title="All orders">
                <AllOrdersContextProvider supplierID = {supplier.id}>
                    <AllOrdersList></AllOrdersList>
                </AllOrdersContextProvider>
            </Tab>
            <Tab eventKey="personalInfo" title="Personal info"></Tab>
        </Tabs>
    </div> );
}
 
export default SupplierPage;