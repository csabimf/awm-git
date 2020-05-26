package com.microfocus.awmplugins.git.executors;

import java.io.File;
import java.nio.file.Paths;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.egit.core.op.ConnectProviderOperation;

import com.microfocus.awm.control.toolexecution.ToolResult;
import com.microfocus.awm.control.toolexecution.ToolUtility;
import com.microfocus.awm.core.TaurusToolException;
import com.microfocus.awm.model.toolexecution.IMassProcessingToolContext;
import com.microfocus.awm.model.toolexecution.IStringInputParameter;
import com.microfocus.awm.model.toolexecution.IToolContext;
import com.microfocus.awm.model.toolexecution.IToolExecutor2;
import com.microfocus.awm.model.toolexecution.IToolResult;
import com.microfocus.awmplugins.git.PluginConstants;

@SuppressWarnings("restriction")
public class ConnectEGitExecutor implements IToolExecutor2 {

    @SuppressWarnings("unused")
    private static final String TOOL_ID = PluginConstants.FUNCTION_PACKAGE_ID + ".ConnectEGit";

    private static final String INPUT_REPOSITORY = "repository";

    private static final String INPUT_PROJECT_NAME = "projectName";

    @Override
    public IToolResult executeSingleProcessing(IToolContext toolContext, IProgressMonitor progressMonitor) throws TaurusToolException {
        String repository = ToolUtility.getInputParameter(toolContext, INPUT_REPOSITORY, IStringInputParameter.class).getParameterValue();

        String projectName = ToolUtility.getInputParameter(toolContext, INPUT_PROJECT_NAME, IStringInputParameter.class).getParameterValue();
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

        connect(project, Paths.get(repository).resolve(".git").toFile());
        
        return new ToolResult(toolContext);
    }

    private static void connect(IProject project, File gitDotDir) throws TaurusToolException {
        try {
            new ConnectProviderOperation(project, gitDotDir).execute(null);
        } catch (CoreException e) {
            throw new TaurusToolException(e);
        }
    }

    @Override
    public boolean supportsMassProcessing() {
        return false;
    }

    @Override
    public IToolResult executeMassProcessing(IMassProcessingToolContext massProcessingToolContext, IProgressMonitor progressMonitor) throws TaurusToolException {
        return null;
    }

}
