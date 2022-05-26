package BLL;

import BE.*;
import DAL.DALFacade;
import DAL.util.DalException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DalMock implements DALFacade {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public List<Case> getAllCases(int schoolid) throws DalException {
        List<Case> cases = new ArrayList<>();
        cases.add(new Case("c1", "d1", "cat1", "sub1", schoolid));
        return cases;
    }

    @Override
    public Case createCase(Case c) throws DalException {
        return c;
    }

    @Override
    public void updateCase(Case c) throws DalException {
        message = "case " + c.getName() + " updated";
    }

    @Override
    public void deleteCase(Case c) throws DalException {
        message = "case " + c.getName() + " deleted";
    }

    @Override
    public User verifyUsers(String useremail, String password) throws DalException {
        return new User(1, ".", ".", "TEACHER");
    }

    @Override
    public List<User> getAllUsers(int schoolid) throws DalException {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "a", "b", "t"));
        return users;
    }

    @Override
    public void updateuser(User user) throws DalException {
        message = "user " + user.getName() + " updated";
    }

    @Override
    public void deleteuser(User user) throws DalException {
        message = "user " + user.getName() + " deleted";
    }

    @Override
    public User addUser(User user) throws DalException {
        return user;
    }

    @Override
    public List<User> searchForUser(String query) throws DalException {
        return null;
    }

    @Override
    public List<Patient> getAllPatients(int schoolid) throws DalException {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("jj", "dd", LocalDate.of(2022, 05, 25), "m", "44", "100", "", "", null, schoolid));
        return patients;
    }

    @Override
    public Patient createPatient(Patient patient) throws DalException {
        return patient;
    }

    @Override
    public void updatePatient(Patient patient) throws DalException {
        message = "patient " + patient.getFirst_name() + " updated";
    }

    @Override
    public void deletePatient(Patient patient) throws DalException {
        message = "patient " + patient.getFirst_name() + " deleted";

    }

    @Override
    public List<School> getAllSchhol() throws DalException {
        return null;
    }

    @Override
    public void createSchool(School school) throws DalException {

    }

    @Override
    public void updateSchool(School school) throws DalException {

    }

    @Override
    public void deleteSchool(School school) throws DalException {

    }

    @Override
    public List<Group> getAllGroups(int schoolID) throws DalException {
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("g1", null, schoolID));
        return groups;
    }

    @Override
    public Group createGroup(Group group) throws DalException {
        return group;
    }

    @Override
    public void updateGroup(Group group) throws DalException {
        message = "group " + group.getName() + " updated";
    }

    @Override
    public void deleteGroup(Group group) throws DalException {
        message = "group " + group.getName() + " deleted";
    }

    @Override
    public List<User> getUsersInGroup(int id) throws DalException {
        return null;
    }

    @Override
    public void addUsertoGroup(Group group, User user) throws DalException {
        message = "user " + user.getName() + " added to " + group.getName();
    }

    @Override
    public void removeUserFromGroup(User user) throws DalException {

    }

    @Override
    public void assignCaseToGroup(Patient p, Case c, Group g) throws DalException {

    }

    @Override
    public void addStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException {
        message = "answer with id " + answer.getId() + " saved";
    }

    @Override
    public StudentQuestion getFirstStudentQuestion() throws DalException {
        return getSampleStudentQuestion();
    }

    @Override
    public StudentQuestion getNextStudentQuestion(int id) throws DalException {
        return getSampleStudentQuestion();
    }

    @Override
    public StudentQuestion getPreviousQuestion(int currentQuestionId) throws DalException {
        return getSampleStudentQuestion();
    }

    @Override
    public StudentQuestionnaireAnswer getQuestionaireAnswer(int questionId, int questionaireId) throws DalException {
        return new StudentQuestionnaireAnswer(43,questionId,1,questionaireId);
    }

    @Override
    public void removeUserAndGroup(User user, Group group) throws DalException {
        message = "user " + user.getName() + " and group " + group.getName() + "  removed";
    }

    @Override
    public List<Case> getCasesAssignedTo(Group group) throws DalException {
        return null;
    }

    @Override
    public List<User> getAllUSERS(int schoolId, String utype) throws DalException {
        return null;
    }

    @Override
    public List<User> getALLUsers(int schoolid, String utype) throws DalException {
        return null;
    }

    @Override
    public Group getGroupOf(User student) throws DalException {
        return null;
    }

    @Override
    public StudentQuestionnaire getQuestionnaireOf(Group group) throws DalException {
        return null;
    }

    @Override
    public List<StudentQuestion> getQuestionnaireQuestions(int questionnaireId) throws DalException {
        return null;
    }

    @Override
    public Patient getPatientOfCase(Case selectedCase, Group group) throws DalException {
        return null;
    }

    @Override
    public void unassignCase(Case selectedItem) throws DalException {

    }

    @Override
    public void markCaseAsGraded(Case selectedItem) throws DalException {

    }

    @Override
    public void unmarkCaseAsGraded(Case selectedItem) throws DalException {

    }

    @Override
    public List<Case> getCasesGradedOf(Group group) throws DalException {
        return null;
    }

    @Override
    public void addObservationToPatient(String text, Patient currentPatient) throws DalException {

    }

    @Override
    public StudentQuestionnaire getQuestionnaire(int questionnaireId) throws DalException {
        return null;
    }

    @Override
    public int getSickPatientId(Patient currentPatient, Case currentCase, Group currentGroup) throws DalException {
        return 0;
    }

    @Override
    public void updateQuestionnaire(StudentQuestionnaire questionnaire) throws DalException {

    }

    @Override
    public int getQuestionnaireOf(int caseId, int groupId) throws DalException {
        return 0;
    }

    @Override
    public List<Category> getAllCategories() throws DalException {
        return null;
    }

    @Override
    public List<SubCategory> getAllSubCategories(int catid) throws DalException {
        return null;
    }

    @Override
    public List<PatientLog> logs(Patient patient) throws DalException {
        return null;
    }

    @Override
    public void updateLog(PatientLog patientLog, Patient patient) throws DalException {

    }

    @Override
    public void addLog(PatientLog patientLog) throws DalException {

    }

    public StudentQuestion getSampleStudentQuestion() {
        return new StudentQuestion(1,"cat1","title","houw are you","blue");
    }
}
