package DataBaseSheet;

import Mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import entity.Test;
import repos.TestRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TestTable{

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestRepository testRepository;

    private static final String SQL_ADD_TEST =
            ("insert into test_table(test_id, title, startAt, duration) values (:testID, :title, :startAt, :duration)");
    private static final String SQL_GET_TEST_START_TIME =
            ("select startAt from test_table where test_id = :test_id");
    private static final String SQL_GET_TEST_END_TIME =
            ("select endAt from test_table where test_id = :test_id");
    private static final String SQL_GET_ALL_TESTS =
            ("select * ");
    private static final String SQL_CREATE_NOT_EXISTING_TABLE =
            ("create table if not exists 'test_table' (test_id, title, startAt, duration)");


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createTestTable()
    {

    }


    public void addTest(Long testID, String title, LocalDateTime startAt, LocalDateTime duration)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("test_id", testID);
        params.addValue("title",title);
        params.addValue("startAt", Timestamp.valueOf(startAt));
        params.addValue("duration", duration);
        jdbcTemplate.update(SQL_ADD_TEST, params);
    };

    public void isTestExists(Long testID)
    {
        MapSqlParameterSource testId = new MapSqlParameterSource();

    }

    public void getTestTime(Long testID)
    {
        MapSqlParameterSource testId = new MapSqlParameterSource();
        testId.addValue("test_id", testID);
    }

    public Test getTest(Long ID)
    {
        return testRepository.getById(ID);
    }

    public List<Test> getTestStatus(Long testID)
    {
        MapSqlParameterSource testId = new MapSqlParameterSource();
        testId.addValue("test_id", testID);
        Test test = getTest(testID);
        LocalDateTime startTime = jdbcTemplate.queryForObject(SQL_GET_TEST_START_TIME, testId, LocalDateTime.class);
        LocalDateTime endTime = jdbcTemplate.queryForObject(SQL_GET_TEST_END_TIME, testId, LocalDateTime.class);
        LocalDateTime nowTime = LocalDateTime.now();
        startTime.toInstant(null).toEpochMilli();


        return null;
    }


}
