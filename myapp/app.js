const express = require('express')
const app = express()
const port = 3000

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
res.json({foo:bar})
// Docker container exit handler - https://github.com/nodejs/node/issues/4182
  process.on('SIGINT', function() {process.exit()})