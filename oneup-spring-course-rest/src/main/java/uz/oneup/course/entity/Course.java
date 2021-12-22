package uz.oneup.course.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by babayev.xushnud@gmail.com on 12/22/2021.
 * Project oneup-course
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private Long id;
    private String name;
    private String description;
}
