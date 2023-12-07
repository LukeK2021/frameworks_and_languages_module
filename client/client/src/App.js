import './App.css';
import {useState, useEffect} from 'react';


function App() {
//initialising an effect hook to enable this client instance to access the data on the flask server https://react.dev/reference/react/hooks#effect-hooks
  const [itemsData, setitemsData] = useState([]) //managing inputs and requests with state -> https://react.dev/learn/managing-state
  const [formData, setFormData] = useState({ 
    user_id: '',
    keywords: [''],
    description:'',
    lat:'',
    lon:'',
  });

  useEffect(() =>{ 
   getData();
  },[])

  const getData = () => { //Function returns a promise https://www.w3schools.com/js/js_promise.asp
    fetch('http://127.0.0.1:8000/items',{
      'method': 'GET',
      headers:{
        'Content-Type':'application/json'
      }
    })
    .then(resp => resp.json())
    .then(resp => setitemsData(resp))
    .catch(error => console.log(error))
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const submitItem = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://127.0.0.1:8000/item', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });
      getData();

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      // Handle the response from the server
      console.log(data);
    } catch (error) {
      // Handle any errors
      console.error('Error:', error);
    }
  };
  return (
    <div className="App">
      <h1>FreeCycle</h1>
      <form onSubmit={submitItem}>
        <label>
           User id:
           <input
           type="text"
           name="user_id"
           id='user_id'
           value={formData.user_id}
           onChange={handleInputChange}
          />
        </label>
        <br />
        <label>
           Keywords:
            <input
            type="text"
            name="keywords"
            id='keywords'
            value={formData.keywords.join(',')}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <label>
         Description:
          <input
            type="text"
            name="description"
            id='description'
            value={formData.description}
            onChange={handleInputChange}
         />
        </label>
        <br />
        <label>
         Lat:
         <input
           type="text"
           name="lat"
           id='lat'
           value={formData.lat}
           onChange={handleInputChange}
         />
        </label>
        <br />
        <label>
         Lon:
         <input
           type="text"
           name="lon"
           id='lon'
           value={formData.lon}
           onChange={handleInputChange}
         />
        </label>
        <br />
        <button type="submit">Submit</button>
      </form>
        <div>
         <h2>Items</h2>
         <ul>
         {itemsData.map((item, id)=>(
         <li key={id}>User id: {item.user_id} Keywords: {item.keywords} Description: {item.description} Lat: {item.lat} Lon: {item.lon} Date: {item.date_from}</li>//data from resp presented in readable format, the id field represents the LI dom key -> https://legacy.reactjs.org/docs/lists-and-keys.html
        ))}
         </ul>
        </div>
      </div>
  );
}

export default App;
