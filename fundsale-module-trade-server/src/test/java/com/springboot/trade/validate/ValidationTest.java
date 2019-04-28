package com.springboot.trade.validate;


import com.springboot.trade.component.ValidationUtil;
import com.springboot.trade.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @Author DGD
 * @date 2018/2/10.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ValidationTest {

    @Test
    public void validateUser() {
        User user = new User();
        ValidationUtil.validate(user);
        Set<ConstraintViolation<User>> violationSet = ValidationUtil.validate(user);
        for (ConstraintViolation<User> model : violationSet) {
            System.out.println("======" + model.getMessage());
        }
    }
}
