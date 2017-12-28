package com.er.model;

public abstract class AbstractNamedEntity extends AbstractEntity {
    protected String name;

    public AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(String name) {
        this.name = name;
    }

    protected AbstractNamedEntity(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, name);
    }

}
