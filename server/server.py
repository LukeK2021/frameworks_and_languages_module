from flask import Flask, jsonify, request
from flask_cors import CORS, cross_origin
from datetime import datetime

app = Flask(__name__)
cors = CORS(app, resources={r"/*": {"origins": "*"}})

items = [ #sample data
    {"id": 1 ,"user_id": "user1234", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275, "date_from": datetime.now().isoformat()},
    {"id": 2 ,"user_id": "user123", "keywords": ["drill", "saw", "sander"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275, "date_from": datetime.now().isoformat()},
    {"id": 3 ,"user_id": "user12", "keywords": ["screwdriver", "tea", "welding iron"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275, "date_from": datetime.now().isoformat()}
]
@app.route('/items', methods=['OPTIONS'])
def options():
    return '', 200

#default page route
@app.route('/',methods=['GET'])#specifying the url route and the http method to perform operations.
def home():
    return "Home", 200

#get all users
@app.route('/items',methods=['GET'])
def getusers():
    return jsonify(items), 200

#takes integer parameter to filter users data by their id as a path parameter https://flask.palletsprojects.com/en/3.0.x/api/#flask.Flask.route
#combining both methods as url syntax is identical according to brief.
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

#post a new item
@app.route('/item',methods=['POST'])
def new_item():
    inputData = request.get_json()
    if "user_id" not in inputData:
        return jsonify({"error": "Missing 'id' field"}), 405
    else:
        post = {"id": len(items)+1 , 'user_id':request.json['user_id'],'keywords':request.json['keywords'], 'description':request.json['description'], 'lat':request.json['lat'], 'lon':request.json['lon'], 'date_time': datetime.now().isoformat(),"date_from": datetime.now().isoformat()}
        items.append(post)
        return jsonify(post), 201
    
@app.route('/item/<string:keyword>',methods=['GET']) #method to filter json objects by keywords that exist within the obj
def get_items_by_keyword(keyword):
    if not keyword:
        return jsonify({'error': 'Keyword is not valid/missing'}), 400

    # Filter items based on the exact keyword
    filtered_items = []
    for item in items:
        lowercase_keywords = [kw.lower() for kw in item['keywords']]
        if keyword.lower() in lowercase_keywords and keyword.lower() in lowercase_keywords:
            filtered_items.append(item)

    return jsonify(filtered_items)


#entrypoint for flask application
if __name__ == '__main__':
    app.run(port=8000,host='0.0.0.0', debug=True )