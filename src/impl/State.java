package impl;

import java.util.ArrayList;

/**
 * Created by kwr2 on 07/10/16.
 */
public class State {

    private boolean accepting;

    public State(boolean accepting) {

        this.accepting = accepting;
    }

    public boolean isAccepting() {

        return accepting;
    }

}
