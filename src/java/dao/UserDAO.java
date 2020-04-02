package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sqlConnection.SQLConnection;
import entity.User;
import java.sql.Connection;

public class UserDAO {

    private SQLConnection sql;

    public UserDAO() {
    }

    public UserDAO(SQLConnection sql) {
        this.sql = sql;
    }
    
    public SQLConnection getSql() {
        if (sql == null) {
            sql = new SQLConnection();
        }
        return sql;
    }
    public void create(User user) {

        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "insert into users values(DEFAULT,"
                    + "'" + user.getNickname() + "'" + ","
                    + "'" + user.getPassword() + "'" + ","
                    + "'" + user.getNameSurname() + "'" + ","
                    + "'" + user.getAddress() + "'" + ","
                    + "'" + user.getCountry() + "'" + ")RETURNING id;";
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                user.setId(rst.getLong("id"));
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(User user) {

        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "update Users set"
                    + "(nickname, password,name_surname,address,country)= (" + "'" + user.getNickname() + "'" + "," + "'" + user.getPassword() + "'" + "," + "'" + user.getNameSurname() + "'" + "," + "'" + user.getAddress() + "'" + "," + "'" + user.getCountry() + "'" + ") where users.id = " + user.getId();
            st.executeUpdate(query);
            System.out.println(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        try {
            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            String query = "delete from users where users.id = " + user.getId();
            st.executeUpdate(query);
            System.out.println(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public User getUser(long id) {
        User user = null;
        try {
            Connection con = getSql().getConnection() ;
            Statement st = con.createStatement();
            String query = "select * from users where id = " + id;
            ResultSet rst = st.executeQuery(query);
            if (rst.next()) {
                user = new User(rst.getLong("id"), rst.getString("nickname"), rst.getString("password"), rst.getString("name_surname"), rst.getString("address"), rst.getString("country"));
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getUserList() {
        List<User> userList = new ArrayList<User>();
        try {

            Connection con = getSql().getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("select * from users");

            while (rst.next()) {
                userList.add(new User(rst.getLong("id"), rst.getString("nickname"), rst.getString("password"), rst.getString("name_surname"), rst.getString("address"), rst.getString("country")));
            }
            rst.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

}
