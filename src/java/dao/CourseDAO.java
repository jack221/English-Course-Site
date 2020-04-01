/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Course;
import entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sqlConnection.SQLConnection;

public class CourseDAO {

    private SQLConnection sql;
    private UserDAO userdata;

    public CourseDAO(SQLConnection sql) {
        this.sql = sql ;
    }

    public CourseDAO() {
    }
    
    public void create(Course course, long user_id) {
        try {
            String query = "insert into course values(DEFAULT,'" + course.getName() + "','" + course.getLessonText() + "'," + user_id + ")RETURNING course.id;";
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                course.setId(rst.getLong("id"));
            }
            rst.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Course course, long user_id) {
        try {
            String query = "update course set(name, lesson_text, users_id) = ('" + course.getName() + "','" + course.getLessonText() + "'," + user_id + ") where id = " + course.getId();
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);

            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        try {
            String query = "delete from course where id =" + course.getId();
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);

            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCourseCategory(Course course, List<Long> categoryIds) {
        try {
            Connection con = getSql().getConnection();
            for (int i = 0; i < categoryIds.size(); i++) {
                String query = "insert into course_category values(" + course.getId() + "," + categoryIds.get(i) + ");";
                Statement st = con.createStatement();
                st.executeUpdate(query);
                st.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Course> getCourseList() {
        List<Course> courseList = new ArrayList<Course>();

        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "select * from course";
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                Course course = new Course(rst.getLong("id"), rst.getString("name"), rst.getString("lesson_text"), user);
                courseList.add(course);
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseList;
    }

    public Course getCourse(long id) {
        Course course = null;
        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "select * from course where id = " + id;
            ResultSet rst = st.executeQuery(query);

            if (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                course = new Course(rst.getLong("id"), rst.getString("name"), rst.getString("lesson_text"), user);

            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return course;
    }

    public List<Course> getCourseCategoryList(long id) {
        List<Course> courseList = new ArrayList<Course>();

        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "select * from course where id IN(select course_id from course_category where category_id = " + id + " );";
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                Course course = new Course(rst.getLong("id"), rst.getString("name"), rst.getString("lesson_text"), user);
                courseList.add(course);
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseList;
    }

    public SQLConnection getSql() {
        if (sql == null) {
            sql = new SQLConnection();
        }
        return sql;
    }

    public UserDAO getUserdata() {
        if (userdata == null) {
            userdata = new UserDAO(this.getSql());
        }
        return userdata;
    }

}
