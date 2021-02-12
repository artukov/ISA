
import React, { useState, useEffect } from 'react';
import { Tabs, Tab } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlCurrentUser } from '../../services/UrlService';
import AllOrdersContextProvider from './all-orders/AllOrdersContext';
import AllOrdersList from './all-orders/AllOrdersList';
import AllOrdersMenu from './all-orders/AllOrdersMenu';
import IncomingOrdersContextProvider from './incoming-orders/IncomingOrdersContext';
import IncomingOrdersList from './incoming-orders/IncomingOrdersList';
import SupplierInfo from './profile/SupplierInfo';
import WareHouseList from './warehouse/WareHouseList';

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
                    <AllOrdersMenu></AllOrdersMenu>
                    <AllOrdersList></AllOrdersList>
                </AllOrdersContextProvider>
            </Tab>
            <Tab eventKey="personalInfo" title="Personal info">
                <SupplierInfo></SupplierInfo>
            </Tab>
            <Tab eventKey="warehouse" title="Warehouse">
                <WareHouseList supplierID= {supplier.id}></WareHouseList>
            </Tab>
        </Tabs>
    </div> );
}
 
export default SupplierPage;