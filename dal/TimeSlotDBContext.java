/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TimeSlot;

/**
 *
 * @author nguye
 */
public class TimeSlotDBContext extends DBContext<TimeSlot>{

    @Override
    public void insert(TimeSlot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(TimeSlot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(TimeSlot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TimeSlot get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<TimeSlot> all() {
        ArrayList<TimeSlot> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM TIMESLOT";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                TimeSlot ts = new TimeSlot();
                ts.setSlot(rs.getInt("slot"));
                ts.setDescription(rs.getString("description"));
                list.add(ts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return list;
    }
    
}
