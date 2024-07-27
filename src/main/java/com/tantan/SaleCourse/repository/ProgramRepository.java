package com.tantan.SaleCourse.repository;

import com.tantan.SaleCourse.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {
}
