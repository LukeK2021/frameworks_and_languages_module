import { getAllItems, getItemById } from '../../data';

export default (req, res) => {
  if (req.method === 'GET') {
    const { id } = req.query;

    if (id) {
      const searchId = parseInt(id, 10);
      const item = getItemById(searchId);

      if (item) {
        res.status(200).json(item);
      } else {
        res.status(404).end(); // Not Found
      }
    } else {
      const allItems = getAllItems();
      res.status(200).json(allItems);
    }
  } else {
    res.status(405).end(); // Method Not Allowed
  }
  if (req.method === 'POST')
  {
    return null; //placeholder
  }
  if (req.method ==='DELETE')
  {
    return null;
  }
};