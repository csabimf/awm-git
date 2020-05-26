package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.CustomRepositoryCommand;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.Repository;

public class CustomRepositoryCommandBuilder extends ExtraParametersBuilder<CustomRepositoryCommandBuilder> {

    private final Repository repository;

    public CustomRepositoryCommandBuilder(final Repository repository) {
        this.repository = repository;
    }

    public void call() throws GitException {
        prepare().execute();
    }

    public CustomRepositoryCommand prepare() {
        final CustomRepositoryCommand command = new CustomRepositoryCommand(repository);
        command.setExtraParameters(getExtraParameters());
        return command;
    }

    @Override
    protected CustomRepositoryCommandBuilder getThis() {
        return this;
    }

}
