package BLL;

import BE.*;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BLLManagerTest {
    private BLLManager manager;
    DalMock dal;

    @BeforeEach
    void setup() {
        dal = new DalMock();
        manager = new BLLManager(dal);
    }

    @Test
    void checkCredentials() {

        try {
            User actualuser = manager.checkCredentials(".", ".");
            User expecteduser = new User(1, ".", ".", "TEACHER");

            assertEquals(actualuser.getUserType(), expecteduser.getUserType());
            assertEquals(actualuser.getName(), expecteduser.getName());
            assertEquals(actualuser.getSchoolID(), expecteduser.getSchoolID());
        } catch (DalException | BLLException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    void createCase() throws DalException {

        Case myCase = getSampleCase();
        Case result = manager.createCase(myCase);
        assertEquals(myCase.getName(), result.getName());
        assertEquals(myCase, result);
    }

    private Case getSampleCase() {
        return new Case("testCase", "description", "category", "subcategory", 1);
    }

    @Test
    void createPatient() throws DalException {
        Patient expectedPatient = new Patient("john", "cena", LocalDate.of(2022, 05, 25), "male", "70", "170", "12", "12", null, 1);
        Patient actualPatient = manager.createPatient(expectedPatient);
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    void getAllGroups() throws DalException {
        List<Group> allGroups = manager.getAllGroups(1);
        assertEquals(allGroups.size(), 1);
        assertEquals(allGroups.get(0).getSchoolId(), 1);
    }

    @Test
    void getAllCases() throws DalException {
        int schoolID = 1;
        List<Case> allCases = manager.getAllCases(schoolID);
        assertEquals(allCases.size(), 1);
        assertEquals(allCases.get(0).getSchoolID(), schoolID);
    }

    @Test
    void getAllPatients() throws DalException {
        int schoolID = 1;
        List<Patient> allPatients = manager.getAllPatients(schoolID);
        assertEquals(allPatients.size(), 1);
        assertEquals(schoolID, allPatients.get(0).getSchoolId());
    }

    @Test
    void addNewStudent() throws DalException {
        User expectedUser = getSampleStudent();
        User actualUser = manager.addNewStudent(expectedUser);
        assertEquals(expectedUser, actualUser);
    }

    private User getSampleStudent() {
        return new User(1, 2, "a", "e", "STUDENT");
    }

    @Test
    void updateStudent() throws DalException {
        manager.updateStudent(getSampleStudent());
        String actualMessage = dal.getMessage();
        assertEquals("user a updated",actualMessage);
    }

    @Test
    void deleteStudent() throws DalException {
        User sampleStudent = getSampleStudent();
        manager.deleteStudent(sampleStudent);
        String actualMessage = dal.getMessage();
        assertEquals("user a deleted",actualMessage);
    }

    @Test
    void getAllStudent() throws DalException {
        int schoolId=1;
        List<User> allStudent = manager.getAllStudent(schoolId);
        assertEquals(allStudent.size(),1);
        assertEquals(allStudent.get(0).getSchoolID(),schoolId);
    }

    @Test
    void updatePatient() throws DalException {
        int schoolId=1;
        Patient actualPatient= getSamplePatient(schoolId);
        manager.updatePatient(actualPatient);
        assertEquals(dal.getMessage(),"patient "+ actualPatient.getFirst_name()+" updated");
    }

    private Patient getSamplePatient(int schoolId) {
        return new Patient("fist", "last", LocalDate.now(), "m", "77", "160", "34", "", null, schoolId);
    }

    @Test
    void createNewGroup() throws DalException {
        Group expedctedGroup= getSampleGroup();
        Group actualGroup = manager.createNewGroup(expedctedGroup);
        assertEquals(expedctedGroup,actualGroup);

    }

    @Test
    void updateGroup() throws DalException {
        Group group= getSampleGroup();
        manager.updateGroup(group);
        assertEquals(dal.getMessage(),"group "+group.getName()+" updated");

    }

    private Group getSampleGroup() {
        return new Group("g1", null, 1);
    }

    @Test
    void addStudentToGroup() throws DalException {
        Group group=getSampleGroup();
        User student = getSampleStudent();
        manager.addStudentToGroup(group,student);
        assertEquals(dal.getMessage(),"user "+student.getName()+" added to "+group.getName());
    }

    @Test
    void deleteGroup() throws DalException {
        Group group = getSampleGroup();
        manager.deleteGroup(group);
        assertEquals(dal.getMessage(),"group "+group.getName()+" deleted");
    }

    @Test
    void removeParticipant() throws DalException {
        Group group = getSampleGroup();
        User user = getSampleStudent();
        manager.removeParticipant(group,user);
        assertEquals(dal.getMessage(),"user " + user.getName() + " and group " + group.getName() + "  removed");
    }

    @Test
    void updateCase() throws DalException {
        Case aCase = getSampleCase();
        manager.updateCase(aCase);
        assertEquals(dal.getMessage(),"case "+aCase.getName()+" updated");
    }

    @Test
    void deleteCase() throws DalException {
        Case sampleCase = getSampleCase();
        manager.deleteCase(sampleCase);
        assertEquals(dal.getMessage(),"case "+sampleCase.getName()+" deleted");
    }

    @Test
    void deletePatient() throws DalException {
        Patient patient = getSamplePatient(1);
        manager.deletePatient(patient);
        assertEquals(dal.getMessage(),"patient "+patient.getFirst_name()+" deleted");

    }

    @Test
    void saveStudentQuestionAnswer() throws DalException {
        StudentQuestionnaireAnswer answer= getSampleStudentQuestionAnswer();
        manager.saveStudentQuestionAnswer(answer);
        assertEquals(dal.getMessage(),"answer with id "+answer.getId() +" saved");
    }

    private StudentQuestionnaireAnswer getSampleStudentQuestionAnswer() {
        return new StudentQuestionnaireAnswer(1, 12, 4, 23);
    }

    @Test
    void getFirstQuestion() throws DalException {
        StudentQuestion actualFirstQuestion = manager.getFirstQuestion();
        StudentQuestion expectedQuestion=dal.getSampleStudentQuestion();
        compareQuestions(expectedQuestion, actualFirstQuestion);
    }

    @Test
    void getNextQuestion() throws DalException, BLLException {
        StudentQuestion question = getSampleQuestion();
        StudentQuestion actualQuestion = manager.getNextQuestion(question);
        StudentQuestion expectedQuestion = dal.getSampleStudentQuestion();
        compareQuestions(expectedQuestion, actualQuestion);

    }

    private void compareQuestions(StudentQuestion expectedQuestion, StudentQuestion actualQuestion) {
        assertEquals(expectedQuestion.getQuestion(), actualQuestion.getQuestion());
        assertEquals(expectedQuestion.getId(), actualQuestion.getId());
        assertEquals(expectedQuestion.getTitle(), actualQuestion.getTitle());
        assertEquals(expectedQuestion.getCategory(), actualQuestion.getCategory());
    }

    private StudentQuestion getSampleQuestion() {
        return new StudentQuestion(2, "cat2", "title2", "what time is it", "red");
    }

    @Test
    void getPreviousQuestion() throws DalException, BLLException {
        StudentQuestion question = getSampleQuestion();
        StudentQuestion actualQuestion = manager.getPreviousQuestion(question.getId());
        StudentQuestion expectedQuesiton=dal.getSampleStudentQuestion();
        compareQuestions(expectedQuesiton,actualQuestion);
    }

    @Test
    void getQuestionaireAnswer() throws DalException {
        int questionaireId = 20;
        int questionId = 1;
        StudentQuestionnaireAnswer actualQuestionaireAnswer = manager.getQuestionaireAnswer(questionId, questionaireId);
        StudentQuestionnaireAnswer expectedQuestionnaireAnswer=new StudentQuestionnaireAnswer(43,questionId,1,questionaireId);
        assertEquals(expectedQuestionnaireAnswer.getId(),actualQuestionaireAnswer.getId());
        assertEquals(expectedQuestionnaireAnswer.getQuestionId(),actualQuestionaireAnswer.getQuestionId());
        assertEquals(expectedQuestionnaireAnswer.getQuestionnaireId(),actualQuestionaireAnswer.getQuestionnaireId());
        assertEquals(expectedQuestionnaireAnswer.getState(),actualQuestionaireAnswer.getState());


    }

}