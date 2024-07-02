/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.assignment;

import DTO.LessionDTO;
import dal.DBContext;
import entity.Attendence;
import entity.Campus;
import entity.Lecturer;
import entity.Lession;
import entity.Room;
import entity.Student;
import entity.StudentGroup;
import entity.Subject;
import entity.TimeSlot;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import util.DateHelper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateTimeHelper;

/**
 *
 * @author ASUS
 */
public class LessionDBContext extends DBContext<Lession> {

    public List<Attendence> getListAttendance(String lessID, List<Attendence> updateList) {
        try {
            int count = 0;
            connection.setAutoCommit(false);
            String sql = "select * from [Attendence] where lessID like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lessID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendence a = updateList.get(count);
                if ((rs.getBoolean(5) != a.isPresent())
                        || (!rs.getString(4).equals(a.getDescription()))) {
                    a.setIsUpdate(true);
                } else {
                    if (rs.getDate(6) != null) {
                        a.setTime(rs.getString(6));
                    }

                }
                count++;
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return updateList;

    }



    public void takeAttendance(String leid, List<Attendence> atts) {
        try {
            connection.setAutoCommit(false);

            String sql_remove_atts = "DELETE Attendence WHERE lessID = ?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setString(1, leid);
            stm_remove_atts.executeUpdate();
            

           
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(atts.size());

            String sql_insert_1 = "INSERT INTO [Attendence]\n"
                    + "           ([lessID]\n"
                    + "           ,[stuID]\n"
                    + "           ,[description]\n"
                    + "           ,[isPresent]\n"
                    + "           ,[capturedtime])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE())";
            String sql_insert_2 = "INSERT INTO [Attendence]\n"
                    + "           ([lessID]\n"
                    + "           ,[stuID]\n"
                    + "           ,[description]\n"
                    + "           ,[isPresent]"
                    + "           ,[capturedtime])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?,?\n)";
            for (Attendence att : atts) {
                PreparedStatement stm_insert_att = null;

                System.out.println(att.isPresent() + " " + att.getStudent().getName() + " " + att.getTime());
                if (att.isIsUpdate() == true || att.getTime() == null) {
                    stm_insert_att = connection.prepareStatement(sql_insert_1);
                } else {

                    stm_insert_att = connection.prepareStatement(sql_insert_2);
                    stm_insert_att.setString(5, att.getTime());
                }
                stm_insert_att.setString(1, leid);
                stm_insert_att.setString(2, att.getStudent().getId());
                stm_insert_att.setString(3, att.getDescription());
                stm_insert_att.setBoolean(4, att.isPresent());

                stm_insert_att.executeUpdate();
            }

            String sql_update_less = "UPDATE Lession SET isAttendence = 1 WHERE lessID = ?";
            PreparedStatement stm_update_less = connection.prepareStatement(sql_update_less);
            stm_update_less.setString(1, leid);
            stm_update_less.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Attendence> getAttendencesByLession(String lessID) {
        ArrayList<Attendence> atts = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "  s.stuID,s.stuName,s.Mail,s.cpID,s.DoB,\n"
                    + "    a.aid,a.isPresent,a.description,a.capturedtime,a.image\n"
                    + "       FROM Student s INNER JOIN Enrollment e ON s.stuID = e.stuID\n"
                    + "          		INNER JOIN [Group] g ON g.gID = e.gID\n"
                    + "                 	INNER JOIN Lession les ON les.gID = g.gID\n"
                    + "                    	LEFT JOIN Attendence a ON les.lessID = a.lessID\n"
                    + "                    	AND a.stuID = s.stuID\n"
                    + "                         WHERE les.lessID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lessID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendence att = new Attendence();
                Student s = new Student();
                Lession les = new Lession();
                Campus c = new Campus();
                s.setId(rs.getString("stuID"));
                s.setName(rs.getString("stuName"));
                s.setMail(rs.getString("Mail"));
                c.setId(rs.getString("cpID"));
                s.setCampus(c);
                s.setDob(rs.getDate("DoB"));
                att.setStudent(s);

                les.setId(lessID);
                att.setLession(les);

                att.setId(rs.getInt("aid"));
                if (att.getId() != 0) {
                    att.setDescription(rs.getString("description"));
                    att.setPresent(rs.getBoolean("isPresent"));
                    att.setTime(rs.getString("capturedtime"));
                    att.setImage(rs.getString("image"));
                }
                atts.add(att);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
    }

    public ArrayList<Lession> getLessionBy(String lid, Date from, Date to) {
        ArrayList<Lession> lessions = new ArrayList<>();
        try {

            String sql = "SELECT\n"
                    + "  le.lessID,le.date,le.isAttendence,\n"
                    + "                    g.gID,g.gname,su.subID,su.subName,\n"
                    + "                    t.tsID,t.Description,t.[Start],t.[End],\n"
                    + "                    r.roomID,r.roomName,r.Capacity,r.Status,r.Type,\n"
                    + "                    l.lecID,l.lecName,l.lecDepartment,l.Gmail\n"
                    + "                    FROM Lession le INNER JOIN [Group] g ON le.gID = g.gID\n"
                    + "                    				INNER JOIN TimeSlot t ON t.tsID = le.tsID\n"
                    + "                    					INNER JOIN Room r ON r.roomID = le.roomID\n"
                    + "                    					INNER JOIN Lecturer l ON le.lecID = l.lecID\n"
                    + "                    						INNER JOIN [Subject] su ON su.subid = g.subid\n"
                    + "                    WHERE l.lecID=? AND le.[date] >= ? AND le.[date] <=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lession le = new Lession();
                StudentGroup g = new StudentGroup();
                Subject sub = new Subject();
                Lecturer l = new Lecturer();
                Room r = new Room();
                TimeSlot slot = new TimeSlot();
                le.setId(rs.getString("lessID"));
                le.setDate(rs.getDate("date"));
                le.setAttended(rs.getBoolean("isAttendence"));

                g.setId(rs.getString("gID"));
                g.setName(rs.getString("gname"));
                sub.setId(rs.getString("subID"));
                sub.setName(rs.getString("subName"));
                g.setSubject(sub);
                le.setGroup(g);

                slot.setId(rs.getString("tsID"));
                slot.setDescription(rs.getString("Description"));
                slot.setStart(rs.getTime("Start"));
                slot.setEnd(rs.getTime("End"));
                le.setSlot(slot);

                r.setId(rs.getString("roomID"));
                r.setName(rs.getString("roomName"));
                r.setCapacity(rs.getByte("Capacity"));
                r.setStatus(rs.getString("Status"));
                r.setType(rs.getString("Type"));
                le.setRoom(r);

                l.setId(rs.getString("lecID"));
                l.setName(rs.getString("lecName"));
                l.setDepartment(rs.getString("lecDepartment"));
                l.setMail(rs.getString("Gmail"));
                le.setLecturer(l);

                lessions.add(le);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessions;
    }

    @Override
    public ArrayList<Lession> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lession get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
