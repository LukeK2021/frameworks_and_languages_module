const id =0; //var for unique ids

const items = [
    {
      "id": id++,
      "user_id": "user1234", 
      "keywords": ["hammer", "nails", "tools"], 
      "description": "A hammer and nails set. In canterbury", 
      "lat": 51.2798438, 
      "lon": 1.0830275,
      "currentDateAndTime": new Date().toISOString(), //setting iso8601 date time
    },
    {
        "id": id++,
        "user_id": "user123456789", 
        "keywords": ["hammer", "nails", "tools","pneumatic drill"], 
        "description": "A hammer and nails set. In canterbury, with an added bonus", 
        "lat": 51.2798438, 
        "lon": 1.0830275,
        "currentDateAndTime": new Date().toISOString(),
    },
    
  ];
  
  export function getAllItems() {
    return items;
  }
  
  export function getItemById(id) {
    return items.find((item) => item.id === id);
  }

  //export function getKeywords(keywords){
  //  return items.find((item) => item.keywords === keywords);
  //}

  //export function getUser(user_id){
  //  return items.find((item) => item.user_id === user_id)
  //}

