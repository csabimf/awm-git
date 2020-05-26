package com.microfocus.awmplugins.git.commands.builder;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microfocus.awmplugins.git.commands.Repository;

public abstract class Git {

    public static CloneRepositoryBuilder cloneRepo() {
        return new CloneRepositoryBuilder();
    }

    public static RepositoryBuilder repo(final String repoDir) {
        return repo(Paths.get(repoDir));
    }

    public static RepositoryBuilder repo(final Path repoDir) {
        return new RepositoryBuilder(new Repository(repoDir));
    }

    public static class RepositoryBuilder {

        private final Repository repository;

        public RepositoryBuilder(final Repository repository) {
            this.repository = repository;
        }

        public AddBuilder add() {
            return new AddBuilder(repository);
        }

        public CommitBuilder commit() {
            return new CommitBuilder(repository);
        }

        public ConfigBuilder config() {
            return new ConfigBuilder(repository);
        }

        public SparseCheckoutBuilder sparseCheckout() {
            return new SparseCheckoutBuilder(repository);
        }

        public CheckoutBuilder checkout() {
            return new CheckoutBuilder(repository);
        }

        public BranchBuilder branch() {
            return new BranchBuilder(repository);
        }

        public PushBuilder push() {
            return new PushBuilder(repository);
        }

        public PullBuilder pull() {
            return new PullBuilder(repository);
        }

        public CustomRepositoryCommandBuilder customRepositoryCommand() {
            return new CustomRepositoryCommandBuilder(repository);
        }

    }

    public static CustomCommandBuilder customCommand() {
        return new CustomCommandBuilder();
    }

}
