package com.stscode.dao;

import com.stscode.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findByNameLike() {
        List<User> xie = userDao.findByNameLike("zhang");
        xie.stream().forEachOrdered(data->{
            System.out.println(data.getName());
            System.out.println(data.getPassword());
        });
    }

    @Test
    public void findUserByPassword(){
        List<User> all = userDao.findAll((Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)->{
            Path<Object> password = root.get("password");
            Predicate equal = criteriaBuilder.equal(password, "123");
            return equal;
        });
        all.stream().forEachOrdered(data->{
            System.out.println(data.getName());
            System.out.println(data.getPassword());
        });

    }
}