const mongoose = require('mongoose')
const studentSchema = require('../models/student.schema.server')
const studentModel = require('../models/student.model.server')
const answerSchema = require('../models/answer.schema.server')
const answerModel = require('../models/answer.model.server')
const quizWidgetSchema = require('../models/quiz-widget.schema.server')
const quizWidgetModel = require('../models/quiz-widget.model.server')
const questionSchema = require('../models/question.schema.server')
const questionModel = require('../models/question.model.server')
const multipleChoiceSchema = require('../models/multiple-choice.schema.server')
const trueFalseSchema = require('../models/true-false.schema.server')


truncateDatabase = async () => {
    await studentModel.deleteMany({});
    await questionModel.deleteMany({});
    await answerModel.deleteMany({});
    await quizWidgetModel.deleteMany({});
}

populateDatabase = async () => {
    try {
        //create students alice and bob
        const alice = {
            _id: 123,
            firstName: 'Alice',
            lastName: 'Wonderland',
            username: 'alice',
            password: 'alice',
            type: 'Student',
            gradYear: 2020,
            scholarship: 15000
        }
        await createStudent(alice);
        const bob = {
            _id: 234,
            firstName: 'Bob',
            lastName: 'Hole',
            username: 'bob',
            password: 'bob',
            type: 'Student',
            gradYear: 2021,
            scholarship: 12000
        }
        await createStudent(bob);

        //create questions
        const question321 = {
            _id: 321,
            question: "Is the following schema valid?",
            points: 10,
            questionType: 'TrueFalse',
            trueFalse: {
                isTrue: false
            }
        }
        await createQuestion(question321);
        const question432 = {
            _id: 432,
            question: "DAO stands for Dynamic Access Object",
            points: 10,
            questionType: 'TrueFalse',
            trueFalse: {
                isTrue: false
            }
        }
        await createQuestion(question432);
        const question543 = {
            _id: 543,
            question: "What does JPA stand for?",
            points: 10,
            questionType: 'MultipleChoice',
            multipleChoice: {
                choices: "Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations",
                correct: 1
            }
        }
        await createQuestion(question543);
        const question654 = {
            _id: 654,
            question: "What does ORM stand for",
            points: 10,
            questionType: 'MultipleChoice',
            multipleChoice: {
                choices: "Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping",
                correct: 4
            }
        }
        await createQuestion(question654);

        // create answers for alice
        const answer123 = {
            _id: 123,
            trueFalseAnswer: true
        }
        const answer234 = {
            _id: 234,
            trueFalseAnswer: false
        }
        const answer345 = {
            _id: 345,
            multipleChoiceAnswer: 1
        }
        const answer456 = {
            _id: 456,
            multipleChoiceAnswer: 2
        }
        await answerQuestion(alice._id, question321._id, answer123)
        await answerQuestion(alice._id, question432._id, answer234)
        await answerQuestion(alice._id, question543._id, answer345)
        await answerQuestion(alice._id, question654._id, answer456)

        // create answers for bob
        const answer567 = {
            _id: 567,
            trueFalseAnswer: false
        }
        const answer678 = {
            _id: 678,
            trueFalseAnswer: true
        }
        const answer789 = {
            _id: 789,
            multipleChoiceAnswer: 3
        }
        const answer890 = {
            _id: 890,
            multipleChoiceAnswer: 4
        }

        await answerQuestion(bob._id, question321._id, answer567)
        await answerQuestion(bob._id, question432._id, answer678)
        await answerQuestion(bob._id, question543._id, answer789)
        await answerQuestion(bob._id, question654._id, answer890)
    }
    catch(err){
        console.log(err)
    }
}

createStudent = student => studentModel.create(student)

deleteStudent = id => studentModel.deleteOne({_id: id})

createQuestion = question => questionModel.create(question)

deleteQuestion = id => questionModel.deleteOne({_id: id})

answerQuestion = async (studentId, questionId, answer) => {
    try{
        let student = await studentModel.findById(studentId)
        let question = await questionModel.findById(questionId)
        if(!student){
            throw new Error('Student not found!')
        }
        else if(!question){
            throw new Error('Question not found!')
        }
        else {
            answerModel.create({
                _id: answer._id,
                trueFalseAnswer: answer.trueFalseAnswer,
                multipleChoiceAnswer: answer.multipleChoiceAnswer,
                student: studentId,
                question: questionId
            })
        }
    }
    catch(err){
        throw err;
    }
}

deleteAnswer = id => answerModel.deleteOne({_id: id})

findAllStudents = () => studentModel.find()

findStudentById = id => studentModel.findById(id)

findAllQuestions = () => questionModel.find()

findQuestionById = id => questionModel.findById(id)

findAllAnswers = () => answerModel.find()

findAnswerById = id => answerModel.findById(id)

findAnswerByStudent = async studentId => {
    try{
        let student = await studentModel.findById(studentId)
        if(!student){
            throw new Error("Student with id:" + studentId + " not found!")
        }
        else{
            return answerModel.find({student: student}).then()
        }
    }
    catch(err){
        throw err;
    }
}

findAnswerByQuestion = async questionId => {
    try{
        let question = await questionId.findById(questionId)
        if(!question){
            throw new Error("Question with id:" + questionId + " not found!")
        }
        else{
            return answerModel.find({question: question}).then()
        }
    }
    catch(err){
        throw err;
    }
}

module.exports = {
    truncateDatabase,
    populateDatabase,
    createStudent,
    deleteStudent,
    createQuestion,
    deleteQuestion,
    answerQuestion,
    deleteAnswer,
    findAllStudents,
    findStudentById,
    findAllQuestions,
    findQuestionById,
    findAllAnswers,
    findAnswerById,
    findAnswerByStudent,
    findAnswerByQuestion
}
