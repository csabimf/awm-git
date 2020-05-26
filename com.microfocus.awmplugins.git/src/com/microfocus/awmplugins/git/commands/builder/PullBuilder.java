package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.GitPull;
import com.microfocus.awmplugins.git.commands.Repository;

public class PullBuilder extends ExtraParametersBuilder<PullBuilder> {

    private final Repository repository;

    public PullBuilder(final Repository repository) {
        this.repository = repository;
    }

    public void call() throws GitException {
        prepare().execute();
    }

    public GitPull prepare() {
        final GitPull command = new GitPull(repository);
        command.setExtraParameters(getExtraParameters());
        return command;
    }

    @Override
    protected PullBuilder getThis() {
        return this;
    }

}
