
import React, { useState, useEffect, useContext } from 'react';
import { Bar} from 'react-chartjs-2'
import { PharmacyReportsContext } from './PharmacyReportsContext';
const FinacesReport = () => {

    const {finances, limits} = useContext(PharmacyReportsContext);

    const [chartData, setChartData] = useState({});
    const [labels, setLabels] = useState([]);

    const options = {
        scales: {
            yAxes: [{
                stacked: false
            }],
            xAxes: [{
                type: 'category',
                labels: labels 
            }]
        }, 
        title : {
            text : 'Graph of results',
            fontSize: 12,
            display : true
        }

    }

    const barChart = (lowerDate,upperDate) => {
     
        let datasets = [
            {
                label : lowerDate,
                backgroundColor : 'red',
                data : [finances[0],0]
            },
            {
                label : upperDate,
                backgroundColor : 'black',
                data : [0,finances[1]]

            }
        ];

        setChartData({
            datasets : datasets
        })
    }

    useEffect(() => {
        const [lowerDate,,] = limits.lowerLimit.split(" ");
        const [upperDate,,] = limits.upperLimit.split(" ");
        setLabels([lowerDate, upperDate]);
        barChart(lowerDate,upperDate);
       
    }, [finances, limits])

    return ( 
    <div>
        <Bar
            data = {chartData}
            options = {options}
        >
        </Bar>
        
    </div> );
}
 
export default FinacesReport;