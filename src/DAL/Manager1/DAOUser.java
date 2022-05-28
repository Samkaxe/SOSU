package DAL.Manager1;

import BE.Group;
import BE.User;
import DAL.util.DalException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser {

    public DAOUser() {

    }

    /*
    this method check if the user available in the database by his username and password
    using the like function
     */
    public User verifyUsers(Connection con ,String useremail, String password) throws DalException {
        User user = null;
        try  {
            String sql = " SELECT * FROM users where email = ? AND password = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, useremail);
            prs.setString(2, password);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("username");
                int schoolid = rs.getInt("schoolid");
                String usertype = rs.getString("usertype");
                user = new User(id, schoolid, name, useremail, usertype);
            }
            return user;

        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }
    }
    // retrieve list of users in the database
    public List<User> getAllUSERS(Connection con ,int schoolId, String utype) throws DalException {
        ArrayList<User> users = new ArrayList<>();
        try  {
            String sql = "SELECT * FROM users where schoolid = ? And usertype = ? ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, schoolId);
            statement.setString(2, utype);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("userid");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");
                int schoolid = rs.getInt("schoolId");
                User user = new User(id, schoolid, username, email, usertype);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }
        return users;
    }

        // update the select user based on his id
    public void updateuser(Connection con ,User user) throws DalException {
        try  {

            String sql = "UPDATE users SET username = ?  , email = ?  , usertype = ? , schoolid = ?  WHERE userid = ? ";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, user.getName());
            prs.setString(2, user.getEmail());
            prs.setString(3, user.getUserType());
            prs.setInt(4, user.getSchoolID());
            prs.setInt(5 , user.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }
    }
    // delete the user based on his id
    public void deleteuser(Connection con ,User user) throws DalException {
        try  {
            String sql = "DELETE FROM users WHERE userid = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, user.getId());

            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }
    }

    // adduser to the database using two select statement
    public User addUser(Connection con ,User user) throws DalException {
        try  {
            String sql = "INSERT INTO users(username , password, email , usertype , schoolid)" +
                    "VALUES  (?,?,?,?,?)";
            String sql2 = "SELECT [userid] FROM [users] WHERE [username] = ? AND [password] = ? AND [email] = ? AND [usertype] = ? AND [schoolid] = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            PreparedStatement prs2 = con.prepareStatement(sql2);
            prs.setString(1, user.getName());
            prs.setString(2, user.getName());
            prs.setString(3, user.getEmail());
            prs.setString(4, user.getUserType());
            prs.setInt(5, user.getSchoolID());
            prs.executeUpdate();

            prs2.setString(1, user.getName());
            prs2.setString(2, user.getName());
            prs2.setString(3, user.getEmail());
            prs2.setString(4, user.getUserType());
            prs2.setInt(5, user.getSchoolID());
            prs2.execute();
            ResultSet rs = prs2.getResultSet();
            while(rs.next()){
                user.setId(rs.getInt("userid"));
            }
            return user;
        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }
    }

}

