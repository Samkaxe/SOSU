package GUI.Models;

import BE.*;
import BLL.BLLFacade;
import BLL.BLLManager;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class StudentQuestionMOD {
    private static BLLFacade bll;
    private int questionnaireId;     //to store current questionaire Id to use in answers
    private ObservableList<Category> categories ;
    private ObservableList<SubCategory> subCategories;
    private static final StudentQuestionMOD QUESTIONSingleton = new StudentQuestionMOD();

    public StudentQuestionMOD() {
        bll = new BLLManager();
    }

    public static StudentQuestionMOD getInstance() {
        if (QUESTIONSingleton == null) {
            return new StudentQuestionMOD();
        }
        return QUESTIONSingleton;
    }

    public StudentQuestion GetFirstQuestion() {         //load first question of question collection and start
        try {
            StudentQuestion firstQuestion = bll.getFirstQuestion();     //get first question from bll
            this.questionnaireId = firstQuestion.getQuestionaireId();        //set questionaire Id
            return firstQuestion;
        } catch (DalException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveStudentQuestionAnswer(StudentQuestionnaireAnswer answer) {
        answer.setQuestionnaireId(questionnaireId);           //using questionaire Id in answers
        try {
            bll.saveStudentQuestionAnswer(answer);
        } catch (DalException e) {
            e.printStackTrace();
        }
    }

    public StudentQuestion getNextQuestion(StudentQuestion question) {
        try {
                return bll.getNextQuestion(question);       //getting next question
        } catch (DalException | BLLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public StudentQuestion getPreviousQuestion(int currentQuestionId) {
        try {
            return bll.getPreviousQuestion(currentQuestionId);
        } catch (BLLException e) {
            e.printStackTrace();
        } catch (DalException e) {
            e.printStackTrace();
        }
        return null;
    }

    public StudentQuestionnaireAnswer getAnswer(int questionId) {
        try {
            return bll.getQuestionaireAnswer(questionId, questionnaireId);
        } catch (DalException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<StudentQuestion> getQuestionnaireQuestions(){  //get all questions that exist in current questionnaire as list
        try {
            return bll.getQuestionnaireQuestions(questionnaireId);
        } catch (DalException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void setCurrentSickPatientId(Patient currentPatient, Case currentCase, Group currentGroup) {
        try {
            bll.UpdateQuestionnaire(questionnaireId,currentCase,currentPatient,currentGroup);
        } catch (DalException e) {
            e.printStackTrace();
        }
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId=questionnaireId;
    }

    public int getQuestionnaireId(int caseId, int groupId) {
        try {
            return  bll.getQuestionnaireOf(caseId,groupId);
        } catch (DalException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ObservableList<Category> categories() throws DalException{
        categories = FXCollections.observableArrayList();
        categories.addAll(bll.getAllCategories());

        return categories;
    }

    public ObservableList<SubCategory> subCategories(int catid)throws DalException{
        subCategories = FXCollections.observableArrayList();
        subCategories.addAll(bll.getAllSubCategories(catid));

        return subCategories;
    }

    public void addnewLog(PatientLog patientLog )throws DalException{
        try {
            bll.addLog(patientLog);
        } catch (DalException e) {
            throw new DalException("cant create Log at the moment ",e);
        }
    }
}
