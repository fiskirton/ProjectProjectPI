package repos;

import entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
     Answer findByTestId(Long testId);
     Answer countByQuestionId(Long questionId);
     @Query("select count (a) from questions a where a.test_id=:testId")
//     Answer findByTestId()
     Long countByTestId(@Param("test_id") Long testId);
//     @Query("select answer_id, text from answers where ")
}
