package dernholm.protect.yourcastle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;




public class rules extends AppCompatActivity {

     private long backPressedTime2;
     @Override
     public void onWindowFocusChanged(boolean hasFocus) {
         super.onWindowFocusChanged(hasFocus);
         //This is used to hide/show 'Status Bar' & 'System Bar'. Swip bar to get it as visible.
         View decorView = getWindow().getDecorView();
         if (hasFocus) {
             decorView.setSystemUiVisibility(
                     View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                             | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                             | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                             | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                             | View.SYSTEM_UI_FLAG_FULLSCREEN
                             | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
         }
     }

     @Override
     public void onBackPressed() {
         if(backPressedTime2+2000>System.currentTimeMillis()){
             super.onBackPressed();
             return;


         }
         else{
             Toast.makeText(getBaseContext(),"Press back again to back", Toast.LENGTH_SHORT).show();
         }
         backPressedTime2 = System.currentTimeMillis();

     }


     public static final String SAVE = "MySavedLayoutFile";
     @Override
     protected void onCreate(Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);
         setContentView(R.layout.rules);
         final TextView rules_text = findViewById(R.id.rules_text);
         final Button rules_button = findViewById(R.id.rules_button);



         rules_button.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {


                 openmainactivity();

             }
         });
     }

     public void openmainactivity(){
         Intent intent2 = new Intent(this, MainActivity.class);
         startActivity(intent2);

     }
 }