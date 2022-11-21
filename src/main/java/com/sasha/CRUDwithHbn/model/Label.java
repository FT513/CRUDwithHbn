package com.sasha.CRUDwithHbn.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "labels", catalog = "datahiber")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "labels", fetch = FetchType.LAZY)
    private List<Post> posts;

    public Label(){

    }

    public Label(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id =" + id +
                "| name ='" + name + '\'' +
                '}';
    }
}
