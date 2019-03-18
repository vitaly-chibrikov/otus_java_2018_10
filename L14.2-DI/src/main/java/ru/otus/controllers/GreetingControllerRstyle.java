package ru.otus.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.services.Greeting;

@RestController
public class GreetingControllerRstyle {
    private static final Logger logger = LoggerFactory.getLogger(GreetingControllerRstyle.class);

    private final Greeting greeting;

    public GreetingControllerRstyle(Greeting greeting) {
        this.greeting = greeting;
    }

    //http://localhost:8080/DIhello/hello/jone
    @RequestMapping(method = RequestMethod.GET, value = "/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return new Gson().toJson(this.greeting.sayHello(name));
    }
}
