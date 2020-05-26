package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.GitAdd;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.Repository;

public class AddBuilder extends ExtraParametersBuilder<AddBuilder> {

    private final Repository repository;

    public AddBuilder(final Repository repository) {
        this.repository = repository;
    }

    public Void call() throws GitException {
        return prepare().execute();
    }

    public GitAdd prepare() {
        final GitAdd command = new GitAdd(repository);
        command.setExtraParameters(getExtraParameters());
        return command;
    }

    @Override
    protected AddBuilder getThis() {
        return this;
    }

}
