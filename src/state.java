import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by kylereckert on 10/10/16.
 */
public class state {

    private Map<Character, Integer> inputs; //input from this state, to State
    private boolean accepting;

    public state() {

        inputs = new HashMap<>();
        accepting = false;
    }

    public Map<Character, Integer> getInputs() {
        return inputs;
    }

    public boolean isAccepting() {
        return accepting;
    }

    public void addInput(String[] input) {

        Character inputChar = input[1].charAt(0);
        Integer toState = Integer.valueOf(input[2]);
        inputs.put(inputChar,toState);
    }

    public void makeAccepting() {

        this.accepting = true;
    }

    public String toString() {

        String returnString = "";
        for (Character input: inputs.keySet()){

            String key = input.toString();
            String value = inputs.get(input).toString();
            returnString = returnString + (key + " " + value + "\n");
        }

        if (accepting) {
            returnString = returnString + "accepting\n";
        }

        return returnString;
    }
}
