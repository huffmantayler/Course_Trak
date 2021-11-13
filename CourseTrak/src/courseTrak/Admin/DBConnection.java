/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 *
 * @author markduah, Alex Reep, taylorhuffman,
 */
public class DBConnection {

    private final String dbName = "course-trakdb";
    private final String dbUrl = "jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak";
    private final String dbUser = "admin";
    private final String dbPassword = "admin111";
    Connection connection = null;
    PreparedStatement insertStatement = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement checkStatement = null;
    ResultSet resultSet = null;

    private int userID;
    private String firstName = "";
    UserSession us = UserSession.getInstance();

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                "admin", "admin111");
        return con;
    }

    public int getUserID() {
        return userID;
    }

    public boolean adminLogin(ActionEvent event, String userName, String Password) {
        boolean userExist = false;
        PassHash passHash = new PassHash();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            preparedStatement = connection.prepareStatement("SELECT AdminID, firstName, UserName, Password FROM Admins where UserName = ?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("Password");
                userID = resultSet.getInt("AdminID");
                firstName = resultSet.getString("firstName");
                System.out.println(password);
                boolean hashedPassword = passHash.checkPassword(Password, password);
                if (hashedPassword) {
                    System.out.println("Password Matched");
                    userExist = true;
                } else {
                    System.out.println("Wrong password");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Login");
                    alert.setHeaderText("Password Verifcation");
                    alert.setContentText("Wrong password, please try again");
                    alert.showAndWait();
                }
                us.setUserID(userID);
                us.setUserName(userName);
                us.setFirstName(firstName);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExist;
    }

    public boolean studentLogin(ActionEvent event, String userName, String Password) {
        boolean userExist = false;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            preparedStatement = connection.prepareStatement("SELECT StudentID, firstName, userName, Password FROM Students where userName = ?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int StudentID = resultSet.getInt("StudentID");
                String username = resultSet.getString("userName");
                String password = resultSet.getString("Password");
                firstName = resultSet.getString("firstName");
                if (username.equals(userName) && password.equals(Password)) {
                    userExist = true;
                }
                us.setUserID(StudentID);
                us.setUserName(username);
                us.setFirstName(firstName);
            }
            //System.out.println("Admin account created successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExist;
    }

    public boolean teacherLogin(ActionEvent event, String userName, String Password) {
        boolean userExist = false;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            preparedStatement = connection.prepareStatement("SELECT TeacherID, firstName, userName, Password FROM Teachers where userName = ?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int TeacherID = resultSet.getInt("TeacherID");
                String username = resultSet.getString("userName");
                String password = resultSet.getString("Password");
                firstName = resultSet.getString("firstName");
                if (username.equals(userName) && password.equals(Password)) {
                    userExist = true;
                }
                us.setUserID(TeacherID);
                us.setUserName(username);
                us.setFirstName(firstName);
            }
            //System.out.println("Admin account created successfully");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExist;
    }

    /**
     *
     * @param event
     * @param firstName
     * @param lastName
     */
    public void adminSignUp(ActionEvent event, String userName, String Password, String firstName, String lastName) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            insertStatement = connection.prepareStatement("INSERT INTO Admins (UserName, Password, firstName, lastName) VALUES (?, ?, ?, ?)");
            insertStatement.setString(1, userName);
            insertStatement.setString(2, Password);
            insertStatement.setString(3, firstName);
            insertStatement.setString(4, lastName);
            insertStatement.executeUpdate();
            System.out.println("Admin account created successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void studentSignUp(ActionEvent event, String userName, String Password, String firstName, String lastName) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            insertStatement = connection.prepareStatement("INSERT INTO Students (UserName, Password, firstName, lastName) VALUES (?, ?, ?, ?)");
            insertStatement.setString(1, userName);
            insertStatement.setString(2, Password);
            insertStatement.setString(3, firstName);
            insertStatement.setString(4, lastName);
            insertStatement.executeUpdate();
            System.out.println("Student account created successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerStudent(ActionEvent event, String userName, String Password, String firstName, String lastName, String level) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            insertStatement = connection.prepareStatement("INSERT INTO Students (UserName, Password, firstName, lastName, GradeLevel) VALUES (?, ?, ?, ?, ?)");
            insertStatement.setString(1, userName);
            insertStatement.setString(2, Password);
            insertStatement.setString(3, firstName);
            insertStatement.setString(4, lastName);
            insertStatement.setString(5, level);
            insertStatement.executeUpdate();
            System.out.println("Student registered successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClass(String name, int credit, String description, String type) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            insertStatement = connection.prepareStatement("INSERT INTO Classes (name, credit, description, type) VALUES (?, ?, ?, ?)");
            insertStatement.setString(1, name);
//            insertStatement.setInt(1, classNum);
            insertStatement.setInt(2, credit);
            insertStatement.setString(3, description);
            insertStatement.setString(4, type);
            insertStatement.executeUpdate();
            System.out.println("Class Added successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTeacher(ActionEvent event, String userName, String Password, String firstName, String lastName, String room, String subject) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            insertStatement = connection.prepareStatement("INSERT INTO Teachers(UserName, Password, firstName, lastName, roomNumber, Subject) VALUES (?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, userName);
            insertStatement.setString(2, Password);
            insertStatement.setString(3, firstName);
            insertStatement.setString(4, lastName);
            insertStatement.setString(5, room);
            insertStatement.setString(6, subject);
            insertStatement.executeUpdate();
            System.out.println("Student registered sucessfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadDocument(int ID, String filePath, String description, String data) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                "admin", "admin111");
        System.out.println("Success");
        insertStatement = connection.prepareStatement("INSERT INTO Documents (OwnerID, Document, Description, Date) VALUES (?, ?, ?, ?)");
        insertStatement.setInt(1, ID);
        insertStatement.setString(2, filePath);
        insertStatement.setString(3, description);
        insertStatement.setString(4, data);
        insertStatement.executeUpdate();
        System.out.println("Document uploded successfully");
        connection.close();
    }

    /**
     *
     * @param ID
     * @param firstName
     * @param lastName
     * @param gradeLevel
     * @param counselor
     * @param dob
     * @param sex
     * @param contact
     * @param contactNumber
     * @param address
     * @throws java.sql.SQLException
     */
    public void updateStudentData(int ID, String firstName, String lastName, String gradeLevel, String counselor,
            String dob, String sex, String contact, String contactNumber, String street, String city, String state, String zipCode) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            preparedStatement = connection.prepareStatement("UPDATE Students SET firstName = ?, lastName = ?, DOB = ?, SEX = ?, GradeLevel = ?, Counselor = ?, EMCName = ?, EMCContact = ?, Street = ?, City = ?, State = ?, Zip = ? WHERE StudentID = ?");
            System.out.println("Success");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, dob);
            preparedStatement.setString(4, sex);
            preparedStatement.setString(5, gradeLevel);
            preparedStatement.setString(6, counselor);
            preparedStatement.setString(7, contact);
            preparedStatement.setString(8, contactNumber);
            preparedStatement.setString(9, street);
            preparedStatement.setString(10, city);
            preparedStatement.setString(11, state);
            preparedStatement.setString(12, zipCode);
            preparedStatement.setInt(13, ID);
            preparedStatement.executeUpdate();
            System.out.println("Student data updated successfully");
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] getClasses(int ID) {
        String classes = "";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://admin@course-trakdb.c7gc6mentdoj.us-east-2.rds.amazonaws.com:3306/Course_Trak",
                    "admin", "admin111");
            System.out.println("Success");
            String userName = "mduah";
            preparedStatement = connection.prepareStatement("SELECT cs.ClassNum, Name, Semester FROM ClassesStudents cs INNER JOIN Classes c ON cs.ClassNum = c.ClassNum WHERE StudentID = '" + ID + "'");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                classes += resultSet.getString("Name") + "|";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String classesArray[] = classes.split("\\|", -1);
        return classesArray;
    }


}
