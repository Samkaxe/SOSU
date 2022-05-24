package DAL.Manager1;

import BE.Category;
import BE.SubCategory;
import DAL.DataAccess.JDBCConnectionPool;
import DAL.util.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaocategoryAndSubCategory {

    private final JDBCConnectionPool dataAccess;
    private Connection con = null;

    public DaocategoryAndSubCategory() {
        dataAccess = new JDBCConnectionPool();
    }

    public List<Category> getAllCategories() throws DalException{
        List<Category> categories = new ArrayList<>();

        try {
            con = dataAccess.getConnection();
            String sql = "select * from Category";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("categoryid");
                String name = rs.getString("name");
                Category category = new Category(id , name );
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
           throw new DalException("Couldn't retrieve list of categories ", e);
        }finally {
            if(con != null){
                dataAccess.releaseConnection(con);
            }
        }
    }

    public List<SubCategory> getAllSubcategories( int catid) throws DalException{
        List<SubCategory> subCategories = new ArrayList<>();

        try {
            con = dataAccess.getConnection();
            String sql = "select * from subcategory where CategoryFid = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , catid);
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while(rs.next()){
                int id = rs.getInt("subcategoryID");
                String name = rs.getString("issue");
                SubCategory subCategory = new SubCategory(id , name );
                subCategories.add(subCategory);
            }
            return subCategories;
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve list of Subcategories ", e);
        }finally {
            if(con != null){
                dataAccess.releaseConnection(con);
            }
        }

    }
}
