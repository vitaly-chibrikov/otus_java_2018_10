package ru.otus.messageSystem.app;

import ru.otus.messageSystem.entity.Address;
import ru.otus.messageSystem.entity.Addressee;
import ru.otus.messageSystem.entity.Message;

/**
 * Created by tully.
 */
public abstract class MsgToDB extends Message {
    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        }
    }

    public abstract void exec(DBService dbService);
}
