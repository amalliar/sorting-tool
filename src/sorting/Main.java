package sorting;

import java.util.List;

public class Main {
    public static void main(final String[] args) {

        if (args.length == 0) {
            printHelp();
            System.exit(0);
        }
        Config config = new Config(args);
        List<String> tokens = new InputReader(config).getTokens();
        Sorter sorter = new Sorter(config, tokens);
        sorter.sort();
    }

    public static void printHelp() {
        System.out.print(
            "Usage: sorter [OPTION]... [FILE]\n" +
            "Sort numbers, words or lines.\n" +
            "\n" +
            "Mandatory arguments to long options are mandatory for short options too.\n" +
            "  -d, -dataType [long|word|line]     chose the appropriate data type\n" +
            "  -s, -sortingType [natural|byCount] specify natural sorting order or by count\n" +
            "  -f, -inputFile [FILE]   file to read from\n" +
            "  -o, -outputFile [FILE]  file to write to, with no FILE write to standard output\n"
        );
    }
}