package com.microfocus.awmplugins.git.executors;

import java.util.Collection;
import java.util.List;

import com.microfocus.awm.control.toolexecution.PropertyOutputParameter;
import com.microfocus.awm.control.toolexecution.ToolUtility;
import com.microfocus.awm.core.TaurusToolException;
import com.microfocus.awm.model.toolexecution.IModeledPropertyOutputParameter;
import com.microfocus.awm.model.toolexecution.IModeledToolOutputParameter;
import com.microfocus.awm.model.toolexecution.IStringInputParameter;
import com.microfocus.awm.model.toolexecution.IToolContext;
import com.microfocus.awm.model.toolexecution.IToolInputParameter;
import com.microfocus.awm.model.toolexecution.IToolOutputParameter;
import com.microfocus.awmplugins.git.commands.GitCommand;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.util.CommandResult;

public class ExecutorUtil {

    private static final String OUTPUT_MESSAGES = "messages";

    private final String executorId;

    private final IToolContext toolContext;

    public ExecutorUtil(final String executorId, final IToolContext toolContext) {
        this.executorId = executorId;
        this.toolContext = toolContext;
    }

    public static void throwFromGitException(final GitException e) throws TaurusToolException {
        final CommandResult result = e.getResult();
        throw new TaurusToolException(String.format("Failed to execute git command:\nstatus=%d\nstdout=>>>%s<<<\nstderr=>>>%s<<<\n", result.getStatus(), result.getStdout(), result.getStderr()));
    }

    public static boolean parseBoolean(final String s) {
        switch (s) {
        case "1":
            return true;
        case "0":
            return false;
        default:
            throw new IllegalArgumentException(String.format("Not a boolean '%s'", s));
        }
    }
    
    public void addMessagesOutputParameter(final GitCommand<?> command, final List<IToolOutputParameter> outputParameters) throws TaurusToolException {
        if (isOutputPropertyPresent(OUTPUT_MESSAGES)) {
            final CommandResult result = command.getResult();
            final String messages = result.getStdout() + "\n" + result.getStderr();
            outputParameters.add(new PropertyOutputParameter(ToolUtility.getOutputParameter(toolContext, OUTPUT_MESSAGES, IModeledPropertyOutputParameter.class), messages));
        }
    }

    public boolean isInputPropertyPresent(final String targetId) {
        return isInputPropertyPresent(targetId, IStringInputParameter.class);
    }

    public <T extends IToolInputParameter> boolean isInputPropertyPresent(final String targetId, final Class<T> type) {
        final String extensionId = executorId + "." + targetId;
        final Collection<T> parameters = ToolUtility.getInputParametersByType(toolContext, type);
        return parameters.stream().anyMatch(p -> p.getModelObject().getExtensionID().equals(extensionId));
    }

    public boolean isOutputPropertyPresent(final String targetId) {
        return isOutputPropertyPresent(targetId, IModeledPropertyOutputParameter.class);
    }

    public <T extends IModeledToolOutputParameter> boolean isOutputPropertyPresent(final String targetId, final Class<T> type) {
        final String extensionId = executorId + "." + targetId;
        final Collection<T> parameters = ToolUtility.getOutputParametersByType(toolContext, type);
        return parameters.stream().anyMatch(p -> p.getModelObject().getExtensionID().equals(extensionId));
    }

}
