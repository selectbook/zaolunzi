package cn.zaolunzi.async.handler;

import cn.zaolunzi.async.EventHandler;
import cn.zaolunzi.async.EventModel;
import cn.zaolunzi.async.EventType;
import cn.zaolunzi.model.Message;
import cn.zaolunzi.model.User;
import cn.zaolunzi.service.MessageService;
import cn.zaolunzi.service.UserService;
import cn.zaolunzi.utils.ChaofenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class LikeHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(ChaofenUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(model.getActorId());
        message.setContent("用户" + user.getName()
                + "赞了你的评论,http://127.0.0.1:8080/question/" + model.getExt("questionId"));

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
