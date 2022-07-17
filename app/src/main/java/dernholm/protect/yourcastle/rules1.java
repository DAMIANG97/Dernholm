package dernholm.protect.yourcastle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;



public class rules1 extends AppCompatActivity {
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

    int numer;
    int ilosczlotaa=100;



    public static final String SAVE = "MySavedLayoutFile";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences loadLayout = getSharedPreferences(SAVE, MODE_PRIVATE);
        numer = loadLayout.getInt("numerr", MODE_PRIVATE);
        setContentView(R.layout.rules1);
        final Button rules_button1 = findViewById(R.id.rules_button1);

        rules_button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                numer++;
                SharedPreferences saveLayout = getSharedPreferences(SAVE, MODE_PRIVATE);
                SharedPreferences.Editor editor = saveLayout.edit();
                editor.putInt("numerr", numer);
                editor.apply();
                editor.putInt("ilosczlotaaa", ilosczlotaa);
                editor.apply();
                openpage2();
                finish();

            }
        });
    }



    public void openpage2(){
        Intent intent2 = new Intent(this, page2.class);
        startActivity(intent2);

    }
    public void openMainActivity(){
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);

    }
}