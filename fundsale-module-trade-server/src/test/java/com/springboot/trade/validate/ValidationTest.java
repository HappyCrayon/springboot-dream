package com.springboot.trade.validate;


import com.springboot.trade.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Author DGD
 * @date 2018/2/10.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void validateUser() {
        User user = new User();
        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        for (ConstraintViolation<User> model : violationSet) {
            System.out.println(model.getMessage());
        }
    }
}
