/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class StudentAttend {
    private Session cl;
    private Student student;
    private boolean attendanceStatus;
    private String lecturerComment;
    private boolean future;

    public boolean isFuture() {
        return future;
    }

    public void setFuture(boolean future) {
        this.future = future;
    }

    public Session getCl() {
        return cl;
    }

    public void setCl(Session cl) {
        this.cl = cl;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getLecturerComment() {
        return lecturerComment;
    }

    public void setLecturerComment(String lecturerComment) {
        this.lecturerComment = lecturerComment;
    }
    
}
