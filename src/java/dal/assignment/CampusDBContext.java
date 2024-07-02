/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.assignment;

import dal.DBContext;
import entity.Campus;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class CampusDBContext extends DBContext<Campus>{

    @Override
    public ArrayList<Campus> list() {
        ArrayList<Campus> campus = new ArrayList<>();
        try {
            String sql = "SELECT cpID,cpName FROM Campus";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Campus c = new Campus();
                c.setId(rs.getString("cpID"));
                c.setName(rs.getString("cpName"));
                campus.add(c);
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return campus;
    }

    @Override
    public void insert(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Campus get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
