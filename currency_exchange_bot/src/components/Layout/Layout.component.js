import React from "react";
import s from "./Layout.module.css";
import ChartBlock from "../ChartBlock/ChartBlock.component";
import {Route, Routes} from "react-router-dom";
import {Link} from "react-router-dom";
import stocks from "../../data";

export default function Layout() {

    
    return (
        <div className={s.root}>
            <div className={s.header}>
                <p >CHOOSE THE COURSE</p>
                <div className={s.buttons}>
                    <Link to= {`/${stocks.sony.title}` }>
                        <button className={s.button}>{stocks.sony.title}</button>
                    </Link>
                    <Link to= {`/${stocks.wa.title}` }>
                        <button className={s.button}>{stocks.wa.title}</button>
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
                    <Route path={`/${stocks.sony.title}` }
                    element={<ChartBlock title={stocks.sony.title} url={stocks.sony.url}/>}                      
                    />
                    <Route path={`/${stocks.wa.title}` } 
                    element={<ChartBlock title={stocks.wa.title} url={stocks.wa.url}/>}                      
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