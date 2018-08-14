package com.lin.course.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author by ljz
 * PS:
 */
@Entity
public class SectionEntity {

    @Id(autoincrement = true)
    private Long id;
    private String chapterName;
    private String courseName;
    private String title;
    private String link;
    private String body;

    public SectionEntity(String chapterName, String courseName, String title, String link, String body) {
        this.chapterName = chapterName;
        this.courseName = courseName;
        this.title = title;
        this.link = link;
        this.body = body;
    }

    @Generated(hash = 527172802)
    public SectionEntity(Long id, String chapterName, String courseName, String title, String link, String body) {
        this.id = id;
        this.chapterName = chapterName;
        this.courseName = courseName;
        this.title = title;
        this.link = link;
        this.body = body;
    }

    @Generated(hash = 1413967982)
    public SectionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    @Override
    public String toString() {
        return "SectionEntity{" +
                "id=" + id +
                ", chapterName='" + chapterName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
