module.exports = function () {
    const mongoose = require('mongoose');
    mongoose.connect('mongodb://localhost:27017/white-board', {
        useNewUrlParser: true,
        useUnifiedTopology: true})
        .then(() => console.log('DB Connected!'))
        .catch(err => {
            console.log(`DB Connection Error: ${err.message}`);
        });
}
