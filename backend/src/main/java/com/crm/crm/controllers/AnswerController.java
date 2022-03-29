package com.crm.crm.controllers;

import com.crm.crm.entity.Answer;
import com.crm.crm.entity.Test;
import com.crm.crm.repos.AnswerRepository;
import com.crm.crm.repos.QuestionRepository;
import com.crm.crm.repos.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class AnswerController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

}
