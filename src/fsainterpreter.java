import java.util.Scanner;

/**
 * Created by kwr2 on 04/10/16.
 */
public class fsainterpreter {

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        String text = "";
        while (scanner.hasNext()) {
            text = text + scanner.next();
        }

        FSACreator fsaCreator = new FSACreator(args[0], text);
        // example-2.fsa.txt
    }
}
