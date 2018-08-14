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
    private String courseContent;
    private String chapterName;
    private String courseLink;
    private String courseImg;

    public CourseListEntity(String courseName, String courseContent, String chapterName, String courseLink, String courseImg) {
        this.courseName = courseName;
        this.courseContent = courseContent;
        this.chapterName = chapterName;
        this.courseLink = courseLink;
        this.courseImg = courseImg;
    }


    @Generated(hash = 1305087935)
    public CourseListEntity(Long id, String courseName, String courseContent, String chapterName, String courseLink,
            String courseImg) {
        this.id = id;
        this.courseName = courseName;
        this.courseContent = courseContent;
        this.chapterName = chapterName;
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

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
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
                ", courseName='" + courseName + '\'' +
                ", courseContent='" + courseContent + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", courseLink='" + courseLink + '\'' +
                ", courseImg='" + courseImg + '\'' +
                '}';
    }
}
