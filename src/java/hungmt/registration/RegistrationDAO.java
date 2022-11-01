/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungmt.registration;

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
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException {
        RegistrationDTO res = null;
        //1. Make connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL statement
                String sql = "Select username, lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ? ";
                //3. Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    res = new RegistrationDTO(username, null, fullname, role);
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
        return res;
    }
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws ClassNotFoundException, SQLException, NamingException {
        //1. Make connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL statement
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname like ?";
                //3. Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end if account is null
                    // account hast exitsted
                    this.accounts.add(dto);
                }//end traveser results
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
    }

    public boolean deleteAccount(String username) throws ClassNotFoundException, SQLException, NamingException {
        boolean res = false;
        //1. Make connection
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL statement
                String sql = "Delete From Registration "
                        + "Where username = ? ";
                //3. Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    res = true;
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
        return res;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin) throws ClassNotFoundException, SQLException, NamingException {
        boolean res = false;
        //1. Make connection
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL statement
                String sql = "update Registration "
                        + "set password = ?, isAdmin = ? "
                        + "where username = ? ";
                //3. Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    res = true;
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
        return res;
    }

    public boolean createAccount(RegistrationDTO dto) throws SQLException, NamingException {
        boolean res = false;
        //1. Make connection
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL statement
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?) ";
                //3. Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    res = true;
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
        return res;
    }
}
