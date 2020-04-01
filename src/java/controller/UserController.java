/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@ManagedBean(name = "UserController")
//@Named
@SessionScoped
public class UserController implements Serializable {

    private User user;
    private UserDAO userdata;
    private List<User> userList;

    public UserController() {
    }

    public UserDAO getUserdata() {
        if (userdata == null) {
            userdata = new UserDAO();
        }
        return userdata;
    }

    public String create() {
        getUserdata().create(getUser());
        return "/users/show";
    }

    public String update(User user) {
        this.user = user;
        return "/users/update";
    }

    public String update() {
        getUserdata().update(this.getUser());
        return "/users/show";
    }

    public String delete(User user) {
        getUserdata().delete(user);
        return "/users/show";
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        userList = getUserdata().getUserList();
        return userList;
    }

}
