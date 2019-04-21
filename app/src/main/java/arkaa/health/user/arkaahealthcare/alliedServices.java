package arkaa.health.user.arkaahealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class alliedServices extends AppCompatActivity {

    private static final int PERSONAL_CARE = 1;
    private static final int ICU = 2;
    private static final int NUTRITION = 3;
    private static final int MOBILITY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allied_services);



        ImageView personalCareImageView = findViewById(R.id.personal_care);
        personalCareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(alliedServices.this,personal_care.class);
                intent.putExtra("type",PERSONAL_CARE);
                startActivity(intent);
            }
        });

        ImageView icuImageView = findViewById(R.id.icu);
        icuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(alliedServices.this,personal_care.class);
                intent.putExtra("type",ICU);
                startActivity(intent);

            }
        });

        ImageView nutritionImageView = findViewById(R.id.nutrition);
        nutritionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(alliedServices.this,personal_care.class);
                intent.putExtra("type",NUTRITION);
                startActivity(intent);

            }
        });


        ImageView mobilityImageView = findViewById(R.id.mobility);
        mobilityImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(alliedServices.this,personal_care.class);
                intent.putExtra("type",MOBILITY);
                startActivity(intent);

            }
        });






    }
}
