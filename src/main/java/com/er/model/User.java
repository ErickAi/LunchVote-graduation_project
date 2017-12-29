package com.er.model;

import java.util.Set;

public class User extends AbstractNamedEntity{
    private String email;
    private String password;
    private Set<Role> role;

    public User() {
    }

    public User(String name, String email, String password) {
        super(name);
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String email, String password, Set<Role> role) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRoles());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return role;
    }

    public void setRoles(Set<Role> role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role=" + role +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
