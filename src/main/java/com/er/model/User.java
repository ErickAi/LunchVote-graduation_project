package com.er.model;

import java.util.Set;

public class User extends AbstractNamedEntity{
    private String email;
    private String password;
    private Set<Role> role;
    private Integer vote;

    public User() {
    }

    public User(String name, String email, String password) {
        super(name);
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String email, String password, Set<Role> role, int vote) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.role = role;
        this.vote = vote;
    }
    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRoles(),u.getVote());
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

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role=" + role +
                ", vote=" + vote +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
