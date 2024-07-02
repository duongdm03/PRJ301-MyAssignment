/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class SubmitMarkDTO {
    private String gradeID;
    private String exID;
    private String stuID;
    private String score;
    private String username;
    private String comment;

    public SubmitMarkDTO() {
    }

    public SubmitMarkDTO(String gradeID, String exID, String stuID, String score, String username, String comment) {
        this.gradeID = gradeID;
        this.exID = exID;
        this.stuID = stuID;
        this.score = score;
        this.username = username;
        this.comment = comment;
    }

    public String getGradeID() {
        return gradeID;
    }

    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }

    public String getExID() {
        return exID;
    }

    public void setExID(String exID) {
        this.exID = exID;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
