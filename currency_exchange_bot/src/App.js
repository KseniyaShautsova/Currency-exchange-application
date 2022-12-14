import './App.css';
import Layout from './components/Layout/Layout.component';



function App(props) {

  return (
    <div >
      <Layout stocksData={props.stocksData}/>
    </div>
  );
}

export default App;
