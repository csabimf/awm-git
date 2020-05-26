package com.microfocus.awmplugins.git.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomCommand extends GitCommand<Void> {

    private List<String> extraParameters = Collections.emptyList();

    public CustomCommand() {
    }

    public void setExtraParameters(List<String> extraParameters) {
        this.extraParameters = new ArrayList<>(extraParameters);
    }

    @Override
    public Void execute() throws GitException {
        addParameters(extraParameters);
        executeCommand();
        return null;
    }

}
