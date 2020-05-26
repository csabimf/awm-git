package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.GitPush;
import com.microfocus.awmplugins.git.commands.Repository;

public class PushBuilder extends ExtraParametersBuilder<PushBuilder> {

    private final Repository repository;

    public PushBuilder(final Repository repository) {
        this.repository = repository;
    }

    public void call() throws GitException {
        prepare().execute();
    }

    public GitPush prepare() {
        final GitPush command = new GitPush(repository);
        command.setExtraParameters(getExtraParameters());
        return command;
    }

    @Override
    protected PushBuilder getThis() {
        return this;
    }

}
