package com.example.aplikasiservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Service;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.os.IBinder;
import java.io.IOException;

public class WallpaperChangeService extends Service implements Runnable {
    private int wallpaperId[] = {R.drawable.gambar, R.drawable.gambar1};
    private int waktu;
    private int FLAG=0;
    private Thread t;
    private Bitmap picture;
    private Bitmap picture1;


    //@Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public  int onStartCommand(Intent intent, int flag, int startId)
    {
        super.onStartCommand(intent,flag, startId);
        Bundle bundle=intent.getExtras();
        waktu = bundle.getInt("durasi");
        picture=BitmapFactory.decodeResource(getResources(),wallpaperId[0]);
        picture1=BitmapFactory.decodeResource(getResources(),wallpaperId[1]);
        t = new Thread(WallpaperChangeService.this);
        t.start();
        return START_STICKY_COMPATIBILITY;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void run() {
        WallpaperManager myWallpaper;
        myWallpaper = WallpaperManager.getInstance(this);
        try {
            while (true){
                if(FLAG==0){
                    myWallpaper.setBitmap(picture);
                    FLAG++;
                }
                else {
                    myWallpaper.setBitmap(picture1);
                    FLAG--;
                }
                Thread.sleep(100*waktu);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
