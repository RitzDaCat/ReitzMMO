package com.paully104.reitzmmo.Party_System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Paul on 7/29/2016.
 */
public class Party {

    private final List<String> members = new ArrayList<>();

    public Party(String creator)
    {
        members.add(creator);
    }

    public void set_Member(String person)
    {
        members.add(person);
    }
    public void Remove_Member(String person)

    {
        members.remove(person);
    }
    public String get_Members()
    {
        return Arrays.toString(members.toArray());
    }
    public List get_MembersList()
    {
        return  members;

    }


}
