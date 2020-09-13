package com.microfocus.awmplugins.git.commands;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

public class SparseCheckoutRemove extends SparseCheckoutCommand<Void> {

    private final List<String> contents;

    public SparseCheckoutRemove(final Repository repository, final List<String> contents) {
        super(repository);
        this.contents = contents;
    }

    public Void execute() throws GitException {
        try {
            LinkedHashSet<String> newContents = new LinkedHashSet<>();
            newContents.addAll(getContents());
            newContents.removeAll(contents);
            setContents(newContents);
        } catch (final IOException e) {
            throw new GitException(null, e);
        }
        return null;
    }

}
