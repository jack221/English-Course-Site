/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Course;
import entity.Question;
import entity.Quiz;
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
public class QuestionDAO {

    private SQLConnection sql;
    private UserDAO userdata;
    private CourseDAO coursedata;
    private QuizDAO quizdata;

    public QuestionDAO() {
    }

    public QuestionDAO(SQLConnection sql) {
        this.sql = sql;
    }

    public void create(Question question, long user_id, long course_id) {

        try {
            String query = "insert into question(id,text_head,answer,text_tail,users_id,course_id) values(DEFAULT,"
                    + "'" + question.getTextHead() + "'" + ","
                    + "'" + question.getAnswer() + "'" + ","
                    + "'" + question.getTextTail() + "'" + ","
                    + user_id + ","
                    + course_id + ""
                    + ")RETURNING id;";
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                question.setId(rst.getLong("id"));
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Question question, long user_id, long course_id) {
        try {
            Connection con = getSql().getConnection();
            /* String query = "update question set(text_head, answer, users_id, course_id, text_tail) = ( "
                    + "'" + question.getTextHead() + "'" + "," + "'" + question.getAnswer() + "'" + "," + user_id + "," + course_id + "," + "'" + question.getTextTail() + "'" + ") where question.id = " + question.getId();*/
            String query = "update question set (text_head, answer, users_id, course_id, text_tail) = "
                    + "('" + question.getTextHead() + "','" + question.getAnswer() + "'," + user_id + "," + course_id + "," + "'" + question.getTextTail() + "') where id = " + question.getId();
            System.out.println("query " + query);
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Question question) {
        try {
            Connection con = getSql().getConnection();
            String query = "delete from question where question.id = " + question.getId();
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Question> getQuestionList() {
        List<Question> questionList = new ArrayList<Question>();
        try {
            String query = "select * from question";
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                Course course = getCoursedata().getCourse(rst.getLong("course_id"));
                questionList.add(new Question(rst.getLong("id"), rst.getString("text_head"), rst.getString("answer"), rst.getString("text_tail"), user, course));
            }
            rst.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }

    public Question getQuestion(long questionId) {
        Question question = null;
        try {
            String query = "select * from question where id = " + questionId;
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            if (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                Course course = getCoursedata().getCourse(rst.getLong("course_id"));
                question = new Question(rst.getLong("id"), rst.getString("text_head"), rst.getString("answer"), rst.getString("text_tail"), user, course);
            }
            rst.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return question;
    }

    public List<Question> getQuizQuestionList(long quizID) {
        List<Question> questionList = new ArrayList<Question>();

        try {
            Connection con = getSql().getConnection();
            String query = "select * from Question where id IN(select question_id from quiz_question where quiz_id = " + quizID + ")";
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                Course course = getCoursedata().getCourse(rst.getLong("course_id"));
                questionList.add(new Question(rst.getLong("id"), rst.getString("text_head"), rst.getString("answer"), rst.getString("text_tail"), user, course));
            }
            rst.close();
            st.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;

    }

    public void deleteQuizQuestion(long id) {
        try {
            Connection con = getSql().getConnection();
            String query = "delete from quiz_question where question_id = " + id;
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public CourseDAO getCoursedata() {
        if (coursedata == null) {
            coursedata = new CourseDAO(this.getSql());
        }
        return coursedata;
    }

    public QuizDAO getQuizdata() {
        return quizdata;
    }

}
