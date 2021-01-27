import React, { useState, useEffect, useContext } from 'react';
import { PharmacyReportsContext } from './PharmacyReportsContext';
import {Line, Bar} from 'react-chartjs-2'

const months = ['January', 'February', 'March', 'April', 'May', 'June', 
'July','August','September','October','November','December' ];
const quarter = ['FIRST', 'SECOND', 'THIRD'];

const ConsumptionReport = () => {

    const {state} = useContext(PharmacyReportsContext);
    
    const [showGraph, setShowGraph] = useState(true);
    const showLineGraph = () => setShowGraph(true);
    const showBarGraph = () => setShowGraph(false);

    const [chartData, setChartData] = useState({});
    const [labels, setLabels] = useState(months);

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

    const colors = ['blue', 'red', 'black','purple','green']; 

    const calculateTimeSpam = () =>{
        if(state.drugs.timespam === "MONTHLY"){
            setLabels(months);
            return 12;
        }
            
        if(state.drugs.timespam === "QUARTERLY"){
            setLabels(quarter);
            return 3;
        }

        setLabels(state.years);
        return 1;
    }

    const getStatsFromState = (index) =>{
        
        let data = [];
        let timespam = calculateTimeSpam();
        const lowerBound = timespam*(index);
        const upperBound = (timespam*(index + 1));
        for(let i = 0; i < state.drugs.stats.length; i++ ){
            if( ((i >= lowerBound )  && ( i < upperBound)) || (index === upperBound)  ){
                data.push(state.drugs.stats[i]);
                continue;
            }
            if( i < lowerBound)
                continue;
            if( i >= upperBound)
                break;
        }

        return data;
    }

    const formataDatasetForGraph = () =>{

        let datasets = [];

        for(let i = 0; i < state.years.length ; i++ ){
            let temp = {
                fill : false,
                label : state.years[i],
                borderColor : colors[i],
                data :  getStatsFromState(i)
            };
            datasets.push(temp);
        }

        return datasets;
    }

    const lineChart = () =>{
        setChartData({
            datasets : formataDatasetForGraph()
        });
        // console.log(chartData);
    }

    const barChart = () =>{
        let datasets = [];

        for(let i = 0; i < state.years.length ; i++ ){
            let temp = {
                // fill : false,
                label : state.years[i],
                backgroundColor : colors[i],
                data :  state.drugs.stats.map((stat, index) =>{
                    if(index === i )
                        return stat;
                    return 0;
                })
            };
            datasets.push(temp);
        }
        
        setChartData({
            datasets : datasets
        });
    }

    useEffect(() => {
        
        if(calculateTimeSpam() !== 1){
            lineChart();
            showLineGraph();
        }
        else{
            barChart();
            showBarGraph();
        }

    }, [state.drugs.stats])
    
    return ( 
        <div>
            {
                showGraph ? (
                    <Line
                        data = {chartData}
                        options = {options}
                    >
                    </Line>
                ) : (
                    <Bar
                        data = {chartData}
                        options = {options}
                    >
                    </Bar>
                )
            }
        </div>
     );
}
 
export default ConsumptionReport;