package arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import arkaa.health.user.arkaahealthcare.R;

public class DisplayPrescription extends AppCompatActivity {

    private String url;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_prescription);

        url = getIntent().getStringExtra("PRESCRIPTION_URL");
        Log.v("prescription_url","prescription_url = "+url);

        imageView = findViewById(R.id.prescription_imageview);

        Glide.with(DisplayPrescription.this)
                .load(url)
                .into(imageView);





    }
}
