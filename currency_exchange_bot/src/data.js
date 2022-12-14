import rerenderEntireTree from "./render";

const stocks = {
    sony: {
        url: "sony",
        title: "SONY",
    },
    wa: {
        url: "gpw.wa",
        title: "WA",
    }
}


export const rerender = ()=>{
    rerenderEntireTree(stocks)
}


export default stocks;