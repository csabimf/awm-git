package com.microfocus.awmplugins.git.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GitCommit extends RepositoryCommand<Void> {

    private final String message;

    private List<String> extraParameters = Collections.emptyList();

    public GitCommit(final Repository repository, final String message) {
        super(repository);
        this.message = message;
    }

    public Void execute() throws GitException {
        addParameters("commit");
        addParameters("--message", message);
        addParameters(extraParameters);
        executeCommand();
        return null;
    }

    public void setExtraParmeters(final List<String> extraParameters) {
        this.extraParameters = new ArrayList<>(extraParameters);
    }

}
