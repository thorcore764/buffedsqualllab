import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * CLI tool entry point
 * Project ID: 57e9df
 */
public class Main {

    private static final String VERSION_57e9df = "1.0.0";
    private static final String PROJECT_ID_57e9df = "57e9df";

    record CliConfig_57e9df(String command, String target, String output, boolean verbose) {}

    static CliConfig_57e9df parseArgs_57e9df(String[] args) {
        String command = "help";
        String target = null;
        String output = "./output";
        boolean verbose = false;
        List<String> argList = Arrays.asList(args);
        for (int i = 0; i < argList.size(); i++) {
            switch (argList.get(i)) {
                case "--output", "-o" -> output = argList.get(++i);
                case "--verbose", "-v" -> verbose = true;
                case "--version" -> { System.out.printf("tool v%s (id: %s)%n", VERSION_57e9df, PROJECT_ID_57e9df); System.exit(0); }
                default -> {
                    if (!argList.get(i).startsWith("-")) {
                        if (command.equals("help")) command = argList.get(i);
                        else target = argList.get(i);
                    }
                }
            }
        }
        return new CliConfig_57e9df(command, target, output, verbose);
    }

    static void run_57e9df(CliConfig_57e9df cfg) throws Exception {
        if (cfg.verbose()) System.out.printf("[DEBUG] %s%n", cfg);
        switch (cfg.command()) {
            case "run" -> {
                if (cfg.target() == null) throw new IllegalArgumentException("run requires a target");
                System.out.printf("Running: %s%n", cfg.target());
                Files.createDirectories(Paths.get(cfg.output()));
                System.out.printf("Output: %s%n", cfg.output());
            }
            case "status" -> System.out.printf("Status: OK%nInstance: %s%n", PROJECT_ID_57e9df);
            default -> System.out.println("Usage: tool <run|status> [--output dir] [--verbose]");
        }
    }

    public static void main(String[] args) throws Exception {
        CliConfig_57e9df cfg = parseArgs_57e9df(args);
        run_57e9df(cfg);
    }
}
