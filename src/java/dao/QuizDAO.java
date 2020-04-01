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
public class QuizDAO {

    private SQLConnection sql;
    private UserDAO userdata;
    private QuestionDAO questiondata;

    public QuizDAO(SQLConnection sql) {
        this.sql = sql;
    }

    public QuizDAO() {
    }

    public void create(Quiz quiz, Long userId) {

        try {
            String query = "insert into quiz values(DEFAULT,"
                    + "'" + quiz.getName() + "'" + ","
                    + "'" + quiz.getComment() + "'" + ","
                    + "'" + userId + "'"
                    + ")RETURNING quiz.id;";
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                quiz.setId(rst.getLong("id"));
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(Quiz quiz, Long userId) {
        try {

            String query = "update quiz set (name, comment, users_id) = ( "
                    + "'" + quiz.getName() + "'" + "," + "'" + quiz.getComment() + "'" + "," + userId + ") where quiz.id = " + quiz.getId();
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);

            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Quiz quiz) {
        try {
            String query = "delete from quiz where quiz.id = " + quiz.getId();
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);

            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createQuizCategory(long quiz_id, List<Long> category_ids) {
        try {
            Connection con = getSql().getConnection();
            for (int i = 0; i < category_ids.size(); i++) {
                Statement st = con.createStatement();
                String query = "insert into quiz_category values(" + quiz_id + "," + category_ids.get(i) + ");";
                st.executeUpdate(query);
                st.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createQuizQuestion(long quiz_id, List<Long> questionIds) {
        try {
            Connection con = getSql().getConnection();
            for (int i = 0; i < questionIds.size(); i++) {
                Statement st = con.createStatement();
                String query = "insert into quiz_question values(" + quiz_id + "," + questionIds.get(i) + ");";
                st.executeUpdate(query);
                st.close();
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Quiz> getQuizCategoryList(long id) {
        List<Quiz> quizList = new ArrayList<Quiz>();
        try {
            Connection con = getSql().getConnection() ;
            String query = "select * from quiz where id IN(select quiz_id from quiz_category where category_id = " + id + " );";
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                List<Question> questionList = getQuestiondata().getQuizQuestionList(rst.getLong("id"));
                quizList.add(new Quiz(rst.getLong("id"), rst.getString("name"), rst.getString("comment"), user, questionList));

            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quizList;
    }

    public List<Quiz> getQuizList() {
        List<Quiz> quizList = new ArrayList<Quiz>();
        try {
            String query = "select * from Quiz";
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                List<Question> questionList = getQuestiondata().getQuizQuestionList(rst.getLong("id"));
                quizList.add(new Quiz(rst.getLong("id"), rst.getString("name"), rst.getString("comment"), user, questionList));

            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quizList;
    }

    public Quiz getQuiz(long id) {
        Quiz quiz = null;
        try {
            String query = "select * from Quiz where id = " + id;
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            if (rst.next()) {
                User user = getUserdata().getUser(rst.getLong("users_id"));
                List<Question> questionList = getQuestiondata().getQuizQuestionList(rst.getLong("id"));
                quiz = new Quiz(rst.getLong("id"), rst.getString("name"), rst.getString("comment"), user, questionList);

            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quiz;
    }

    public UserDAO getUserdata() {
        if (userdata == null) {
            userdata = new UserDAO(this.getSql());
        }
        return userdata;
    }

    public QuestionDAO getQuestiondata() {
        if (questiondata == null) {
            questiondata = new QuestionDAO(this.getSql());
        }
        return questiondata;
    }

    public SQLConnection getSql() {
        if (sql == null) {
            sql = new SQLConnection();
        }
        return sql;
    }

}
