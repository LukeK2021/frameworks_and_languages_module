Technical Report
================

The intended purpose of this technical report is to review the existing prototype implementations of the "freecycle" product, this critique will be solely of the programming languages and frameworks that exist within this product suite and to provide a potential solution to issues should they arise, furthermore the new implementation of the service will also come under review under the same procedure. This report will include technical language so it is highly recommended

Issues raised will be accompanied by a permalink to take you direct to the offending file & line of code.

Critique of Server/Client prototype
---------------------

### Overview

Current server/client prototype is not fit for purpose, the writer has tried to re-invent the wheel by writing functions that already exist as reference libraries/functions in the language, in addition the client data structure and html rendering are too intertwined.

Furthermore the code is quite tricky to follow and to determine data paths etc. and there is no documentation.

### Server Socket Handling

https://github.com/calaldees/frameworks_and_languages_module/blob/0f55f66639768032a3f0c0d795e6517ca52f0a11/example_server/app/http_server.py#L109C1-L109C1
``` Python
with conn:
                #log.debug(f'Connected by ')
                #while True:
                    data = conn.recv(65535)  # If the request does not come though in a single recv/packet then this server will fail and will not composit multiple TCP packets. Sometimes the head and the body are sent in sequential packets. This happens when the system switches task under load.
                    #if not data: break
                    try:
                        request = parse_request(data)
                    except InvalidHTTPRequest as ex:
                        log.exception("InvalidHTTPRequest")
                        continue
```
Firstly if the business needed to expand to different forms of media this may cause issues as the max packet size as highlighed in the snippet is 65 KiloBytes and it will cause the program to fail, however the engineer has anticipated this and has included a hack that will attempt to piece together the rest of the data should it exceed this packet size. Awful for scalability and data integrity.

### A Lack of Data state


https://github.com/calaldees/frameworks_and_languages_module/blob/0f55f66639768032a3f0c0d795e6517ca52f0a11/example_client/index.html#L402

``` Javascript
function renderItems(data) {
		const $item_list = document.querySelector(`[data-page="items"] ul`);
		const new_item_element = () => document.querySelector(`[data-page="items"] li`).cloneNode(true);

		for (let item_data of data) {
			const $new_item_element = new_item_element();
			$item_list.appendChild($new_item_element);
			renderDataToTemplate(item_data, $new_item_element, renderItemListFieldLookup);
			attachDeleteAction($new_item_element);
		}
	}
	function render_items(params) {
		fetch(`${urlAPI}/items?${new URLSearchParams(params).toString()}`)
			.then(response => response.json())
			.then(renderItems)
		.catch(err => console.error(err));
	}
```


There is no code in the client where the data retrieved from the server is stored (a state), it is parsed and then displayed to the user, as there is no state attempting to make changes to the output of the client will require time consuming re-writing, this is because both the data strcture and data rendering are amalagamated together.

In addition attempting to follow the flow of data through the client program is tricky as the code has been written in a very obtuse manner.

### Recommendation
As were at the prototyping phase we can assume that the scope of the project will increase over time and the current prototypes that are present offer a working solution, however the code is deeply flawed and implementing the current prototypes will incur technical debt and thats before any scaling at all.

The existing codebase needs to be re-written to incude frameworks with the reccomendation being flask for the server side and react for client side as they both contain large feature sets and said feature sets make them both extremely popular frameworks.

Server Framework Features
-------------------------

### Application routing.

Flask contains route decorators to assign URLS within our application and attach specific functions to them enabling some routes to send/recieve data, enables parameters to exist within the urls that can then be used by said functions.

https://github.com/LukeK2021/frameworks_and_languages_module/blob/1399e0a5991dcf39b100cced0cce7da3c5bf941f/server/server.py#L29
```Python
@app.route('/item/<int:Id>',methods=['GET','DELETE'])
def get_id(Id):
    if request.method == 'GET': #accessing the get method https://flask.palletsprojects.com/en/2.3.x/api/#flask.Request.method
        for user in items:
            if user['id'] == Id:
                return jsonify(user), 200
        return jsonify({'error': 'not found'}), 404
    if request.method == 'DELETE': #accessing the delete method
        for user in items:
            if user['id']==Id:
                items.remove(user)
                return jsonify(user), 204
        return jsonify({'error': 'not found'}), 404
```
By compartmentalizing routes and functions we can ensure that actions can only be performed as they are requested and increase the modularity. For example if the code snippet above were to be deleted from the program, the server would still function and the data would still be there.
By including path parameters as part of the routing we can take those parameters and use them in our functions, like the snippet above shows for getting and deleting a specific peice of data, by the ID.

https://flask.palletsprojects.com/en/2.3.x/api/#flask.Flask.route




### Flask Debug mode

The flask framework provides a debugging suite that outputs a traceback to your browser if it encounters an unhandled exception in a human readable format, however this is not reccomended for production as the debug mode, can reduce performance and vulnerabilities. 

https://github.com/LukeK2021/frameworks_and_languages_module/blob/db04b9f5650e723eb4691dbe9472ab6345376a67/server/server.py#L70
```python
#entrypoint for flask application
if __name__ == '__main__':
    app.run(port=8000,host='0.0.0.0', debug=True )
```
The debug feature makes it simple to diagnose issues with the application at runtime as some bugs will only present themselves at run time, and the traceback shows the developers where to look to squash the bug. However running debug mode in production is not reccomended for production as if the stack trace is still there it could introduce vulnerabilities by giving bad actors an insight into the underlying logic of the server and make it easier for them to exploit it.

https://flask.palletsprojects.com/en/2.3.x/debugging/#the-built-in-debugger


### Flask extensions

Flask provides support for 3rd party extensions (middleware), in the newly developed solution some of this middleware has been installed, namely Flask-Cors and this pre-built package allows cors configuration without the need to write extensive functions as they are already provided for you.

https://github.com/LukeK2021/frameworks_and_languages_module/blob/db04b9f5650e723eb4691dbe9472ab6345376a67/server/server.py#L6

```python
cors = CORS(app, resources={r"/*": {"origins": "*"}})

```

By including reference libraries that encompass many features that are desirable to a web service, that need to be installed manually keeps the overheads of the program at a minimum by not including anything the user does not need. In addition using or removing these modules will only remove the feature, enabling true modularity to the developers.

https://flask.palletsprojects.com/en/2.3.x/extensions/
https://flask-cors.readthedocs.io/en/latest/


Server Language Features
-----------------------

### Dynamic types

In python there is no need to declare a data type when initializing a variable, it is all handled at run time by the interpreter. In statically typed languages it is required to declare the data type when initializing the variable, see snippets below for examples:

Static types:
```C#
using System;
					
public class Program
{
	public static void Main()
	{
        //Notice the int prefix when declaring the variables, in python these are not required.
		int a = 1;
		int b = 2;
		Console.WriteLine(a+b);
	}
}
```
Dynamic types:
```Python
items = [ #sample data
    {"id": 1 ,"user_id": "user1234", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275, "date_from": datetime.now().isoformat()},
    {"id": 2 ,"user_id": "user123", "keywords": ["drill", "saw", "sander"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275, "date_from": datetime.now().isoformat()},
    {"id": 3 ,"user_id": "user12", "keywords": ["screwdriver", "tea", "welding iron"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275, "date_from": datetime.now().isoformat()}
]
```
Notice how the dictionary posted does not need any type declarations, the program will run and behave as expected without any errors. This is great for beginner/intermediate programmers or those without a vast technical knowledge as it makes code simple to read and understand. Many educators choose python as a first language for this very reason.


https://docs.python.org/3/library/types.html?highlight=type#module-types


### List comprehension

List comprehension enables users to more concisely create lists based on the values of other lists, while performing filtering/mapping or conditional logic on its members. 

```Python
lowercase_keywords = [kw.lower() for kw in item['keywords']]
```

The list comprehension feature allows developers to transform a traditional for loop initialisation from multiple lines to a single line of code, this is especially useful if you are using nested loops, however it is more tricky to decipher what the code than a traditional for loop.

https://docs.python.org/3/tutorial/datastructures.html#list-comprehensions



Client Framework Features
-------------------------

### Re-useable components

React uses components (re-usable ui elements) as its core selling point, components allow users to compartmentalise aspects of their application and create their own reference objects.

```JSX
return (
    <div className="App-header"> 
    <head><meta name="viewport" content="width=device-width, initial-scale=1.0"/> </head>
      <h1>FreeCycle</h1> 
      <form onSubmit={create_item} className='flex-form'>
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
);
```
Components allow developer man hours to be used elsewhere by cutting down on writing code multiple times througout a project, in additon to the time saved the open source nature of components allows users to draw from a vast library of pre-fabricated components, all contained from the main logic of the program.

https://react.dev/learn/your-first-component

### Boostrap integration.

React bootstrap, a package designed with react in mind to style applications using bootstrap css styling, enabling the use of all the features boostrap provides.

https://github.com/LukeK2021/frameworks_and_languages_module/blob/f807eb2bccb826db2c9d6fc594315c46dc87782b/client/src/App.js#L175
```JSX
<ul className="list-group"> 
        {itemsData.map((item, id) => (
          <li key={item.id} className="Li-header" data-field="id"> 
            <Row> 
              <Col data-field="id">id: {item.id}</Col>
              <Col data-field="user_id">User id: {item.user_id}</Col>
              <Col data-field="description">Description: {item.description}</Col>
              <Col data-field="keywords">Keywords: {item.keywords}</Col>
              <Col data-field="lat">Lat: {item.lat}</Col>
              <Col data-field="lon">Lon: {item.lon}</Col>
              <Col data-field="date_from">Date: {item.date_from}</Col>
              <Col>
                <Button variant="danger" type="button" data-action="delete" onClick={() => delItem(item.id)}>Delete</Button> 
              </Col>
            </Row>
          </li>
        ))}
      </ul>
```

Boostrap allows users to style their applications with their expanded css, in the snippet above the rendering of the data has been assigned to a grid system enabling a consistant styling across the application.

By utilising the css classes and additional styling techniques such as dynamically sizing elements based on screen resolution, cuts down on developer time as they have their own custom references to call. Each styling class only has to be written once.

https://react-bootstrap.netlify.app/docs/getting-started/introduction/


### One way data binding

When data is flowing through the program it does so from top to bottom, meaning parent components pass on to child components only. Children cannot pass data to parent components but there is communication between the two in regards to updating states and components that rely on said states.


https://github.com/LukeK2021/frameworks_and_languages_module/blob/f807eb2bccb826db2c9d6fc594315c46dc87782b/client/src/App.js#L7
```jsx
const [itemsData, setitemsData] = useState([]) //state to hold data retrieved from server line #7
  const [formData, setFormData] = useState({ 
    user_id: 0,
    keywords: [''],
    description:'',
    lat:0.1,
    lon:0.1,
  });
```


With one way data binding data flows in one direction making it easy to trace a data source for debugging and isolate issues more effectively.


https://dev.to/parnikagupta/one-way-data-binding-in-react-30ea


Client Language Features
------------------------

### State

A state refers to the data currently stored in the program in a given moment either in the entire program or individual class, in this use case there are states for sending and recieving data to the server.

https://github.com/LukeK2021/frameworks_and_languages_module/blob/f807eb2bccb826db2c9d6fc594315c46dc87782b/client/src/App.js#L7

```Javascript
//initialising an effect hook to enable this client instance to access the data on the flask server https://react.dev/reference/react/hooks#effect-hooks
  const [itemsData, setitemsData] = useState([]) //managing inputs and requests with state -> https://react.dev/learn/managing-state
  const [formData, setFormData] = useState({ // State for handling the data that will be posted to server, note json fields are present to prevent 405.
    user_id: 0,
    keywords: [''],
    description:'',
    lat:0.1,
    lon:0.1,
  });
```


The state gives us a place to store our data completely seperate from any other objects in the program and we can have a nearly limitless amount of functions or render objects that can use it at any time. If a component is using a state, any changes to that state will refresh the component reflecting the changes.

https://www.altcademy.com/blog/what-is-state-in-javascript/#:~:text=In%20JavaScript%2C%20state%20refers%20to,part%20of%20the%20game's%20state

### Dynamic types

Like python javascript does not need a type declaration when initializing a variable, the type is defined by the stored value and interpreted at runtime.
The snippet below shows a structure designed to handle inputs to the server, note how despite the server data structure containing strings, integers and decimals, these do not need to be declared beforehand
https://github.com/LukeK2021/frameworks_and_languages_module/blob/f807eb2bccb826db2c9d6fc594315c46dc87782b/client/src/App.js#L44
```JSX
const handleInputChange = (e) => { //when this function is called it will store data contained within textboxes to the formData obj
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };
```

When writing smaller pieces of code a dynamic type streamlines the process by not having to declare types and some variables can be assigned a different data type, however should a type error occur it will not flag until run time.

https://www.solwey.com/posts/static-vs-dynamic-typing-choosing-the-right-approach-for-your-programming-needs
https://www.quora.com/What-are-the-advantages-and-disadvantages-of-dynamically-typed-programming-languages#:~:text=Advantages%3A,of%20variables%20before%20using%20them.



Conclusions
-----------

Frameworks are neccessary as they enable developers to create applications in a modular format and once blueprints are created they can be re-used as many times as needed, increasing efficiency as developers do not need to re-write code constantly and reduces the cognitive load caused by reading lots of code.
Another aspect is middleware, middleware is key for web-services as it allows us to pre/post process information coming in and out of the server, but most importantly it is modular, giving developers freedom to add and remove middleware at will without effecting the overall server functionality.

For the server i would reccomed flask as it is a python framework and python is by far the most popular programming language used. Due to this popularity there are lots of resources out there to assist in building a production server and this popularity comes from python being a friendly language to learn and read, however python and flask is not without its issues, python is not a very fast language as its interpreted rather than compiled so if its a fast response that is required i would reccomend a platform written in a C based language.

React for the client provides several benefits again like python it has the largest community amongst front end frameworks, this is due to the modularity it provides with render templates, furthermore it enables developers to isolate data to certain components, reducing memory and processing overheads.
