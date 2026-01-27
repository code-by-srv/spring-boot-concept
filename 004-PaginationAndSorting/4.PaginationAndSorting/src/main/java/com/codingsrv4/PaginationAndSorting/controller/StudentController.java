package com.codingsrv4.PaginationAndSorting.controller;

import com.codingsrv4.PaginationAndSorting.entity.StudentEntity;
import com.codingsrv4.PaginationAndSorting.repository.StudentRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/students")
public class StudentController {
    private  final StudentRepository studentRepository;

    private final int pageSize = 5;

    @GetMapping("/age")     //sorting using derived query method
    public List<StudentEntity> getAllAgeByAcs(){
        return studentRepository.findAllByOrderByAgeAsc();
    }

    @GetMapping("/height")    //sorting using derived query method
    public List<StudentEntity> getAllInDesc(){
        return studentRepository.findAllByOrderByHeightDesc();
    }

    @GetMapping("/name")   //sorting using derived query method
    public StudentEntity getStudentByName(@PathVariable String name){
        return studentRepository.findByName(name);
    }

    @GetMapping("/sortBy")   // Sort all results by the field name stored in sortBy, in DESCENDING order
    public List<StudentEntity> getSorted(@RequestParam(defaultValue = "id") String sortBy){
        Sort sort = Sort.by(sortBy).descending();
        return studentRepository.findBy(sort);
    }

    @GetMapping("/sort")  // multi-field sorting with single direction.
    public List<StudentEntity> getSortedMultiple(){
        Sort sort = Sort.by(Sort.Direction.ASC,"age","name","height");
        return studentRepository.findAll(sort);
    }

    @GetMapping("/sort1")   // multi-field sorting with different directions
    public List<StudentEntity> getSortedByOrder(){
        Sort sort = Sort.by(Sort.Order.desc("age"), Sort.Order.asc("name"));
        return studentRepository.findAll(sort);
    }

    @GetMapping("/sort2")  // Pagination + sorting
    public Page<StudentEntity> getPageFixed(){
        Pageable pageable = PageRequest.of(0,5,Sort.by("name").ascending());
        return studentRepository.findAll(pageable);
    }

    @GetMapping("/sort3")   // Pagination + sorting
    public Page<StudentEntity> getPageDynamic(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy).descending());
        return studentRepository.findAll(pageable);
    }

    @GetMapping("/sort4")  // filter + Pagination + Sorting
    public List<StudentEntity> findByList(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "id") String sortBy){

            Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy).descending());
            return studentRepository.findByNameContainingIgnoreCase(name,pageable);
    }







}
