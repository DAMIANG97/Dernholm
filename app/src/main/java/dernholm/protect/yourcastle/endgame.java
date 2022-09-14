package dernholm.protect.yourcastle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;                                                       // biblioteki

import com.bumptech.glide.Glide;

import java.io.IOException;


public class endgame extends AppCompatActivity {
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
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);                // zmiana widoku interfejsu użytkownika.
        }
    }


    int ilosczlotaa;
    int numer;                                                                      // wprowadzenie zmiennej "numer".
    public static final String SAVE = "MySavedLayoutFile";                          // wprowadzenie zmiennej "SAVE".
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {                                                                               //
        super.onCreate(savedInstanceState);                                         //
        SharedPreferences loadLayout = getSharedPreferences(SAVE, MODE_PRIVATE);     // wprowadzenie interfejsu loadlayout pozwalającego przechowywać dane
        numer = loadLayout.getInt("numerr", MODE_PRIVATE);                       // przypisanie dla numer wartości int z loadlayout, brak przypisanej wartości to 0
        setContentView(R.layout.end);                                     // wywołanie interfejsu użytkownika "activity_main"
        final Button backmenu = findViewById(R.id.endend);                                // wprowadzenie przycisku "menu" odnoszącego się do przycisk z layoutu o id "menu"
        openbackgroundsound();


        backmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                                       // kliknięcie przez użytkownika przycisku menu spowoduje otwarcie nowej aktywności
                // openalternate();
                numer=0;
                ilosczlotaa = 0; // zmiana wartości dla dobrej odpowiedzi
                SharedPreferences saveLayout = getSharedPreferences(SAVE, MODE_PRIVATE);
                SharedPreferences.Editor editor = saveLayout.edit();
                editor.putInt("numerr", numer);
                editor.apply();
                editor.putInt("ilosczlotaaa", ilosczlotaa);
                editor.apply();
                    backtomenu();
                                   // wartość zmiennej "numer" definiuje, która zawartość zostanie otwarta




                finish();                                                           // zamknięcie aktywności
            }
        });




    }
    public void openbackgroundsound() {                                                             // metoda odpowiadająca za tło muzyczne


            if (mp.isPlaying()) {
                mp.stop();
            }

            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("trumpet.mp3");

                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.setVolume((float) 0.4,(float)0.4);
                mp.start();


            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //
    }

    /*  public void openalternate ()
      {
          Intent intent2 = new Intent(this, alternate.class);
          startActivity(intent2);
      } */
    public void backtomenu() {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }





}