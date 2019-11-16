const mongoose = require('mongoose')

module.exports = mongoose.Schema({
    _id: Number,
    choice: String,
    correct: Number
})