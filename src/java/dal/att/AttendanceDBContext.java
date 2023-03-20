/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.att;

import dal.DBContext;
import dal.DepartmentDBContext;
import dal.StudentDBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.User;
import model.att.Student;
import model.att.Attendance;
import model.att.Group;
import model.att.Lecturer;
import model.att.Room;
import model.att.Session;
import model.att.TimeSlot;

/**
 *
 * @author sonnt
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public void updateAtts(ArrayList<Attendance> atts, int sessionid) {
        ArrayList<PreparedStatement> stms = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            //UPDATE Session Record
            String sql_update_session = "UPDATE Session SET status = 1 WHERE sessionid = ?";
            PreparedStatement stm_update_session = connection.prepareStatement(sql_update_session);
            stm_update_session.setInt(1, sessionid);
            stm_update_session.executeUpdate();
            stms.add(stm_update_session);

            //PROCESS Attendace records
            for (Attendance att : atts) {
                if (att.getId() == 0) //INSERT
                {
                    String sql_insert_att = "INSERT INTO [Attendance]\n"
                            + "           ([sid]\n"
                            + "           ,[sessionid]\n"
                            + "           ,[status]\n"
                            + "           ,[description])\n"
                            + "     VALUES\n"
                            + "           (?\n"
                            + "           ,?\n"
                            + "           ,?\n"
                            + "           ,?)";
                    PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                    stm_insert_att.setInt(1, att.getStudent().getId());
                    stm_insert_att.setInt(2, sessionid);
                    stm_insert_att.setBoolean(3, att.isStatus());
                    stm_insert_att.setString(4, att.getDescription());
                    stm_insert_att.executeUpdate();
                    stms.add(stm_insert_att);

                } else //UPDATE
                {
                    String sql_update_att = "UPDATE Attendance SET status = ?,description = ? WHERE aid = ?";
                    PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);
                    stm_update_att.setBoolean(1, att.isStatus());
                    stm_update_att.setString(2, att.getDescription());
                    stm_update_att.setInt(3, att.getId());
                    stm_update_att.executeUpdate();
                    stms.add(stm_update_att);
                }
            }

            connection.commit();
        } catch (SQLException ex) {

            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (PreparedStatement stm : stms) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Attendance> getAttsBySessionID(int sessionid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT \n"
                    + "                    s.sid,s.sname\n"
                    + "                    ,ISNULL(a.status,0) as [status], ISNULL(a.description,'') as [description],a.aid\n"
                    + "                    FROM Student s INNER JOIN StudentGroup sg ON sg.sid = s.sid\n"
                    + "                    		INNER JOIN [Group] g ON g.gid = sg.gid\n"
                    + "                    		INNER JOIN [Session] ses ON ses.gid = g.gid\n"
                    + "                    		LEFT JOIN [Attendance] a\n"
                    + "                    		ON a.sid = s.sid AND a.sessionid = ses.sessionid\n"
                    + "                    		WHERE ses.sessionid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setId(rs.getInt("aid"));
                a.setStatus(rs.getBoolean("status"));
                a.setDescription(rs.getString("description"));
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                a.setStudent(s);
                atts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atts;
    }

    public ArrayList<Attendance> getAttendReport(int sid, int gid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.noSession\n"
                    + "      ,a.[status]\n"
                    + "      ,a.[description] adesciption\n"
                    + "	  ,s.date\n"
                    + "	  ,s.tid\n"
                    + "	  ,ts.description\n"
                    + "	  ,r.rname\n"
                    + "	  ,u.username\n"
                    + "	  ,g.gname\n"
                    + "  FROM [Attendance] a\n"
                    + "  INNER JOIN Session s on s.sessionid = a.sessionid\n"
                    + "  INNER JOIN Time ts on ts.tid = s.tid\n"
                    + "  INNER JOIN Room r on r.rid = s.rid\n"
                    + "  INNER JOIN Lecturer l on l.lid = s.lid\n"
                    + "  INNER JOIN [User] u on u.userid = l.userid\n"
                    + "  INNER JOIN [Group] g on s.gid = g.gid\n"
                    + "  INNER JOIN Student stu on stu.sid = a.sid\n"
                    + "  WHERE g.gid = ?\n"
                    + "  and stu.[sid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            stm.setInt(2, sid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Session s = new Session();
                s.setNoSession(rs.getInt("noSession"));
                s.setDate(rs.getDate("date"));
                TimeSlot ts = new TimeSlot();
                ts.setId(rs.getInt("tid"));
                ts.setDescription(rs.getString("description"));
                s.setSlot(ts);
                Room r = new Room();
                r.setName(rs.getString("rname"));
                Lecturer l = new Lecturer();
                User u = new User();
                u.setUsername(rs.getString("username"));
                l.setUser(u);
                Group g = new Group();
                g.setName(rs.getString("gname"));
                s.setGroup(g);
                s.setLecturer(l);
                a.setSession(s);
                a.setStatus(rs.getBoolean("status"));
                a.setDescription(rs.getString("adesciption"));
                atts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atts;
    }

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
