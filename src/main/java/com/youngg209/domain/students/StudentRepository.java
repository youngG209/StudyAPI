package com.youngg209.domain.students;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    int countByPhoneNumber(String phone);

    List<Student> findAll();
}
