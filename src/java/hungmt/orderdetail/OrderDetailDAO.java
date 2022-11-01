/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.orderdetail;

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
public class OrderDetailDAO implements Serializable {

    public boolean insertNewRecord(OrderDetailDTO dto) throws SQLException, NamingException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into tblOrderDetails(sku, orderId, quantity, price, total) "
                        + "values (?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getSku());
                stm.setInt(2, dto.getOrderID());
                stm.setInt(3, dto.getQuantity());
                stm.setFloat(4, dto.getPrice());
                stm.setFloat(5, dto.getTotal());
                int rs;
                rs = stm.executeUpdate();
                if (rs > 0) {
                    result = true;
                }
            }
        } finally {
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
