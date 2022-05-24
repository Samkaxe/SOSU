package DAL.Manager1;

import BE.*;
import DAL.DataAccess.JDBCConnectionPool;
import DAL.util.DalException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOStudentQuestion {
    private final JDBCConnectionPool dataAccess;
    private Connection con ;
    public DAOStudentQuestion() {
        dataAccess = new JDBCConnectionPool();
    }

    public void addStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException {  //to save question answer in answer table
        try  {
            con = dataAccess.getConnection();
            String sql = "insert  into [dbo].[StudentQuestionAnswer] (questionId,state,questionaireId )values  (?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, answer.getQuestionId());
            prs.setInt(2, answer.getState());
            prs.setInt(3, answer.getQuestionnaireId());
            prs.execute();

        } catch (SQLException e) {
            throw new DalException("Couldn't pass this Answer please try again later   ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
    }

    public int addQuestionaire() throws DalException {              //to create new questionnaire and save it in database
        try  {
            con = dataAccess.getConnection();
            String sql = "insert  into [dbo].[Questionaire] (date )values  (getdate())";  //id is identity and will be generated in database and we save only current date
            PreparedStatement prs = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  // RETURN_GENERATED_KEYS is for returning generated questionnaire id
            prs.execute();
            ResultSet rs = prs.getGeneratedKeys();  //get generated id
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new DalException("Couldn't add Questionaire ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
    }

    public List<StudentQuestion> getAllQuestions() throws DalException {
        List<StudentQuestion> studentQuestions = new ArrayList<>();
        try {
            con = dataAccess.getConnection();
            String sql = "Select * from StudentQuestion ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                studentQuestions.add(new StudentQuestion(
                        rs.getInt("id"),
                        rs.getString("category"),
                        rs.getString("title"),
                        rs.getString("question"),
                        rs.getString("color")));
            }
            return studentQuestions;
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of Questions ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
    }

    public StudentQuestionnaireAnswer getQuestionaireAnswer(int questionId, int questionaireId) throws DalException {

        try {
            con = dataAccess.getConnection();
            String sql = " Select * from StudentQuestionAnswer  where questionId=? and QuestionaireId=? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1,questionId);
            prs.setInt(2,questionaireId);
            StudentQuestionnaireAnswer answer = new StudentQuestionnaireAnswer();
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {         //because there is only one row we can return after first row fetched
                answer.setId(rs.getInt("id"));

                answer.setQuestionId(rs.getInt("questionId"));
                answer.setState(rs.getInt("state"));
                answer.setQuestionnaireId(rs.getInt("QuestionaireId"));

            }
            return answer;
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of Questions ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
    }

    public StudentQuestionnaire getQuestionnaireOf(Group group) throws DalException {
        String query="\n" +
                "select q.* from Groups g \n" +
                "join GroupUsers gu on g.id=gu.Groupid\n" +
                "join SickPatient sp on sp.Groupid=gu.id\n" +
                "join Questionaire q on q.SickPatientId=sp.SickPatientid"+
                " where g.id=?";

        try {
            con = dataAccess.getConnection();
            PreparedStatement prs = con.prepareStatement(query);
            prs.setInt(1,group.getId());
            StudentQuestionnaire questionnaire = new StudentQuestionnaire();
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {         //because there is only one row we can return after first row fetched
               questionnaire.setId(rs.getInt("id"));
               questionnaire.setDate(rs.getDate("Date"));
               questionnaire.setSickPatientId(rs.getInt("SickPatientId"));

            }
            return questionnaire;
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of Questions ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
    }

    public List<StudentQuestion> getQuestionnaireQuestions(int questionnaireId) throws DalException { //get all questions that exist in specific questionnaire
        List<StudentQuestion> studentQuestions=new ArrayList<>();
        String query="\n" +
                "  select q.category,q.title,q.question,q.color,qa.id,qa.questionId,qa.state,qa.QuestionaireId from StudentQuestion q\n" +
                "  join StudentQuestionAnswer qa on q.id=qa.questionId\n" +
                "  where qa.QuestionaireId=?";
        try  {
            con = dataAccess.getConnection();
            PreparedStatement prs = con.prepareStatement(query);
            prs.setInt(1,questionnaireId);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                StudentQuestion question=new StudentQuestion();
                question.setId(rs.getInt("questionId"));
                question.setCategory(rs.getString("category"));
                question.setTitle(rs.getString("title"));
                question.setQuestion(rs.getString("question"));
                question.setColor(rs.getString("color"));
                StudentQuestionnaireAnswer answer=new StudentQuestionnaireAnswer();
                answer.setId(rs.getInt("id"));
                answer.setQuestionnaireId(rs.getInt("QuestionaireId"));
                answer.setState(rs.getInt("state"));
                question.setAnswer(answer);
                studentQuestions.add(question);

            }
            return studentQuestions;
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of Questions ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
    }

    public StudentQuestionnaire getQuestionnaire(int questionnaireId) throws DalException {
        String query="select * from [Questionaire] where id=?";
        try {
            con = dataAccess.getConnection();
            PreparedStatement prs = con.prepareStatement(query);
            prs.setInt(1,questionnaireId);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                return new StudentQuestionnaire(rs.getInt("id"),rs.getDate("Date"),rs.getInt("SickPatientId"));


            }
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of Questions ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
        return null;
    }

    public int getSickPatientId(Patient currentPatient, Case currentCase, Group currentGroup) throws DalException {
        String query="select SickPatientid from SickPatient where patientid=? and caseid=? and Groupid=?";
        try  {
            con = dataAccess.getConnection();
            PreparedStatement prs = con.prepareStatement(query);
            prs.setInt(1,currentPatient.getId());
            prs.setInt(2,currentCase.getId());
            prs.setInt(3,currentGroup.getId());
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                return rs.getInt("SickPatientid");


            }
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of Questions ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
        return -1;
    }

    public void updateQuestionnaireSickPatient(StudentQuestionnaire questionnaire) throws DalException {
        String query="update Questionaire set SickPatientId=? where id=?";
        try {
            con = dataAccess.getConnection();
            PreparedStatement prs = con.prepareStatement(query);
            prs.setInt(1,questionnaire.getSickPatientId());
            prs.setInt(2,questionnaire.getId());
            prs.executeUpdate();


        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of Questions ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
    }

    public int getQuestionnaireOf(int caseId, int groupId) throws DalException {
        String query="select max(id) as qId\n" +
                "                 from SickPatient sp\n" +
                "                join Questionaire q on q.SickPatientId=sp.SickPatientid\n" +
                "                 where sp.Groupid=? and sp.caseid=?";

        try {
            con = dataAccess.getConnection();
            PreparedStatement prs = con.prepareStatement(query);
            prs.setInt(1,groupId);
            prs.setInt(2,caseId);

            ResultSet rs = prs.executeQuery();
            while (rs.next()) {         //because there is only one row we can return after first row fetched
                return rs.getInt("qId");  //get last questionnaire id

            }
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of Questions ", e);
        }finally {
            if(con != null) {
                dataAccess.releaseConnection(con);
            }
        }
        return 0;
    }
}
