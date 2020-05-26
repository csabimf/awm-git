package com.microfocus.awmplugins.git.commands;

import com.microfocus.awmplugins.git.commands.util.CommandResult;

public class GitException extends Exception {

    private static final long serialVersionUID = 1L;

    private final CommandResult result;

    public GitException(CommandResult result) {
        this.result = result;
    }

    public GitException(CommandResult result, Throwable cause) {
        super(cause);
        this.result = result;
    }

    public CommandResult getResult() {
        return result;
    }

}
