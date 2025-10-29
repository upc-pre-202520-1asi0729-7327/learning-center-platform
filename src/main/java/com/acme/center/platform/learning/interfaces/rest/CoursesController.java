package com.acme.center.platform.learning.interfaces.rest;

import com.acme.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.center.platform.learning.domain.services.CourseCommandService;
import com.acme.center.platform.learning.domain.services.CourseQueryService;
import com.acme.center.platform.learning.interfaces.rest.resources.CourseResource;
import com.acme.center.platform.learning.interfaces.rest.resources.CreateCourseResource;
import com.acme.center.platform.learning.interfaces.rest.resources.UpdateCourseResource;
import com.acme.center.platform.learning.interfaces.rest.transform.CourseResourceFromEntityAssembler;
import com.acme.center.platform.learning.interfaces.rest.transform.CreateCourseCommandFromResourceAssembler;
import com.acme.center.platform.learning.interfaces.rest.transform.UpdateCourseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * CoursesController
 * <p>
 *     All course-related endpoints.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/courses", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "Available Course Endpoints")
public class CoursesController {
    private final CourseCommandService courseCommandService;
    private final CourseQueryService courseQueryService;


    /**
     * Constructor
     *
     * @param courseCommandService The {@link CourseCommandService} instance
     * @param courseQueryService   The {@link CourseQueryService} instance
     */
    public CoursesController(CourseCommandService courseCommandService, CourseQueryService courseQueryService) {
        this.courseCommandService = courseCommandService;
        this.courseQueryService = courseQueryService;
    }

    /**
     * Create a new course
     *
     * @param resource The {@link CreateCourseResource} instance
     * @return The {@link CourseResource} resource for the created course
     */
    @PostMapping
    @Operation(summary = "Create a new course", description = "Create a new course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    public ResponseEntity<CourseResource> createCourse(@RequestBody CreateCourseResource resource) {
        var createCourseCommand = CreateCourseCommandFromResourceAssembler.toCommandFromResource(resource);
        var courseId = courseCommandService.handle(createCourseCommand);
        if (courseId == null || courseId == 0L) return ResponseEntity.badRequest().build();
        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(getCourseByIdQuery);
        if (course.isEmpty()) return ResponseEntity.notFound().build();
        var courseEntity = course.get();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(courseEntity);
        return new ResponseEntity<>(courseResource, HttpStatus.CREATED);
    }

    /**
     * Get course by id
     *
     * @param courseId The course id
     * @return The {@link CourseResource} resource for the course
     */
    @GetMapping("/{courseId}")
    @Operation(summary = "Get course by id", description = "Get course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    public ResponseEntity<CourseResource> getCourseById(@PathVariable Long courseId) {
        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(getCourseByIdQuery);
        if (course.isEmpty()) return ResponseEntity.notFound().build();
        var courseEntity = course.get();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(courseEntity);
        return ResponseEntity.ok(courseResource);
    }

    /**
     * Get all courses
     *
     * @return The list of {@link CourseResource} resources for all courses
     */
    @GetMapping
    @Operation(summary = "Get all courses", description = "Get all courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses found"),
            @ApiResponse(responseCode = "404", description = "Courses not found")})
    public ResponseEntity<List<CourseResource>> getAllCourses() {
        var courses = courseQueryService.handle(new GetAllCoursesQuery());
        if (courses.isEmpty()) return ResponseEntity.notFound().build();
        var courseResources = courses.stream()
                .map(CourseResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(courseResources);
    }

    /**
     * Update course
     *
     * @param courseId The course id
     * @param resource The {@link UpdateCourseResource} instance
     * @return The {@link CourseResource} resource for the updated course
     */
    @PutMapping("/{courseId}")
    @Operation(summary = "Update course", description = "Update course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    public ResponseEntity<CourseResource> updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseResource resource) {
        var updateCourseCommand = UpdateCourseCommandFromResourceAssembler.toCommandFromResource(courseId, resource);
        var updatedCourse = courseCommandService.handle(updateCourseCommand);
        if (updatedCourse.isEmpty()) return ResponseEntity.notFound().build();
        var updatedCourseEntity = updatedCourse.get();
        var updatedCourseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(updatedCourseEntity);
        return ResponseEntity.ok(updatedCourseResource);
    }

    /**
     * Delete course
     *
     * @param courseId The course id
     * @return The message for the deleted course
     */
    @DeleteMapping("/{courseId}")
    @Operation(summary = "Delete course", description = "Delete course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        var deleteCourseCommand = new DeleteCourseCommand(courseId);
        courseCommandService.handle(deleteCourseCommand);
        return ResponseEntity.ok("Course with given id successfully deleted");
    }
}
