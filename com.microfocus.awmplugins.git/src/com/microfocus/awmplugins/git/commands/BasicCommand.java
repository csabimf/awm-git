package com.microfocus.awmplugins.git.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicCommand extends RepositoryCommand<Void> {

    private final String command;

    private List<String> extraParameters = Collections.emptyList();

    public BasicCommand(final Repository repository, final String command) {
        super(repository);
        this.command = command;
    }

    public void setExtraParameters(List<String> extraParameters) {
        this.extraParameters = new ArrayList<>(extraParameters);
    }

    @Override
    public Void execute() throws GitException {
        addParameters(command);
        addParameters(extraParameters);
        executeCommand();
        return null;
    }

}
