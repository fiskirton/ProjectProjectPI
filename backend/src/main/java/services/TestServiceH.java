package services;

import org.springframework.stereotype.Service;
import entity.Test;

@Service
public class TestServiceH implements TestService{

    @Override
    public Test getTest(Long test_id) {
        Test test = new Test();
        test.setTestId(test_id);
        return test;
    }




}
