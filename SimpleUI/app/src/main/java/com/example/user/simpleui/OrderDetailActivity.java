package com.example.user.simpleui;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseClassName;

import android.os.Handler;

import java.lang.ref.WeakReference;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Order order = getIntent().getParcelableExtra("order");

        TextView noteTextView = (TextView)findViewById(R.id.noteTextView);
        TextView orderResultTextView = (TextView)findViewById(R.id.orderResultsTextView);
        TextView storeInfoTextView = (TextView)findViewById(R.id.storeInfoTextView);

        noteTextView.setText(order.getNote());
        storeInfoTextView.setText(order.getStoreInfo());

        String orderResultsText = "";
        for(DrinkOrder drinkOrder: order.getDrinkOrders())
        {
            String mNumber = String.valueOf(drinkOrder.getmNumber());
            String lNumber = String.valueOf(drinkOrder.getlNumber());
            String drinkName = drinkOrder.getDrink().getName();
            orderResultsText += drinkName + "  M: " + mNumber + "  L:  " + lNumber + "\n";
        }

        orderResultTextView.setText(orderResultsText);

        ImageView staticMapImageView = (ImageView)findViewById(R.id.staticMapImageView);

        (new GeoCodingTak(staticMapImageView)).execute("");

        Log.e("Main Thread ID", Long.toString(Thread.currentThread().getId()));

//        Handler handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                testTextView.setText("Hello Handler");
//                Log.e("Handler Thread ID", Long.toString(Thread.currentThread().getId()));
//                return false;
//            }
//        });
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                testTextView.setText("Hello Handler POST DELAY");
//            }
//        },1000);
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
////                    testTextView.setText("Hello Thread");
//                    Log.e("Thread ID", Long.toString(Thread.currentThread().getId()));
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

//        thread.run();
    }

    public static class GeoCodingTak extends AsyncTask<String, Void, Bitmap>{

        WeakReference<ImageView> imageViewWeakReference;

        @Override
        protected Bitmap doInBackground(String... params) {
//            Log.e("Background Thread ID", Long.toString(Thread.currentThread().getId()));
            double[] latlng = Utils.getLatLngFromAddress(params[0]);
            if(latlng != null)
            {
                return Utils.getStaticMapFromLatLng(latlng);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
//            Log.e("PostExecute Thread ID", Long.toString(Thread.currentThread().getId()));
            super.onPostExecute(bitmap);
            if(bitmap != null)
            {
                if(imageViewWeakReference.get() != null)
                {
                    ImageView imageView =imageViewWeakReference.get();
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        public GeoCodingTak(ImageView imageView)
        {
            imageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

    }
}
