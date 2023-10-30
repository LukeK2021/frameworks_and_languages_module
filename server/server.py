from flask import Flask, jsonify, request
import datetime

app = Flask(__name__)

#sample data
items = [
    {"id": 1 ,"user_id": "user1234", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275,"date_from": datetime.datetime.now().isoformat()},
    {"id": 2 ,"user_id": "user123", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275,"date_from": datetime.datetime.now().isoformat()},
    {"id": 3 ,"user_id": "user12", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275,"date_from": datetime.datetime.now().isoformat()}
]

#get home page
@app.route('/',methods=['GET'])
def home():
    return "Home"

#get all users
@app.route('/items',methods=['GET'])
def getusers():
    return items, 200

#takes integer parameter to filter users data by their id
@app.route('/item/<int:userId>',methods=['GET'])
def get_id(userId):
    for user in items:
        if user['id']==userId:
            return user, 200
    return {'error':'not found'}, 404


#post a new item
@app.route('/item',methods=['POST'])
def new_item():
    post = {"id": len(items)+1, 'user_id':request.json['user_id'],'keywords':request.json['keywords'], 'description':request.json['description'], 'lat':request.json['lat'], 'lon':request.json['lon'], "date_time": datetime.datetime.now().isoformat()}
    items.append(post)
    return post, 201

#delete an item using id param
@app.route('/item/<int:Id>',methods=['DELETE'])
def remove():
    for user in items:
        if user['id']==Id:
            items.remove(user)
            return{"data":"deleted"}, 204
        return {'error':'not found'}, 404




#entrypoint for flask application
if __name__ == '__main__':
    app.run(debug=True, port=8000,host='0.0.0.0')