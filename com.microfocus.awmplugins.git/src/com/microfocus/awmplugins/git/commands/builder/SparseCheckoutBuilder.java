package com.microfocus.awmplugins.git.commands.builder;

import java.util.Arrays;
import java.util.List;

import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.Repository;
import com.microfocus.awmplugins.git.commands.SparseCheckoutGet;
import com.microfocus.awmplugins.git.commands.SparseCheckoutSet;

public class SparseCheckoutBuilder {

    private final Repository repository;

    public SparseCheckoutBuilder(final Repository repository) {
        this.repository = repository;
    }

    public GetBuilder get() {
        return new GetBuilder();
    }

    public SetBuilder set() {
        return new SetBuilder();
    }

    public class GetBuilder {

        public List<String> call() throws GitException {
            return prepare().execute();
        }

        public SparseCheckoutGet prepare() {
            return new SparseCheckoutGet(repository);
        }

    }

    public class SetBuilder {

        public CallBuilder contents(final String... contents) {
            return contents(Arrays.asList(contents));
        }

        public CallBuilder contents(final List<String> contents) {
            return new CallBuilder(contents);
        }

        public class CallBuilder {

            private final List<String> contents;

            public CallBuilder(final List<String> contents) {
                this.contents = contents;
            }

            public void call() throws GitException {
                prepare().execute();
            }

            public SparseCheckoutSet prepare() {
                return new SparseCheckoutSet(repository, contents);
            }

        }

    }

}
