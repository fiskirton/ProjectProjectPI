package com.crm.crm.repos;

import com.crm.crm.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
//    public Test getById(Long id);
//    public Test findTestById(Long id);
    Optional<Test> findById(Long id);
}
