package io.github.paulanthonyreitz.reitzmmo.Party_System;

/**
 * Created by Paul on 7/31/2016.
 */
public class Party_Queue {
    private final String creator;

    private final String invited;

    private final String passcode;

    public Party_Queue(String creator, String invited, String passcode) {
        this.creator = creator;
        this.invited = invited;
        this.passcode = passcode;
    }

    public String getPasscode() {
        return this.passcode;
    }

    public String getCreator() {
        return this.creator;
    }

    public String getInvited() {
        return this.invited;
    }
}
