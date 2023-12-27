Technical Report
================

The intended purpose of this technical report is to review the existing prototype implementations of the "freecycle" product, this critique will be solely of the programming languages and frameworks that exist within this product suite and to provide a potential solution to issues should they arise, furthermore the new implementation of the service will also come under review under the same procedure.

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

### A Lack of Data state.


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
As were at the prototyping phase we can assume that the scope of the project will increase over time and the current prototypes that are present offer a working solution, however the code is deeply flawed and it will require an immense amount of time to scale up the platform.


The existing codebase needs to be re-written to incude frameworks with the reccomendation being flask for the server side and react for client side as they both contain large feature sets and said feature sets make them both extremely popular frameworks.

Server Framework Features
-------------------------

### (name of Feature 1)

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




### (name of Feature 2)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)


### (name of Feature 3)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)


Server Language Features
-----------------------

### (name of Feature 1)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)


### (name of Feature 2)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)



Client Framework Features
-------------------------

### (name of Feature 1)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)


### (name of Feature 2)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)


### (name of Feature 3)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)


Client Language Features
------------------------

### (name of Feature 1)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)

### (name of Feature 2)

(Technical description of the feature - 40ish words)
(A code block snippet example demonstrating the feature)
(Explain the problem-this-is-solving/why/benefits/problems - 40ish words)
(Provide reference urls to your sources of information about the feature - required)



Conclusions
-----------

(justify why frameworks are recommended - 120ish words)
(justify which frameworks should be used and why 180ish words)
