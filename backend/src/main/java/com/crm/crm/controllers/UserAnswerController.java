package com.crm.crm.controllers;

import com.crm.crm.entity.Answer;
import com.crm.crm.entity.UserAnswer;
import com.crm.crm.repos.AnswerRepository;
import com.crm.crm.repos.QuestionRepository;
import com.crm.crm.repos.TestRepository;
import com.crm.crm.repos.UserAnswerRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserAnswerController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @PostMapping("/sendAnswer")
    public Object sendAnswerForQuestion(@RequestParam(value = "answer_id") Long answerId,
                                        @RequestParam(value = "question_id") Long questionId,
                                        @RequestParam(value = "user_nick_name") String userNickName) {

        if (!answerRepository.existsById(answerId) || !questionRepository.existsById(questionId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            UserAnswer userAnswer = new UserAnswer(answerId, questionId, userNickName);
            userAnswerRepository.save(userAnswer);
        }
        List<Answer> answers = answerRepository.findRightAnswer(questionId);
        return answers.get(0);
    }
}
