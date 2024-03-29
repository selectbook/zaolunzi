package cn.zaolunzi;

import cn.zaolunzi.dao.QuestionDAO;
import cn.zaolunzi.dao.UserDAO;
import cn.zaolunzi.model.EntityType;
import cn.zaolunzi.model.Question;
import cn.zaolunzi.model.User;
import cn.zaolunzi.service.FollowService;
import cn.zaolunzi.service.SensitiveService;
import cn.zaolunzi.utils.JedisAdapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChaofenApplication.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {
    @Autowired
    UserDAO userDAO;

    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    SensitiveService sensitiveUtil;

    @Autowired
    FollowService followService;

    @Autowired
    JedisAdapter jedisAdapter;

    @Test
    public void contextLoads() {
        Random random = new Random();
        jedisAdapter.getJedis().flushDB();
        for (int i = 0; i < 11; ++i) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i+1));
            user.setPassword("");
            user.setSalt("");
            userDAO.addUser(user);

            for (int j = 1; j < i; ++j) {
                followService.follow(j, EntityType.ENTITY_USER, i);
            }

            user.setPassword("newpassword");
            userDAO.updatePassword(user);

            Question question = new Question();
            question.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
            question.setCreatedDate(date);
            question.setUserId(i + 1);
            question.setTitle(String.format("TITLE{%d}", i));
            question.setContent(String.format("Balaababalalalal Content %d", i));
            questionDAO.addQuestion(question);
        }

        Assert.assertEquals("newpassword", userDAO.selectById(1).getPassword());
        //userDAO.deleteById(1);
        //Assert.assertNull(userDAO.selectById(1));
    }

    @Test
    public void testSensitive() {
        String content = "question content <img src=\"https:\\/\\/baidu.com/ff.png\">色情赌博";
        String result = sensitiveUtil.filter(content);
        System.out.println(result);
    }
}
