package com.example.user.simpleui;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by user on 2016/8/2.
 */
public class SimpleApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("R2SpjyyrvkDWPD5C0kO80XsqvHEbQ66nkevNRkfZ")
                .server("https://parseapi.back4app.com/")
                .clientKey("XMnvFTLd8Gbi9yZ0BqPnmZyBbQLy0n2XrTc25QFf")
                        //.applicationId("76ee57f8e5f8bd628cc9586e93d428d5")
                        //.server("http://parseserver-ps662-env.us-east-1.elasticbeanstalk.com/parse/")
                        //.clientKey("XMnvFTLd8Gbi9yZ0BqPnmZyBbQLy0n2XrTc25QFf")
                .build());

    }
}
