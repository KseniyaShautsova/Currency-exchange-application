import React from "react";
import { useEffect, useState, useMemo } from "react";
import Chart from 'react-apexcharts';
import s from "./ChartElement.module.css"

export default function ChartElement() {

    const [series, setSeries] = useState([{
        data: []
    }])
    const [price, setPrice] = useState(0)
    const [prevPrice, setPrevPrice] = useState(0)
    const [priceTime, setPriceTime] = useState(null)

    const proxyUrl = "https://cors-anywhere.herokuapp.com/"
    const stocksUrl = `${proxyUrl}https://query1.finance.yahoo.com/v8/finance/chart/GME`;

    const chart = {
        options: {
            chart: {
                type: 'candlestick',
                height: 350
            },
            title: {
                text: 'CandleStick Chart',
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
                const gme = data.chart.result[0];
                setPrevPrice(price);
                setPrice(gme.meta.regularMarketPrice.toFixed(2));
                setPriceTime(new Date(gme.meta.regularMarketTime * 1000));
                const quote = gme.indicators.quote[0]
                const prices = gme.timestamp.map((timestamp, index) => {

                    return {
                        x: new Date(timestamp * 1000),
                        // O, H, L, C
                        y: [quote.open[index], quote.high[index], quote.low[index], quote.close[index]].map(round)
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

    return (
        <div className={s.chart}>
            <p>  ${price}
                <img src={direction} className={s.img} alt="stock arrow" /> </p>
            <p>  {priceTime && priceTime.toLocaleTimeString()} </p>

            <Chart options={chart.options} series={series} type="candlestick" width={500} height={320} />
        </div>
    )
}