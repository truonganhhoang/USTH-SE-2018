const express = require('express')
const app = express()

app.use(express.static('public'));
app.use('/models', express.static(__dirname + '/models'))
app.set('view engine', 'ejs')

app.get('/', function (req, res) {
  res.render('gameTest');
})




app.listen(3000, function () {
  console.log('Example app listening on port 3000!')
})
