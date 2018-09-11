package com.lin.course.bean.model

import com.lin.course.db.entity.ChapterEntity
import com.lin.course.db.entity.CourseListEntity
import com.lin.course.db.entity.SectionEntity

/**
 * Author by ljz
 * PS:
 */
class DbModel(var chapterEntity: ChapterEntity?,
              var courseListEntity: CourseListEntity?,
              var sectionEntity: SectionEntity?,
              val chapterList: ArrayList<ChapterEntity>,
              val courseList: ArrayList<CourseListEntity>,
              val sectionList: ArrayList<SectionEntity>) {

    constructor() : this(null, null, null, ArrayList<ChapterEntity>(), ArrayList<CourseListEntity>(), ArrayList<SectionEntity>())
}