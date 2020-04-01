/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import entity.Course;
import entity.Quiz;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sqlConnection.SQLConnection;

/**
 *
 * @author ERDEM
 */
public class CategoryDAO {

    private SQLConnection sql;
    private CourseDAO coursedata;
    private QuizDAO quizdata;

    public CategoryDAO() {
    }

    public CategoryDAO(SQLConnection sql) {
        this.sql = sql;
    }

    
    public void create(Category category) {
        try {
            String query = "insert into category values(DEFAULT,'" + category.getText() + "') RETURNING id;";
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                category.setId(rst.getLong("id"));
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Category category) {
        try {
            String query = "update category set text = '" + category.getText() + "' where category.id = " + category.getId();
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Category category) {
        try {
            String query = "delete from category where id = " + category.getId();
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<Category>();
        Connection con = getSql().getConnection();
        try {
            String query = "select * from category";
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                List<Course> courseCategory = getCoursedata().getCourseCategoryList(rst.getLong("id"));
                List<Quiz> quizCategory = getQuizdata().getQuizCategoryList(rst.getLong("id"));
                categoryList.add(new Category(rst.getLong("id"), rst.getString("text"), courseCategory, quizCategory));
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryList;
    }
    public Category getCategory(long id) {
        Category category = null ;
        
        try {
            Connection con = getSql().getConnection();
            String query = "select * from category where id = " + id;
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                List<Course> courseCategory = getCoursedata().getCourseCategoryList(rst.getLong("id"));
                List<Quiz> quizCategory = getQuizdata().getQuizCategoryList(rst.getLong("id"));
                category = new Category(rst.getLong("id"), rst.getString("text"), courseCategory, quizCategory);
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }
    public List<Category> getQuizCategoryList(long quizID) {
        List<Category> categoryList = new ArrayList<Category>();

        try {
            String query = "select * from category where category.id IN (select category_id from quiz_category where quiz_id = " + quizID + ");";
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                Category category = new Category();
                category.setId(rst.getLong("id"));
                category.setText(rst.getString("text"));
                category.setCourseCategory(null);
                category.setQuizCategory(null);
                categoryList.add(category);
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public List<Category> getCourseCategoryList(long courseId) {
        List<Category> categoryList = new ArrayList<Category>();

        try {
            String query = "select * from category where category.id IN (select category_id from course_category where course_id = " + courseId + ");";
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                Category category = new Category();
                category.setId(rst.getLong("id"));
                category.setText(rst.getString("text"));
                category.setCourseCategory(null);
                category.setQuizCategory(null);
                categoryList.add(category);
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public SQLConnection getSql() {
        if (sql == null) {
            sql = new SQLConnection();
        }
        return sql;
    }

    public CourseDAO getCoursedata() {
        if (coursedata == null) {
            coursedata = new CourseDAO(this.getSql());
        }
        return coursedata;
    }

    public QuizDAO getQuizdata() {
        if (quizdata == null) {
            quizdata = new QuizDAO(this.getSql());
        }
        return quizdata;
    }

}
