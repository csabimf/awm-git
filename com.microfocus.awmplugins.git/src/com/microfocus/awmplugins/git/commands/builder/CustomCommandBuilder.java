package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.CustomCommand;
import com.microfocus.awmplugins.git.commands.GitException;

public class CustomCommandBuilder extends ExtraParametersBuilder<CustomCommandBuilder> {

    public CustomCommandBuilder() {
    }

    public void call() throws GitException {
        prepare().execute();
    }

    public CustomCommand prepare() {
        final CustomCommand command = new CustomCommand();
        command.setExtraParameters(getExtraParameters());
        return command;
    }

    @Override
    protected CustomCommandBuilder getThis() {
        return this;
    }

}
