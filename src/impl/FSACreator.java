package impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by kwr2 on 07/10/16.
 */
public class FSACreator {

    private String fsaFile;
    private String text;

    private HashMap<String, Integer> fsa; //String is line from the fsa file, integer is the state
    private ArrayList<Integer> acceptingStates;
    private BufferedReader reader;

    public FSACreator(String fsaFile, String text) throws Exception{


        fsa = new HashMap<>();
        acceptingStates = new ArrayList<>();

        this.fsaFile = fsaFile;
        this.text = text;
        reader = new BufferedReader(new FileReader(fsaFile));

        occupyMap();
    }

    /**
     *
     * @throws Exception
     */
    public void occupyMap() throws Exception {

        //loops through fsa file
        String line = null;
        while ((line = reader.readLine()) != null) {

            if (line.equals(""))
                break;

            if (checkAccepting(line)) {

                line = chopAndAddString(line);
            }

            Integer state = getState(line);
            fsa.put(line, state);
        }

        printHashMap();
        printAcceptingState();
    }

    private void printAcceptingState() {

        System.out.println();
        System.out.println("accepting states");
        for (int i = 0; i < acceptingStates.size(); i++) {

            System.out.println(acceptingStates.get(i));
        }
    }

    /**
     * reutrns true if last character in the line is * and returns true
     * @param line
     * @return
     */
    public boolean checkAccepting(String line) {

        if ((line.charAt(line.length()-1)) == '*') {

            return true;
        }
        return false;
    }

    /**
     * chops string until it's only a triple
     * calls method to add accepting state;
     * @param line
     * @return
     */
    public String chopAndAddString(String line) {

        for (int i = 0; i < line.length(); i++) {

            //chops off last char of string until it's a number
            if (line.charAt(line.length() - (i + 1)) == '*' ) {

                // chops off last char of string
                line = line.substring(0, line.length() - 2);
            } else {

                break;
            }
        }

        addAcceptingState(line);
        return line;
    }

    /**
     * adds state integer to arraylist
     * @param line
     */
    public void addAcceptingState(String line) {

        char state = line.charAt(line.length() - 1);
        Integer intState = Character.getNumericValue(state);
        acceptingStates.add(intState);
    }

    public Integer getState(String line) {

        char state = line.charAt(line.length() - 1);
        Integer intState = Character.getNumericValue(state);
        return intState;
    }

    public void printHashMap() {

        for (String line: fsa.keySet()){

            System.out.println(line);
        }
    }
}
