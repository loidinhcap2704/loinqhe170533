/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
public class StudentTimetableDBContext extends DBContext<StudentAttend>{

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
    
    public ArrayList<StudentAttend> getWeek(String username, java.sql.Date start, java.sql.Date end){
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<StudentAttend> list = new ArrayList<>();
        try {
            String sql = "select ts.Slot, ts.description, class.date, gname, cou.shortname, rname, sa.attedanceStatus\n" +
                "from [Group] g, Course cou, Class class, Room r, StudentAttend sa, Student s, TimeSlot ts\n" +
                "where g.cid = cou.cid and class.gid = g.gid and class.rid = r.rid and sa.classid = class.classid and sa.sid = s.sid and ts.Slot = class.slot\n" +
                "and s.username = ? and class.date between ? and ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setDate(2, start);
            stm.setDate(3, end);
            rs = stm.executeQuery();
            while(rs.next()){
                StudentAttend sa = new StudentAttend();
                
                model.Session cl = new model.Session();
                
                TimeSlot ts = new TimeSlot();
                ts.setSlot(rs.getInt("Slot"));
                ts.setDescription(rs.getString("description"));
                cl.setSlot(ts);
                
                cl.setDate(rs.getDate("date"));
                
                Group g = new Group();
                g.setGname(rs.getString("gname"));
                
                Course course = new Course();
                course.setShortname(rs.getString("shortname"));
                g.setCourse(course);
                cl.setGroup(g);
                
                Room r = new Room();
                r.setRname(rs.getString("rname"));
                cl.setRoom(r);
                
                sa.setAttendanceStatus(rs.getBoolean("attedanceStatus"));
                sa.setCl(cl);
                
                list.add(sa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public ArrayList<StudentAttend> getAttendReport(String username, int cid) {
        ArrayList<StudentAttend> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select c.slotOfCourse, c.date, c.slot, ts.description, r.rname, l.username, g.gname, sa.attedanceStatus, sa.[lecturer'sComment]\n"
                + "from Student s, StudentAttend sa, Class c, TimeSlot ts, Room r, [Group] g, Course cou, Lecturer l\n"
                + "where s.sid = sa.sid and sa.classid = c.classid and c.slot = ts.Slot and c.rid = r.rid and c.gid = g.gid and cou.cid = g.cid and l.lid = c.lid\n"
                + "and s.username = 'loinq101' and cou.cid=4";
        try {
            stm = connection.prepareStatement(sql);
//            stm.setString(1, username);
//            stm.setInt(2, cid);
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
                
                Room r = new Room();
                r.setRname(rs.getString("rname"));
                
                Lecturer l = new Lecturer();
                User user = new User();
                user.setUsername(rs.getString("username"));
                l.setUser(user);
                
                Group g = new Group();
                g.setGname(rs.getString("gname"));
                s.setGroup(g);
                
                sa.setCl(s);
                sa.setAttendanceStatus(rs.getBoolean("attendanceStatus"));
                sa.setLecturerComment(rs.getString("lecturer'sComment"));
                
                list.add(sa);
            }
            if(list.isEmpty()){
                StudentAttend sa = new StudentAttend();
                list.add(sa);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
}

class DateConverter {
    public static java.util.Date sqlDateToUtilDate(java.util.Date sqlDate) {
        // Create a calendar object and set its time to the SQL date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sqlDate);

        // Get the year, month, and day from the calendar object
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a java.util.Date object with only the year, month, and day
        java.util.Date utilDate = new java.util.Date(year - 1900, month, day);

        // Return the java.util.Date object
        return utilDate;
    }
}