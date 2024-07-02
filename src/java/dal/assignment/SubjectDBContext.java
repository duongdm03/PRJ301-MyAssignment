/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.assignment;

import dal.DBContext;
import entity.Assessment;
import entity.ShowMarkDTO;
import entity.StudentShowMarkDTO;
import entity.Subject;
import entity.SubjectDTO;
import entity.SubmitMarkDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class SubjectDBContext extends DBContext<Subject> {

    @Override
    public ArrayList<Subject> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<SubjectDTO> listAllSubjectOfLecture(String lecID) {
        List<SubjectDTO> list = new ArrayList<>();
        try {

            connection.setAutoCommit(false);
            String sql = "select subID,gID from [Group] where lecID like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lecID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                list.add(new SubjectDTO(rs.getString(1).trim(), rs.getString(2)));
            }
            connection.commit();
        } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.INFO, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<ShowMarkDTO> listAllMarkOfStudent(String gID, String assID) {
        List<ShowMarkDTO> list = new ArrayList<>();
        try {

            connection.setAutoCommit(false);
            String sql = "with t as(select s.stuID as stuID,s.stuName as stuName,a.assID as assID,a.assName as assName,ex.exID as exID from Enrollment e,Student s,[Group] g,Assessment a,Exam ex where s.stuID like e.stuID and e.gID like ? and a.assID like ? \n"
                    + "and e.gID like g.gID and a.subID like g.subID and a.assID like ex.assID)\n"
                    + "select t.stuID,t.stuName,t.assID,t.assName,t.exID,g.Score,g.Comment from t left join Grade g on g.exID like t.exID and t.stuID like g.stuID;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, gID);
            stm.setString(2, assID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new ShowMarkDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.INFO, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public void insert(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Assessment> listAssessment(String subID) {
        List<Assessment> list = new ArrayList<>();
        try {

            connection.setAutoCommit(false);
            String sql = "select * from Assessment where subID like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + subID + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                list.add(new Assessment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.INFO, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;

    }

    public String getSubjectIdByGid(String gID) {
        try {

            connection.setAutoCommit(false);
            String sql = "select subID from [Group] where gID like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, gID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                return rs.getString(1);
            }
            connection.commit();
        } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.INFO, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";

    }
    public static List<SubmitMarkDTO> list = new ArrayList<>();

    public void updateMark(List<SubmitMarkDTO> listSubmit) {
        try {

            connection.setAutoCommit(false);
            String sql_remove_atts = "DELETE Grade WHERE exID like ?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setString(1, listSubmit.get(0).getExID());
            stm_remove_atts.executeUpdate();

            String sql_insert_1 = "INSERT INTO [dbo].[Grade]\n"
                    + "           ([gradeID]\n"
                    + "           ,[exID]\n"
                    + "           ,[stuID]\n"
                    + "           ,[Score]\n"
                    + "           ,[lecID]\n"
                    + "           ,[Comment])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            for (SubmitMarkDTO submitMarkDTO : listSubmit) {
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_1);
                System.out.println(submitMarkDTO.getComment());
                stm_insert_att.setString(1, submitMarkDTO.getGradeID());
                stm_insert_att.setString(2, submitMarkDTO.getExID());
                stm_insert_att.setString(3, submitMarkDTO.getStuID());
                stm_insert_att.setString(4, submitMarkDTO.getScore());
                stm_insert_att.setString(5, submitMarkDTO.getUsername());
                stm_insert_att.setString(6, submitMarkDTO.getComment());
                stm_insert_att.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.INFO, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getStudentID(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<String> getSubjectOfStudent(String studentName) {

        List<String> list = new ArrayList<>();
        try {

            connection.setAutoCommit(false);
            String sql = "select distinct g.subID from Enrollment e,[Group] g,Account a,Student s where e.gID like g.gID and a.username like s.username and a.username like ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentName);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                list.add(rs.getString(1).trim());
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.INFO, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<StudentShowMarkDTO> result(String parameter, String username) {
        List<StudentShowMarkDTO> list = new ArrayList<>();
        try {

            connection.setAutoCommit(false);
            String sql = "with t as(select s.stuID as stuID,s.stuName as stuName,a.assID as assID,a.assName as assName,ex.exID as exID,a.weight as weight from Enrollment e,Student s,[Group] g,Assessment a,Exam ex  where s.stuID like e.stuID and s.username like ? and g.subID like ?\n"
                    + "                                       and e.gID like g.gID and a.subID like g.subID and a.assID like ex.assID)\n"
                    + "                                     select t.assName,g.Score,t.weight,g.Comment from t left join Grade g on g.exID like t.exID and t.stuID like g.stuID ORDER BY CAST(t.weight AS INT)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, "%" + parameter + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                list.add(new StudentShowMarkDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.INFO, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public double average(List<StudentShowMarkDTO> list) {
        double total = 0;
        for (StudentShowMarkDTO studentShowMarkDTO : list) {
            if (studentShowMarkDTO.getScore() != null) {
                
                try {
                    int weight = Integer.parseInt(studentShowMarkDTO.getWeight());
                    double score = Double.parseDouble(studentShowMarkDTO.getScore());
                    total += (weight * score);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                

            } else {
                return -1;
            }

        }
        return total / 100;
    }

}
