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
public class CourseTypeEntity {

    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String typeName;

    public CourseTypeEntity(String typeName) {
        this.typeName = typeName;
    }

    @Generated(hash = 424651925)
    public CourseTypeEntity(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    @Generated(hash = 384486730)
    public CourseTypeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "CourseTypeEntity{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
}
