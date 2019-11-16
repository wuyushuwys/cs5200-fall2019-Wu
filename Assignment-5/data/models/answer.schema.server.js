const mongoose = require('mongoose')
const student = require('./student.schema.server')
const question = require('./question.schema.server')
const answer = mongoose.Schema({
    _id: Number,
    trueFalseAnswer: Boolean,
    multipleChoiceAnswer: Number,
    student: {type: mongoose.Schema.Types.Number, ref: 'StudentModel'},
    question: {type: mongoose.Schema.Types.Number, ref: 'QuestionModel'}
}, {collection: 'answers'})
module.exports = answer