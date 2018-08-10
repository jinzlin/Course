package com.lin.course.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author by ljz
 * PS:
 */
@Entity
public class CourseEntity {

    @Id(autoincrement = true)
    private Long id;
    private String type;
    private String courseName;
    private String courseName2;
    private String title;
    private String link;
    private String body;


    @Generated(hash = 483818505)
    public CourseEntity() {
    }

    @Generated(hash = 1156180968)
    public CourseEntity(Long id, String type, String courseName, String courseName2,
            String title, String link, String body) {
        this.id = id;
        this.type = type;
        this.courseName = courseName;
        this.courseName2 = courseName2;
        this.title = title;
        this.link = link;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName2() {
        return courseName2;
    }

    public void setCourseName2(String courseName2) {
        this.courseName2 = courseName2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
