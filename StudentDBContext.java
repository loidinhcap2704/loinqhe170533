/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Student;

/**
 *
 * @author nguye
 */



public class StudentDBContext extends DBContext<Student>{

    @Override
    public void insert(Student model) {
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO Student(sname,gender,dob,did,currentTerm) VALUES(?,?,?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, model.getSname());
            stm.setBoolean(2, model.isGender());
            stm.setDate(3, model.getDob());
            stm.setInt(4, model.getDept().getDid());
            stm.setInt(5, model.getCurrentTerm());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Student model) {
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE [Student]\n"
                    + "   SET [sname] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[did] = ?\n"
                    + "      ,[currentTerm] = ?\n"
                    + "      ,[username] = ?\n"
                    + " WHERE [sid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, model.getSname());
            stm.setBoolean(2, model.isGender());
            stm.setDate(3, model.getDob());
            stm.setInt(4, model.getDept().getDid());
            stm.setInt(5, model.getCurrentTerm());
            stm.setString(6, model.getUser().getUsername());
            stm.setInt(7, model.getSid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Student model) {
        PreparedStatement stm = null;
        try {
            String sql = "DELETE Student WHERE [sid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getSid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Student get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.[sid],s.sname,s.gender,s.dob,d.did,d.dname FROM Student s\n"
                    + "			INNER JOIN Department d\n"
                    + "			ON s.did = d.did WHERE s.[sid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                Department d = new Department();

                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                d.setDid(rs.getInt("did"));
                d.setDname(rs.getString("dname"));

                s.setDept(d);
                return s;
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
        return null;
    }
    
    public Student getLast() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.[sid],s.sname,s.gender,s.dob,d.did,d.dname FROM Student s\n"
                    + "			INNER JOIN Department d\n"
                    + "			ON s.did = d.did WHERE s.[sid] = (SELECT max(sid) from Student)";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                Department d = new Department();

                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                d.setDid(rs.getInt("did"));
                d.setDname(rs.getString("dname"));

                s.setDept(d);
                return s;
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
        return null;
    }

    @Override
    public ArrayList<Student> all() {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT sid, sname, gender, dob,d.did, d.dname\n" +
                    "FROM Student s inner join Department d\n" +
                    "ON s.did = d.did";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                
                Department dept = new Department();
                dept.setDid(rs.getInt("did"));
                dept.setDname(rs.getString("dname"));
                s.setDept(dept);
                
                students.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }
    
    public ArrayList<Student> search(ArrayList<Integer> dids) {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.[sid],s.sname,s.gender,s.dob,d.did,d.dname FROM Student s\n"
                    + "			INNER JOIN Department d\n"
                    + "			ON s.did = d.did";
            
            String params = " WHERE d.did IN (";
            for (Integer did : dids) {
                params += "?,";
            }
            params = params.substring(0, params.length()-1) + ")";
            sql+= params;
            stm = connection.prepareStatement(sql);
            
            for (int i = 0; i < dids.size(); i++) {
                stm.setInt(i+1, dids.get(i));
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                Department d = new Department();

                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                d.setDid(rs.getInt("did"));
                d.setDname(rs.getString("dname"));

                s.setDept(d);
                students.add(s);
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
        return students;
    }
}
