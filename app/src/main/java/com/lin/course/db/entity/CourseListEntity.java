package com.lin.course.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author by ljz
 * PS:
 */
@Entity
public class CourseListEntity {

    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String courseName;
    private String courseType;
    private String courseLink;
    private String courseImg;

    @Generated(hash = 405005451)
    public CourseListEntity(Long id, String courseName, String courseType,
            String courseLink, String courseImg) {
        this.id = id;
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseLink = courseLink;
        this.courseImg = courseImg;
    }

    @Generated(hash = 1221559686)
    public CourseListEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }
}
