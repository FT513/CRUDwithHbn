package com.sasha.CRUDwithHbn.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table (name = "posts", catalog = "datahiber")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private Date created;

    @Column(name = "updated")
    @Temporal(TemporalType.DATE)
    private Date updated;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "post_labels",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")

    )
        private List<Label> labels = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "post_status")
    private PostStatus postStatus;

    @ManyToMany(mappedBy = "posts", fetch = FetchType.LAZY)
    private List<Writer> writers;


    public Post() {
    }

    public Post(Integer id, String content, Date created, Date updated, List<Label> labels, PostStatus postStatus) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.postStatus = postStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", labels=" + labels +
                '}';
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }


}
