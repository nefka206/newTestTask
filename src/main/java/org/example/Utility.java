package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

class Utility {

    public static long countLines(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.count();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return -1;
        }
    }

    public static void execute(String[] args, String[] paths, String optionO, String optionP){
        int strCount = 0;
        int key=1;
        while (key!=0) {
            key=0;
            for (String file : args) {
                if (file.startsWith("-") || file.equals(optionO) || file.equals(optionP)){
                    continue;
                }
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    for (int i = 0; i < strCount; i++) {
                        bufferedReader.readLine();
                    }
                    String line;
                    if ((line = bufferedReader.readLine()) != null) {
                        Recorder.strRecord(line, paths);
                        key++;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            strCount++;
        }
    }

    public static void printBriefStat(String[] paths){
        if(paths[0] != null) System.out.println("Количество объектов типа int:" + " " + Utility.countLines(paths[0]));
        if(paths[1] != null) System.out.println("Количество объектов типа float:" + " " + Utility.countLines(paths[1]));
        if(paths[2] != null) System.out.println("Количество объектов типа string:" + " " + Utility.countLines(paths[2]));
    }

    public static void printFullStat(String[] paths){
        if(paths[0] != null) printIntFullStat(paths);
        if(paths[1] != null) printDoubleFullStat(paths);
        if(paths[2] != null) printStringFullStat(paths);
    }

    public static void printIntFullStat(String[] paths) {
        ArrayList<Long> arrayInt = new ArrayList<>();
        long sum = 0;
        double mean = 0;
        Long maxVal = 0L;
        Long minVal = 0L;
        if (Utility.countLines(paths[0]) != 0) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(paths[0]))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    arrayInt.add(Long.parseLong(line.trim()));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (long val : arrayInt) {
                sum += val;
            }
            maxVal = Collections.max(arrayInt);
            minVal = Collections.min(arrayInt);
            mean = (double) sum / Utility.countLines(paths[0]);
        }
        System.out.println("Количество объектов типа int:" + " " + Utility.countLines(paths[0]));
        System.out.println("Максимальное значение типа int:" + " " +  maxVal);
        System.out.println("Минимальное значение типа int:" + " " + minVal);
        System.out.println("Сумма всех элементов типа int:" + " " + sum);
        System.out.println("Среднее значение среди элементов типа int:" + " " + mean);
    }

    public static void printDoubleFullStat(String[] paths) {
        ArrayList<Double> arrayDouble = new ArrayList<>();
        double sum = 0;
        double mean = 0;
        Double maxVal = 0.0;
        Double minVal = 0.0;
        if (Utility.countLines(paths[1]) != 0) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(paths[1]))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    arrayDouble.add(Double.parseDouble(line.trim()));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (double val : arrayDouble) {
                sum += val;
            }
            maxVal = Collections.max(arrayDouble);
            minVal = Collections.min(arrayDouble);
            mean = sum / Utility.countLines(paths[1]);
        }
        System.out.println("Количество объектов типа double:" + " " + Utility.countLines(paths[0]));
        System.out.println("Максимальное значение типа double:" + " " +  maxVal);
        System.out.println("Минимальное значение типа double:" + " " + minVal);
        System.out.println("Сумма всех элементов типа double:" + " " + sum);
        System.out.println("Среднее значение среди элементов типа double:" + " " + mean);
    }

    public static void printStringFullStat(String[] paths) {
        int maxVal = 0;
        int minVal = 0;
        boolean key = true;
        if (Utility.countLines(paths[2]) != 0) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(paths[2]))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if(key) {
                        maxVal = line.length();
                        minVal = line.length();
                        key=false;
                    }
                    if (line.length()>maxVal) maxVal = line.length();
                    if (line.length()<minVal) minVal = line.length();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Количество объектов типа string:" + " " + Utility.countLines(paths[2]));
        System.out.println("Длина самой длинной строки:" + " " +  maxVal);
        System.out.println("Длина самой короткой строки:" + " " + minVal);
    }
}
