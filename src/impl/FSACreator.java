package impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kwr2 on 07/10/16.
 */
public class FSACreator {

    private String fsaFile = "example-1.fsa.txt";;
    private String text;
    ArrayList<Integer> acceptingStates;
    BufferedReader reader;

    public FSACreator(String fsaFile, String text) throws Exception{

        acceptingStates = new ArrayList<>();
        this.fsaFile = fsaFile;
        this.text = text;
        reader = new BufferedReader(new FileReader(fsaFile));
        occupyMap();
    }

    public void occupyMap() throws Exception {

        //loops through fsa file
        String line = null;
        while ((line = reader.readLine()) != null) {

            State state = createState(line);
        }
    }

    public State createState(String triple) {

        //checks for * accepting
        if (triple.charAt(triple.length()) == '*') {


        } else {

        }

        return null;
    }
}
