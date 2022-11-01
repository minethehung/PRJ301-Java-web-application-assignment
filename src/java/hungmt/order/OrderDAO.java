/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.order;

import hungmt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author PC
 */
public class OrderDAO implements Serializable {

    public int insertNewRecord(float totalSum) throws SQLException, NamingException {
        int result = -1;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into tblOrders(date, total) "
                        + "OUTPUT inserted.orderId "
                        + "values (getdate(), ?) ";
                stm = con.prepareStatement(sql);
                stm.setFloat(1, totalSum);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
