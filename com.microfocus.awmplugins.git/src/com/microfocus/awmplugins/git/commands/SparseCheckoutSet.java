package com.microfocus.awmplugins.git.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SparseCheckoutSet extends SparseCheckoutCommand<Void> {

    private final List<String> contents;

    public SparseCheckoutSet(final Repository repository, final List<String> contents) {
        super(repository);
        this.contents = contents;
    }

    public Void execute() throws GitException {
        try {
            executeUnsafe();
        } catch (final IOException e) {
            throw new GitException(null, e);
        }
        return null;
    }

    private void executeUnsafe() throws IOException {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(getSparseCheckoutFile().toFile()))) {
            for (final String line : contents) {
                writer.write(line);
//              writer.newLine();
                writer.write('\n');
            }
        }
    }

}
