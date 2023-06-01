package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/students/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Wyatt",
                "Combs"
        );
//        return new ResponseEntity<>(student, HttpStatus.OK);
        return  ResponseEntity.ok().header("custom-header", "Wyatt")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Wyatt", "Combs"));
        students.add(new Student(2, "Jackson", "Smith"));
        students.add(new Student(3, "Luke", "Weidner"));
        students.add(new Student(4, "Mac", "Jett"));
        return ResponseEntity.ok(students);
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1/wyatt/combs
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student =  new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Lincoln&lastName=Combs
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API that handles HTTP POST Request - creating new resource
    // http://localhost:8080/students/create
    // @PostMapping and @RequestBody
    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring BOOT REST API that handles HTTP PUT Request - update existing resource
    // http://localhost:8080/students/{id}/update
    // @PutMapping and @RequestBody
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                 @PathVariable("id") int studentId) {
        System.out.println(studentId);
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API that handles HTTP DELETE Request - delete existing resource
    // http://localhost:8080/students/{id}/delete
    // @DeleteMapping
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println("StudentId: " + studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }

}
