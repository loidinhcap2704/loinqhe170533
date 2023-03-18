/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.att;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
import model.att.Attendance;
import model.att.Course;
import model.att.Group;
import model.att.Room;
import model.att.Session;
import model.att.TimeSlot;

/**
 *
 * @author nguye
 */
public class StudentDBContex extends DBContext<Student> {

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attendance> timetable(int sid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT isnull(a.aid,0) aid\n"
                + "      ,a.[status]\n"
                + "	  ,stu.sid\n"
                + "	  ,s.date\n"
                + "	  ,g.gname\n"
                + "	  ,c.cname\n"
                + "	  ,r.rname\n"
                + "       ,ts.tid\n"
                + "  FROM [Group] g\n"
                + "  INNER JOIN Course c on g.cid = c.cid\n"
                + "  INNER JOIN [Session] s on g.gid = s.gid\n"
                + "  INNER JOIN [Time] ts on ts.tid = s.tid\n"
                + "  INNER JOIN Room r on r.rid = s.rid\n"
                + "  INNER JOIN StudentGroup sg on sg.gid = g.gid and sg.gid = g.gid\n"
                + "  INNER JOIN Student stu on sg.sid = stu.sid\n"
                + "  LEFT JOIN Attendance a on a.sessionid = s.sessionid and a.sid = stu.sid\n"
                + "  WHERE sg.sid = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                att.setId(rs.getInt("aid"));
                att.setStatus(rs.getBoolean("status"));
                model.att.Student student = new model.att.Student();
                student.setId(sid);
                att.setStudent(student);
                Session ses = new Session();
                ses.setDate(rs.getDate("date"));
                Group group = new Group();
                group.setName(rs.getString("gname"));
                Course c = new Course();
                c.setName(rs.getString("cname"));
                group.setCourse(c);
                ses.setGroup(group);
                TimeSlot ts = new TimeSlot();
                ts.setId(rs.getInt("tid"));
                ses.setSlot(ts);
                Room r = new Room();
                r.setName(rs.getString("rname"));
                ses.setRoom(r);
                att.setSession(ses);
                atts.add(att);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContex.class.getName()).log(Level.SEVERE, null, ex);
        }

        return atts;
    }

}
