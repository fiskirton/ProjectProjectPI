package controllers;


import DataBaseSheet.TestTable;
import entity.Answer;
import exceptions.TestNotFoundExceprion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import entity.Test;
import repos.TestRepository;
import services.TestServiceH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TestController {
    TestServiceH testService;

    @Autowired
    private TestTable testTable;

    @Autowired
    private TestRepository testRepository;

    @PostMapping("/create_test")
    @ResponseStatus(HttpStatus.CREATED)
    public void writeTest(@RequestBody Test request) {//добавление нового теста
        testTable.addTest(request.getTestId(), request.getTitle(), request.getStartAt(), request.getEndAt());
    }

//    @GetMapping("{testID}/hu") //НУЖЕН контроллер для узер ответов
//    public List<Test> getQuestion(
//            @PathVariable("test_id") int testID,
//            @PathVariable("quest_id") int questID,
//            @PathVariable("userNickName") String userNickName)
//    {
//        return testTable.;
//    }


    @GetMapping("/tests/{id}")
    @ResponseBody
    public Map<String, String> getTestStatus(@PathVariable(value = "id") Long testId) {
        if (!testRepository.existsById(testId)) {
            throw new TestNotFoundExceprion();
        }
        Optional<Test> test = testRepository.findById(testId);
        HashMap<String, String> map = new HashMap<>();
        if (test.get().getEndAt().isAfter(test.get().getStartAt())) {
            map.put("status", "?");
            return map;

        } else if (test.get().getStartAt().isAfter(test.get().getEndAt())) {
            map.put("status", "finished");
            return map;
        } else {
            map.put("status", "notFound");
            return map;
        }

    }

//    @PostMapping(path = "/testsssss", consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED) //TODO: какая то хуйня хз как заполнять это говно
//    public void createTest(@RequestBody Test requset) //@Valid
//    {
//        Test test = new Test(requset.getTestId(), requset.getTitle(), requset.getStartAt(), requset.getEndAt());
//        Answer answer = new Answer();
//        testRepository.save(test);
//    }


//    @GetMapping(path = "huina", consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<MapSqlParameterSource> getTestStartTime(@RequestParam Long testId) {
//        if (!testRepository.existsById(testId)) {
//            return new ResponseEntity<MapSqlParameterSource>(HttpStatus.BAD_REQUEST);
//        }
//        MapSqlParameterSource map = new MapSqlParameterSource();
//        Test test = (Test) testRepository.findTestByID(testId);
//        map.addValue("startAt", test.getStartAt());
//        map.addValue("finishAt", test.getEndAt());
//        return //TODO: возвращать нужно строку, либо выебываться, либо кастить мапу к строке, либо без http респонсов
//
//    }


    @RequestMapping(path = "/error")
    public MapSqlParameterSource getAllTests() {
        List<Test> tests = testRepository.findAll();
        MapSqlParameterSource map = new MapSqlParameterSource();
        for (Test test : tests) {
            map.addValue("id", test.getTestId());
            map.addValue("title", test.getTitle());
        }
        return map;
    }


}
