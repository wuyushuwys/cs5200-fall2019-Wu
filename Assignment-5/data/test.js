require('./db')();
const universityDao = require('./daos/university.dao.server');



const truncateDatabase = () => universityDao.truncateDatabase().then(x => console.log("\ntruncate Database"))

const populateDatabase = () => universityDao.populateDatabase().then(x => console.log("\npopulate Database"))

const testStudentsInitialCount = () => universityDao.findAllStudents()
    .then(doc => console.log("\nStudents initial count: " + doc.length + " students"))

const testQuestionsInitialCount = () => universityDao.findAllQuestions()
    .then(doc => console.log("\nQuestions initial count: " + doc.length + " questions"))

const testAnswersInitialCount = () => universityDao.findAllAnswers()
    .then(doc => console.log("\nAnswers initial count: " + doc.length + " answers"))

const testDeleteAnswer = () => universityDao.deleteAnswer(890)
    .then(x => {
       console.log("\nRemove Bob’s answer for the multiple choice question “What does ORM stand for?”")
       return universityDao.findAllAnswers()
    })
    .then(doc => {
       console.log("Total number of answers : " + doc.length)
       return universityDao.findAnswerByStudent(234)
    })
    .then(doc => console.log("Bob's total number of answers is " + doc.length))

const testDeleteQuestion = () => universityDao.deleteQuestion(321)
    .then(x => {
       console.log("\nDelete Question \"Is the following schema valid?\"")
       return universityDao.findAllQuestions()
    })
    .then(doc => console.log("Total number of questions is " + doc.length))

const testDeleteStudent = () => universityDao.deleteStudent(234)
    .then(x => {
       console.log("\nRemove student Bob ")
       return universityDao.findAllStudents()
    })
    .then(doc => console.log("Total number of students is " + doc.length))

truncateDatabase()
    .then(() => populateDatabase())
    .then(() => testStudentsInitialCount())
    .then(() => testQuestionsInitialCount())
    .then(() => testAnswersInitialCount())
    .then(() => testDeleteAnswer())
    .then(() => testDeleteQuestion())
    .then(() =>console.log("\nValidation finished"))
// validation = async () => {
//    await truncateDatabase()
//    await populateDatabase()
//    await testStudentsInitialCount()
//    await testQuestionsInitialCount()
//    await testAnswersInitialCount()
//    await testDeleteAnswer()
//    await testDeleteQuestion()
// }
//
// validation()
//     .then(r => console.log('\ntest finished'))
