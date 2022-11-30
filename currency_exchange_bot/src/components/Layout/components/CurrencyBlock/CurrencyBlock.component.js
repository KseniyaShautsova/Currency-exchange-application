import React from "react";
import s from "./CurrencyBlock.module.css";
import ChartElement from "./components/ChartElement/ChartElement.component";


export default function CurrencyBlock(props) {


    const [isActive, setActive] = React.useState(false);
 
    function Active(){
       setActive(isActive=>!isActive)
    }

    return (


        <div className={s.root}>
            <p>{props.title}</p>
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
                <ChartElement />
            </div>

        </div>
    )
}