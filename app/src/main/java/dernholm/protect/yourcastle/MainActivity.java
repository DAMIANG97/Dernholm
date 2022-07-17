package dernholm.protect.yourcastle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;                                                       // biblioteki

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity
{
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        //This is used to hide/show 'Status Bar' & 'System Bar'. Swip bar to get it as visible.
        View decorView = getWindow().getDecorView();
        if (hasFocus)
        {
                                    decorView.setSystemUiVisibility(
                                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);                // zmiana widoku interfejsu użytkownika.
        }
    }

    AnimationDrawable castleanimation;                                              // wprowadzenie zmiennej "castleanimation".
    AnimationDrawable titleanimation;                                               // wprowadzenie zmiennej "titleanimation".

    int numer;                                                                      // wprowadzenie zmiennej "numer".
    public static final String SAVE = "MySavedLayoutFile";                          // wprowadzenie zmiennej "SAVE".





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {                                                                               //
        super.onCreate(savedInstanceState);                                         //
        SharedPreferences loadLayout = getSharedPreferences(SAVE,MODE_PRIVATE);     // wprowadzenie interfejsu loadlayout pozwalającego przechowywać dane
        numer = loadLayout.getInt("numerr",MODE_PRIVATE);                       // przypisanie dla numer wartości int z loadlayout, brak przypisanej wartości to 0
        setContentView(R.layout.activity_main);                                     // wywołanie interfejsu użytkownika "activity_main"
        final Button menu = findViewById(R.id.menu);                                // wprowadzenie przycisku "menu" odnoszącego się do przycisk z layoutu o id "menu"
        final Button rules = findViewById(R.id.rules);                              // wprowadzenie przycisku "rules" odnoszącego się do przycisk z layoutu o id "rules"
        ImageView castle_animation = findViewById(R.id.castle0);                    //
        castle_animation.setBackgroundResource(R.drawable.animation_castle);        //
        castleanimation = (AnimationDrawable) castle_animation.getBackground();     // wczytanie za pomocą obiektu imageView i start animacji o nazwie "castleanimation"
        castleanimation.start();                                                    // pobranej z pliku animation_castle
        ImageView title_animation = findViewById(R.id.titles0);                     //
        title_animation.setBackgroundResource(R.drawable.animation_title);          //
        titleanimation = (AnimationDrawable) title_animation.getBackground();       // wczytanie za pomocą obiektu imageView i start animacji o nazwie "titleanimation"
        titleanimation.start();                                                     // pobranej z pliku animation_title






        menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {                                                                       // kliknięcie przez użytkownika przycisku menu spowoduje otwarcie nowej aktywności
               // openalternate();
                if(numer==0){openrules1();}                                         // wartość zmiennej "numer" definiuje, która zawartość zostanie otwarta
                if(numer==1){openpage2();}                                          //
                if(numer==2){openpage3();}                                          //
                if(numer==3){openpage4();}



                finish();                                                           // zamknięcie aktywności
            }
        });




        rules.setOnClickListener(new View.OnClickListener()
        {                                                                           // kliknięcie przez użytkownika przycisku rule spowoduje otwarcie nowej aktywności
                                                                                    // rules.class
                    @Override
                    public void onClick(View v)
                    {
                        openrules();
                    }
        });

    }

@Override
public void onDestroy(){
        super.onDestroy();
                                      //
}
  /*  public void openalternate ()
    {
        Intent intent2 = new Intent(this, alternate.class);
        startActivity(intent2);
    } */
    public void openrules ()
    {
        Intent intent2 = new Intent(this, rules.class);
        startActivity(intent2);
    }
    public void openrules1 ()
    {
        Intent intent2 = new Intent(this, rules1.class);
        startActivity(intent2);
    }
    public void openpage2 ()
    {
        Intent intent2 = new Intent(this, page2.class);
        startActivity(intent2);
    }
    public void openpage3 ()
    {
        Intent intent2 = new Intent(this, page3.class);
        startActivity(intent2);
    }
    public void openpage4 ()
    {
        Intent intent2 = new Intent(this, page4.class);
        startActivity(intent2);
    }

}