package com.microfocus.awmplugins.git.commands;

public class GitConfigGet extends RepositoryCommand<String> {

    private final String name;

    public GitConfigGet(final Repository repository, final String name) {
        super(repository);
        this.name = name;
    }

    public String execute() throws GitException {
        addParameters("config", "--local", name);
        executeCommand();
        return getResult().getStdout().trim();
    }

}
