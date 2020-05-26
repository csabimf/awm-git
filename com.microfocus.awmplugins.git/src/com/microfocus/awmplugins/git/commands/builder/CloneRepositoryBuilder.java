package com.microfocus.awmplugins.git.commands.builder;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.microfocus.awmplugins.git.commands.CloneType;
import com.microfocus.awmplugins.git.commands.GitCloneRepository;
import com.microfocus.awmplugins.git.commands.GitException;

public class CloneRepositoryBuilder extends ExtraParametersBuilder<CloneRepositoryBuilder> {

    private URI repository;

    private Path directory;

    private Integer depth;

    private LocalDate since;

    private String branch;

    private Boolean checkout;

    public CloneRepositoryBuilder repository(final String repository) {
        return repository(URI.create(repository));
    }

    public CloneRepositoryBuilder repository(final URI repository) {
        this.repository = repository;
        return this;
    }

    public CloneRepositoryBuilder directory(final String directory) {
        return directory(Paths.get(directory));
    }

    public CloneRepositoryBuilder directory(final Path directory) {
        this.directory = directory;
        return this;
    }

    public CloneRepositoryBuilder depth(final Integer depth) {
        this.depth = depth;
        this.since = null;
        return this;
    }

    public CloneRepositoryBuilder shallowSince(final LocalDate since) {
        this.depth = null;
        this.since = since;
        return this;
    }

    public CloneRepositoryBuilder branch(final String branch) {
        this.branch = branch;
        return this;
    }

    public CloneRepositoryBuilder checkout(final Boolean checkout) {
        this.checkout = checkout;
        return this;
    }

    public void call() throws GitException {
        prepare().execute();
    }

    public GitCloneRepository prepare() {
        final GitCloneRepository cloneCommand = new GitCloneRepository(repository, directory);

        if (depth == null && since == null) {
            cloneCommand.setCloneType(CloneType.FULL);
        } else if (depth != null) {
            cloneCommand.setCloneType(CloneType.DEPTH);
            cloneCommand.setDepth(depth);
        } else if (since != null) {
            cloneCommand.setCloneType(CloneType.SINCE);
            cloneCommand.setSince(since);
        }

        cloneCommand.setBranch(branch);

        if (checkout != null) {
            cloneCommand.setCheckout(checkout);
        }

        cloneCommand.setExtraParmeters(getExtraParameters());

        return cloneCommand;
    }

    @Override
    protected CloneRepositoryBuilder getThis() {
        return this;
    }

}
