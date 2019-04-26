package com.springtest.model;

import com.springtest.model.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class User extends BaseModel<Long> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

	@Column
	private String login;

    @Column
    private String password;

    @Column
    private Role role;

    @Column
    private String pathImg;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return firstName + (secondName != null ? " " + secondName.charAt(0) + "." : "");
    }
}


