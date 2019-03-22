package ru.otus.messageSystem.app.messages;

import ru.otus.messageSystem.app.DBService;
import ru.otus.messageSystem.app.MsgToDB;
import ru.otus.messageSystem.entity.Address;

/**
 * Created by tully.
 */
public class MsgGetUserId extends MsgToDB {
    private final String login;

    public MsgGetUserId(Address from, Address to, String login) {
        super(from, to);
        this.login = login;
    }

    @Override
    public void exec(DBService dbService) {
        int id = dbService.getUserId(login);
        dbService.getMS().sendMessage(new MsgGetUserIdAnswer(getTo(), getFrom(), login, id));
    }
}
