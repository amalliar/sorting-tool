package sorting;

import java.util.*;

final class Sorter {

    private final Config config;
    private final List<String> tokens;
    private final OutputWriter writer;

    public Sorter(Config config, List<String> tokens) {
        this.config = new Config(config);
        this.tokens = new LinkedList<>(tokens);
        this.writer = new OutputWriter(config);
    }

    public void sort() {
        if ("natural".equals(config.getSortingType())) {
            sortNatural();
        } else if ("byCount".equals(config.getSortingType())) {
            sortByCount();
        }
    }

    public void sortNatural() {
        switch (config.getDataType()) {

            case "long":
                writer.println(String.format("Total numbers: %d.", tokens.size()));
                ArrayList<Long> longArr = new ArrayList<>(tokens.size());
                for (String token : tokens) {
                    try {
                        long longNum = Long.parseLong(token);
                        longArr.add(longNum);
                    } catch (NumberFormatException ex) {
                        System.err.printf(
                                "\"%s\" is not a long. It will be skipped.", token);
                    }
                }
                longArr.sort(null);
                writer.print("Sorted data:");
                longArr.forEach(elem -> writer.print(String.format(" %d", elem)));
                writer.print("\n");
                break;

            case "word":
                writer.println(String.format("Total words: %d.", tokens.size()));
                tokens.sort(null);
                writer.print("Sorted data:");
                tokens.forEach(elem -> writer.print(String.format(" %s", elem)));
                writer.print("\n");
                break;

            case "line":
                writer.println(String.format("Total lines: %d.", tokens.size()));
                tokens.sort(null);
                tokens.forEach(writer::println);
                break;
        }
    }

    public void sortByCount() {
        switch (config.getDataType()) {

            case "long":
            {
                writer.println(String.format("Total numbers: %d.", tokens.size()));
                Map<Long, Integer> longMap = new HashMap<>();
                for (String token : tokens) {
                    try {
                        Long longNum = Long.parseLong(token);
                        longMap.put(longNum, longMap.getOrDefault(longNum, 0) + 1);
                    } catch (NumberFormatException ex) {
                        System.err.printf(
                                "\"%s\" is not a long. It will be skipped.", token);
                    }
                }
                List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(longMap.entrySet());
                entryList.sort(Map.Entry.comparingByKey());
                entryList.sort(Map.Entry.comparingByValue());
                entryList.forEach(elem -> writer.println(
                        String.format("%d: %d time(s), %d%%",
                                elem.getKey(), elem.getValue(), elem.getValue() * 100 / tokens.size())));
                break;
            }
            case "word":
            case "line":
            {
                writer.println(String.format("Total %ss: %d.", config.getDataType(), tokens.size()));
                Map<String, Integer> stringMap = new HashMap<>();
                tokens.forEach(elem -> stringMap.put(
                        elem, stringMap.getOrDefault(elem, 0) + 1));
                List<Map.Entry<String, Integer>> entryList = new ArrayList<>(stringMap.entrySet());
                entryList.sort(Map.Entry.comparingByKey());
                entryList.sort(Map.Entry.comparingByValue());
                entryList.forEach(elem -> writer.println(
                        String.format("%s: %d time(s), %d%%",
                                elem.getKey(), elem.getValue(), elem.getValue() * 100 / tokens.size())));
                break;
            }
        }
    }
}