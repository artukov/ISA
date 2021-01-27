import React, { useState, useEffect } from 'react';
import AppointmentReport from './AppointmentReportComponent';
import ConsumptionReport from './ConsumptionReportsComponent';
import FinacesReport from './FinacesReportComponent';
// import {Chart, Axis, Tooltip, Interval, Point} from "bizcharts";
// import {Line} from 'react-chartjs-2'
// import { Card } from 'react-bootstrap';

const ReportDetails = () => {

    return ( 
        <div>
            <AppointmentReport></AppointmentReport>
            <ConsumptionReport></ConsumptionReport>
            <FinacesReport></FinacesReport>
            
        </div>
     );
}
 
export default ReportDetails;