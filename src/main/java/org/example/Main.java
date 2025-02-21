package org.example;

import org.apache.commons.cli.*;


public class Main {
    public static void main(String[] args) {

        Options options = new Options();

        options.addOption("o", "o",true,"Указать путь до файла");
        options.addOption("p","p", true,"Дописать к имени файла");
        options.addOption("a", "a", false,"Включить дописывание в существующий файл");
        options.addOption("f", "f", false, "Полная статистика");
        options.addOption("s", "s", false, "Краткая статистика");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        String path = "";
        String filename = "";
        String[] paths = new String[3];
        boolean rewrite = false;

        try {

            cmd = parser.parse(options, args);

            if (cmd.hasOption("o")) {
                if(cmd.getOptionValue("o") != null) path = cmd.getOptionValue("o");
            }

            if (cmd.hasOption("p")) {
                if(cmd.getOptionValue("p") != null) filename = cmd.getOptionValue("p") + filename;
            }

            if (cmd.hasOption("a")) {
                rewrite=true;
            }

        } catch (ParseException e) {
            System.err.println("Ошибка при разборе аргументов: " + e.getMessage());
            formatter.printHelp("CliExample", options);
            System.exit(1);
        }

        String optionO = cmd.getOptionValue("o");
        String optionP = cmd.getOptionValue("p");

        Recorder.strCat(path,filename,paths);
        Recorder.rewriter(rewrite,paths);

        Utility.execute(args, paths,optionO,optionP);

        if (cmd.hasOption("s") && !cmd.hasOption("f")) {
            Utility.printBriefStat(paths);
        }

        if (cmd.hasOption("f")) {
            Utility.printFullStat(paths);
        }
    }
}
