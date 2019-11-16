const mongoose = require('mongoose')

module.exports = mongoose.Schema({
    _id: Number,
    username: String,
    password: String,
    firstName: String,
    lastName: String,
    userType: {type: String, enum:['Student', 'Faculty']},
    gradYear: Number,
    scholarship: Number
}, {collection: 'students'})