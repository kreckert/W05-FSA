import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by kwr2 on 07/10/16.
 */
public class FSACreator {

    private Map<Integer, state> states;

    private BufferedReader reader;
    private String textFile;
    //private BufferedReader txtreader;

    public FSACreator(String fsaFile, String textFile) throws Exception{

        states = new HashMap<>();
        reader = new BufferedReader(new FileReader(fsaFile));
        this.textFile = textFile;

        occupyMap();
        checkTxt();
    }

    /**
     *
     * @throws Exception
     */
    private void occupyMap() throws Exception {

        //loops through fsa file
        String[] lineArray;
        String line;
        while ((line = reader.readLine()) != null) {

            //breaks if line is empty
            if (line.equals(""))
                break;

            lineArray = line.split("\\s+");

            checkForState(lineArray);
        }
    }

    private void checkForState(String[] line) {

        Integer key = Integer.parseInt(line[0]);
        if (states.containsKey(key)) {

            states.get(key).addInput(line);
        } else {

            state state = new state();
            states.put(key, state);
            states.get(key).addInput(line);
        }

        if (line.length == 4) {

            states.get(key).makeAccepting();
        }
    }

    private void checkTxt() throws Exception{

        /*
        String text = "";
        String line;

        while ((line = txtreader.readLine()) != null) {

            text = text + line;
        }
        */

        if (recursiveCheck(0, textFile, 1))
            System.out.println("Accepted");
        else
            System.out.println("Not Accepted");
    }

    private boolean recursiveCheck(Integer currentLetter, String text, Integer currentState) {

        if (currentLetter == text.length()-1) {

            if (states.get(currentState).isAccepting())
                return true;
            else
                return false;
        }

        Character currentLetterChar = text.charAt(currentLetter);

        if (states.get(currentState).getInputs().containsKey(currentLetterChar)) {

            currentState = states.get(currentState).getInputs().get(currentLetterChar);
        } else {

            return false;
        }

        return recursiveCheck(currentLetter + 1, text, currentState);
    }

    /*
    private void printHashMap() {

        for (Integer state: states.keySet()){

            System.out.println(state);
            System.out.println(states.get(state).toString());
        }
    }
    */
}
