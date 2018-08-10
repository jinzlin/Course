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
    private String courseTitle;
    private String courseContent;
    private String courseType;
    private String courseLink;
    private String courseImg;

    public CourseListEntity(String courseTitle, String courseContent, String courseType, String courseLink, String courseImg) {
        this.courseTitle = courseTitle;
        this.courseContent = courseContent;
        this.courseType = courseType;
        this.courseLink = courseLink;
        this.courseImg = courseImg;
    }

    @Generated(hash = 531830211)
    public CourseListEntity(Long id, String courseTitle, String courseContent,
            String courseType, String courseLink, String courseImg) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.courseContent = courseContent;
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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
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

    @Override
    public String toString() {
        return "CourseListEntity{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseContent='" + courseContent + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseLink='" + courseLink + '\'' +
                ", courseImg='" + courseImg + '\'' +
                '}';
    }
}
