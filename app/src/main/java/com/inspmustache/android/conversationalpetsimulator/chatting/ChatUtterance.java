package com.inspmustache.android.conversationalpetsimulator.chatting;

/**
 * Created by nicolai on 30.11.17.
 */

public class ChatUtterance {
    private int speaker;
    private String utterance;

    static final int USER_ID = 0;
    static final int ANIMAL_ID = 1;

    ChatUtterance(int speaker, String utterance) {
        this.speaker = speaker;
        this.utterance = utterance;

        if ( ! ( (this.speaker == ChatUtterance.USER_ID) ||
                (this.speaker == ChatUtterance.ANIMAL_ID) ) ) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getSpeaker() {
        return speaker;
    }

    public String getUtterance() {
        return utterance;
    }
}
