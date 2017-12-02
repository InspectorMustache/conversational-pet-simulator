package com.inspmustache.android.conversationalpetsimulator.chatting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolai on 01.12.17.
 */

public class ChatProtocolViewModel extends ViewModel {
    public final MutableLiveData<List<ChatUtterance>> chatData;

    ChatProtocolViewModel() {
        List<ChatUtterance> chatProtocol = new ArrayList<>();
        this.chatData = new MutableLiveData<>();
        this.chatData.setValue(chatProtocol);
    }

    public ChatUtterance getUtteranceAt(int position) {
        return this.chatData.getValue().get(position);
    }

    public void addUtterance(ChatUtterance utterance) {
        List<ChatUtterance> chatProtocol = this.chatData.getValue();
        chatProtocol.add(utterance);
        chatData.setValue(chatProtocol);
    }

    public int getLastPosition() {
        return getSize() - 1;
    }

    public int getSize() {
        return this.chatData.getValue().size();
    }
}
