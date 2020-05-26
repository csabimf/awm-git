package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.GitCommit;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.Repository;

public class CommitBuilder extends ExtraParametersBuilder<CommitBuilder> {

    private final Repository repository;

    public CommitBuilder(final Repository repository) {
        this.repository = repository;
    }

    public CallBuilder message(final String message) {
        return new CallBuilder(message);
    }

    public class CallBuilder extends ExtraParametersBuilder<CallBuilder> {

        private final String message;

        public CallBuilder(final String message) {
            this.message = message;
        }

        public void call() throws GitException {
            prepare().execute();
        }

        public GitCommit prepare() {
            GitCommit command = new GitCommit(repository, message);
            command.setExtraParmeters(CommitBuilder.this.getExtraParameters());
            command.setExtraParmeters(getExtraParameters());
            return command;
        }

        @Override
        protected CallBuilder getThis() {
            return this;
        }

    }

    @Override
    protected CommitBuilder getThis() {
        return this;
    }

}
