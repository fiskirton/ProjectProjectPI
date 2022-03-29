package controllers;

import entity.Answer;
import entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repos.AnswerRepository;
import repos.QuestionRepository;
import repos.TestRepository;

import java.util.Optional;

@Controller
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(value = "/answer", produces = "application/json")
    public ResponseEntity<MapSqlParameterSource> getQuestionByTestId(@PathVariable(value = "id") Long testId,
                                                                     @PathVariable(value = "userNickName") String nickName) {
        if (!testRepository.existsById(testId)) {
            return new ResponseEntity<MapSqlParameterSource>(HttpStatus.BAD_REQUEST);
        }
        Optional<Test> test = testRepository.findById(testId);
        Answer answer = answerRepository.findByTestId(testId);


        //Question question =questionRepository.findBy()
        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("questionNumber", questionRepository.getQuestionNumberByTestId(testId));
        map.addValue("totalQuestion", answerRepository.countByTestId(testId));
        map.addValue("text", answer.getText());
//        map.addValue();// TODO: сюда вопрос
        return new ResponseEntity<MapSqlParameterSource>(HttpStatus.OK);
    }
    @GetMapping()
    public String a()
    {
        return "group-add";
    }


//    @PostMapping(value = "jopa", produces = "application/json")
//    public ResponseEntity<> sendAnswerForQuestion( //TODO: Тип
//            @RequestParam Long testId,
//            @RequestParam Long questionId,
//            @RequestParam String userNickName) {
//        if (!testRepository.existsById(testId)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } else {
//            Answer answer = answerRepository.countByQuestionId(questionId);
//            Long answerId = answer.getAnswerId();
//            return new ResponseEntity<>(answerId.toString(), HttpStatus.OK);
//        }
//    }

}
