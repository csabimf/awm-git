package com.microfocus.awmplugins.git.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class SparseCheckoutCommand<R> extends RepositoryCommand<R> {

    protected SparseCheckoutCommand(final Repository repository) {
        super(repository);
    }

    protected Path getSparseCheckoutFile() {
        return getRepository().getGitDir().resolve(Paths.get("info", "sparse-checkout"));
    }

}
