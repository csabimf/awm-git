package com.microfocus.awmplugins.git.commands.builder;

import com.microfocus.awmplugins.git.commands.GitConfigGet;
import com.microfocus.awmplugins.git.commands.GitConfigSet;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.Repository;

public class ConfigBuilder {

    private final Repository repository;

    public ConfigBuilder(final Repository repository) {
        this.repository = repository;
    }

    public GetBuilder get() {
        return new GetBuilder();
    }

    public SetBuilder set() {
        return new SetBuilder();
    }

    public class GetBuilder {

        public CallBuilder name(final String name) {
            return new CallBuilder(name);
        }

        public class CallBuilder {

            private final String name;

            public CallBuilder(final String name) {
                this.name = name;
            }

            public String call() throws GitException {
                return prepare().execute();
            }

            public GitConfigGet prepare() {
                return new GitConfigGet(repository, name);
            }

        }

    }

    public class SetBuilder {

        public ValueBuilder name(final String name) {
            return new ValueBuilder(name);
        }

        public NameBuilder value(final String value) {
            return new NameBuilder(value);
        }

        public class ValueBuilder {

            private final String name;

            private ValueBuilder(final String name) {
                this.name = name;
            }

            public CallBuilder value(final String value) {
                return new CallBuilder(name, value);
            }

        }

        public class NameBuilder {

            private final String value;

            private NameBuilder(final String value) {
                this.value = value;
            }

            public CallBuilder name(final String name) {
                return new CallBuilder(name, value);
            }

        }

        public class CallBuilder {

            private final String name;

            private final String value;

            public CallBuilder(final String name, final String value) {
                this.name = name;
                this.value = value;
            }

            public void call() throws GitException {
                prepare().execute();
            }

            public GitConfigSet prepare() {
                return new GitConfigSet(repository, name, value);
            }

        }

    }

}
