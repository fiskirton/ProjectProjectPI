package com.crm.crm.controllers;

import com.crm.crm.entity.Answer;
import com.crm.crm.entity.Question;
import com.crm.crm.entity.Rating;
import com.crm.crm.entity.Test;
import com.crm.crm.repos.AnswerRepository;
import com.crm.crm.repos.QuestionRepository;
import com.crm.crm.repos.TestRepository;
import com.crm.crm.repos.UserAnswerRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RestController
@CrossOrigin
public class TestController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @GetMapping("/tests/{id}/status")
    @ResponseBody
    public Map<String, String> getTestStatus(@PathVariable(value = "id") Long testId) {
        Optional<Test> test = testRepository.findById(testId);
        HashMap<String, String> map = new HashMap<>();
        if (!testRepository.existsById(testId)) {
            map.put("status", "notFound");
            return map;
        }
        if (test.get().getStartAt().isAfter(LocalDateTime.now())) {
            map.put("status", "notStarted");
            return map;
        } else if (LocalDateTime.now().isAfter(test.get().getEndAt())) {
            map.put("status", "finished");
            return map;
        } else {
            map.put("status", "inProgress");
            return map;
        }
    }

    @GetMapping("/tests/{id}/time")
    @ResponseBody
    public Object getTestTime(@PathVariable(value = "id") Long testId) {
        if (!testRepository.existsById(testId)) {
            return new ResponseEntity<MapSqlParameterSource>(HttpStatus.BAD_REQUEST);
        }
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("startAt", testRepository.findById(testId).get().getStartAt());
        map.addValue("endAt", testRepository.findById(testId).get().getEndAt());
        return map.getValues();
    }

    @GetMapping("/tests/allTests")
    @ResponseBody
    public Object getAllTests() {
        List<Test> tests = testRepository.findAll();
        List< Map<String, Object>> maps= new ArrayList< Map<String, Object>>();
        for (Test test : tests) {
            MapSqlParameterSource newMap = new MapSqlParameterSource();
            newMap.addValue("id", test.getTestId());
            newMap.addValue("title", test.getTitle());
            maps.add(newMap.getValues());
        }
        return maps;
    }

    @GetMapping("/tests/{id}/getRating")
    @ResponseBody
    public ArrayList<Map<String, Object>> getRating(@PathVariable("id") Long testId) {
        List<String> response = questionRepository.getQuestionNumberByTestId(testId);
        ArrayList<Map<String, Object>> mapArrayList = new ArrayList<>();
        for (String string : response) {
            mapArrayList.add(parseString(string));
        }
        return mapArrayList;
    }

    private Map<String, Object> parseString(String string) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        String string2 = string.replace('"', ' ');
        String[] substr = string2.split(",");
        map.addValue("name", substr[0]);
        map.addValue("score", substr[1]);
        return map.getValues();
    }

    @GetMapping("/getCurrentAnswer")
    private Object getCurrentAnswer(@RequestParam("test_id") Long testId,
                                    @RequestParam("user_nick_name") String userNickName) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        Long questionNumber = answerRepository.countAnswersByTestId(testId);//все вопросы для данного теста
        List<Question> testQuestions = questionRepository.findAllByTestId(testId);
        Long userAnswerNumber = userAnswerRepository.countByUserNickName(userNickName);//число ответов данное этим пользователем
        Question questionToSend = new Question();
        if(userAnswerNumber>=questionNumber) {
            return new ResponseEntity<MapSqlParameterSource>(HttpStatus.BAD_REQUEST);
        }
        Question question = questionRepository.getQuestion(userAnswerNumber+1, testId);
        List<Answer> getAllAnswers = answerRepository.getAllAnswers(question.getQuestionId());
        for (Answer answer : getAllAnswers)
        {
            answer.setRight(null);
        }
        map.addValue("question", question);
        map.addValue("answer", getAllAnswers);
        return map.getValues();
    }
}
