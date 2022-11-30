package com.example.prog3_ab5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int WIDTH = 800, HEIGHT = 800, TEXT_SIZE = 50;
    private ImageView imageView;
    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;

    private void createHorizontalCenteredText(){

    }

    private void helloWorld(){
        String hwString = "Hello World!";
        float textWidth = this.paint.measureText(hwString);
        this.paint.setColor(Color.BLACK);
        this.canvas.drawText(hwString, (this.WIDTH - textWidth) / 2, this.HEIGHT / 2, this.paint);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        imageView = new ImageView(this);
        imageView.setImageBitmap(this.bitmap);
        paint = new Paint();
        setContentView(imageView);

        this.canvas.drawColor(Color.argb(255,191,214,199));
        this.paint.setTextSize(TEXT_SIZE);

        helloWorld();
    }
}