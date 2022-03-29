package com.crm.crm.repos;

import com.crm.crm.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findById(Long testId);

    @Query(value = "select * from answer where is_right = 1 and question_id = :question_id limit 1", nativeQuery = true)
    List<Answer> findRightAnswer(@Param("question_id") Long questionId);

    @Query(value = "select  count(*) from question where test_id =:test_id", nativeQuery = true)
    Long countAnswersByTestId(@Param("test_id") Long testId);

    @Query(value = "select * from answer where question_id =:question_id", nativeQuery = true)
    List<Answer> getAllAnswers(@Param("question_id") Long questionId);
}
