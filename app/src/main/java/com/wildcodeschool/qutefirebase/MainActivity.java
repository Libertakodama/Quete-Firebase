package com.wildcodeschool.qutefirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference bestScoreRef = database.getReference("bestScore");

        Button bSend = findViewById(R.id.b_send);
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etName = findViewById(R.id.et_name);
                String et_name = etName.getText().toString();
                EditText etBestScore = findViewById(R.id.et_best_score);
                String et_best_score = etBestScore.getText().toString();

                if (et_best_score.isEmpty() || et_name.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter your name and your score.", Toast.LENGTH_SHORT).show();
                } else {
                    BestScore monster = new BestScore(et_name, Integer.valueOf(et_best_score));
                    bestScoreRef.push().setValue(monster);
                    etName.setText("");
                    etBestScore.setText("");
                }

                bestScoreRef.orderByChild("score").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TextView etBSName = findViewById(R.id.tv_bs_name);
                        TextView etBSScore = findViewById(R.id.tv_bs_score);
                        for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren()) {
                            BestScore monster = scoreSnapshot.getValue(BestScore.class);
                            etBSName.setText(monster.getName());
                            etBSScore.setText(String.valueOf(monster.getScore()));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }

}
