/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentDAO;
import entity.Comment;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

/**
 *
 * @author ERDEM
 */
@ManagedBean(name = "CommentController")
@Named
@SessionScoped
public class CommentController implements Serializable {

    private Comment comment;
    private List<Comment> commentList;
    private CommentDAO commentdata;

    @ManagedProperty(value = "#{UserController}")
    private UserController userController;
    private long selectedUserId ;

    @ManagedProperty(value = "#{CourseController}")
    private CourseController courseController;
    private long selectedCourseId ;

    public CommentController() {
    }

    public Comment getComment() {
        if (comment == null) {
            comment = new Comment();
        }
        return comment;
    }

    public String create() {
        this.getCommentdata().create(this.getComment(),selectedUserId, selectedCourseId);
        return "/comment/show";
    }

    public String update() {
        this.getCommentdata().update(comment, selectedUserId, selectedCourseId);
        return "/comment/show";
    }

    public String update(Comment comment) {
        this.comment = comment;
        return "/comment/update" ;
    }
    public String delete(Comment comment){
        this.getCommentdata().delete(comment);
        return "/comment/show" ;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getCommentList() {
        commentList = getCommentdata().getCommentList();
        return commentList;
    }

    public CommentDAO getCommentdata() {
        if (commentdata == null) {
            commentdata = new CommentDAO();
        }
        return commentdata;
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

    public long getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(long selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public long getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(long selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

}
