/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author ERDEM
 */
public class Question {

    private long id;
    private String textHead;
    private String answer;
    private String textTail;
    private User user;
    private Course course;

    public Question() {
    }

    public Question(long id, String textHead, String answer, String textTail, User user, Course course) {
        this.id = id;
        this.textHead = textHead;
        this.answer = answer;
        this.textTail = textTail;
        this.user = user;
        this.course = course;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTextHead() {
        return textHead;
    }

    public void setTextHead(String textHead) {
        this.textHead = textHead;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTextTail() {
        return textTail;
    }

    public void setTextTail(String textTail) {
        this.textTail = textTail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return textHead + " " + answer + " " + textTail ;
    }

    
}
