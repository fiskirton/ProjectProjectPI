package com.crm.crm.repos;

import com.crm.crm.entity.Question;
import com.crm.crm.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT ua.user_nick_name as \"userNickName\", SUM(a.is_right) * 10 as \"score\" FROM user_answer ua \n" +
            "INNER JOIN answer a on a.answer_id = ua.answer_id\n" +
            "INNER JOIN question q on ua.question_id = q.question_id\n" +
            "WHERE q.test_id = :test_id\n" +
            "GROUP BY ua.user_nick_name\n" +
            "ORDER BY score DESC;\n",nativeQuery = true)

    List<String> getQuestionNumberByTestId(@Param("test_id") Long testId);

    List<Question> findAllByTestId(Long testId);

    @Query(value = "select * from question where question_number = :question_number and test_id = :test_id", nativeQuery = true)
    Question getQuestion(@Param("question_number") Long questionNumber, @Param("test_id") Long testId);

//    Collection<Question> find



}
