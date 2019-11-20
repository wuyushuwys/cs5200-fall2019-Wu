const mongoose = require('mongoose')

module.exports = mongoose.Schema({
    _id: Number,
    choices: String,
    correct: Number
})