import React, { useState, useEffect } from 'react';
import AppointmentReport from './AppointmentReportComponent';
import ConsumptionReport from './ConsumptionReportsComponent';
import FinacesReport from './FinacesReportComponent';
// import {Chart, Axis, Tooltip, Interval, Point} from "bizcharts";
// import {Line} from 'react-chartjs-2'
// import { Card } from 'react-bootstrap';

const ReportDetails = () => {

    const years = [2020,2021];
    return ( 
        <div>
            <AppointmentReport></AppointmentReport>
            <ConsumptionReport></ConsumptionReport>
            <FinacesReport></FinacesReport>
            {/* <Card>
                <Card.Body>
                    <Line

                    data =  {
                        {
                            // labels: ['January', 'February', 'March', 'April', 'May', 'June'],
                            datasets : [                                
                                {
                                    label : years[0],
                                    data : [ 1,2,3,4,6,5,7,8,10,11,2],
                                    fill : false,
                                    borderColor : 'red',
                                    // borderWidth : 0.2
                                },
                                {
                                    label : years[1],
                                    data : [ 1,20,3,4.5,6,5,18,8,9,10,11,2],
                                    fill : false,
                                    borderColor : 'blue',
                                }
                            ],
                        }
                    }
                    options = {{
                        scales: {
                            yAxes: [{
                                stacked: false
                            }],
                            xAxes: [{
                                type: 'category',
                                labels: ['January', 'February', 'March', 'April', 'May', 'June']
                            }]
                        }, 
                        title : {
                            text : 'Title',
                            fontSize: 24,
                            display : true
                        }
                       
                        
                    }}
                   
                    >

                    </Line>
                </Card.Body>
            </Card> */}
        </div>
     );
}
 
export default ReportDetails;