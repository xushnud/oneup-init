package uz.oneup.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.oneup.course.entity.Course;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by babayev.xushnud@gmail.com on 12/22/2021.
 * Project oneup-course
 */
@RequestMapping("/api/v1/course")
@RestController
public class CourseControllerV1 {

    private List<Course> courseList = Stream.of(
            new Course(1L, "Java course v1", "Oneup Course V1"),
            new Course(2L, "Java course v2", "Oneup Course V2")
    ).collect(Collectors.toList());

    /**
     * Create
     * @param course from json
     * @return created Course
     */
    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) throws URISyntaxException {
        courseList.add(course);
        Course createdCourse = course;

        if (createdCourse == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdCourse.getId())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(createdCourse);
        }
    }

    /**
     * Read
     * @param id of course from Path
     * @return founded Course
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> read(@PathVariable Long id) {
        Course foundedCourse = courseList
                .stream()
                .filter(userEntity -> userEntity.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (foundedCourse == null || foundedCourse.getId()==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundedCourse);
        }
    }

    /**
     * Update
     * @param course from body
     * @param id of course from Path
     * @return updated Course
     */
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@RequestBody Course course, @PathVariable Long id) {
        Course updatedCourse = courseList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst().orElseGet(Course::new);

        if (updatedCourse == null || updatedCourse.getId()==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedCourse);
        }
    }

    /**
     * Delete
     * @param id of course from Path
     * @return no Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        courseList.removeIf(course -> course.getId().equals(id));

        return ResponseEntity.noContent().build();
    }

    /**
     * List
     * @return All Course List
     */
    @GetMapping
    public List<Course> list() {
        return courseList;
    }

}
