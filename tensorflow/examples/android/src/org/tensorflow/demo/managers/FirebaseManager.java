package org.tensorflow.demo.managers;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.demo.model.RecognizedSign;
import org.tensorflow.demo.model.User;

import java.util.List;

public abstract class FirebaseManager {

    // --------------------------- VALUES ----------------------------

    private static final String TAG = "FirebaseManager";

    // ---------------------------- SAVE -----------------------------

    /**
     * Create a user in our DB when the user registers for the first time
     *
     * @param user user to be stored.
     */
    static void createUser(final User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Create the user only if it doesn't exists
        final DatabaseReference usersRef = database.getReference("users");

        usersRef.child(user.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.getValue() == null)
                    usersRef.child(user.getId()).setValue(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: An error occurred saving the user on firebase",
                        databaseError.toException());
            }
        });
    }

    /**
     * Store a new recognizedSign in the Database and create the relation between the current user and such
     * recognizedSign.
     *
     * @param recognizedSigns Recognized signs to be stored.
     */
    public static void saveRecognizedSigns(long timestamp, List<RecognizedSign> recognizedSigns ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

//        for (RecognizedSign recognizedSign : recognizedSigns) {
            // Create new recognizedSign with unique key
            DatabaseReference eventsRef = database.getReference("recognizedSigns");
            String eventId = String.valueOf(timestamp);
            eventsRef.child(eventId).setValue(recognizedSigns);

            // Create the relation User - RecognizedSign
            DatabaseReference userEventsRef = database.getReference("userSigns");
            userEventsRef.child(AuthManager.sUser.getId()).child(eventId).setValue(recognizedSigns.size());
//        }
    }
}
