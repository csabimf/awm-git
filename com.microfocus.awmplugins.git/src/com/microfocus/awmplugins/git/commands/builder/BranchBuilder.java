package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.GitBranch;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.Repository;

public class BranchBuilder extends ExtraParametersBuilder<BranchBuilder> {

    private final Repository repository;

    public BranchBuilder(final Repository repository) {
        this.repository = repository;
    }

    public void call() throws GitException {
        prepare().execute();
    }

    public GitBranch prepare() {
        final GitBranch command = new GitBranch(repository);
        command.setExtraParameters(getExtraParameters());
        return command;
    }

    @Override
    protected BranchBuilder getThis() {
        return this;
    }

}
