package com.palringo.candidate.chat.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.palringo.candidate.R;
import com.palringo.candidate.chat.viewModels.ChatViewModel;

public class ChatFragment extends Fragment {
    public static final String TAG = "ChatFragment";
    private static final String AVATAR_PATH = "file:///android_asset/leopard.jpg";

    private EditText messageEditText;
    private ChatRecyclerAdapter recyclerAdapter;
    private ChatViewModel chatViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChatFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpViewModel();
        return setUpViews(inflater, container);
    }

    private View setUpViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.chat_item_list, container, false);

        View emptyStateView = view.findViewById(R.id.empty_view);
        recyclerAdapter = new ChatRecyclerAdapter(null, emptyStateView);

        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recyclerAdapter);

        messageEditText = view.findViewById(R.id.typedMessage);
        ImageButton sendButton = view.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(v -> {
            chatViewModel.sendMessage(messageEditText.getText().toString());
            messageEditText.setText("");
        });

        return view;
    }

    private void setUpViewModel() {
        if (getActivity() != null) {
            chatViewModel = new ViewModelProvider(getActivity()).get(ChatViewModel.class);
            chatViewModel.getNewMessageLiveData().observe(getViewLifecycleOwner(), message -> {
                recyclerAdapter.addMessage(AVATAR_PATH, message);
                messageEditText.setText("");
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        for (String message :
                chatViewModel.getMessages()) {
            recyclerAdapter.addMessage(AVATAR_PATH, message);
        }

    }
}



