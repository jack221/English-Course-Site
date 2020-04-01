/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Comment;
import entity.Course;
import entity.User;
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
public class CommentDAO {

    private SQLConnection sql;
    private CourseDAO coursedata;
    private UserDAO userdata;

    public CommentDAO(SQLConnection sql) {
        this.sql = sql;
    }

    public CommentDAO() {
    }

    public void create(Comment comment, long user_id, long course_id) {
        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "insert into comments values(DEFAULT,'" + comment.getText() + "'," + course_id + "," + user_id + ")RETURNING comments.id;";
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                comment.setId(rst.getLong("id"));
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Comment comment, long user_id, long course_id) {
        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "update comments set(text,course_id, users_id) = ('" + comment.getText() + "'," + course_id + "," + user_id + ") where id = " + comment.getId();
            st.executeQuery(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Comment comment) {
        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "delete from comments where id = " + comment.getId();
            st.executeQuery(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getCommentList() {
        List<Comment> commentList = new ArrayList<Comment>();
        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "select * from comments";
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                Course course = getCoursedata().getCourse(rst.getLong("course_id"));
                User user = getUserdata().getUser(rst.getLong("users_id"));
                Comment comment = new Comment(rst.getLong("id"), rst.getString("text"), course, user);
                commentList.add(comment);
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commentList;
    }

    public Comment getComment(long id) {
        Comment comment = null;
        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "select * from comments where id = " + id;
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                Course course = getCoursedata().getCourse(rst.getLong("course_id"));
                User user = getUserdata().getUser(rst.getLong("users_id"));
                comment = new Comment(rst.getLong("id"), rst.getString("text"), course, user);
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comment;
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

    public UserDAO getUserdata() {
        if (userdata == null) {
            userdata = new UserDAO(this.getSql());
        }
        return userdata;
    }

}
