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
    private ArrayList<String> inputs = new ArrayList<>();
    private ArrayList<String> acceptingStates = new ArrayList<>();

    public FSACreator(String fsaFile, String text) throws Exception{

        this.fsaFile = fsaFile;
        this.text = text;
        occupyMap();
    }

    public void occupyMap() throws Exception {

        //loops through fsa file
        BufferedReader reader = new BufferedReader(new FileReader(fsaFile));
        String line = null;
        while ((line = reader.readLine()) != null) {


        }
    }

    public State createState(String line) {

        return null;
    }

    public boolean checkStateExists(String line) {

        return true;
    }
}
