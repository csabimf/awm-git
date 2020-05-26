package com.microfocus.awmplugins.git.commands;

import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GitCloneRepository extends GitCommand<Void> {

    private final URI repository;

    private final Path directory;

    private CloneType cloneType;

    private int depth;

    private LocalDate since;

    private String branch;

    private boolean checkout;

    private List<String> extraParameters = Collections.emptyList();

    public GitCloneRepository(final URI repository, final Path directory) {
        this.repository = repository;
        this.directory = directory;
    }

    public Void execute() throws GitException {
        addParameters("clone");

        switch (cloneType) {
        case DEPTH:
            addParameters("--depth", String.valueOf(depth));
            break;
        case SINCE:
            addParameters("--shallow-since", since.format(DateTimeFormatter.ISO_LOCAL_DATE));
            break;
        default:
            break;
        }

        if (branch != null) {
            addParameters("--branch", branch);
        }

        if (!checkout) {
            addParameters("--no-checkout");
        }

        addParameters(extraParameters);

        addParameters(repository.toString());
        addParameters(directory.toString());

        executeCommand();

        return null;
    }

    public URI getRepository() {
        return repository;
    }

    public Path getDirectory() {
        return directory;
    }

    public CloneType getCloneType() {
        return cloneType;
    }

    public void setCloneType(final CloneType cloneType) {
        this.cloneType = cloneType;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(final int depth) {
        this.depth = depth;
    }

    public LocalDate getSince() {
        return since;
    }

    public void setSince(final LocalDate since) {
        this.since = since;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(final String branch) {
        this.branch = branch;
    }

    public boolean getCheckout() {
        return checkout;
    }

    public void setCheckout(final boolean checkout) {
        this.checkout = checkout;
    }

    public void setExtraParmeters(final List<String> extraParameters) {
        this.extraParameters = new ArrayList<>(extraParameters);
    }

}
