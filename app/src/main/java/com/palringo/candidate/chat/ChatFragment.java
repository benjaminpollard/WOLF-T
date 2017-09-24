package com.palringo.candidate.chat;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.palringo.candidate.R;

public class ChatFragment extends Fragment {
    public static final String TAG = "ChatFragment";
    private static final String AVATAR_PATH = "file:///android_asset/leopard.jpg";

    private EditText messageEditText;
    private ChatRecyclerAdapter recyclerAdapter;

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
        View view = inflater.inflate(R.layout.chat_item_list, container, false);

        View emtpyStateView = view.findViewById(R.id.empty_view);
        recyclerAdapter = new ChatRecyclerAdapter(null, emtpyStateView);

        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recyclerAdapter);

        messageEditText = view.findViewById(R.id.typedMessage);
        ImageButton sendButton = view.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerAdapter.addMessage(AVATAR_PATH, messageEditText.getText().toString());
                messageEditText.setText("");
            }
        });

        return view;
    }
}



