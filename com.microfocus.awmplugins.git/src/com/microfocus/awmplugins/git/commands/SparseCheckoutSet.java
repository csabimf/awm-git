package com.microfocus.awmplugins.git.commands;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

public class SparseCheckoutSet extends SparseCheckoutCommand<Void> {

    private final List<String> contents;

    public SparseCheckoutSet(final Repository repository, final List<String> contents) {
        super(repository);
        this.contents = contents;
    }

    public Void execute() throws GitException {
        try {
            setContents(new LinkedHashSet<>(contents));
        } catch (final IOException e) {
            throw new GitException(null, e);
        }
        return null;
    }

}
