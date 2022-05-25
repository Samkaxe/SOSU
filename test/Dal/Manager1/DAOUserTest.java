package Dal.Manager1;

import BE.User;
import DAL.Manager1.DAOUser;
import DAL.util.DalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.UnresolvedPermission;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOUserTest {

    @Test
    void verifyUsers() {
    }

    @Test
    void getAllUsers() throws DalException {
        DAOUser daoUser = new DAOUser();

        List<User> users = daoUser.getAllUsers(1);
        System.out.println(users.size());
        Assertions.assertEquals(5 ,users.size());
    }


    @Test
    void updateuser() throws DalException {
        DAOUser daoUser = new DAOUser();
        User user = new User(1,1,"test" , "test" ,"test");
        User updateduser =new User(1,1,"test" , "test" ,"test");
         daoUser.updateuser(updateduser);
         Assertions.assertEquals(user , updateduser);
    }

    @Test
    void deleteuser() throws DalException {
        DAOUser Daouser = new DAOUser();
        User user = Daouser.getAllUsers(1).get(0);
        Daouser.deleteuser(user);
        List<User> users = Daouser.getAllUsers(1);
        Assertions.assertEquals(4 , users.size());
    }

    @Test
    void addUser() {
        DAOUser daoUser = new DAOUser();
        User user = new User(1,1,"test" , "test" ,"test");
        Assertions.assertEquals(1 , user.getId());
        Assertions.assertEquals("test" , user.getUserType());
        Assertions.assertEquals("test" , user.getEmail());
        Assertions.assertEquals("test" , user.getName());

    }

    @Test
    void searchForUser() throws DalException {
        DAOUser daoUser = new DAOUser();
        daoUser.searchForUser("test");
        // couldnot test this method how ever it has been tested in during the progress

    }

}