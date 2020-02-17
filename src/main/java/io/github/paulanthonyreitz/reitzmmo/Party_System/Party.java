package io.github.paulanthonyreitz.reitzmmo.Party_System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Party {
    private final List<String> members = new ArrayList<>();

    public Party(String creator) {
        this.members.add(creator);
    }

    public void set_Member(String person) {
        this.members.add(person);
    }

    public void Remove_Member(String person) {
        this.members.remove(person);
    }

    public String get_Members() {
        return Arrays.toString(this.members.toArray());
    }

    public List get_MembersList() {
        return this.members;
    }
}