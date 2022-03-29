package repos;

import entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select question_number from questions where test_id =:testId")
    Long getQuestionNumberByTestId(@Param("testId") Long testId);
}
