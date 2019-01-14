package ru.outs.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class Executor {
    private final List<Command> commands = new ArrayList<>();
    public Executor() {

    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executCommands() {
        commands.forEach(cmd -> {
            String result = cmd.execute("value");
            System.out.println(result);
        });
    }
}
