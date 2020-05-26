package com.microfocus.awmplugins.git.commands;

public abstract class RepositoryCommand<R> extends GitCommand<R> {

    private final Repository repository;

    protected RepositoryCommand(final Repository repository) {
        this.repository = repository;
        addParameters("--git-dir", repository.getGitDir().toString());
        addParameters("--work-tree", repository.getWorkTree().toString());
    }

    protected Repository getRepository() {
        return repository;
    }

}
