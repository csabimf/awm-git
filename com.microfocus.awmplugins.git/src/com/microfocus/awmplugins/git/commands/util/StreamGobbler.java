package com.microfocus.awmplugins.git.commands.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class StreamGobbler implements Runnable {

    public static void gobble(final InputStream is, final OutputStream os) throws IOException {
        try {
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    private final InputStream is;

    private final OutputStream os;

    private IOException error;

    public StreamGobbler(final InputStream is, final OutputStream os) {
        this.is = is;
        this.os = os;
    }

    @Override
    public void run() {
        try {
            gobble(is, os);
        } catch (IOException e) {
            error = e;
        }
    }

    public IOException getError() {
        return error;
    }

}
