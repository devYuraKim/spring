package com.devyurakim.devschool.controller;

import com.devyurakim.devschool.model.Courses;
import com.devyurakim.devschool.model.EazyClass;
import com.devyurakim.devschool.model.Person;
import com.devyurakim.devschool.repository.CoursesRepository;
import com.devyurakim.devschool.repository.EazyClassRepository;
import com.devyurakim.devschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EazyClassRepository eazyClassRepository;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @RequestMapping("/displayClasses")
    public String displayClasses(Model model){
        List<EazyClass> eazyClasses = eazyClassRepository.findAll();
        model.addAttribute("eazyClasses", eazyClasses);
        model.addAttribute("eazyClass", new EazyClass());
        return "classes.html";
    }

    @PostMapping("/addNewClass")
    public String addNewClass(Model model, @ModelAttribute EazyClass eazyClass){
        eazyClassRepository.save(eazyClass);
        return "redirect:/admin/displayClasses";
    }

    @RequestMapping("/deleteClass")
    public String deleteClass(Model model, @RequestParam int id){
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);
        /*
        해당 EazyClass를 참조하고 있는 Person instance가 없어야 EazyClass instance를 삭제 가능.
        Cascading 설정을 안 했기 때문에 수동으로 각 Person instance에서 사용되는 EazyClass FK를 null로 설정해야 함.
        >> 결론: 해당 EazyClass를 수강 중인 모든 Person instances를 찾아서 해당 class reference를 null로 update 후 EazyClass instance 삭제
        */
        for(Person person : eazyClass.get().getPersons()){
            person.setEazyClass(null);
            personRepository.save(person);
        }
        eazyClassRepository.deleteById(id);
        return "redirect:/admin/displayClasses";
    }

    @RequestMapping("/displayStudents")
    public String displayStudents(Model model, @RequestParam int classId, HttpSession session, @RequestParam(required=false) String error){
        Optional<EazyClass> eazyClassOptional = eazyClassRepository.findById(classId);
        EazyClass eazyClass = eazyClassOptional.get();
        /*Optional에 있는 실제 instance를 가져오기 위해서는 get()을 사용해야 함*/
        model.addAttribute("eazyClass", eazyClass);
        /*addStudent할 때 EazyClass 정보를 전달할 방법이 없어서 HttpSession에 담아서 보냄*/
        session.setAttribute("eazyClass", eazyClass);
        /*to bind data from form
        * When using Thymeleaf, if you reference an object in the template (e.g., using th:object or th:field), that object must be added to the model in your controller.
        * If it is not present in the model, Thymeleaf will throw an error because it won't be able to resolve the expression.*/
        model.addAttribute("person", new Person());

        String errorMessage = null;
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            model.addAttribute("errorMessage", errorMessage);
        }
        return "students.html";
    }

    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute Person person, HttpSession session){
        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        Person newStudent = personRepository.readByEmail(person.getEmail());
        if(newStudent==null || !(newStudent.getPersonId()>0)){
            return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId()+"&error=true";
        }
        newStudent.setEazyClass(eazyClass);
        personRepository.save(newStudent);

        eazyClass.getPersons().add(newStudent);
        eazyClassRepository.save(eazyClass);

        return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId();
    }

    @RequestMapping("/deleteStudent")
    String deleteStudent(@RequestParam int personId, HttpSession session){
        Optional<Person> personOptional = personRepository.findById(personId);
        Person toBeDeleted = personOptional.get();

        toBeDeleted.setEazyClass(null);
        personRepository.save(toBeDeleted);

        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        eazyClass.getPersons().remove(toBeDeleted);
        EazyClass eazyClassSaved = eazyClassRepository.save(eazyClass);
        session.setAttribute("eazyClass", eazyClassSaved);

        return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId();
    }

    @GetMapping("/displayCourses")
    public String displayCourses(Model model){
        //List<Courses> courses = coursesRepository.findByOrderByName();
        //아래 method에서 name을 parameter로 받아서 변경하면 그것이 dynamic sorting!
        List<Courses> courses = coursesRepository.findAll(Sort.by("name").descending());
        model.addAttribute("courses", courses);
        model.addAttribute("course", new Courses());
        return "courses_secure.html";
    }

    @RequestMapping("/addNewCourse")
    public String addNewCourse(@ModelAttribute Courses course, Model model){
        Courses newCourse = coursesRepository.save(course);
        return "redirect:/admin/displayCourses";
    }

    @GetMapping("/viewStudents")
    public String viewStudents(Model model, @RequestParam int id, HttpSession session, @RequestParam(required = false) String error){

        String errorMessage = null;

        Optional<Courses> courseOptional = coursesRepository.findById(id);
        Courses course = courseOptional.get();
        model.addAttribute("course", course);
        session.setAttribute("course", course);
        model.addAttribute("person", new Person());

        if(error!=null){
            errorMessage = "Invalid Email address";
            model.addAttribute("errorMessage", errorMessage);
        }
            return "courses_students.html";
    }

    @PostMapping("/addStudentToCourse")
    public String addStudentToCourse(@ModelAttribute Person person, Model model, HttpSession session){

        Courses course = (Courses) session.getAttribute("course");
        String email = person.getEmail();
        Person newStudent = personRepository.readByEmail(email);

        if(newStudent==null || !(newStudent.getPersonId()>0)){
            return "redirect:/admin/viewStudents?id="+course.getCourseId()+"&error=true";
        }

        newStudent.getCourses().add(course);
        course.getPersons().add(newStudent);
        personRepository.save(newStudent);
        session.setAttribute("course", course);

        return "redirect:/admin/viewStudents?id="+course.getCourseId();
    }

    @RequestMapping("/deleteStudentFromCourse")
    public String deleteStudentFromCourse(@RequestParam int personId, HttpSession session){

        Courses course = (Courses) session.getAttribute("course");

        Optional<Person> personOptional = personRepository.findById(personId);
        Person person = personOptional.get();
        person.getCourses().remove(course);
        course.getPersons().remove(person);
        //coursesRepository.save(course); //Cascade가 Persist이기 때문에 위의 personRepository.save(person) 때문에 저장될 거임!
        //session.setAttribute("course", course);

        personRepository.save(person);

        return "redirect:/admin/viewStudents?id="+course.getCourseId();
    }

}
