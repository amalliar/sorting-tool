package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

final class InputReader {

    private final Config config;

    public InputReader(Config config) {
        this.config = new Config(config);
    }

    public List<String> getTokens() {
        Scanner scanner = new Scanner(System.in);
        if (!"stdin".equals(config.getReadFrom())) {
            try {
                scanner = new Scanner(new File(config.getReadFrom()));
            } catch (FileNotFoundException ex) {
                System.err.printf("Can't open %s for reading: %s\n",
                        config.getReadFrom(), ex.getMessage());
                System.exit(1);
            }
        }
        List<String> tokens = new LinkedList<>();
        if ("line".equals(config.getDataType())) {
            while (scanner.hasNextLine()) {
                tokens.add(scanner.nextLine());
            }
        } else {
            while(scanner.hasNext()) {
                tokens.add(scanner.next());
            }
        }
        return tokens;
    }
}