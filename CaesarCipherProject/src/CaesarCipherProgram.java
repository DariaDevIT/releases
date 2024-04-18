import Interface.Arguments;
import Interface.Console;
import Interface.GUI;

public class CaesarCipherProgram {
    private static final boolean GUI = false;
    private static final boolean CONSOLE = false;

    // User Interaction Choices. Default through arguments.
    public static void main(String[] args) {
        if (args.length >= 2) {
            Arguments arguments1 = new Arguments();
            arguments1.process(args);
        } else if (CONSOLE) {
            Console console1 = new Console();
            console1.process();
        } else if (GUI) {
            GUI cli = new GUI();
            cli.setVisible(true);
        }
    }
}
