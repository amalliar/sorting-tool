package sorting;

import java.io.IOException;
import java.io.PrintWriter;

final class OutputWriter {

    private PrintWriter writer;

    public OutputWriter(Config config) {
        writer = new PrintWriter(System.out);
        if (!"stdout".equals(config.getWriteTo())) {
            try {
                writer = new PrintWriter(config.getWriteTo());
            } catch (IOException ex) {
                System.err.printf("Can't open %s for writing: %s\n",
                        config.getWriteTo(), ex.getMessage());
                System.exit(1);
            }
        }
    }

    public void print(String data) {
        writer.print(data);
        writer.flush();
    }

    public void println(String data) {
        writer.println(data);
        writer.flush();
    }
}