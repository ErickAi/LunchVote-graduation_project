package com.er.model;

public class Restaurant extends AbstractNamedEntity {

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(name);
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
