import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router-dom";


let rerenderEntireTree = (props) => {
    const root = ReactDOM.createRoot(document.getElementById('root'));
    root.render(
        <React.StrictMode>
            <BrowserRouter> <App stocksData={props}/></BrowserRouter>
        </React.StrictMode>
    );

    reportWebVitals();


}

export default rerenderEntireTree;