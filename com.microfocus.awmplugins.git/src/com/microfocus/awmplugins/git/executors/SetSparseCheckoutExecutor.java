package com.microfocus.awmplugins.git.executors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com.microfocus.awm.control.toolexecution.ToolOutput;
import com.microfocus.awm.control.toolexecution.ToolResult;
import com.microfocus.awm.control.toolexecution.ToolUtility;
import com.microfocus.awm.core.TaurusToolException;
import com.microfocus.awm.model.toolexecution.IMassProcessingToolContext;
import com.microfocus.awm.model.toolexecution.IStringInputParameter;
import com.microfocus.awm.model.toolexecution.IToolContext;
import com.microfocus.awm.model.toolexecution.IToolExecutor2;
import com.microfocus.awm.model.toolexecution.IToolResult;
import com.microfocus.awmplugins.git.PluginConstants;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.SparseCheckoutCommand;
import com.microfocus.awmplugins.git.commands.builder.Git;
import com.microfocus.awmplugins.git.commands.builder.SparseCheckoutBuilder;

public class SetSparseCheckoutExecutor implements IToolExecutor2 {

    @SuppressWarnings("unused")
    private static final String TOOL_ID = PluginConstants.FUNCTION_PACKAGE_ID + ".setSparseCheckout";

    private static final String ATTRIBUTE_OPERATION = "operation";

    private static final String INPUT_REPOSITORY = "repository";

    private static final String INPUT_PATH = "path";

    @Override
    public IToolResult executeSingleProcessing(final IToolContext toolContext, final IProgressMonitor progressMonitor) throws TaurusToolException {
        final String operation = (String) toolContext.getAttributeValue(ATTRIBUTE_OPERATION);

        final String repository = ToolUtility.getInputParameter(toolContext, INPUT_REPOSITORY, IStringInputParameter.class).getParameterValue();

        final SparseCheckoutBuilder builder = Git.repo(repository).sparseCheckout();

        final List<String> contents = new ArrayList<>();

        final Collection<IStringInputParameter> paths = ToolUtility.getInputParameters(toolContext, INPUT_PATH, IStringInputParameter.class);
        for (final IStringInputParameter path : paths) {
            contents.add(path.getParameterValue());
        }

        SparseCheckoutCommand<Void> command = null;

        if (operation.equals("Add")) {
            command = builder.add().contents(contents).prepare();
        } else if (operation.equals("Remove")) {
            command = builder.remove().contents(contents).prepare();
        } else {
            command = builder.set().contents(contents).prepare();
        }

        try {
            command.execute();
        } catch (final GitException e) {
            ExecutorUtil.throwFromGitException(e);
        }

        return new ToolResult(toolContext, new ToolOutput());
    }

    @Override
    public boolean supportsMassProcessing() {
        return false;
    }

    @Override
    public IToolResult executeMassProcessing(final IMassProcessingToolContext massProcessingToolContext, final IProgressMonitor progressMonitor) throws TaurusToolException {
        return null;
    }

}
