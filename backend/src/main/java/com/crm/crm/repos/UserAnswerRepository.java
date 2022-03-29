package com.crm.crm.repos;

import com.crm.crm.entity.Answer;
import com.crm.crm.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    @Query(value = "select count(*) from user_answer where user_nick_name = :user_nick_name", nativeQuery =true)
    Long countByUserNickName(@Param("user_nick_name") String userNickName);
}
