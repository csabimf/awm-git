package com.microfocus.awmplugins.git.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.microfocus.awmplugins.git.commands.util.CommandExecutor;
import com.microfocus.awmplugins.git.commands.util.CommandResult;
import com.microfocus.awmplugins.git.commands.util.ExecutionException;

public abstract class GitCommand<R> {

    private String workingDirectory = System.getProperty("user.dir");

    private String executable = "git";

    private List<String> parameters = new ArrayList<>();

    private boolean separateOutputError = true;

    private CommandResult result;

    protected void addParameters(final String... parameters) {
        addParameters(Arrays.asList(parameters));
    }

    protected void addParameters(final List<String> parameters) {
        this.parameters.addAll(parameters);
    }

    public abstract R execute() throws GitException;

    protected void executeCommand() throws GitException {
        result = new CommandResult();

        try {
            CommandExecutor.execute(workingDirectory, executable, parameters, result, separateOutputError);
        } catch (ExecutionException e) {
            StringWriter stackTrace = new StringWriter();
            e.printStackTrace(new PrintWriter(stackTrace));
            result.setStderr(stackTrace.toString());

            throw new GitException(result);
        }

        if (result.getStatus() != 0) {
            throw new GitException(result);
        }
    }

    public CommandResult getResult() {
        return result;
    }

    protected String getWorkingDirectory() {
        return workingDirectory;
    }

    protected void setWorkingDirectory(final String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    protected String getExecutable() {
        return executable;
    }

    protected void setExecutable(final String executable) {
        this.executable = executable;
    }

    protected List<String> getParameters() {
        return parameters;
    }

    protected void setParameters(final List<String> parameters) {
        this.parameters = parameters;
    }

    protected boolean isSeparateOutputError() {
        return separateOutputError;
    }

    protected void setSeparateOutputError(final boolean separateOutputError) {
        this.separateOutputError = separateOutputError;
    }

}
