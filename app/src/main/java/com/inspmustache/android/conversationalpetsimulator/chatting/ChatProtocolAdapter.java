package com.inspmustache.android.conversationalpetsimulator.chatting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inspmustache.android.conversationalpetsimulator.R;

/**
 * Created by nicolai on 30.11.17.
 */

public class ChatProtocolAdapter extends RecyclerView.Adapter<ChatProtocolAdapter.ViewHolder> {

    // member variables and constructor
    private ChatProtocolViewModel protocolModel;
    private Context context;

    ChatProtocolAdapter(Context context, ChatProtocolViewModel protocolModel) {
        this.protocolModel = protocolModel;
        this.context = context;
    }

    // very simple view holder for animal and user speechBubbleViews
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView speechBubbleView;

        ViewHolder(View view) {
            super(view);
            speechBubbleView = view.findViewById(R.id.speech_bubble);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new speechBubbleView with the correct style
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View speechBubbleView = null;
        if (viewType == ChatUtterance.ANIMAL_ID) {
            speechBubbleView = layoutInflater.inflate(R.layout.chat_bubble_left, parent, false);
        } else if (viewType == ChatUtterance.USER_ID) {
            speechBubbleView = layoutInflater.inflate(R.layout.chat_bubble_right, parent, false);
        }

        return new ViewHolder(speechBubbleView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatUtterance currentSpeech = this.protocolModel.getUtteranceAt(position);
        TextView currentSpeechBubble = holder.speechBubbleView;

        // set text and appearance depending on who's the speaker
        currentSpeechBubble.setText(currentSpeech.getUtterance());
    }

    @Override
    public int getItemViewType(int position) {
        return protocolModel.getUtteranceAt(position).getSpeaker();
    }



    @Override
    public int getItemCount() {
        return protocolModel.getSize();
    }
}
