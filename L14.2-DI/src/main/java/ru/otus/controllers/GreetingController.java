package ru.otus.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.services.Greeting;

@RestController
public class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private final Greeting greeting;

    public GreetingController(Greeting greeting) {
        this.greeting = greeting;
    }

    //http://localhost:8080/DIhello/hello?name=ddd
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(@RequestParam(name = "name") String name) {
        return new Gson().toJson(this.greeting.sayHello(name));
    }
}
