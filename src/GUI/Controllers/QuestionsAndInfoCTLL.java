package GUI.Controllers;

import BE.*;
import DAL.util.DalException;
import GUI.Alerts.ConfirmationAlert;
import GUI.Models.StudentQuestionMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionsAndInfoCTLL implements Initializable {
    @FXML
    private TableColumn<Category , String> categoryColumn;
    @FXML
    private TableColumn<SubCategory , String> subCategoryColumn;
    @FXML
    private TableView<Category> catTable;
    @FXML
    private TableView<SubCategory> subCatTable;
    @FXML
    private RadioButton state1;
    @FXML
    private RadioButton state2;
    @FXML
    private RadioButton state3;
    @FXML
    private RadioButton state4;
    @FXML
    private RadioButton state5;
    @FXML
    private RadioButton state6;
    @FXML
    private TextField mestring;
    @FXML
    private TextField motivation;
    @FXML
    private TextField ressourcer;
    @FXML
    private TextField roller;
    @FXML
    private TextField vaner;
    @FXML
    private TextField uddannelse;
    @FXML
    private TextField livshistorie;
    @FXML
    private TextField helbreds;
    @FXML
    private TextField hjælpe;
    @FXML
    private TextField binretning;
    @FXML
    private TextField netverk;

    private StudentQuestionMOD studentQuestionMOD;
    private Group currentGroup;
    private Patient currentPatient;
    private Case currentCase;
    StudentQuestion currentQuestion;
    private User currentStudent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       studentQuestionMOD = StudentQuestionMOD.getInstance();
       displayCategories();

    }

    public void displayCategories(){
        try {
            catTable.setItems(studentQuestionMOD.categories());
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            subCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        } catch (DalException e) {
          new ConfirmationAlert("please select valid Category");
        }

    }


    public void displayInfoInCat(MouseEvent mouseEvent) {
        if(catTable.getSelectionModel().getSelectedIndex() != -1 ) {

            try {
                subCatTable.setItems(studentQuestionMOD.subCategories(catTable.getSelectionModel().getSelectedItem().getId()));
            } catch (DalException e) {
                new ConfirmationAlert("please select valid Category");
            }
        }
    }

    public void addnewLogbtn(ActionEvent event) {
       PatientLog patientLog = new PatientLog(1 ,mestring.getText(),motivation.getText(),
               ressourcer.getText(), roller.getText() , vaner.getText() , uddannelse.getText() ,
               livshistorie.getText(),netverk.getText() ,helbreds.getText() , hjælpe.getText() , binretning.getText(),currentPatient.getId());
        try {
            studentQuestionMOD.addnewLog(patientLog);
        } catch (DalException e) {
           new ConfirmationAlert("unable to add new Log");
        }
    }

    public void setGroup(Group currentGroup) {
        this.currentGroup = currentGroup;
    }

    public void setPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
        setModelSickPatient(currentPatient, currentCase, currentGroup);
    }

    private void setModelSickPatient(Patient currentPatient, Case currentCase, Group currentGroup) {
        if (currentCase == null || currentGroup == null || currentPatient == null)
            return;
        studentQuestionMOD.setCurrentSickPatientId(currentPatient, currentCase, currentGroup);
    }

    public void setCase(Case currentCase) {
        this.currentCase = currentCase;
    }

    public void setUser(User currentStudent) {
        this.currentStudent = currentStudent;
    }
}
