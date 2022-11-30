import React from "react";
import s from "./Layout.module.css";
import CurrencyBlock from './components/CurrencyBlock/CurrencyBlock.component';
import {Route, Routes} from "react-router-dom";
import {Link} from "react-router-dom";

export default function Layout() {

    
    return (
        <div className={s.root}>
            <div className={s.header}>
                <p >CHOOSE THE COURSE</p>
                <div className={s.buttons}>
                    <Link to= "/EURUSD">
                        <button className={s.button}>EURUSD</button>
                    </Link>
                    <Link to= "/GOLD">
                        <button className={s.button}>GOLD</button>
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
                    <Route path="/EURUSD" 
                    element={<CurrencyBlock title="EURUSD"/>}                      
                    />
                    <Route path="/GOLD" 
                    element={<CurrencyBlock title="GOLD" />}                      
                    />
                    <Route path="/SILVER" 
                    element={<CurrencyBlock title="SILVER" />}                      
                    />
                    <Route path="/OIL" 
                    element={<CurrencyBlock title="OIL" />}                      
                    />
                </Routes>
            </div>
        </div>
    )
}