package com.lin.course.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lin.course.db.entity.CourseEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COURSE_ENTITY".
*/
public class CourseEntityDao extends AbstractDao<CourseEntity, Long> {

    public static final String TABLENAME = "COURSE_ENTITY";

    /**
     * Properties of entity CourseEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type = new Property(1, String.class, "type", false, "TYPE");
        public final static Property CourseName = new Property(2, String.class, "courseName", false, "COURSE_NAME");
        public final static Property CourseName2 = new Property(3, String.class, "courseName2", false, "COURSE_NAME2");
        public final static Property Title = new Property(4, String.class, "title", false, "TITLE");
        public final static Property Link = new Property(5, String.class, "link", false, "LINK");
    }


    public CourseEntityDao(DaoConfig config) {
        super(config);
    }
    
    public CourseEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COURSE_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TYPE\" TEXT," + // 1: type
                "\"COURSE_NAME\" TEXT," + // 2: courseName
                "\"COURSE_NAME2\" TEXT," + // 3: courseName2
                "\"TITLE\" TEXT," + // 4: title
                "\"LINK\" TEXT);"); // 5: link
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COURSE_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CourseEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(2, type);
        }
 
        String courseName = entity.getCourseName();
        if (courseName != null) {
            stmt.bindString(3, courseName);
        }
 
        String courseName2 = entity.getCourseName2();
        if (courseName2 != null) {
            stmt.bindString(4, courseName2);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(6, link);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CourseEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(2, type);
        }
 
        String courseName = entity.getCourseName();
        if (courseName != null) {
            stmt.bindString(3, courseName);
        }
 
        String courseName2 = entity.getCourseName2();
        if (courseName2 != null) {
            stmt.bindString(4, courseName2);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(6, link);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CourseEntity readEntity(Cursor cursor, int offset) {
        CourseEntity entity = new CourseEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // courseName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // courseName2
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // link
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CourseEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCourseName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCourseName2(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLink(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CourseEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CourseEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CourseEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}