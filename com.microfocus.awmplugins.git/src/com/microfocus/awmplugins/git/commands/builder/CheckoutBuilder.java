package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.GitCheckout;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.Repository;

public class CheckoutBuilder extends ExtraParametersBuilder<CheckoutBuilder> {

    private final Repository repository;

    public CheckoutBuilder(final Repository repository) {
        this.repository = repository;
    }

    public void call() throws GitException {
        prepare().execute();
    }
    
    public GitCheckout prepare() {
        final GitCheckout command = new GitCheckout(repository);
        command.setExtraParameters(getExtraParameters());
        return command;
    }

    @Override
    protected CheckoutBuilder getThis() {
        return this;
    }

}
