package GUI.Controllers;

import BE.School;
import BE.User;
import DAL.util.DalException;
import GUI.Alerts.ConfirmationAlert;
import GUI.Models.AdminMOD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminCTLL implements Initializable {
    @FXML
    private Label welcomeLbl;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TableView<School> schoolTalbeView;
    @FXML
    private TableColumn<School , Integer> schoolID;
    @FXML
    private TableColumn<School , String> schoolName;
    @FXML
    private TableView<User> teacherTableView;
    @FXML
    private TableColumn<User , Integer> teacherID;
    @FXML
    private TableColumn<User , String> teacherName;
    @FXML
    private TableColumn<User , String> teacherEmail;
    @FXML
    private TableView<User> StudentTableView;
    @FXML
    private TableColumn<User , Integer> studnetID;
    @FXML
    private TableColumn<User , String > studnetName;
    @FXML
    private TableColumn<User , String> studnetEmail;
    @FXML
    private TextField filterid;
    @FXML
    private ImageView filterImageid;
    @FXML
    private Button Closeid;
    @FXML
    private ContextMenu contextMenu;

    private AdminMOD adminMOD ;
    private User logedUser ;

    MenuItem addSchool = new MenuItem("Tilføj skole");
    MenuItem updateSchool = new MenuItem("opdatere skole ");
    MenuItem help = new MenuItem("Help");
    MenuItem deleteSchool = new MenuItem("Slet skole");
    MenuItem addTeacher = new MenuItem("Tilføj lærer");
    MenuItem addStudent  = new MenuItem("Tilføj elev");
    MenuItem deleteUser = new MenuItem("Slet");
    MenuItem updateTeacher = new MenuItem("opdatere lærer");
    MenuItem updateStudent= new MenuItem("opdatere elev");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("/res/felterimage.png");
        filterImageid.setImage(image);
        adminMOD = AdminMOD.getInstance();
        displaySchools();
        navigateBetweenNode();
        menuOption();
        // task();
    }

    public void displaySchools(){
        try {
            schoolTalbeView.setItems(adminMOD.getAllSchools());
            schoolID.setCellValueFactory(new PropertyValueFactory<>("id"));
            schoolName.setCellValueFactory(new PropertyValueFactory<>("name"));
            teacherID.setCellValueFactory(new PropertyValueFactory<>("id"));
            teacherName.setCellValueFactory(new PropertyValueFactory<>("name"));
            teacherEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            studnetID.setCellValueFactory(new PropertyValueFactory<>("id"));
            studnetName.setCellValueFactory(new PropertyValueFactory<>("name"));
            studnetEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            if(schoolTalbeView.getSelectionModel().getSelectedIndex() != -1){
                teacherTableView.setItems(adminMOD.getAllTeachers(schoolTalbeView.getSelectionModel().getSelectedItem().getId()));
                StudentTableView.setItems(adminMOD.getAllSudents(schoolTalbeView.getSelectionModel().getSelectedItem().getId()));
            }

        } catch (DalException e) {
           new ConfirmationAlert("couldn't retrieve data from the database ");
        }
    }

    public void task(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    schoolTalbeView.setItems(adminMOD.getAllSchools());
                } catch (DalException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void filterField(KeyEvent keyEvent)  {
        // Paint value0 = Paint.valueOf("#FE776F");
      // filterid.setStyle(value0.toString());

            try {
                if (filterid.getText() == null || filterid.getText().length() <= 0) {
                teacherTableView.setItems(adminMOD.getallusers(schoolTalbeView.getSelectionModel().getSelectedItem().getId() ,"TEACHER"));
                StudentTableView.setItems(adminMOD.getallusers(schoolTalbeView.getSelectionModel().getSelectedItem().getId() ,"STUDENT"));
                } else {
                    ObservableList<User> foundStudents;
                    foundStudents = adminMOD.searchforUser(adminMOD.getallusers(schoolTalbeView.getSelectionModel().getSelectedItem().getId() ,"STUDENT"), filterid.getText());
                    ObservableList<User> foundTeachers;
                    foundTeachers = adminMOD.searchforUser(adminMOD.getallusers(schoolTalbeView.getSelectionModel().getSelectedItem().getId() ,"TEACHER"), filterid.getText());

                    teacherTableView.setItems(foundTeachers);
                    StudentTableView.setItems(foundStudents);

                }
            } catch (DalException |NullPointerException e) {
                new ConfirmationAlert("please select school");
            }
    }

    public void createSchoolbtn(ActionEvent event) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        final javafx.scene.control.TextField name = new TextField();
        name.setPromptText("Username");
        Button button = new Button();
        button.setText("Create");
        grid.add(new Label("name:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(button, 1, 2);
        Stage stage = new Stage();
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
        button.setOnAction(event1 -> {
            try {
                adminMOD.createSchools(name.getText());
            } catch (DalException e) {
              new ConfirmationAlert("please insert valid name");
            }
        });
    }

    public void menuOption(){
        addSchool.setOnAction(event -> {
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(0, 10, 0, 10));
                final javafx.scene.control.TextField name = new TextField();
                Button button = new Button();
                button.setText("Tilføj skole");
                grid.add(new Label("name:"), 0, 0);
                grid.add(name, 1, 0);
                grid.add(button, 1, 2);
                Stage stage = new Stage();
                Scene scene = new Scene(grid);
                stage.setScene(scene);
                stage.show();
                button.setOnAction(event1 -> {
                    try {
                        adminMOD.createSchools(name.getText());
                    } catch (DalException e) {
                        new ConfirmationAlert("please insert valid name");
                    }
                });

        });

        addTeacher.setOnAction(event -> {
            iWillInsertYou("TEACHER");
        });

        addStudent.setOnAction(event -> {
            iWillInsertYou("STUDENT");
        });

        updateSchool.setOnAction(event -> {
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(0, 10, 0, 10));
            final javafx.scene.control.TextField name = new TextField();
            name.setText(schoolTalbeView.getSelectionModel().getSelectedItem().getName());
            Button button = new Button();
            button.setText("Update");
            grid.add(new Label("name:"), 0, 0);
            grid.add(name, 1, 0);
            grid.add(button, 1, 2);
            Stage stage = new Stage();
            Scene scene = new Scene(grid);
            stage.setScene(scene);
            stage.show();
            button.setOnAction(event1 -> {
                try {
                    schoolTalbeView.getSelectionModel().getSelectedItem().setName(name.getText());
                    adminMOD.updateSchool(schoolTalbeView.getSelectionModel().getSelectedItem());
                } catch (DalException e) {
                    new ConfirmationAlert("please insert valid name");
                }
            });
        });

        updateTeacher.setOnAction(event -> {
            if(teacherTableView.getSelectionModel().getSelectedIndex() != -1) {
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(0, 10, 0, 10));
                final javafx.scene.control.TextField username = new TextField();
                username.setText(teacherTableView.getSelectionModel().getSelectedItem().getName());
                final javafx.scene.control.TextField email = new TextField();
                email.setText(teacherTableView.getSelectionModel().getSelectedItem().getEmail());
                final javafx.scene.control.TextField school = new TextField();
                school.setText(String.valueOf(teacherTableView.getSelectionModel().getSelectedItem().getSchoolID()));
                Button button = new Button();
                button.setText("Update");
                grid.add(new Label("Username:"), 0, 0);
                grid.add(username, 1, 0);
                grid.add(new Label("email:"), 0, 1);
                grid.add(email, 1, 1);
                grid.add(new Label("school"), 0, 2);
                grid.add(school, 1, 2);
                grid.add(button, 2, 2);
                Stage stage = new Stage();
                Scene scene = new Scene(grid);
                stage.setScene(scene);
                stage.show();
                button.setOnAction(event1 -> {
                    int i = Integer.parseInt(String.valueOf(school.getText()));

                    try {

                        teacherTableView.getSelectionModel().getSelectedItem().setName(username.getText());
                        teacherTableView.getSelectionModel().getSelectedItem().setEmail(email.getText());
                        teacherTableView.getSelectionModel().getSelectedItem().setSchoolID(i);
                        adminMOD.updateUser(teacherTableView.getSelectionModel().getSelectedItem());
                    } catch (DalException e) {
                        new ConfirmationAlert("please enter valid data ");
                    }
                });
            }
        });

        deleteUser.setOnAction(event -> {
            if(teacherTableView.getSelectionModel().getSelectedIndex() != -1 ){
                try {
                    adminMOD.removeUser(teacherTableView.getSelectionModel().getSelectedItem());
                    teacherTableView.setItems(adminMOD.getAllTeachers(teacherTableView.getSelectionModel().getSelectedItem().getSchoolID()));
                } catch (DalException e) {
                    new ConfirmationAlert("please Select teacher");
                }
            }
           else if(StudentTableView.getSelectionModel().getSelectedIndex() != -1){
                try {
                    adminMOD.removeUser(StudentTableView.getSelectionModel().getSelectedItem());
                    StudentTableView.setItems(adminMOD.getAllSudents(StudentTableView.getSelectionModel().getSelectedItem().getSchoolID()));
                } catch (DalException e) {
                    new ConfirmationAlert("please Select Student");
                }
            }
        });

        deleteSchool.setOnAction(event -> {
            if(schoolTalbeView.getSelectionModel().getSelectedIndex() != -1){
                try {
                    adminMOD.deleteSchool(schoolTalbeView.getSelectionModel().getSelectedItem());
                } catch (DalException e) {
                    new ConfirmationAlert("please Select School");
                }
            }
        });

        help.setOnAction(event -> {
            new ConfirmationAlert("Ctrl + S : Tilføj skole \nCtrl + L : Tilføj lærer \nCtrl + E : Tilføj elev  " );
        });
    }

    public void iWillInsertYou(String text){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        final javafx.scene.control.TextField username = new TextField();
        username.setPromptText("Username");
        final javafx.scene.control.TextField email = new TextField();
        username.setPromptText("email");
        final javafx.scene.control.TextField school = new TextField();
        username.setPromptText("enter username");
        email.setPromptText("enter email");
        username.setPromptText("enter schoolID");
        Button button = new Button();
        button.setText("Create");
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("email:"), 0, 1);
        grid.add(email , 1 , 1);
        grid.add(new Label("school") ,0 ,2);
        grid.add(school,1,2);
        grid.add(button, 2, 2);
        Stage stage = new Stage();
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
        button.setOnAction(event1 -> {
            int i = Integer.parseInt(String.valueOf(school.getText()));

            try {
                adminMOD.createUser(new User(1,i ,username.getText(),email.getText(),text));
            } catch (DalException e) {
                new ConfirmationAlert("please enter valid data ");
            }
        });
    }

    public void createTeacherBtn(ActionEvent event) {
        iWillInsertYou("TEACHER");
    }

    public void createStudentBTN(ActionEvent event) {
        iWillInsertYou("STUDENT");
    }

    public void deleteSchoolbtn(ActionEvent event) {
        try {
            if(schoolTalbeView.getSelectionModel().getSelectedIndex() != -1)
            adminMOD.deleteSchool(schoolTalbeView.getSelectionModel().getSelectedItem());
        } catch (DalException e) {
            new ConfirmationAlert("please select school ");
        }
    }

    public void deleteTeacherBtn(ActionEvent event) {
            try {
                if(teacherTableView.getSelectionModel().getSelectedIndex() != -1 ) {
                    adminMOD.removeUser(teacherTableView.getSelectionModel().getSelectedItem());
                   teacherTableView.setItems(adminMOD.getAllTeachers(teacherTableView.getSelectionModel().getSelectedItem().getSchoolID()));
                }
            } catch (DalException e) {
               new ConfirmationAlert("please select a Teacher ");
            }

    }

    public void deleteStudentbtn(ActionEvent event) {
        try {
            if(StudentTableView.getSelectionModel().getSelectedIndex() != -1 ) {
                adminMOD.removeUser(StudentTableView.getSelectionModel().getSelectedItem());
                StudentTableView.setItems(adminMOD.getAllSudents(StudentTableView.getSelectionModel().getSelectedItem().getSchoolID()));
            }
        } catch (DalException e) {
            new ConfirmationAlert("please select a Student ");
        }
    }

    public void ClloseBtn(ActionEvent event) {
        Stage st = (Stage) Closeid.getScene().getWindow();
        st.close();
    }

    public void setUser(User logedUser) {
        this.logedUser = logedUser;
        welcomeLbl.setText("Welcome Back "+logedUser.getName());
        welcomeLbl.setEffect(new DropShadow(2.0, Color.BLACK));
        welcomeLbl.setTextFill(Color.RED);
        welcomeLbl.setFont(new Font("serif" ,20));
    }

    public void exitmenu(MouseEvent mouseEvent) {
        contextMenu.hide();
    }

    public void displayusersinschool(MouseEvent mouseEvent) {
        if(schoolTalbeView.getSelectionModel().getSelectedIndex() != -1){
            try {
                teacherTableView.setItems(adminMOD.getAllTeachers(schoolTalbeView.getSelectionModel().getSelectedItem().getId()));
                StudentTableView.setItems(adminMOD.getAllSudents(schoolTalbeView.getSelectionModel().getSelectedItem().getId()));
            } catch (DalException e) {
                new ConfirmationAlert("No Users Found");
            }
        }
    }

    public void navigateBetweenNode() {
        anchorpane.setOnContextMenuRequested(event -> {
            if (event.getTarget().getClass() == AnchorPane.class) {
                changeOptionAnchorPane();
            } else {
                String tableid = "";
                if (((Node) event.getTarget()).getParent() != null) {
                    if (((Node) event.getTarget()).getParent().getClass() == javafx.scene.control.TableView.class) {
                        TableView tableRow = (TableView) ((Node) event.getTarget()).getParent();
                        tableid = tableRow.getId();
                    }
                    if (((Node) event.getTarget()).getParent().getClass() == javafx.scene.control.TableRow.class) {
                        TableRow tableRow = (TableRow) ((Node) event.getTarget()).getParent();
                        tableid = tableRow.getTableView().getId();
                    }
                    if (((Node) event.getTarget()).getParent().getClass() == javafx.scene.control.skin.TableColumnHeader.class) {
                        TableColumnHeader tableHeader = (TableColumnHeader) ((Node) event.getTarget()).getParent();
                        tableid = tableHeader.getParent().getParent().getParent().getId();
                    }
                    if (((Node) event.getTarget()).getParent().getParent() != null) {
                        if (((Node) event.getTarget()).getParent().getParent().getClass() == javafx.scene.control.TableRow.class) {
                            TableRow tableRow = (TableRow) ((Node) event.getTarget()).getParent().getParent();
                            tableid = tableRow.getTableView().getId();
                        }
                        if (((Node) event.getTarget()).getParent().getParent().getClass() == javafx.scene.control.skin.TableColumnHeader.class) {
                            TableColumnHeader tableHeader = (TableColumnHeader) ((Node) event.getTarget()).getParent().getParent();
                            tableid = tableHeader.getParent().getParent().getParent().getId();
                        }
                    }
                }
                if (tableid.contains("schoolNodeID")) {
                    schools();
                } else if (tableid.contains("teacherNodeID")) {
                    teachers();
                } else if (tableid.contains("studentNodeID")) {
                    students();
                }
            }
            contextMenu.show(anchorpane, event.getScreenX(), event.getScreenY());

        });
    }

    private void schools() {
       contextMenu.getItems().clear();
       contextMenu.getItems().addAll(addSchool,deleteSchool , updateSchool);
    }

    private void teachers() {
        contextMenu.getItems().clear();
        contextMenu.getItems().addAll(addTeacher, updateTeacher ,  deleteUser);
    }

    private void changeOptionAnchorPane() {
        contextMenu.getItems().clear();
        contextMenu.getItems().addAll(help);
    }

    private void students() {
       contextMenu.getItems().clear();
       contextMenu.getItems().addAll(addStudent ,updateStudent ,  deleteUser);
    }

}
