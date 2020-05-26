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
import com.microfocus.awm.model.toolexecution.IToolOutputParameter;
import com.microfocus.awm.model.toolexecution.IToolResult;
import com.microfocus.awmplugins.git.PluginConstants;
import com.microfocus.awmplugins.git.commands.CustomCommand;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.builder.CustomCommandBuilder;
import com.microfocus.awmplugins.git.commands.builder.Git;

public class CustomCommandExecutor implements IToolExecutor2 {

    private static final String TOOL_ID = PluginConstants.FUNCTION_PACKAGE_ID + ".customCommand";

    private static final String INPUT_PARM = "parm";

    @Override
    public IToolResult executeSingleProcessing(final IToolContext toolContext, final IProgressMonitor progressMonitor) throws TaurusToolException {
        final ExecutorUtil executorUtil = new ExecutorUtil(TOOL_ID, toolContext);
        
        final CustomCommandBuilder builder = Git.customCommand();

        final Collection<IStringInputParameter> aditionalParms = ToolUtility.getInputParameters(toolContext, INPUT_PARM, IStringInputParameter.class);
        for (final IStringInputParameter additionalParm : aditionalParms) {
            builder.param(additionalParm.getParameterValue());
        }

        final CustomCommand command = builder.prepare();

        try {
            command.execute();
        } catch (final GitException e) {
            ExecutorUtil.throwFromGitException(e);
        }

        final List<IToolOutputParameter> outputParameters = new ArrayList<>();
        executorUtil.addMessagesOutputParameter(command, outputParameters);
        return new ToolResult(toolContext, new ToolOutput(outputParameters));
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
