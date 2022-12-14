import React from "react";
import s from "./ChartBlock.module.css";
import { useEffect, useState, useMemo } from "react";
import Chart from 'react-apexcharts';


export default function ChartBlock(props) {


    const [isActive, setActive] = React.useState(false);

    const [series, setSeries] = useState([{
        data: []
    }])
    const [price, setPrice] = useState(0)
    const [prevPrice, setPrevPrice] = useState(0)
    const [priceTime, setPriceTime] = useState(null)

    const stocksUrl = `http://localhost:8080/csv/${props.url}`;


 
    const chart = {
        options: {
            chart: {
                type: 'candlestick',
                height: 350
            },
            title: {
                text: 'Chart',
                align: 'left'
            },
            xaxis: {
                type: 'datetime'
            },
            yaxis: {
                tooltip: {
                    enabled: true
                }
            }
        },
    }

    const round = (number) => {
        return number ? +number.toFixed(2) : null;
    }


    async function getStocks() {
        const response = await fetch(stocksUrl);
        return response.json();

    }



    //gme - Game Stop Crop
    useEffect(() => {

        let timeoutId;

        async function getLatestPrice() {

            try {


                const data = await getStocks();
                setPrevPrice(price);
                setPrice(data.regularMarketPrice.toFixed(2));
                setPriceTime(new Date(data.regularMarketTime * 1000));
                const prices = data.timestamp.map((timestamp, index) => {

                    return {
                        x: new Date(timestamp * 1000),
                        // O, H, L, C
                        y: [data.open[index], data.high[index], data.low[index], data.close[index]].map(round)
                    }
                })
                setSeries([{
                    data: prices,
                }])

            } catch (error) {
                console.log(error);
            }

            timeoutId = setTimeout(getLatestPrice, 10000);
        }
        getLatestPrice();

        return () => {
            clearTimeout(timeoutId);
        }
    }, [])

    const direction = useMemo(() => prevPrice < price ? "./img/upArrow.png" : prevPrice > price ? "./img/downArrow.png" : ' ', [prevPrice, price]);

    function Active() {
        setActive(isActive => !isActive)
    }

    return (


        <div className={s.root}>
            {}
            <p className={s.text}>{props.title}</p>
            <div className={s.buttons}>
                <div className={s.buttonsBuySell}>
                    <button className={s.button}>BUY 0.09974</button>
                    <button className={s.button}>SELL 0.90074</button>
                </div>
                <div>
                    <button onClick={Active} className={s.button} >hide chart</button>
                </div>
            </div>
            <div className={`${s.chart} ${isActive ? `${s.hide}` : `${s.show}`}`}>
                <div className={s.chart}>
                    <p>  ${price}
                        <img src={direction} className={s.arrow} alt="stock arrow" /> </p>
                    <p>  {priceTime && priceTime.toLocaleTimeString()} </p>

                    <Chart options={chart.options} series={series} type="candlestick" width={500} height={320} />
                </div>
            </div>

        </div>
    )
}