/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_discount;

import discount_classes.DiscountCodeEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import simplejdbc.DAO;
import simplejdbc.DAOException;

/**
 *
 * @author vanat
 */
public class DAO_Discount extends DAO{
    
    public DAO_Discount(DataSource dataSource) {
		super(dataSource);
    }
    
    public List<DiscountCodeEntity> getCode() throws DAOException {
        List<DiscountCodeEntity> result = new LinkedList<>();
        String sql = "SELECT * FROM DISCOUNT_CODE";
        try (   Connection connection = myDataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next()) {
                    String id = rs.getString("DISCOUNT_CODE");
                    float rate = rs.getFloat("RATE");
                    DiscountCodeEntity dcE = new DiscountCodeEntity(id,rate);
                    result.add(dcE);
                }
            
        } catch(SQLException e) {
            
        }
              
        return result;
    }
    
    public void updateCode (String code, float rate) throws SQLException, DAOException {
        String sql = "UPDATE DISCOUNT_CODE SET RATE = ? WHERE DISCOUNT_CODE = ?";
        try (	Connection connection = myDataSource.getConnection(); 
			PreparedStatement stmt = connection.prepareStatement(sql)) {
                            stmt.setFloat(1, rate);
                            stmt.setString(2,code);
                            stmt.execute();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} 
    }
    public void addCode(String discountID, float rate) throws SQLException, DAOException {
                String sql1 = "SELECT DISCOUNT_CODE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
		String sql = "INSERT INTO DISCOUNT_CODE(DISCOUNT_CODE,RATE)"
                        + "VALUES(?,?)";
                try (	Connection connection = myDataSource.getConnection(); 
			PreparedStatement stmt = connection.prepareStatement(sql1)) {
                            stmt.setString(1,discountID);
                           try (ResultSet rs = stmt.executeQuery()) {
                               if (rs.next()) {
                                   updateCode(discountID,rate);
                               } else {
                                PreparedStatement stmt1 = connection.prepareStatement(sql);
                                stmt1.setString(1,discountID);
                                stmt1.setFloat(2, rate);
                                stmt1.execute();
                               }
                           }
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} 
		

	}	
    
    public void suppCode(String discountCode) throws SQLException, DAOException {
		String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
		try (	Connection connection = myDataSource.getConnection(); 
			PreparedStatement stmt = connection.prepareStatement(sql)) {
                            stmt.setString(1, discountCode);
                            stmt.execute();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}	
}



















