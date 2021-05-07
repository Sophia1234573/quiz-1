package com.solution.denisovich;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class contains main-method
 *
 * @author Sophia Denisovich
 * @version 1.0
 */
public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);
    private static final String ABSOLUTE_PATH_IO = "D:\\Sophia\\Java\\Nix Education\\Education\\quiz-1\\src\\main\\resources\\text1.txt";
    private static final String ABSOLUTE_PATH_NIO = "D:\\Sophia\\Java\\Nix Education\\Education\\quiz-1\\src\\main\\resources\\text2.txt";

    /**
     * main-method is the entry point of java program
     *
     * @param args
     */
    public static void main(String[] args) {
        FileReaderAndWriter task = new FileReaderAndWriter();
        task.createFile(ABSOLUTE_PATH_IO);
        task.createFileNIO(ABSOLUTE_PATH_NIO);
    }
}
