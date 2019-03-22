package ru.otus.messageSystem.db;

import ru.otus.messageSystem.app.DBService;
import ru.otus.messageSystem.app.MessageSystemContext;
import ru.otus.messageSystem.entity.Address;
import ru.otus.messageSystem.entity.MessageSystem;

/**
 * Created by tully.
 */
public class DBServiceImpl implements DBService {
    private final Address address;
    private final MessageSystemContext context;

    public DBServiceImpl(MessageSystemContext context, Address address) {
        this.context = context;
        this.address = address;
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

    public int getUserId(String name) {
        //todo: load id from db
        return name.hashCode();
    }
}
