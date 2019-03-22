package ru.otus.messageSystem;


import ru.otus.messageSystem.app.DBService;
import ru.otus.messageSystem.app.FrontendService;
import ru.otus.messageSystem.app.MessageSystemContext;
import ru.otus.messageSystem.db.DBServiceImpl;
import ru.otus.messageSystem.front.FrontendServiceImpl;
import ru.otus.messageSystem.entity.Address;
import ru.otus.messageSystem.entity.MessageSystem;

public class MSMain {
    public static void main(String[] args) throws InterruptedException {
        MessageSystem messageSystem = new MessageSystem();

        MessageSystemContext context = new MessageSystemContext(messageSystem);
        Address frontAddress = new Address("Frontend");
        context.setFrontAddress(frontAddress);
        Address dbAddress = new Address("DB");
        context.setDbAddress(dbAddress);

        FrontendService frontendService = new FrontendServiceImpl(context, frontAddress);
        frontendService.init();

        DBService dbService = new DBServiceImpl(context, dbAddress);
        dbService.init();

        messageSystem.start();

        //for test
        frontendService.handleRequest("tully");
        frontendService.handleRequest("sully");

        frontendService.handleRequest("tully2");
        frontendService.handleRequest("sully3");

        Thread.sleep(1000);
        messageSystem.dispose();
    }
}
