/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import entity.Question;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

/**
 *
 * @author ERDEM
 */
@ManagedBean(name = "QuestionController")
//@Named
@SessionScoped
public class QuestionController implements Serializable {

    private QuestionDAO questiondata;
    private Question question;
    private List<Question> questionList;

    @ManagedProperty(value = "#{UserController}")
    private UserController userController;
    private long selectedUserIds;

    @ManagedProperty(value = "#{CourseController}")
    private CourseController courseController;
    private long selectedCourseIds;

    public QuestionController() {
    }

    public String create() {
        this.getQuestiondata().create(question, selectedUserIds, selectedCourseIds);
        return "/question/show";
    }

    public String update(Question question) {
        this.question = question;
        return "/question/update";
    }

    public String update() {
        this.getQuestiondata().update(question, selectedUserIds, selectedCourseIds);
        return "/question/show";
    }

    public String delete(Question question) {
        this.getQuestiondata().delete(question);
        return "/question/show";
    }

    public Question getQuestion() {
        if (question == null) {
            question = new Question();
        }
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestionList() {
        questionList = getQuestiondata().getQuestionList();
        if (questionList == null) {
            questionList = new ArrayList<Question>();
        }
        return questionList;
    }

    public QuestionDAO getQuestiondata() {
        if (questiondata == null) {
            questiondata = new QuestionDAO();
        }
        return questiondata;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public CourseController getCourseController() {
        return courseController;
    }

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    public long getSelectedCourseIds() {
        return selectedCourseIds;
    }

    public void setSelectedCourseIds(long selectedCourseIds) {
        this.selectedCourseIds = selectedCourseIds;
    }

    public long getSelectedUserIds() {
        return selectedUserIds;
    }

    public void setSelectedUserIds(long selectedUserIds) {
        this.selectedUserIds = selectedUserIds;
    }

}
