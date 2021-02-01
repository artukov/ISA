
import React, { useState, useEffect } from 'react';
import { Tabs, Tab } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlCurrentUser } from '../../services/UrlService';
import IncomingOrdersContextProvider from './IncomingOrdersContext';
import IncomingOrdersList from './IncomingOrdersList';

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
        <Tabs defaultActiveKey="incomming" onSelect={() => {}}>
            <Tab eventKey="incomming" title="Incomming orders">
                <IncomingOrdersContextProvider supplierID = {supplier.id}>
                    <IncomingOrdersList></IncomingOrdersList>
                </IncomingOrdersContextProvider>
            </Tab>
            <Tab eventKey="all" title="All orders"></Tab>
            <Tab eventKey="personalInfo" title="Personal info"></Tab>
        </Tabs>
    </div> );
}
 
export default SupplierPage;