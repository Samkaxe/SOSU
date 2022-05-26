package DAL;

import BE.*;
import DAL.DataAccess.JDBCConnectionPool;
import DAL.Manager1.*;
import DAL.util.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Manager implements DALFacade {

    private static final Logger LOGGER = Logger.getLogger(Manager.class);
    private final DAOCase daoCase;
    private final DAOUser daoUser;
    private final DAOPatient daoPatient;
    private final DAOSchool daoSchool;
    private final DAOGroup daoGroup;
    private final DAOStudentQuestion daoStudentQuestion;
    private final DaocategoryAndSubCategory daoCat ;
    private final JDBCConnectionPool connector ;

    public Manager() {
        connector = new JDBCConnectionPool();
        daoCase = new DAOCase();
        daoUser = new DAOUser();
        daoPatient = new DAOPatient();
        daoSchool = new DAOSchool();
        daoGroup = new DAOGroup();
        daoStudentQuestion = new DAOStudentQuestion();
        daoCat = new DaocategoryAndSubCategory();
    }


    @Override
    public List<Case> getAllCases(int schoolid) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoCase.getAllCases(con,schoolid);
        } catch (SQLServerException e) {
           throw new DalException("Cant get All cases",e);
        }

    }

    @Override
    public Case createCase(Case c) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoCase.createCase(con ,c);
        } catch (SQLServerException e) {
            throw new DalException("cant create the case ",e);
        }

    }

    @Override
    public void updateCase(Case c) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoCase.updateCase(con ,c);
        } catch (SQLServerException e) {
            throw new DalException("cant update the case ",e);
        }
    }

    @Override
    public void deleteCase(Case c) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoCase.deleteCase(con ,c);
        } catch (SQLServerException e) {
            throw new DalException("cant delete case ",e);
        }

    }

    @Override
    public User verifyUsers(String useremail, String password) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoUser.verifyUsers(con,useremail, password);
        } catch (SQLServerException e) {
            LOGGER.error("cant verify user " , e);
            throw new DalException("cant verfiy the user",e);
        }

    }

    @Override
    public List<User> getAllUsers(int schoolid) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoUser.getAllUsers(con ,schoolid);
        } catch (SQLServerException e) {
            throw new DalException("cant get the users ",e);
        }

    }

    @Override
    public void updateuser(User user ) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoUser.updateuser(con ,user);
        } catch (SQLServerException e) {
            throw new DalException("cant update the user ",e);
        }

    }

    @Override
    public void deleteuser(User user) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoUser.deleteuser( con ,user);
        } catch (SQLServerException e) {
            throw new DalException("cant delete the user ",e);
        }

    }

    @Override
    public User addUser(User user) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoUser.addUser(con ,user);
        } catch (SQLServerException e) {
            throw new DalException("cant add the user",e);
        }

    }

    @Override
    public List<User> searchForUser(String query) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoUser.searchForUser(con,query);
        } catch (SQLServerException e) {
            throw new DalException("cant search for user ",e);
        }

    }

    @Override
    public List<Patient> getAllPatients(int schoolid) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoPatient.getAllPatients(con ,schoolid);
        } catch (SQLServerException e) {
            throw new DalException("cant get list of patients ",e);
        }

    }

    @Override
    public Patient createPatient(Patient patient) throws DalException {

        try {
            Connection   con = connector.getConnection();
            return daoPatient.createPatient(con ,patient);
        } catch (SQLServerException e) {
            throw new DalException("cant create this patient ",e);
        }

    }

    @Override
    public void updatePatient(Patient patient) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoPatient.updatepatient(con ,patient);
        } catch (SQLServerException e) {
            throw new DalException("cant upadte the patient ",e);
        }

    }

    @Override
    public void deletePatient(Patient patient) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoPatient.deletePatient(con,patient);
        } catch (SQLServerException e) {
            throw new DalException("cant delete the patient ",e);
        }

    }

    @Override
    public List<School> getAllSchhol() throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoSchool.getAllSchhol(con);
        } catch (SQLServerException e) {
            throw new DalException("cant get the schools ",e);
        }

    }

    @Override
    public void createSchool(School school) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoSchool.createSchool(con,school);
        } catch (SQLServerException e) {
            throw new DalException("cant create school ",e);
        }

    }

    @Override
    public void updateSchool(School school) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoSchool.updateSchool(con,school);
        } catch (SQLServerException e) {
            throw new DalException("cant update school ",e);
        }

    }

    @Override
    public void deleteSchool(School school) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoSchool.deleteSchool(con,school);
        } catch (SQLServerException e) {
            throw new DalException("cant delete school ",e);
        }
    }

    @Override
    public List<Group> getAllGroups(int schoolID) throws DalException {
        Connection con = null;
        try {
            con =  connector.getConnection();
            return daoGroup.getAllGroups(con, schoolID);
        } catch (SQLServerException e) {
          throw new DalException("cant preform this task ",e);
        }finally {
            if(con != null ){
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public Group createGroup(Group group) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoGroup.createGroup(con ,group);
        } catch (SQLServerException e) {
            throw new DalException("cant create group",e);
        }

    }

    @Override
    public void updateGroup(Group group) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoGroup.updateGroup(con ,group);
        } catch (SQLServerException e) {
            throw new DalException("cant update group ",e);
        }

    }

    @Override
    public void deleteGroup(Group group) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoGroup.deleteGroup(con ,group);
        } catch (SQLServerException e) {
            throw new DalException("cant delete group",e);
        }

    }

    @Override
    public List<User> getUsersInGroup(int id) throws DalException {
        Connection con = null;
        try {
            con = connector.getConnection();
            return daoGroup.getUsersInGroup(con,id);
        } catch (SQLServerException e) {
           throw new DalException("cant preform this task ",e);
        }finally {
            if(con != null){
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public void addUsertoGroup(Group group, User user) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoGroup.addUsertoGroup(con ,group, user);
        } catch (SQLServerException e) {
            throw new DalException("cant add user to group",e);
        }

    }

    @Override
    public void removeUserFromGroup(User user) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoGroup.removeUserFromGroup(con ,user);
        } catch (SQLServerException e) {
            throw new DalException("cant remove this user ",e);
        }


    }

    @Override
    public void assignCaseToGroup(Patient patient, Case assignedCase, Group group) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoCase.assignCaseToGroup(con ,patient,assignedCase,group);
        } catch (SQLServerException e) {
            throw new DalException("cant assign to group",e);
        }

    }

    @Override
    public void addStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException {
        daoStudentQuestion.addStudentQuestionAnswer(answer);
    }

    @Override
    public StudentQuestion getFirstStudentQuestion() throws DalException {
        var questionaireId=daoStudentQuestion.addQuestionaire();
        StudentQuestion question = daoStudentQuestion.getAllQuestions().get(0);
        question.setQuestionaireId(questionaireId);
        return question;
    }

    @Override
    public StudentQuestion getNextStudentQuestion(int id) throws DalException {
        List<StudentQuestion> questions = daoStudentQuestion.getAllQuestions();
        Optional<StudentQuestion> nextQuestion =
                questions.stream().filter(question -> question.getId() > id).min(Comparator.comparingInt(StudentQuestion::getId));
        if (nextQuestion.isPresent()) return nextQuestion.get();
        return null;
    }

    @Override
    public StudentQuestion getPreviousQuestion(int currentQuestionId) throws DalException {
        List<StudentQuestion> questions = daoStudentQuestion.getAllQuestions();
        Optional<StudentQuestion> previousQuestion =
                questions.stream().filter(question -> question.getId() < currentQuestionId).max(Comparator.comparingInt(StudentQuestion::getId));
        if (previousQuestion.isPresent()) return previousQuestion.get();
        return null;
    }

    @Override
    public StudentQuestionnaireAnswer getQuestionaireAnswer(int questionId, int questionaireId) throws DalException {
        return daoStudentQuestion.getQuestionaireAnswer(questionId,questionaireId);
    }

    @Override
    public void removeUserAndGroup(User user, Group group) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoGroup.removeUserAndGroup(con ,user , group);
        } catch (SQLServerException e) {
            throw new DalException("cant remove the user from group ",e);
        }

    }

    @Override
    public List<Case> getCasesAssignedTo(Group group) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoCase.getCasesAssignedTo(con ,group);
        } catch (SQLServerException e) {
            throw new DalException("cant get the cases ",e);
        }
    }

    @Override
    public List<User> getAllUSERS(int schoolId ,String utype) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoUser.getAllUSERS(con ,schoolId , utype);
        } catch (SQLServerException e) {
          throw new DalException("cant get all users ",e);
        }

    }

    @Override
    public List<User> getALLUsers(int schoolid, String utype) throws DalException {
        return null;
    }


    @Override
    public Group getGroupOf(User student) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoGroup.getGroupOf(con ,student);
        } catch (SQLServerException e) {
         throw new DalException("cant preform this task",e);
        }
    }

    @Override
    public StudentQuestionnaire getQuestionnaireOf(Group group) throws DalException {
        return daoStudentQuestion.getQuestionnaireOf(group);
    }

    @Override
    public List<StudentQuestion> getQuestionnaireQuestions(int questionnaireId) throws DalException {
        return daoStudentQuestion.getQuestionnaireQuestions(questionnaireId);}


    public Patient getPatientOfCase(Case selectedCase, Group group) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoPatient.getPatientOfCase(con ,selectedCase, group);
        } catch (SQLServerException e) {
         throw new  DalException("cant get the patient ",e);
        }
    }

    @Override
    public void unassignCase(Case selectedItem) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoCase.unassignCase(con ,selectedItem);
        } catch (SQLServerException e) {
            throw new DalException("cant preform this task",e);
        }

    }

    @Override
    public void markCaseAsGraded(Case selectedItem) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoCase.markCaseAsGraded(con ,selectedItem);
        } catch (SQLServerException e) {
           throw new DalException("cant preform this task ",e);
        }

    }

    @Override
    public void unmarkCaseAsGraded(Case selectedItem) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoCase.unmarkCaseAsGraded(con ,selectedItem);
        } catch (SQLServerException e) {
           throw new DalException("cant preform this task ",e);
        }

    }

    @Override
    public List<Case> getCasesGradedOf(Group group) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoCase.getCasesGradedOf(con ,group);
        } catch (SQLServerException e) {
            throw new DalException("cant preform this task",e);
        }
    }

    @Override
    public void addObservationToPatient(String text, Patient currentPatient) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoPatient.addObservation(con ,text,currentPatient);
        } catch (SQLServerException e) {
            throw new DalException("cant preform this task",e);
        }

    }

    @Override
    public StudentQuestionnaire getQuestionnaire(int questionnaireId) throws DalException {
        return  daoStudentQuestion.getQuestionnaire(questionnaireId);
    }

    @Override
    public int getSickPatientId(Patient currentPatient, Case currentCase, Group currentGroup) throws DalException {
        return daoStudentQuestion.getSickPatientId(currentPatient,currentCase,currentGroup);
    }

    @Override
    public void updateQuestionnaire(StudentQuestionnaire questionnaire) throws DalException {
        daoStudentQuestion.updateQuestionnaireSickPatient(questionnaire);
    }

    @Override
    public int getQuestionnaireOf(int caseId, int groupId) throws DalException {
       return daoStudentQuestion.getQuestionnaireOf(caseId,groupId);
    }

    @Override
    public List<Category> getAllCategories() throws DalException {
        return daoCat.getAllCategories();
    }

    @Override
    public List<SubCategory> getAllSubCategories(int catid) throws DalException {
        return daoCat.getAllSubcategories(catid);
    }

    @Override
    public List<PatientLog> logs(Patient patient) throws DalException {
        try {
            Connection con = connector.getConnection();
            return daoPatient.getLogs(con ,patient);
        } catch (SQLServerException e) {
            throw new DalException("cant preform this task",e);
        }
    }

    @Override
    public void updateLog(PatientLog patientLog, Patient patient) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoPatient.updateLog(con ,patientLog, patient);
        } catch (SQLServerException e) {
            throw new DalException("cant preform this task",e);
        }

    }

    @Override
    public void addLog(PatientLog patientLog) throws DalException {
        try {
            Connection con = connector.getConnection();
            daoPatient.addLog(con ,patientLog);
        } catch (SQLServerException e) {
            throw new DalException("cant preform this task",e);
        }
    }

}
