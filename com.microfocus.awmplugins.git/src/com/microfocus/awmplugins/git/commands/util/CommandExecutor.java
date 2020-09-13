package com.microfocus.awmplugins.git.commands.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandExecutor {

    public static void execute(final String workingDirectory, final String executable, final List<String> parameters, final CommandResult result, final boolean separateStreams) throws ExecutionException {
        try {
            doExecute(workingDirectory, executable, parameters, result, separateStreams);
        } catch (IOException | InterruptedException e) {
            throw new ExecutionException(e);
        }
    }

    private static void doExecute(final String workingDirectory, final String executable, final List<String> parameters, final CommandResult result, final boolean separateStreams) throws IOException, InterruptedException {
        final ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File(workingDirectory));

        Map<String, String> environment = pb.environment();
        environment.put("GIT_TERMINAL_PROMPT", "0");
        environment.put("GIT_ASKPASS", "");

        pb.command(buildCommand(executable, parameters));
        pb.redirectErrorStream(!separateStreams);

        final Process p = pb.start();

        p.getOutputStream().close();

        final ByteArrayOutputStream stdoutBytes = new ByteArrayOutputStream();
        final ByteArrayOutputStream stderrBytes = new ByteArrayOutputStream();

        Thread stderrGobbler = null;
        if (separateStreams) {
            stderrGobbler = new Thread(new StreamGobbler(p.getErrorStream(), stderrBytes));
            stderrGobbler.start();
        }

        StreamGobbler.gobble(p.getInputStream(), stdoutBytes);

        final int status = p.waitFor();

        if (stderrGobbler != null) {
            stderrGobbler.join();
        }

        result.setStatus(status);
        result.setStdout(stdoutBytes.toString());
        result.setStderr(stderrBytes.toString());
    }

    private static List<String> buildCommand(final String executable, final List<String> parameters) {
        final List<String> command = new ArrayList<>();
        command.add(executable);
        command.addAll(parameters);
        return command;
    }

}
