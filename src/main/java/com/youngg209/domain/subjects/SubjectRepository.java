package com.youngg209.domain.subjects;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    int countByName(String name);

}
