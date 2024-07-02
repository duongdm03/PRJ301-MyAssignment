/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;


import entity.Lession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kienb
 */
public class LessionDTO {
    private LocalDate date;
    private Lession session;

    public LessionDTO() {
    }

    public LessionDTO(LocalDate date, Lession session) {
        this.date = date;
        this.session = session;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Lession getSession() {
        return session;
    }

    public void setSession(Lession session) {
        this.session = session;
    }

    
    public String showDate(){
        return date.format(DateTimeFormatter.ofPattern("dd/MM"));
    }
}
