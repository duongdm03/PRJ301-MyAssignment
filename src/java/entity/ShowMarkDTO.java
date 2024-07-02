/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class ShowMarkDTO {
    private String stuID;
    private String stuName;
    private String assID;
    private String assName;
    private String exID;
    private String score;
    private String comment;

    public ShowMarkDTO() {
    }

    public ShowMarkDTO(String stuID, String stuName, String assID, String assName, String exID, String score, String comment) {
        this.stuID = stuID;
        this.stuName = stuName;
        this.assID = assID;
        this.assName = assName;
        this.exID = exID;
        this.score = score;
        this.comment = comment;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getAssID() {
        return assID;
    }

    public void setAssID(String assID) {
        this.assID = assID;
    }

    public String getAssName() {
        return assName;
    }

    public void setAssName(String assName) {
        this.assName = assName;
    }

    public String getExID() {
        return exID;
    }

    public void setExID(String exID) {
        this.exID = exID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
    
  
}
