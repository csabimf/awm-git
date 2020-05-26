package com.microfocus.awmplugins.git.executors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.microfocus.awmplugins.git.commands.GitCloneRepository;
import com.microfocus.awmplugins.git.commands.GitException;
import com.microfocus.awmplugins.git.commands.builder.CloneRepositoryBuilder;
import com.microfocus.awmplugins.git.commands.builder.Git;

public class CloneRepositoryExecutor implements IToolExecutor2 {

    private static final String TOOL_ID = PluginConstants.FUNCTION_PACKAGE_ID + ".cloneRepository";

    private static final String ATTRIBUTE_CLONE_TYPE = "cloneType";

    private static final String INPUT_REMOTE_URL = "remoteUrl";

    private static final String INPUT_LOCAL_PATH = "localPath";

    private static final String INPUT_DEPTH = "depth";

    private static final String INPUT_SINCE = "since";

    private static final String INPUT_BRANCH = "branch";

    private static final String INPUT_CHECKOUT = "checkout";

    private static final String INPUT_ADDITIONAL_PARM = "additionalParm";

    @Override
    public IToolResult executeSingleProcessing(final IToolContext toolContext, final IProgressMonitor progressMonitor) throws TaurusToolException {
        final ExecutorUtil executorUtil = new ExecutorUtil(TOOL_ID, toolContext);
        
        final String cloneType = (String) toolContext.getAttributeValue(ATTRIBUTE_CLONE_TYPE);

        final String remoteUrl = ToolUtility.getInputParameter(toolContext, INPUT_REMOTE_URL, IStringInputParameter.class).getParameterValue();
        final String localPath = ToolUtility.getInputParameter(toolContext, INPUT_LOCAL_PATH, IStringInputParameter.class).getParameterValue();

        final CloneRepositoryBuilder builder = Git.cloneRepo().repository(remoteUrl).directory(localPath);

        if (cloneType.equals("Full")) {
            // No input parameter required.
        } else if (cloneType.equals("Depth")) {
            final String depth = ToolUtility.getInputParameter(toolContext, INPUT_DEPTH, IStringInputParameter.class).getParameterValue();
            builder.depth(Integer.parseInt(depth));
        } else if (cloneType.equals("Since")) {
            final String since = ToolUtility.getInputParameter(toolContext, INPUT_SINCE, IStringInputParameter.class).getParameterValue();
            builder.shallowSince(LocalDate.parse(since, DateTimeFormatter.ISO_LOCAL_DATE));
        }

        if (executorUtil.isInputPropertyPresent(INPUT_BRANCH)) {
            final String branch = ToolUtility.getInputParameter(toolContext, INPUT_BRANCH, IStringInputParameter.class).getParameterValue();
            builder.branch(branch);
        }

        if (executorUtil.isInputPropertyPresent(INPUT_CHECKOUT)) {
            final String checkout = ToolUtility.getInputParameter(toolContext, INPUT_CHECKOUT, IStringInputParameter.class).getParameterValue();
            builder.checkout(ExecutorUtil.parseBoolean(checkout));
        }

        final Collection<IStringInputParameter> aditionalParms = ToolUtility.getInputParameters(toolContext, INPUT_ADDITIONAL_PARM, IStringInputParameter.class);
        for (final IStringInputParameter additionalParm : aditionalParms) {
            builder.param(additionalParm.getParameterValue());
        }

        final GitCloneRepository command = builder.prepare();

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
