package com.inspmustache.android.conversationalpetsimulator.chatting;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.inspmustache.android.conversationalpetsimulator.R;
import com.inspmustache.android.conversationalpetsimulator.animals.AnimalAbstract;
import com.inspmustache.android.conversationalpetsimulator.animals.Cat;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    // initialize views
    private AnimalAbstract animal;
    private EditText inputField;
    private RecyclerView recyclerView;
    // initialize other stuff
    private ChatProtocolAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ChatProtocolViewModel protocolModel;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the fragment layout
        View fragmentView = inflater.inflate(R.layout.fragment_chat, container, false);

        // process sent arguments
        processArgs(fragmentView, getArguments());

        // get the interactive views
        ImageButton sendButton = fragmentView.findViewById(R.id.chat_send_button);
        this.inputField = fragmentView.findViewById(R.id.chat_input_edit_text);

        // create the general clicklistener
        View.OnClickListener generalClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we'll assume this will be connected to other views in the future
                switch (view.getId()) {
                    case R.id.chat_send_button:
                        clickSendButton();
                        ;;
                }
            }
        };

        // connect things to it
        sendButton.setOnClickListener(generalClickListener);

        // get the viewmodel and connect it to the observer that checks for updates on the chat protocol
        this.protocolModel = ViewModelProviders.of(this).get(ChatProtocolViewModel.class);

        Observer protocolModelObserver = new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                adapter.notifyItemInserted(protocolModel.getSize() - 1);
            }
        };

        this.protocolModel.chatData.observe(this, protocolModelObserver);

        // get recyclerview, layoutmanager and bind the chatProtocol to the adapter
        this.recyclerView = fragmentView.findViewById(R.id.chat_protocol_container);
        this.adapter = new ChatProtocolAdapter(getContext(), protocolModel);
        this.layoutManager = new SmoothScrollingLayoutManager(getContext());
        this.layoutManager.setStackFromEnd(true);

        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);

        return fragmentView;
    }

    private void processArgs(View parentView, Bundle args) {
        // set background color of fragment view
        parentView.setBackgroundColor(args.getInt("backgroundColor"));

        // create the appropriate animal based on which button was clicked
        switch (args.getInt("clickedViewId")) {
            case R.id.cat_entry:
                this.animal = new Cat(this.getContext());
                break;
            case R.id.dog_entry:
                this.animal = new Cat(this.getContext());
                break;
            case R.id.chicken_entry:
                this.animal = new Cat(this.getContext());
                break;
        }
    }

    private void appendToChat(ChatUtterance utterance) {
        this.protocolModel.addUtterance(utterance);

        // smooth scroll to the new position if it's out side of the recyclerview
        if (this.layoutManager.findLastCompletelyVisibleItemPosition() !=
                this.protocolModel.getLastPosition()) {
            this.recyclerView.smoothScrollToPosition(this.protocolModel.getLastPosition());
        }
    }

    private void clickSendButton() {
        String inputString = this.inputField.getText().toString();

        // only proceed if somethin was entered
        if (inputString.length() > 0) {
            // add user input to chat
            ChatUtterance userUtterance = new ChatUtterance(ChatUtterance.USER_ID, inputString);
            appendToChat(userUtterance);

            // add pet output to chat
            ChatUtterance petUtterance = new ChatUtterance(ChatUtterance.ANIMAL_ID,
                    this.animal.outputSpeech());
            appendToChat(petUtterance);

            // clear the input field
            this.inputField.setText("");
        }
    }
}
