package repos;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findTestByID(Long id);
}
