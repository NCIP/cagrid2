package org.cagrid.core.stress;

import gov.nih.nci.cagrid.common.Utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ThreadStressTestDriver {

    /*
     * On Mac this dies with Out of Memory Error around 2000 threads, based on this setting: % sysctl
     * kern.num_taskthreads kern.num_taskthreads: 2048
     */

    public static void main(final String[] args) {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(final Thread t, final Throwable e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        });
        int numThreads = 10000;
        if (args.length == 1) {
            numThreads = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                Thread t = new Thread(new WorkerThread(i));
                t.start();
                // sleep briefly to get more precise on which thread dies
                Thread.sleep(5);
            } catch (final OutOfMemoryError e) {
                throw new RuntimeException(String.format("Out of Memory Error on Thread %d", i), e);
            } catch (InterruptedException e1) {
                throw new RuntimeException(String.format("Intterupted on Thread %d", i), e1);
            }
        }
    }

    private static class WorkerThread implements Runnable {
        private final int i;

        private WorkerThread(final int i) {
            this.i = i;
        }

        public void run() {
            try {
                File f = File.createTempFile(getClass().getSimpleName() + "-" + i + "-", ".txt");
                System.out.format("Thread %d about to work using file %s\n", this.i, f.getCanonicalPath());
                while (true) {
                    String uuid = UUID.randomUUID().toString();
                    Utils.stringBufferToFile(new StringBuffer(uuid), f);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
