from flask import Flask, jsonify, request
from flask_cors import CORS
from datetime import datetime
import uuid

app = Flask(__name__)
CORS(app, origins="http://localhost:8000",
      allow_headers=["Content-Type", "Authorization", "Access-Control-Allow-Credentials"],
      supports_credentials=True)


items = [ #sample data
    {"id": uuid.uuid4() ,"user_id": "user1234", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275,"date_from": datetime.now().isoformat()},
    {"id": uuid.uuid4() ,"user_id": "user123", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275,"date_from": datetime.now().isoformat()},
    {"id": uuid.uuid4() ,"user_id": "user12", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275,"date_from": datetime.now().isoformat()}
]

#get home page
@app.route('/',methods=['GET'])#specifying the url route and the http method to perform operations.
def home():
    return "Home", 200

#get all users
@app.route('/items',methods=['GET'])
def getusers():
    return items, 200

#takes integer parameter to filter users data by their id as a path parameter https://flask.palletsprojects.com/en/3.0.x/api/#flask.Flask.route
@app.route('/item/<int:userId>',methods=['GET'])
def get_id(userId):
    for user in items:
        if user['id']==userId:
            return user, 200
    return jsonify({'error':'not found'}), 404


#post a new item
@app.route('/item',methods=['POST'])
def new_item():
    inputData = request.get_json()
    if "user_id" not in inputData:
        return jsonify({"error": "Missing 'id' field"}), 405
    else:
        post = {"id": len(items)+1, 'user_id':request.json['user_id'],'keywords':request.json['keywords'], 'description':request.json['description'], 'lat':request.json['lat'], 'lon':request.json['lon'], "date_time": datetime.now().isoformat(),"date_from": datetime.now().isoformat()}
        items.append(post)
        return post, 201
    

#delete an item using id param
@app.route('/item/<int:Id>',methods=['DELETE'])
def remove(Id):
    for user in items:
        if user['id']==Id:
            items.remove(user)
            return jsonify({"data":"deleted"}), 204
        return jsonify({'error':'not found'}), 404




#entrypoint for flask application
if __name__ == '__main__':
    app.run(port=8000, host='0.0.0.0', debug=True )