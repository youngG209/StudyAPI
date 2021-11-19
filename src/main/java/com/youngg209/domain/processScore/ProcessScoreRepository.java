package com.youngg209.domain.processScore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProcessScoreRepository extends JpaRepository<ProcessScore, Long> {

    @Query("select ss from STUDENT_SUBJECT ss where ss.student.studentId = :studentId and ss.subject.subjectId = :subjectId")
    ProcessScore findByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);

    @Query("select ss from STUDENT_SUBJECT ss where ss.subject.subjectId = :subjectId")
    List<ProcessScore> findBySubjectId(@Param("subjectId") Long subjectId);

    @Query("select ss from STUDENT_SUBJECT ss where ss.student.studentId = :studentId")
    List<ProcessScore> findByStudentId(@Param("studentId") Long studentId);

    @Modifying
    @Transactional
    @Query("delete from STUDENT_SUBJECT ss where ss.student.studentId = :studentId")
    void deleteByStudent(@Param("studentId") Long studentId);

    @Modifying
    @Transactional
    @Query("delete from STUDENT_SUBJECT ss where ss.subject.subjectId = :subjectId")
    void deleteBySubject(@Param("subjectId") Long subjectId);

}
