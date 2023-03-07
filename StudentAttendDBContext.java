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
import model.Course;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.StudentAttend;
import model.TimeSlot;
import model.User;

/**
 *
 * @author nguye
 */
public class StudentAttendDBContext extends DBContext<StudentAttend> {

    @Override
    public void insert(StudentAttend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(StudentAttend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(StudentAttend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StudentAttend get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<StudentAttend> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<StudentAttend> getAttendReport(String username, int cid) {
    ArrayList<StudentAttend> list = new ArrayList<>();
    PreparedStatement stm = null;
    ResultSet rs = null;
    String sql = "select c.slotOfCourse, c.date, c.slot, ts.description, r.rname, l.username, g.gname, sa.attedanceStatus, sa.[lecturer\'sComment]\n"
            + "from Student s, StudentAttend sa, Class c, TimeSlot ts, Room r, [Group] g, Course cou, Lecturer l\n"
            + "where s.sid = sa.sid and sa.classid = c.classid and c.slot = ts.Slot and c.rid = r.rid and c.gid = g.gid and cou.cid = g.cid and l.lid = c.lid\n"
            + "and s.username = ? and cou.cid = ?";
    try {
        stm = connection.prepareStatement(sql);
        stm.setString(1, username);
        stm.setInt(2, cid);
        rs = stm.executeQuery();
        while (rs.next()) {
            StudentAttend sa = new StudentAttend();
            
            Session s = new Session();
            s.setSlotOfCourse(rs.getInt("slotOfCourse"));
            s.setDate(rs.getDate("date"));
            
            TimeSlot ts = new TimeSlot();
            ts.setSlot(rs.getInt("slot"));
            ts.setDescription(rs.getString("description"));
            s.setSlot(ts);
            
            Room room = new Room();
            room.setRname(rs.getString("rname"));
            s.setRoom(room);
            
            Lecturer lecturer = new Lecturer();
            User user = new User();
            user.setUsername(rs.getString("username"));
            lecturer.setUser(user);
            s.setLecturer(lecturer);
            
            Group group = new Group();
            group.setGname(rs.getString("gname"));
            s.setGroup(group);
            
            sa.setCl(s);
            sa.setAttendanceStatus(rs.getBoolean("attedanceStatus"));
            sa.setLecturerComment(rs.getString("lecturer\'sComment"));
            
            list.add(sa);
            
            // your existing code
            // ...
        }
    } catch (SQLException ex) {
        Logger.getLogger(StudentAttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }finally {
        try {
            // your existing code
            // ...
            rs.close();
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return list;
}
    
    
}
