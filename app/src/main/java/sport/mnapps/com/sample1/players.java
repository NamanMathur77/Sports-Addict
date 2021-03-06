package sport.mnapps.com.sample1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class players extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> imgList;
    private ListView lv;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;
    public static final String FB_DATABASE_PATH="footballplayers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        imgList=new ArrayList<>();
        lv=(ListView)findViewById(R.id.listViewImage);


        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Please wait loading list image...");
        progressDialog.show();

        mDatabaseRef= FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    ImageUpload img= snapshot.getValue(ImageUpload.class);
                    imgList.add(img);
                }

                adapter = new ImageListAdapter(players.this,R.layout.image_item2, imgList);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        });
    }
    public void onBackPressed(){
        Intent intent=new Intent(players.this,football1.class);
        startActivity(intent);
        finish();
    }

    }
