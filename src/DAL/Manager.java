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
    private final JDBCConnectionPool connector ;

    public Manager() {
        connector = new JDBCConnectionPool();
        daoCase = new DAOCase();
        daoUser = new DAOUser();
        daoPatient = new DAOPatient();
        daoSchool = new DAOSchool();
        daoGroup = new DAOGroup();
        daoStudentQuestion = new DAOStudentQuestion();

    }


    @Override
    public List<Case> getAllCases(int schoolid) throws DalException {
        Connection con = null ;
        try {
          con = connector.getConnection();
            return daoCase.getAllCases(con,schoolid);
        } catch (SQLServerException e) {
            LOGGER.error("Cant create the case ",e);
           throw new DalException("Cant get All cases",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public Case createCase(Case c) throws DalException {
        Connection con = null ;
        try {
            con = connector.getConnection();
            return daoCase.createCase(con ,c);
        } catch (SQLServerException e) {
            LOGGER.error("cant create the case " ,e);
            throw new DalException("cant create the case ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void updateCase(Case c) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            daoCase.updateCase(con ,c);
        } catch (SQLServerException e) {
            LOGGER.error("cant update the case ",e);
            throw new DalException("cant update the case ",e);
        } finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public void deleteCase(Case c) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            daoCase.deleteCase(con ,c);
        } catch (SQLServerException e) {
            LOGGER.error("cant delete this case ",e);
            throw new DalException("cant delete case ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public User verifyUsers(String useremail, String password) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            return daoUser.verifyUsers(con,useremail, password);
        } catch (SQLServerException e) {
            LOGGER.error("cant verify user " , e);
            throw new DalException("cant verfiy the user",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public List<User> getAllUsers(int schoolid) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            return daoUser.getAllUsers(con ,schoolid);
        } catch (SQLServerException e) {
            LOGGER.error("cant get the users ",e);
            throw new DalException("cant get the users ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void updateuser(User user ) throws DalException {
      Connection con = null ;
        try {
             con = connector.getConnection();
            daoUser.updateuser(con ,user);
        } catch (SQLServerException e) {
            LOGGER.error("cant update the user  ",e);
            throw new DalException("cant update the user ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void deleteuser(User user) throws DalException {
      Connection con = null ;
        try {
             con = connector.getConnection();
            daoUser.deleteuser( con ,user);
        } catch (SQLServerException e) {
            LOGGER.error("cant delete the user ",e);
            throw new DalException("cant delete the user ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public User addUser(User user) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            return daoUser.addUser(con ,user);
        } catch (SQLServerException e) {
            LOGGER.error("cant add the user ",e);
            throw new DalException("cant add the user",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public List<User> searchForUser(String query) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            return daoUser.searchForUser(con,query);
        } catch (SQLServerException e) {
            LOGGER.error("cant serach for user ",e);
            throw new DalException("cant search for user ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public List<Patient> getAllPatients(int schoolid) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            return daoPatient.getAllPatients(con ,schoolid);
        } catch (SQLServerException e) {
            LOGGER.error("cant get list of patients ",e);
            throw new DalException("cant get list of patients ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public Patient createPatient(Patient patient) throws DalException {
        Connection con = null ;
        try {
               con = connector.getConnection();
            return daoPatient.createPatient(con ,patient);
        } catch (SQLServerException e) {
            LOGGER.error("cant create this patient  ",e);
            throw new DalException("cant create this patient ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void updatePatient(Patient patient) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            daoPatient.updatepatient(con ,patient);
        } catch (SQLServerException e) {
            LOGGER.error("cant update the patient  ",e);
            throw new DalException("cant upadte the patient ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void deletePatient(Patient patient) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            daoPatient.deletePatient(con,patient);
        } catch (SQLServerException e) {
            LOGGER.error("cant delete the pateint  ",e);
            throw new DalException("cant delete the patient ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public List<School> getAllSchhol() throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            return daoSchool.getAllSchhol(con);
        } catch (SQLServerException e) {
            LOGGER.error("cant get the schhol",e);
            throw new DalException("cant get the schools ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void createSchool(School school) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            daoSchool.createSchool(con,school);
        } catch (SQLServerException e) {
            LOGGER.error("cant create school",e);
            throw new DalException("cant create school ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void updateSchool(School school) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            daoSchool.updateSchool(con,school);
        } catch (SQLServerException e) {
            LOGGER.error("cant cant update school",e);
            throw new DalException("cant update school ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void deleteSchool(School school) throws DalException {
       Connection con = null;
        try {
             con = connector.getConnection();
            daoSchool.deleteSchool(con,school);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant delete school ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public List<Group> getAllGroups(int schoolID) throws DalException {
        Connection con = null;
        try {
            con =  connector.getConnection();
            return daoGroup.getAllGroups(con, schoolID);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
          throw new DalException("cant preform this task ",e);
        }finally {
            if(con != null ){
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public Group createGroup(Group group) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            return daoGroup.createGroup(con ,group);
        } catch (SQLServerException e) {
            LOGGER.error("cant create group",e);
            throw new DalException("cant create group",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void updateGroup(Group group) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            daoGroup.updateGroup(con ,group);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant update group ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void deleteGroup(Group group) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            daoGroup.deleteGroup(con ,group);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant delete group",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public List<User> getUsersInGroup(int id) throws DalException {
        Connection con = null;
        try {
            con = connector.getConnection();
            return daoGroup.getUsersInGroup(con,id);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
           throw new DalException("cant preform this task ",e);
        }finally {
            if(con != null){
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public void addUsertoGroup(Group group, User user) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            daoGroup.addUsertoGroup(con ,group, user);
        } catch (SQLServerException e) {
            LOGGER.error("cant add user to group",e);
            throw new DalException("cant add user to group",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void removeUserFromGroup(User user) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            daoGroup.removeUserFromGroup(con ,user);
        } catch (SQLServerException e) {
            LOGGER.error("cant remove this user",e);
            throw new DalException("cant remove this user ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }


    }

    @Override
    public void assignCaseToGroup(Patient patient, Case assignedCase, Group group) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            daoCase.assignCaseToGroup(con ,patient,assignedCase,group);
        } catch (SQLServerException e) {
            LOGGER.error("cant assign to group ",e);
            throw new DalException("cant assign to group",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void addStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException {
        Connection con = null ;
        try {
            con = connector.getConnection();
            daoStudentQuestion.addStudentQuestionAnswer(con ,answer);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant remove the user from group ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public StudentQuestion getFirstStudentQuestion() throws DalException {
        Connection con = null ;

        try {
            con = connector.getConnection();
            var questionaireId=daoStudentQuestion.addQuestionaire(con);
            StudentQuestion question = daoStudentQuestion.getAllQuestions(con).get(0);
            question.setQuestionaireId(questionaireId);
            return question;
        }  catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant remove the user from group ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public StudentQuestion getNextStudentQuestion(int id) throws DalException {
        Connection con = null ;
        try {
            con = connector.getConnection();
            List<StudentQuestion> questions = daoStudentQuestion.getAllQuestions(con);
            Optional<StudentQuestion> nextQuestion =
                    questions.stream().filter(question -> question.getId() > id).min(Comparator.comparingInt(StudentQuestion::getId));
            if (nextQuestion.isPresent()) return nextQuestion.get();
            return null;
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant remove the user from group ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public StudentQuestion getPreviousQuestion(int currentQuestionId) throws DalException {
        Connection con = null ;

        try {
            con = connector.getConnection();
            List<StudentQuestion> questions = daoStudentQuestion.getAllQuestions(con);
            Optional<StudentQuestion> previousQuestion =
                    questions.stream().filter(question -> question.getId() < currentQuestionId).max(Comparator.comparingInt(StudentQuestion::getId));
            if (previousQuestion.isPresent()) return previousQuestion.get();
            return null;
        }  catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant remove the user from group ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public StudentQuestionnaireAnswer getQuestionaireAnswer(int questionId, int questionaireId) throws DalException {
        Connection con = null ;
        try {
            con = connector.getConnection();
            return daoStudentQuestion.getQuestionaireAnswer(con ,questionId,questionaireId);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant remove the user from group ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public void removeUserAndGroup(User user, Group group) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            daoGroup.removeUserAndGroup(con ,user , group);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant remove the user from group ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public List<Case> getCasesAssignedTo(Group group) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            return daoCase.getCasesAssignedTo(con ,group);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant get the cases ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public List<User> getAllUSERS(int schoolId ,String utype) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            return daoUser.getAllUSERS(con ,schoolId , utype);
        } catch (SQLServerException e) {
            LOGGER.error("cant get all users ",e);
          throw new DalException("cant get all users ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public List<User> getALLUsers(int schoolid, String utype) throws DalException {
        return null;
    }


    @Override
    public Group getGroupOf(User student) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            return daoGroup.getGroupOf(con ,student);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
         throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public StudentQuestionnaire getQuestionnaireOf(Group group) throws DalException {
        Connection con = null ;

        try {
            con = connector.getConnection();
            return daoStudentQuestion.getQuestionnaireOf(con ,group);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public List<StudentQuestion> getQuestionnaireQuestions(int questionnaireId) throws DalException {
        Connection con = null ;
        try {
            con = connector.getConnection();
            return daoStudentQuestion.getQuestionnaireQuestions(con,questionnaireId);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }


    public Patient getPatientOfCase(Case selectedCase, Group group) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            return daoPatient.getPatientOfCase(con ,selectedCase, group);
        } catch (SQLServerException e) {
            LOGGER.error("cant get the pateint ",e);
         throw new  DalException("cant get the patient ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public void unassignCase(Case selectedItem) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            daoCase.unassignCase(con ,selectedItem);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void markCaseAsGraded(Case selectedItem) throws DalException {
        Connection con = null;
        try {
             con = connector.getConnection();
            daoCase.markCaseAsGraded(con ,selectedItem);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
           throw new DalException("cant preform this task ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public void unmarkCaseAsGraded(Case selectedItem) throws DalException {
        Connection con = null ;
        try {
             con = connector.getConnection();
            daoCase.unmarkCaseAsGraded(con ,selectedItem);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
           throw new DalException("cant preform this task ",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public List<Case> getCasesGradedOf(Group group) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            return daoCase.getCasesGradedOf(con ,group);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public void addObservationToPatient(String text, Patient currentPatient) throws DalException {
       Connection con = null ;
        try {
             con = connector.getConnection();
            daoPatient.addObservation(con ,text,currentPatient);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public StudentQuestionnaire getQuestionnaire(int questionnaireId) throws DalException {
        Connection con = null ;
        try {
            con = connector.getConnection();
            return  daoStudentQuestion.getQuestionnaire(con , questionnaireId);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }

    }

    @Override
    public int getSickPatientId(Patient currentPatient, Case currentCase, Group currentGroup) throws DalException {
        Connection con = null ;
        try {
            con = connector.getConnection();
            return daoStudentQuestion.getSickPatientId(con ,currentPatient,currentCase,currentGroup);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public void updateQuestionnaire(StudentQuestionnaire questionnaire) throws DalException {
        Connection con = null ;
        try {
            con = connector.getConnection();
            daoStudentQuestion.updateQuestionnaireSickPatient(con ,questionnaire);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

    @Override
    public int getQuestionnaireOf(int caseId, int groupId) throws DalException {
        Connection con = null;
        try {
            con = connector.getConnection();
            return daoStudentQuestion.getQuestionnaireOf(con, caseId, groupId);
        } catch (SQLServerException e) {
            LOGGER.error("cant preform this task",e);
            throw new DalException("cant preform this task",e);
        }finally {
            if(con != null) {
                connector.releaseConnection(con);
            }
        }
    }

}
