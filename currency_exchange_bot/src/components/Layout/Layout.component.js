import React from "react";
import s from "./Layout.module.css";
import ChartBlock from "../ChartBlock/ChartBlock.component";
import {Route, Routes} from "react-router-dom";
import {Link} from "react-router-dom";
import { rerender } from "../../data";

export default function Layout(props) {

    
    return ( 
        <div className={s.root}>
            <div className={s.header}>
                <p className={s.text}>CHOOSE THE COURSE</p>
                <div className={s.buttons}>
                    <Link to= {`/${props.stocksData.sony.title}` }>
                        <button className={s.button} onClick={rerender}>{props.stocksData.sony.title}</button>
                    </Link>
                    <Link to= {`/${props.stocksData.wa.title}` }>
                        <button className={s.button} onClick={rerender}>{props.stocksData.wa.title}</button>
                    </Link>
                    <Link to= "/SILVER">
                        <button className={s.button}>SILVER</button>
                    </Link>
                    <Link to= "/OIL">
                        <button className={s.button}>OIL</button>
                    </Link>
                </div>
            </div>
            <div className={s.body}>
                <Routes>
                    <Route path={`/${props.stocksData.sony.title}` }
                    element={<ChartBlock title={props.stocksData.sony.title} url={props.stocksData.sony.url}/>}                      
                    />
                    <Route path={`/${props.stocksData.wa.title}` } 
                    element={<ChartBlock title={props.stocksData.wa.title} url={props.stocksData.wa.url}/>}                      
                    />
                    <Route path="/SILVER" 
                    element={<ChartBlock title="SILVER" />}                      
                    />
                    <Route path="/OIL" 
                    element={<ChartBlock title="OIL" />}                      
                    />
                </Routes>
            </div>
        </div>
    )
}