package sorting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

final class Config {

    private String dataType = "word";
    private String sortingType = "natural";
    private String readFrom = "stdin";
    private String writeTo = "stdout";

    public Config(String[] args) {
        parseArgs(args);
    }

    public Config(Config config) {
        dataType = config.getDataType();
        sortingType = config.getSortingType();
        readFrom = config.getReadFrom();
        writeTo = config.getWriteTo();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        Set<String> dataTypes = new HashSet<>(Arrays.asList("long", "line", "word"));
        if (!dataTypes.contains(dataType)) {
            System.err.println("No data type defined!");
            System.exit(1);
        }
        this.dataType = dataType;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        Set<String> sortingTypes = new HashSet<>(Arrays.asList("natural", "byCount"));
        if (!sortingTypes.contains(sortingType)) {
            System.err.println("No sorting type defined!");
            System.exit(1);
        }
        this.sortingType = sortingType;
    }

    public String getReadFrom() {
        return readFrom;
    }

    public void setReadFrom(String readFrom) {
        this.readFrom = readFrom;
    }

    public String getWriteTo() {
        return writeTo;
    }

    public void setWriteTo(String writeTo) {
        this.writeTo = writeTo;
    }

    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            try {
                switch (args[i]) {
                    case "-dataType":
                    case "-d":
                        setDataType(args[i + 1]);
                        break;
                    case "-sortingType":
                    case "-s":
                        setSortingType(args[i + 1]);
                        break;
                    case "-inputFile":
                    case "-f":
                        setReadFrom(args[i + 1]);
                        break;
                    case "-outputFile":
                    case "-o":
                        setWriteTo(args[i + 1]);
                        break;
                    default:
                        System.err.printf(
                                "\"%s\" is not a valid parameter. It will be skipped.", args[i]);
                        --i;
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println("Missing required parameter!");
            }
        }
    }
}