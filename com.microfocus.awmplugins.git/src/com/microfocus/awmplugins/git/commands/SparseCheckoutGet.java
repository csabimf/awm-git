package com.microfocus.awmplugins.git.commands;

import java.io.IOException;
import java.util.List;

public class SparseCheckoutGet extends SparseCheckoutCommand<List<String>> {

    public SparseCheckoutGet(final Repository repository) {
        super(repository);
    }

    public List<String> execute() throws GitException {
        try {
            return getContents();
        } catch (final IOException e) {
            throw new GitException(null, e);
        }
    }



}
