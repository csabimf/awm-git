package com.microfocus.awmplugins.git.commands;

import java.nio.file.Path;

public class Repository {

    private final Path workTree;

    private final Path gitDir;

    public Repository(final Path repoDir) {
        this.workTree = repoDir;
        this.gitDir = repoDir.resolve(".git");
    }

    public Path getWorkTree() {
        return workTree;
    }

    public Path getGitDir() {
        return gitDir;
    }

}
