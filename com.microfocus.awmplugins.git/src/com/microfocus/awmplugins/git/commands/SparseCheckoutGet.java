package com.microfocus.awmplugins.git.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SparseCheckoutGet extends SparseCheckoutCommand<List<String>> {

    public SparseCheckoutGet(final Repository repository) {
        super(repository);
    }

    public List<String> execute() throws GitException {
        try {
            return executeUnsafe();
        } catch (final IOException e) {
            throw new GitException(null, e);
        }
    }

    private List<String> executeUnsafe() throws IOException {
        final List<String> result = new ArrayList<>();

        final Path sparseCheckoutFile = getSparseCheckoutFile();

        try (final BufferedReader reader = new BufferedReader(new FileReader(sparseCheckoutFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (final FileNotFoundException e) {
        }

        return result;
    }

}
