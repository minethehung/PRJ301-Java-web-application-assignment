/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.product;

import hungmt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author PC
 */
public class ProductDAO implements Serializable {

    public List<ProductDTO> getBookList() throws NamingException, SQLException {
        List<ProductDTO> result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT sku, name, description, price "
                    + "From tblProducts ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                ProductDTO dto = new ProductDTO(sku, name, description, price);
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(dto);
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

    public ProductDTO getBookBySKU(String key) throws NamingException, SQLException {
        ProductDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT sku, name, description, price "
                    + "From tblProducts "
                    + "Where sku = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, key);
            rs = stm.executeQuery();
            if (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                result = new ProductDTO(sku, name, description, price);
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
