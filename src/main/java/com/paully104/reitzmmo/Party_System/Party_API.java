package com.paully104.reitzmmo.Party_System;

import java.util.HashMap;

/**
 * Created by Paul on 7/29/2016.
 */
public class Party_API {
    //Rparty accept [passcode] joins party. Need to match password to join
    public static final HashMap<String, Party_Queue> Password_Queue = new HashMap<>();

    public static final HashMap<String, Party> Party_Leaders = new HashMap<>();
                          //person,inviter
    public static final HashMap<String, String>inParty = new HashMap<>();
}
