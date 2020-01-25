package com.english.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kI042U1GYFHPHbvKvPB5poro7PGf53a4SpP8NZKQ")
                // if defined
                .clientKey("WcFnyg2vn5RAaQDLe2EDcAdoorKiiXL3xMnehUm7")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }

}
