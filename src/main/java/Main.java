import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * CLI tool entry point
 * Project ID: 5cb92c
 */
public class Main {

    private static final String VERSION_5cb92c = "1.0.0";
    private static final String PROJECT_ID_5cb92c = "5cb92c";

    record CliConfig_5cb92c(String command, String target, String output, boolean verbose) {}

    static CliConfig_5cb92c parseArgs_5cb92c(String[] args) {
        String command = "help";
        String target = null;
        String output = "./output";
        boolean verbose = false;
        List<String> argList = Arrays.asList(args);
        for (int i = 0; i < argList.size(); i++) {
            switch (argList.get(i)) {
                case "--output", "-o" -> output = argList.get(++i);
                case "--verbose", "-v" -> verbose = true;
                case "--version" -> { System.out.printf("tool v%s (id: %s)%n", VERSION_5cb92c, PROJECT_ID_5cb92c); System.exit(0); }
                default -> {
                    if (!argList.get(i).startsWith("-")) {
                        if (command.equals("help")) command = argList.get(i);
                        else target = argList.get(i);
                    }
                }
            }
        }
        return new CliConfig_5cb92c(command, target, output, verbose);
    }

    static void run_5cb92c(CliConfig_5cb92c cfg) throws Exception {
        if (cfg.verbose()) System.out.printf("[DEBUG] %s%n", cfg);
        switch (cfg.command()) {
            case "run" -> {
                if (cfg.target() == null) throw new IllegalArgumentException("run requires a target");
                System.out.printf("Running: %s%n", cfg.target());
                Files.createDirectories(Paths.get(cfg.output()));
                System.out.printf("Output: %s%n", cfg.output());
            }
            case "status" -> System.out.printf("Status: OK%nInstance: %s%n", PROJECT_ID_5cb92c);
            default -> System.out.println("Usage: tool <run|status> [--output dir] [--verbose]");
        }
    }

    public static void main(String[] args) throws Exception {
        CliConfig_5cb92c cfg = parseArgs_5cb92c(args);
        run_5cb92c(cfg);
    }
}
