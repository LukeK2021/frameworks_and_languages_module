import './App.css';
import {useState, useEffect} from 'react';
import {Col ,Button, Row} from 'react-bootstrap';

function App() {
//initialising an effect hook to enable this client instance to access the data on the flask server https://react.dev/reference/react/hooks#effect-hooks
  const [itemsData, setitemsData] = useState([]) //managing inputs and requests with state -> https://react.dev/learn/managing-state
  const [formData, setFormData] = useState({ // State for handling the data that will be posted to server, note json fields are present to prevent 405.
    user_id: 0,
    keywords: [''],
    description:'',
    lat:0.1,
    lon:0.1,
  });
  const urlParams = new URLSearchParams(window.location.search);
	const ApiUrl = (urlParams.get('api') || '').replace(/\/$/, '');  // Get api url (and remove trailing slash if present) -> Allan Calahan -> https://github.com/calaldees/frameworks_and_languages_module/blob/main/docs/xx%20assignment_hints.md

  if (!ApiUrl)
  {
    alert("No query ?api= string present this will not work");
  }
  useEffect(() =>{ 
   getData(); //lets me syncronise with another system, in this case the getData method will populate the client with data from server.
  },[])

  const getData = () => { //Function returns a promise https://www.w3schools.com/js/js_promise.asp
    fetch(`${ApiUrl}/items`,{ //template literals substitution of placeholder strings https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Template_literals
      'method': 'GET',
      headers:{
        'Content-Type':'application/json' //ensuring content json is present for headers.
      }
    })
    .then(resp => resp.json())
    .then(resp => setitemsData(resp))
    .catch(error => console.log(error))
  };

  const handleInputChange = (e) => { //when this function is called it will 
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const submitItem = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`${ApiUrl}/item`, {
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
  const delItem = (id)=>{
    if(window.confirm('Are you sure'))
    {
      fetch(`${ApiUrl}/item/`+id,{method:'DELETE'})
      .then (()=> getData())
    }

  }
  return (
    <div className="App-header"> 
      <h1>FreeCycle</h1>
      <form onSubmit={submitItem}>
        <label className='Label-header'>
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
        <label className='Label-header'>
           Keywords:
            <input
            type="text"
            name="keywords"
            id='keywords'
            value={formData.keywords}
            onChange={handleInputChange}
          />
        </label>
        <br />
        <label className='Label-header'>
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
        <label className='Label-header'>
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
        <label className='Label-header'>
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
      <ul className="list-group">
        {itemsData.map((item, id) => (
          <li key={item.id} className="items">
            <Row> 
              <Col>User id: {item.user_id}</Col>
              <Col>Description: {item.description}</Col>
              <Col>Keywords: {item.keywords}</Col>
              <Col>Lat: {item.lat}</Col>
              <Col>Lon: {item.lon}</Col>
              <Col>Date: {item.date_from}</Col>
              <Col>
                <Button
                  variant="danger"
                  type="button"
                  onClick={() => delItem(item.id)}
                >
                  Delete
                </Button>
              </Col>
            </Row>
          </li>
        ))}
      </ul>
      </div>
  );
}

export default App;
