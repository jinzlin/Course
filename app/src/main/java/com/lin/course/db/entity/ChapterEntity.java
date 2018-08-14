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
public class ChapterEntity {

    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String name;

    public ChapterEntity(String name) {
        this.name = name;
    }

    @Generated(hash = 2036948822)
    public ChapterEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1142697545)
    public ChapterEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "ChapterEntity{" +
                "id=" + id +
                ", typeName='" + name + '\'' +
                '}';
    }

}
