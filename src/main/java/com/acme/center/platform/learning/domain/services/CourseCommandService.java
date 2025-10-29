package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;

/**
 * CourseCommandService
 * Service that handles course commands
 */
public interface CourseCommandService {
    /**
     * Handle a create course command
     * @param command The create course command containing the course data
     * @return The created course
     * @see CreateCourseCommand
     */
    Long handle(CreateCourseCommand command);
    /**
     * Handle an update course command
     * @param command The update course command containing the course data
     * @return The updated course
     * @see UpdateCourseCommand
     */
    Optional<Course> handle(UpdateCourseCommand command);
    /**
     * Handle a delete course command
     * @param command The delete course command containing the course id
     * @see DeleteCourseCommand
     */
    void handle(DeleteCourseCommand command);
    /**
     * Handle an add tutorial to course learning path command
     * @param command The add tutorial to course learning path command containing the course id and tutorial id
     * @see AddTutorialToCourseLearningPathCommand
     */
    void handle(AddTutorialToCourseLearningPathCommand command);
}
