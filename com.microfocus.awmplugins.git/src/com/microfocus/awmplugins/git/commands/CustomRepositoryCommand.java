package com.microfocus.awmplugins.git.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomRepositoryCommand extends RepositoryCommand<Void> {

    private List<String> extraParameters = Collections.emptyList();

    public CustomRepositoryCommand(final Repository repository) {
        super(repository);
    }

    public void setExtraParameters(List<String> extraParameters) {
        this.extraParameters = new ArrayList<>(extraParameters);
    }

    @Override
    public Void execute() throws GitException {
        addParameters(extraParameters);
        executeCommand();
        return null;
    }

}
