package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

class Recorder {
    public static void record(String line, String file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(line);
            bufferedWriter.write("\n");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void strCat(String path, String filename, String[] paths){
        String integers = filename + "integers.txt";
        String floats = filename + "floats.txt";
        String strings = filename + "strings.txt";

        Path intPath = Path.of(path+integers);
        Path floatPath = Path.of(path+floats);
        Path stringPath = Path.of(path+strings);

        paths[0]=intPath.toString();
        paths[1]=floatPath.toString();
        paths[2]=stringPath.toString();
    }

    public static void strRecord(String line, String[] paths) {

        Path intPath = Path.of(paths[0]);
        Path floatPath = Path.of(paths[1]);
        Path stringPath = Path.of(paths[2]);

        if (Checker.isInteger(line)) {
            try {
                if (Files.notExists(intPath)) {
                    Files.createFile(intPath);
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
            Recorder.record(line, intPath.toString());
        }
        else if (Checker.isDouble(line)) {
            try {
                if (Files.notExists(floatPath)) {
                    Files.createFile(floatPath);
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
            Recorder.record(line, floatPath.toString());
        } else {
            try {
                if (Files.notExists(stringPath)) {
                    Files.createFile(stringPath);
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
            Recorder.record(line, stringPath.toString());
        }
    }

    public static void rewriter(boolean rewrite, String[] paths) {
        for (String file : paths) {
            if (!rewrite && file!= null) {
                try (FileWriter fileWriter = new FileWriter(file, false)) {
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
    }

}

