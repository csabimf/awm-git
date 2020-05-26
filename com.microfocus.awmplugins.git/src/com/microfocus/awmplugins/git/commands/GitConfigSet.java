package com.microfocus.awmplugins.git.commands;

public class GitConfigSet extends RepositoryCommand<Void> {

    private String name;

    private String value;

    public GitConfigSet(final Repository repository, final String name, final String value) {
        super(repository);
        this.name = name;
        this.value = value;
    }

    public Void execute() throws GitException {
        addParameters("config", "--local", name, value);
        executeCommand();
        return null;
    }

}
