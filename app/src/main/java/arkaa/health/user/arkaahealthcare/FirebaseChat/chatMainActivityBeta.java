package arkaa.health.user.arkaahealthcare.FirebaseChat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class chatMainActivityBeta extends AppCompatActivity {

    //firebase

    private String mUsername;

    private FirebaseDatabase mFirebaseDataBase ;
    private DatabaseReference mMessagesDatabaseReference ;
    private ChildEventListener mChildEventListner ;
    private String doctorUniqueIdIntent;
    private String patientUniueId;
    private String patientName;
    private String chatUniqueId;
    private ChatView chatView;
    private String firebaseChildName ;

    //firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main_beta);

        //firebase
        doctorUniqueIdIntent = getIntent().getStringExtra("DOCTOR_UNIQUE_ID");
        firebaseChildName = getIntent().getStringExtra("FIREBASE_CHILD_NAME");
//        doctorUniqueIdIntent = "1528461923982";



        if(doctorUniqueIdIntent == null) {
            Toast.makeText(chatMainActivityBeta.this,"Something went wrong, Please try again later",Toast.LENGTH_SHORT).show();
            finish();

        }
        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");
//        patientUniueId = "test";

        patientName = preferences.getString("PATIENT_NAME","");
        chatUniqueId = patientUniueId+doctorUniqueIdIntent;
//        chatUniqueId = "test1528461923982";



        mUsername = patientName;
//        mUsername = "Aakash";


        mFirebaseDataBase = FirebaseDatabase.getInstance();
//        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("textconsultation").child("arkaaglobal").child(chatUniqueId);
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("textconsultation").child(firebaseChildName).child(chatUniqueId);



        //firebase

        chatView = (ChatView) findViewById(R.id.chat_view);
//        chatView.addMessage(new ChatMessage("Message received", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
//        chatView.addMessage(new ChatMessage("A message with a sender name", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "Ryan Java"));
        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {

                FriendlyMessage friendlyMessage = new FriendlyMessage(chatMessage.getMessage(),mUsername,null,patientUniueId);
                mMessagesDatabaseReference.push().setValue(friendlyMessage);
                chatView.getInputEditText().setText("");

                return false;
            }
        });

        chatView.setTypingListener(new ChatView.TypingListener() {
            @Override
            public void userStartedTyping() {

            }

            @Override
            public void userStoppedTyping() {

            }
        });



        mChildEventListner = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.v("listner","listner patient unique id ="+patientUniueId);
                FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
//                mMessageAdapter.add(friendlyMessage);


                ChatMessage.Type chatMessageType;
                Log.v("listner","friendly message sender id = "+friendlyMessage.getSender_id());
                if(friendlyMessage.getSender_id().equals(patientUniueId)){
                    chatMessageType = ChatMessage.Type.SENT;
                }else {
                    chatMessageType = ChatMessage.Type.RECEIVED;
                }
                Log.v("listner","chatMessageType ="+chatMessageType);

                chatView.addMessage(new ChatMessage(friendlyMessage.getText(), 0, chatMessageType,friendlyMessage.getName()));


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        mMessagesDatabaseReference.addChildEventListener(mChildEventListner);






    }
}
