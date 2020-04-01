/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDAO;
import entity.Course;
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
@ManagedBean(name = "CourseController")
//@Named
@SessionScoped
public class CourseController implements Serializable {

    private Course course;
    private CourseDAO coursedata;
    private List<Course> courseList;

    @ManagedProperty(value = "#{UserController}")
    private UserController userController;
    private long selectedUserId;
    
    @ManagedProperty(value = "#{CategoryController}")
    private CategoryController categoryController ;
    private List<Long> selectedCategoryIds ;

    public CourseController() {
    }

    public String create() {
        this.getCoursedata().create(course, selectedUserId);
        this.getCoursedata().createCourseCategory(course, selectedCategoryIds);
        return "/course/show";
    }

    public String update() {
        this.getCoursedata().update(course, selectedUserId);
        return "/course/show";
    }

    public String update(Course course) {
        this.course = course;
        return "/course/update";
    }

    public String delete(Course course) {
        this.getCoursedata().delete(course);
        return "/course/show";
    }

    public List<Course> getCourseList() {
        if (courseList == null) {
            courseList = new ArrayList<Course>();
        }
        courseList = getCoursedata().getCourseList();
        return courseList;
    }

    public Course getCourse() {
        if (course == null) {
            course = new Course();
        }
        return course;
    }

    public CourseDAO getCoursedata() {
        if (coursedata == null) {
            coursedata = new CourseDAO();
        }
        return coursedata;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setSelectedUserId(long selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public long getSelectedUserId() {
        return selectedUserId;
    }

    public CategoryController getCategoryController() {
        return categoryController;
    }

    public void setCategoryController(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    public List<Long> getSelectedCategoryIds() {
        if(selectedCategoryIds == null)
            selectedCategoryIds = new ArrayList<Long>();
        return selectedCategoryIds;
    }

    public void setSelectedCategoryIds(List<Long> selectedCategoryIds) {
        this.selectedCategoryIds = selectedCategoryIds;
    }
    
}
