import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by kwr2 on 07/10/16.
 */
public class FSACreator {

    private Map<Integer, state> states;
    private BufferedReader reader;

    /**
     * all fsa creation methods and text file checking method are called
     * upon creation of object
     *
     * @param fsaFile
     * @param textFile
     * @throws Exception
     */
    public FSACreator(String fsaFile, String textFile) throws IOException{

        states = new HashMap<>();
        try {

            reader = new BufferedReader(new FileReader(fsaFile));
            //creates fsa then runs txt file through it
            occupyMap();
            //tests text against fsa
            //starting state is assumed to always be 1 and starts with first letter of string
            if (recursiveCheck(0, textFile, 1))
                System.out.println("Accepted");
            else
                System.out.println("Not Accepted");
        } catch (IOException e) {

            System.out.println("File found, enter correct file names");
        }
    }

    /**
     *fsa file is looped through to create a schema
     *
     * @throws Exception
     */
    private void occupyMap() throws IOException {

        String[] lineArray;
        String line;
        try {

            while ((line = reader.readLine()) != null) {

                //breaks if line is empty
                if (line.equals(""))
                    break;
                lineArray = line.split("\\s+");
                checkForState(lineArray);
            }
        } catch (IOException e) {

            System.out.println("IOException");
        }
    }

    /**
     * checks if state is already created, if so the input and ending state is added
     * to the object; If not the new state is added to the hashmap along with the input
     * and ending state
     *
     * @param line
     */
    private void checkForState(String[] line) {

        Integer key = Integer.parseInt(line[0]);//starting state of input
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

    /**
     * goes through entire text file making sure that the input is valid for that state
     * and updates current state depending on the input. Before returning checks if the
     * last state is accepting
     *
     * @param currentLetter current letter in String of text file for char.at
     * @param text text file
     * @param currentState changes depending on the input
     * @return
     */
    private boolean recursiveCheck(Integer currentLetter, String text, Integer currentState) {

        if (currentLetter == text.length()-1) { //last character of text

            if (states.get(currentState).isAccepting())
                return true;
            else
                return false;
        }
        Character currentLetterChar = text.charAt(currentLetter);
        //checks if input is valid for that state
        if (states.get(currentState).getInputs().containsKey(currentLetterChar)) {

            //changes state depending on input
            currentState = states.get(currentState).getInputs().get(currentLetterChar);
        } else {

            return false;
        }
        return recursiveCheck(currentLetter + 1, text, currentState);
    }

    /**
     * for testing purposes. Prints state then toString for state for entire hashmap
     *
     */
    private void printHashMap() {

        for (Integer state: states.keySet()){

            System.out.println(state);
            System.out.println(states.get(state).toString());
        }
    }
}
