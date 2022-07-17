package dernholm.protect.yourcastle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import android.media.MediaPlayer;
import java.io.IOException;
import android.content.res.AssetFileDescriptor;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast; //Biblioteki




public class page5 extends AppCompatActivity implements OnUserEarnedRewardListener {
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //This is used to hide/show 'Status Bar' & 'System Bar'. Swip bar to get it as visible.
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(                                                //
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE                                       //
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION                    //
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN                         //
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION                           //
                            | View.SYSTEM_UI_FLAG_FULLSCREEN                                //
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);                        // zmiana widoku interfejsu użytkownika.
        }
    }
    private long backPressedTime5;                                                          // wprowadzenie zmiennej "backPressedTime2"
    private RewardedInterstitialAd rewardedInterstitialAd;                                  // wprowadzenie zmiennej "rewardedInterstitialAd"
    final String TAG = "page5";                                                             // wprowadzenie zmiennej "TAG"
    private AdView mAdView;                                                                 // wprowadzenie zmiennej "mAdView'
    int numer;                                                                              // wprowadzenie zmiennej "numer"
    int ilosczlotaa;                                                                        // wprowadzenie zmiennej "ilosczlotaa"
    public static final String SAVE = "MySavedLayoutFile";                                  // wprowadzenie zmiennej "SAVE".
    AnimationDrawable scoutanimation;                                                       // wprowadzenie zmiennej "scoutanimation"
    MediaPlayer mp = new MediaPlayer();
    MediaPlayer horse = new MediaPlayer();
    MediaPlayer turnPage = new MediaPlayer();


    @Override
    public void onBackPressed() {                                                           // kliknięcie przez użytkownika dwukrotnie przycisku powrót
        if(backPressedTime5+2000>System.currentTimeMillis()){                                // spowoduje przejście do ekranu głównego urządzenie
            moveTaskToBack(true);                                                    // nie zamykając aplikacji
            // kliknięcie przez użytkownika raz przycisku powrót
            // spowoduje pojawienie się toasta
        }
        else{
            Toast.makeText(getBaseContext(),"Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime5 = System.currentTimeMillis();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openbackgroundsound();
        SharedPreferences loadLayout = getSharedPreferences(SAVE, MODE_PRIVATE);             // wprowadzenie interfejsu loadlayout pozwalającego przechowywać dane
        numer = loadLayout.getInt("numerr", MODE_PRIVATE);                              // przypisanie dla "numer" wartości int z loadLayout, "1" z aktywności rules1
        ilosczlotaa = loadLayout.getInt("ilosczlotaaa", MODE_PRIVATE);                  // przypisanie dla "ilosczlotaa" wartosci int z loadLayout, "100" z aktywności rules1
        setContentView(R.layout.page5);                                                     // wywołanie interfejsu użytkownika "page2"
        ImageView scout_animation = findViewById(R.id.scout5);                              //
        scout_animation.setBackgroundResource(R.drawable.animation_scout);                  //
        scoutanimation = (AnimationDrawable) scout_animation.getBackground();               // wczytanie za pomocą obiektu imageView  animacji o nazwie "scoutanimation"
        final Button button_animation = findViewById(R.id.button_scout5);                   // wprowadzenie przycisku "button_animation" odnoszącego się do przycisku z interfejsu użytkownnika o id "button_scout2".
        final Button correct2_button1 = findViewById(R.id.next5_1);                         // wprowadzenie czterech przycisków odnoszących się
        final Button incorrect2_button2 = findViewById(R.id.next5_2);                       // do przycisków z interfejsu użytkownika o id next2_1 itd.
        final Button incorrect2_button3 = findViewById(R.id.next5_3);                       // przyciski te są nałożone na siebie
        final Button incorrect2_button4 = findViewById(R.id.next5_4);                       // kliknięcie poprawnego przycisku nagradza użyt
        final RadioButton opcja1 = findViewById(R.id.radioButton5_1);                         //
        final RadioButton opcja2 = findViewById(R.id.radioButton5_2);                         //
        final RadioButton opcja3 = findViewById(R.id.radioButton5_3);                         // wprowadzenie przycisków wyboru jednej z czterech opcji
        final RadioButton opcja4 = findViewById(R.id.radioButton5_4);                         // odnoszących się do radiobuttons z layoutu użytkownika o id radioButton1 itd.
        final RadioButton opcja5 = findViewById(R.id.radioButton5_5);                         // wprowadzenie fejk przycisku, eliminującego błedy.
        final ImageButton scroll = findViewById(R.id.scrollid5);                             // wprowadzenie graficznego przycisku "scroll" odnoszącego się do przycisku z layoutu użytkownika o id "scrollid"
        correct2_button1.setEnabled(false);                                                 //
        correct2_button1.setVisibility(View.VISIBLE);                                       //
        incorrect2_button2.setEnabled(false);                                               //
        incorrect2_button2.setVisibility(View.INVISIBLE);                                   // stan początkowy przycisków pozwalających na przejście
        incorrect2_button3.setEnabled(false);                                               // do następnej aktywności,
        incorrect2_button3.setVisibility(View.INVISIBLE);                                   // widoczny jest tylko jeden z przycisków
        incorrect2_button4.setEnabled(false);                                               // w tym stanie użytkownik może przejść do interakcji
        incorrect2_button4.setVisibility(View.INVISIBLE);                                   // tylko z jednym przyciskiem - correct2_button1




        MobileAds.initialize(this, initializationStatus -> loadAd());                //
        MobileAds.setAppVolume(0.18f);

     /*   mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1287137257492728/9682158872");
        mInterstitialAd.loadAd(new AdRequest.Builder().build()); */
        mAdView = findViewById(R.id.banner5);                                               // powiązanie zmiennej mAdView z banerem z interfejsu użytkownika o id banner2
        AdRequest adRequest = new AdRequest.Builder().build();                              //
        mAdView.loadAd(adRequest);                                                          //
        final TextView ilosczlota2 = findViewById(R.id.amout_gold5);                        // wprowadzenie tekstu "ilosczlota2" powiązanego z id "amout_gold2" z layoutu
        final Button pożyczka2 = findViewById(R.id.credit5);                                // wprowadzenie przycisku "pożyczka2" powiązanego z przyciskiem o id "credit2" z layoutu
        ilosczlota2.setText(String.valueOf(ilosczlotaa));                                   // tekst "ilosczlota2" wyświetla wartość zmiennej "ilosczlotaa"

        button_animation.setOnClickListener(new View.OnClickListener() {                    // kliknięcie przez  użytkownika przycisku button_animation powoduje działanie:
            @Override
            public void onClick(View v) {                                                   //
                if(ilosczlotaa>=50) {                                                       // możliwe tylko dla ilosczlotaa=>50 (posiadanej ilości waluty przez użytkownika)
                    button_animation.setVisibility(View.GONE);                              //
                    button_animation.setEnabled(false);                                     // zniknięcie i  uniemożliwienie działania przycisku
                    scoutanimation.start();                                                 // start animacji "scoutanimation"
                    final Handler handler = new Handler();                                                                        //
                    handler.postDelayed(() -> {                                             // z opóźnieniem równym 2 sekundy ujawnienie przycisku "scroll", który przekazuje użytkownikowi wskazówki fabularne
                        scroll.setVisibility(View.VISIBLE);                                 // oraz zniknięcie grafiki "scout2" odpowiadającego za animację
                        findViewById(R.id.scout5).setVisibility(View.GONE);                 //
                    }, 2000);                                                      //
                    ilosczlotaa = ilosczlotaa - 50;                                         // zmniejszenie wartości zmiennej "ilosczlotaa" o 50 (koszt kliknięcia przycisku button_animation)
                    SharedPreferences saveLayout = getSharedPreferences(SAVE, MODE_PRIVATE);// wprowadzenie interfejsu loadLayout pozwalającego przechowywać dane
                    SharedPreferences.Editor editor = saveLayout.edit();                    // wczytanie interfejsu umożliwającego zmiany wartości w loadLayout
                    ilosczlota2.setText(String.valueOf(ilosczlotaa));                       // aktualizacja wartości wyświetlanej przez tekst "ilosczlota2"
                    editor.putInt("ilosczlotaaa", ilosczlotaa);                             // zmiana wartości przechowywanej w "ilosczlotaaa" na aktualną wartość "ilosczlotaa"
                    editor.apply();                                                         // zatwierdzenie zmiany w loadLayout

                    correct2_button1.setEnabled(false);                                     // na użycie tylko przycisku powiązanego fabularnie z przyciskiem "opcja3"
                    correct2_button1.setVisibility(View.INVISIBLE);
                    incorrect2_button2.setEnabled(false);
                    incorrect2_button2.setVisibility(View.INVISIBLE);
                    incorrect2_button3.setEnabled(false);
                    incorrect2_button3.setVisibility(View.INVISIBLE);
                    incorrect2_button4.setEnabled(false);
                    incorrect2_button4.setVisibility(View.INVISIBLE);

                    opcja1.setChecked(false);                                                           // zmiana statusu przycisków wyboru po powrocie z reklamy do aktywności
                    opcja2.setChecked(false);                                                           // wybrany zostaje przycisk, który jest niewidoczny, dzięki czemu dla użytkownika
                    opcja3.setChecked(false);                                                           // po powrocie do reklamy żaden z przycisków nie jest wybrany.
                    opcja4.setChecked(false);                                                           // Dzięki temu nie występują błędy związane z złotem.
                    opcja5.setChecked(true);                                                            //


                    if (horse.isPlaying()) {
                        horse.stop();
                    }

                    try {
                        horse.reset();
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("horse.mp3");

                        horse.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        horse.prepare();
                        horse.start();
                        horse.setVolume((float) 1.0,(float)1.0);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        scroll.setOnClickListener(view ->{                                                  // klikniecie przycisku "scroll" powoduje ujawnienie po 0,2s layoutu o id "infoscroll2"
            // w interejsie użytkownika pojawia się wskazówka fabularna i przycisk
            // pozwalajacy na powrót do głównej zawartości aktywności.

            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                findViewById(R.id.infoscroll5).setVisibility(View.VISIBLE);
                // w interfejsie użytkownika jest to powrót do głównej zawartości aktywności.
            }, 200);

            if (turnPage.isPlaying()) {
                turnPage.stop();
            }

            try {
                turnPage.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("turnPage.mp3");

                turnPage.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                turnPage.prepare();
                turnPage.start();
                turnPage.setVolume((float) 0.6,(float)0.6);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




        });

        final Button scrollback2 = findViewById(R.id.backscroll5);                               // wprowadzenie przycisku "scrollback2" odnoszący się to przycisku z layoutu o id "backscroll2.
        scrollback2.setOnClickListener(v -> {                                                    // klikniecie przycisku "scrollback2"
            //




            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                findViewById(R.id.infoscroll5).setVisibility(View.INVISIBLE);                   // powoduje znikniecie po 0,2s layoutu o id "infoscroll2"
                // w interfejsie użytkownika jest to powrót do głównej zawartości aktywności.
            }, 200);

            if (turnPage.isPlaying()) {
                turnPage.stop();
            }

            try {
                turnPage.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("turnPage.mp3");

                turnPage.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                turnPage.prepare();
                turnPage.start();
                turnPage.setVolume((float) 0.6,(float)0.6);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });


        pożyczka2.setOnClickListener(v -> {

            if (rewardedInterstitialAd != null) {
                rewardedInterstitialAd.show(/* Activity */ page5.this,/*
OnUserEarnedRewardListener */ page5.this);
                mp.pause();
            } else {
                Toast.makeText(page5.this, "Ad Not Loaded", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Ad Not Loaded");
            }
        });

        opcja4.setOnClickListener(view -> {                                                 // wybór przycisku "opcja1" zmienia stan przycisków poniżej, pozawaljąc użytkownikowi
            // na użycie tylko przycisku powiązanego fabularnie z przyciskiem "opcja1"
            correct2_button1.setEnabled(true);
            correct2_button1.setVisibility(View.VISIBLE);
            correct2_button1.setClickable(true);
            incorrect2_button2.setEnabled(false);
            incorrect2_button2.setVisibility(View.INVISIBLE);
            incorrect2_button3.setEnabled(false);
            incorrect2_button3.setVisibility(View.INVISIBLE);
            incorrect2_button4.setEnabled(false);
            incorrect2_button4.setVisibility(View.INVISIBLE);

            if (ilosczlotaa<60){                                                            // jeżeli wartość zmiennej "ilosczlotaa" (zasoby użytkownika) jest mniejsza
                correct2_button1.setEnabled(false);                                         // od 40, czyli kosztu wybranej opcji to także powiązany fabularnie przycisk
                correct2_button1.setClickable(false);                                       // nie jest możliwy do kliknięcia przez użytkownika

            }

        });





        opcja2.setOnClickListener(view -> {                                                 // wybór przycisku "opcja2" zmienia stan przycisków poniżej, pozawaljąc użytkownikowi
            correct2_button1.setEnabled(false);                                             // na użycie tylko przycisku powiązanego fabularnie z przyciskiem "opcja2"
            correct2_button1.setVisibility(View.INVISIBLE);
            incorrect2_button2.setEnabled(true);
            incorrect2_button2.setVisibility(View.VISIBLE);
            incorrect2_button3.setEnabled(false);
            incorrect2_button3.setVisibility(View.INVISIBLE);
            incorrect2_button4.setEnabled(false);
            incorrect2_button4.setVisibility(View.INVISIBLE);

            if (ilosczlotaa<50){                                                            // jeżeli wartość zmiennej "ilosczlotaa" (zasoby użytkownika) jest mniejsza
                // od 50, czyli kosztu wybranej opcji to także powiązany fabularnie przycisk
                incorrect2_button2.setEnabled(false);                                       // nie jest możliwy do kliknięcia przez użytkownika
                incorrect2_button2.setVisibility(View.INVISIBLE);

            }


        });

        opcja3.setOnClickListener(view -> {                                                 // wybór przycisku "opcja3" zmienia stan przycisków poniżej, pozawaljąc użytkownikowi
            correct2_button1.setEnabled(false);                                             // na użycie tylko przycisku powiązanego fabularnie z przyciskiem "opcja3"
            correct2_button1.setVisibility(View.INVISIBLE);
            incorrect2_button2.setEnabled(false);
            incorrect2_button2.setVisibility(View.INVISIBLE);
            incorrect2_button3.setEnabled(true);
            incorrect2_button3.setVisibility(View.VISIBLE);
            incorrect2_button4.setEnabled(false);
            incorrect2_button4.setVisibility(View.INVISIBLE);

            if (ilosczlotaa<40){                                                            // jeżeli wartość zmiennej "ilosczlotaa" (zasoby użytkownika) jest mniejsza
                // od 60, czyli kosztu wybranej opcji to także powiązany fabularnie przycisk
                // nie jest możliwy do kliknięcia przez użytkownika

                incorrect2_button3.setEnabled(false);
                incorrect2_button3.setVisibility(View.INVISIBLE);

            }


        });

        opcja1.setOnClickListener(view -> {                                                 // wybór przycisku "opcja4" zmienia stan przycisków poniżej, pozawaljąc użytkownikowi
            correct2_button1.setEnabled(false);                                             // na użycie tylko przycisku powiązanego fabularnie z przyciskiem "opcja4"
            correct2_button1.setVisibility(View.INVISIBLE);
            incorrect2_button2.setEnabled(false);
            incorrect2_button2.setVisibility(View.INVISIBLE);
            incorrect2_button3.setEnabled(false);
            incorrect2_button3.setVisibility(View.INVISIBLE);
            incorrect2_button4.setEnabled(true);
            incorrect2_button4.setVisibility(View.VISIBLE);
            if (ilosczlotaa<30){                                                            // jeżeli wartość zmiennej "ilosczlotaa" (zasoby użytkownika) jest mniejsza
                incorrect2_button4.setEnabled(false);                                       // od 70, czyli kosztu wybranej opcji to także powiązany fabularnie przycisk
                incorrect2_button4.setVisibility(View.INVISIBLE);                           // nie jest możliwy do kliknięcia przez użytkownika
            }


        });

        opcja5.setOnClickListener(view -> {                                                 // wybór przycisku "opcja4" zmienia stan przycisków poniżej, pozawaljąc użytkownikowi
            correct2_button1.setEnabled(false);                                             // na użycie tylko przycisku powiązanego fabularnie z przyciskiem "opcja4"
            correct2_button1.setVisibility(View.INVISIBLE);
            incorrect2_button2.setEnabled(false);
            incorrect2_button2.setVisibility(View.INVISIBLE);
            incorrect2_button3.setEnabled(false);
            incorrect2_button3.setVisibility(View.INVISIBLE);
            incorrect2_button4.setEnabled(false);
            incorrect2_button4.setVisibility(View.INVISIBLE);



        });


        correct2_button1.setOnClickListener(v -> {                                          // kliknięcie przycisku ""correct2_button1" powoduje:
            numer++;                                                                        // zwiększenie wartości zmiennej "numer" o jeden, odpowiadającej za zapis
            ilosczlotaa = ilosczlotaa + 40;                                                 // zwiększenie wartości zmiennej "ilosczlotaa" o 60, zwiększenie zasobów gracza o 60
            SharedPreferences saveLayout = getSharedPreferences(SAVE, MODE_PRIVATE);        // wprowadzenie interfejsu loadLayout pozwalającego przechowywać dane
            SharedPreferences.Editor editor = saveLayout.edit();                            // wczytanie interfejsu umożliwającego zmiany wartości w loadLayout
            editor.putInt("numerr", numer);                                                 // zmiana wartości przechowywanej w "numerr" na aktualną wartość "numer"
            editor.apply();                                                                 // zatwierdzenie zmiany w loadLayout
            ilosczlota2.setText(String.valueOf(ilosczlotaa));                               // aktualizacja wartości wyświetlanej przez tekst "ilosczlota2"
            editor.putInt("ilosczlotaaa", ilosczlotaa);                                     // zmiana wartości przechowywanej w "ilosczlotaaa" na aktualną wartość "ilosczlotaa"
            editor.apply();                                                                 // zatwierdzenie zmiany w loadLayout
            openpage5();                                                                    // wywołanie metody "openpage()"
        });


        incorrect2_button2.setOnClickListener(v -> {                                        // kliknięcie przycisku "incorrect2_button2" powoduje:

            ilosczlotaa = ilosczlotaa - 50;                                                 // zmniejszenie wartości zmiennej "ilosczlotaa" o 50, zmniejszenie zasobów gracza o 50
            SharedPreferences saveLayout = getSharedPreferences(SAVE, MODE_PRIVATE);        // wprowadzenie interfejsu loadLayout pozwalającego przechowywać dane
            SharedPreferences.Editor editor = saveLayout.edit();                            // wczytanie interfejsu umożliwającego zmiany wartości w loadLayout
            ilosczlota2.setText(String.valueOf(ilosczlotaa));                                // aktualizacja wartości wyświetlanej przez tekst "ilosczlota2"
            editor.putInt("ilosczlotaaa", ilosczlotaa);                                     // zmiana wartości przechowywanej w "ilosczlotaaa" na aktualną wartość "ilosczlotaa"
            editor.apply();                                                                 // zatwierdzenie zmiany w loadLayout
            findViewById(R.id.przegrales5).setVisibility(View.VISIBLE);                      // layout o id "przegrales" staje się widoczny
            findViewById(R.id.radioButton5_2).setVisibility(View.INVISIBLE);                  // przycisk wyboru pozwalający na kliknięcie "incorrect2_button2" znika i nie może być wybrany.
            incorrect2_button2.setEnabled(false);                                           //
            incorrect2_button2.setVisibility(View.INVISIBLE);                               //
        });

        incorrect2_button3.setOnClickListener(v -> {                                        // kliknięcie przycisku "incorrect2_button3" powoduje:

            ilosczlotaa = ilosczlotaa - 40;                                                 // zmniejszenie wartości zmiennej "ilosczlotaa" o 60 zmniejszenie zasobów gracza o 60
            SharedPreferences saveLayout = getSharedPreferences(SAVE, MODE_PRIVATE);        // wprowadzenie interfejsu loadLayout pozwalającego przechowywać dane
            SharedPreferences.Editor editor = saveLayout.edit();                            // wczytanie interfejsu umożliwającego zmiany wartości w loadLayout
            ilosczlota2.setText(String.valueOf(ilosczlotaa));                               // aktualizacja wartości wyświetlanej przez tekst "ilosczlota2"
            editor.putInt("ilosczlotaaa", ilosczlotaa);                                     // zmiana wartości przechowywanej w "ilosczlotaaa" na aktualną wartość "ilosczlotaa"
            editor.apply();                                                                 // zatwierdzenie zmiany w loadLayout
            findViewById(R.id.przegrales5).setVisibility(View.VISIBLE);                      // layout o id "przegrales" staje się widoczny
            findViewById(R.id.radioButton5_3).setVisibility(View.INVISIBLE);                  // przycisk wyboru pozwalający na kliknięcie "incorrect2_button3" znika i nie może być wybrany.
            incorrect2_button3.setEnabled(false);                                           //
            incorrect2_button3.setVisibility(View.INVISIBLE);                               //
        });

        incorrect2_button4.setOnClickListener(v -> {                                        // kliknięcie przycisku "incorrect2_button4" powoduje:
            ilosczlotaa = ilosczlotaa - 30;                                                 // zmniejszenie wartości zmiennej "ilosczlotaa" o 70 zmniejszenie zasobów gracza o 70
            SharedPreferences saveLayout = getSharedPreferences(SAVE, MODE_PRIVATE);        // wprowadzenie interfejsu loadLayout pozwalającego przechowywać dane
            SharedPreferences.Editor editor = saveLayout.edit();                            // wczytanie interfejsu umożliwającego zmiany wartości w loadLayout
            ilosczlota2.setText(String.valueOf(ilosczlotaa));                               // aktualizacja wartości wyświetlanej przez tekst "ilosczlota2"
            editor.putInt("ilosczlotaaa", ilosczlotaa);                                     // zmiana wartości przechowywanej w "ilosczlotaaa" na aktualną wartość "ilosczlotaa"
            editor.apply();                                                                 // zatwierdzenie zmiany w loadLayout
            findViewById(R.id.przegrales5).setVisibility(View.VISIBLE);                      // layout o id "przegrales" staje się widoczny
            findViewById(R.id.radioButton5_1).setVisibility(View.INVISIBLE);                  // przycisk wyboru pozwalający na kliknięcie "incorrect2_button4" znika i nie może być wybrany.
            incorrect2_button4.setEnabled(false);                                           //
            incorrect2_button4.setVisibility(View.INVISIBLE);                               //
        });

        final Button you_lose = findViewById(R.id.powrot5);                                  // wprowadzenie przycisku "you_lose" odnoszącego się do przycisku "powrot" z layoutu użytkownika
        you_lose.setOnClickListener(v -> {                                                  // kliknięcie przycisku "you_lose" powoduje:
            findViewById(R.id.przegrales5).setVisibility(View.INVISIBLE);                    // layout o id "przegrales" staje się niewidoczny, powrót do głównej aktywności


        });





    }




    private void loadAd() {
        // Use the test ad unit ID to load an ad.
        RewardedInterstitialAd.load(page5.this, "ca-app-pub-3940256099942544/5354046379",
                new AdRequest.Builder().build(),  new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(RewardedInterstitialAd ad) {
                        rewardedInterstitialAd = ad;
                        Log.e(TAG, "onAdLoaded");

                        rewardedInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            /** Called when the ad failed to show full screen content. */
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Log.i(TAG, "onAdFailedToShowFullScreenContent");
                            }

                            /** Called when ad showed the full screen content. */
                            @Override
                            public void onAdShowedFullScreenContent() {
                                Log.i(TAG, "onAdShowedFullScreenContent");
                            }

                            /** Called when full screen content is dismissed. */
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.i(TAG, "onAdDismissedFullScreenContent");

                                loadAd();
                            }
                        });
                    }
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        Log.e(TAG, "onAdFailedToLoad");

                    }
                });
    }


    public void openbackgroundsound() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {

            if (mp.isPlaying()) {
                mp.stop();
            }

            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("castle.mp3");

                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.setVolume((float) 0.5,(float)0.5);
                mp.start();
                mp.setLooping(true);


            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 2000);
    }



    @Override
    public void onStop(){
        super.onStop();
        mp.pause();
    }
    @Override
    public void onStart(){
        super.onStart();
        mp.start();
        mp.setLooping(true);

    }


    public void openpage5(){
        Intent intent5 = new Intent(this, page5.class);                         // wprowadzenie intencji "intent2" otwierającej aktywność "page3.class"
        startActivity(intent5);                                                             // metoda otwierająca aktywność opisaną w intencji "intent2"
    }



    @Override
    public void onUserEarnedReward(@NonNull  RewardItem rewardItem) {                       // nagroda dla gracza za obejrzenie reklamy
        Log.i(TAG, "onUserEarnedReward");
        final TextView ilosczlota2 = findViewById(R.id.amout_gold5);                        // wprowadzenie tekstu "ilosczlota2" powiązanego z id "amout_gold2" z layoutu
        ilosczlotaa=ilosczlotaa+50;                                                         // zwiększenie wartości zmiennej "ilosczlotaa" o 50, zwiększenie zasobów gracza o 50
        ilosczlota2.setText(String.valueOf(ilosczlotaa));                                   // aktualizacja wartości wyświetlanej przez tekst "ilosczlota2"
        SharedPreferences saveLayout = getSharedPreferences(SAVE, MODE_PRIVATE);            // wprowadzenie interfejsu loadLayout pozwalającego przechowywać dane
        SharedPreferences.Editor editor = saveLayout.edit();                                // wczytanie interfejsu umożliwającego zmiany wartości w loadLayout
        editor.putInt("ilosczlotaaa", ilosczlotaa);                                         // zmiana wartości przechowywanej w "ilosczlotaaa" na aktualną wartość "ilosczlotaa"
        editor.apply();                                                                     // zatwierdzenie zmiany w loadLayout
        final RadioButton opcja1 = findViewById(R.id.radioButton5_1);                         //
        final RadioButton opcja2 = findViewById(R.id.radioButton5_2);                         //
        final RadioButton opcja3 = findViewById(R.id.radioButton5_3);                         // wprowadzenie przycisków wyboru jednej z czterech opcji
        final RadioButton opcja4 = findViewById(R.id.radioButton5_4);                         // odnoszących się do radiobuttons z layoutu użytkownika o id radioButton1 itd.
        final RadioButton opcja5 = findViewById(R.id.radioButton5_5);                         // wprowadzenie fejk przycisku, eliminującego błedy.
        opcja1.setChecked(false);                                                           // zmiana statusu przycisków wyboru po powrocie z reklamy do aktywności
        opcja2.setChecked(false);                                                           // wybrany zostaje przycisk, który jest niewidoczny, dzięki czemu dla użytkownika
        opcja3.setChecked(false);                                                           // po powrocie do reklamy żaden z przycisków nie jest wybrany.
        opcja4.setChecked(false);                                                           // Dzięki temu nie występują błędy związane z złotem.
        opcja5.setChecked(true);                                                            //

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            mp.start();
            mp.isLooping();
        }, 1000);

    /*    final Handler handler = new Handler();                                  // z opóźnieniem równym 2 sekundy ujawnienie przycisku "scroll", który przekazuje użytkownikowi wskazówki fabularne
        handler.postDelayed(() -> {                                             // oraz zniknięcie grafiki "scout2" odpowiadającego za animację
            if (mp.isPlaying()) {
                mp.stop();
            }

            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("coins.mp3");
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 2000); */




    }

}
