import './App.css';
import {useState, useEffect} from 'react';


function App() {
//initialising an effect hook to enable this client instance to access the data on the flask server https://react.dev/reference/react/hooks#effect-hooks
  const [data, setData] = useState([])

  useEffect(() =>{ //Function returns a promise https://www.w3schools.com/js/js_promise.asp
    fetch('http://localhost:8000/items',{
      'method': 'GET',
      headers:{
        'Content-Type':'application/json'
      }
    })
    .then(resp => resp.json)
    .then(resp => console.log(resp))
    .catch(error => console.log(error))
  },[])

  return (
    <div className="App">
      <h1>Flask react test</h1>
    </div>
  );
}

export default App;
