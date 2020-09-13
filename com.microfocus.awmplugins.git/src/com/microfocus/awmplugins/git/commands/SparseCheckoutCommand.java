package com.microfocus.awmplugins.git.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class SparseCheckoutCommand<R> extends RepositoryCommand<R> {

    protected SparseCheckoutCommand(final Repository repository) {
        super(repository);
    }

    protected List<String> getContents() throws IOException {
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

    protected void setContents(Collection<String> contents) throws IOException {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(getSparseCheckoutFile().toFile()))) {
            for (final String line : contents) {
                writer.write(line);
                writer.write('\n');
            }
        }
    }

    private Path getSparseCheckoutFile() {
        return getRepository().getGitDir().resolve(Paths.get("info", "sparse-checkout"));
    }

}
