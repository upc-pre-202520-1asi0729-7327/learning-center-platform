package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * CourseQueryService
 * Service that handles course queries
 */
public interface CourseQueryService {
    /**
     * Handle a get course by id query
     * @param query The get course by id query containing the course id
     * @return The course
     * @see GetCourseByIdQuery
     */
    Optional<Course> handle(GetCourseByIdQuery query);
    /**
     * Handle a get all courses query
     * @param query The get all courses query
     * @return The list of courses
     * @see GetAllCoursesQuery
     */
    List<Course> handle(GetAllCoursesQuery query);
    /**
     * Handle a get learning path item by course id and tutorial id query
     * @param query The get learning path item by course id and tutorial id query containing the course id and tutorial id
     * @return The learning path item
     * @see GetLearningPathItemByCourseIdAndTutorialIdQuery
     */
    Optional<LearningPathItem> handle(GetLearningPathItemByCourseIdAndTutorialIdQuery query);
}
