package com.example.prog3_ab5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private final int WIDTH = 800, HEIGHT = 800, TEXT_SIZE = 50;
    private final int BB_Border_top = 400, BB_Border_bottom = 770, BB_Border_left = 30, BB_Border_right = 770;
    private ImageView imageView;
    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;
    private Timer timer = new Timer();
    int ballRadius = 20;
    float ballX = BB_Border_left + ballRadius + 10;
    float ballY = BB_Border_top + ballRadius + 10;
    float ballSpeedX = 7.6f;
    float ballSpeedY = 4.5f;

    private void helloNeighbour() {

        createHorizontalCenteredText("Hallo, Daniel und Luca", this.HEIGHT / 5);
    }

    private void createHorizontalCenteredText(String text, int y) {
        float textWidth = this.paint.measureText(text);
        this.paint.setColor(Color.BLACK);
        this.canvas.drawText(text, (this.WIDTH - textWidth) / 2, y, this.paint);
    }

    private void helloWorld() {
        String hwString = "Hello World!";
       /* float textWidth = this.paint.measureText(hwString);
        this.paint.setColor(Color.BLACK);
        this.canvas.drawText(hwString, (this.WIDTH - textWidth) / 2, this.HEIGHT / 2, this.paint);*/

        createHorizontalCenteredText(hwString, this.HEIGHT / 10);
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

        this.canvas.drawColor(Color.argb(255, 191, 214, 199));
        this.paint.setTextSize(TEXT_SIZE);
        helloWorld();
        helloNeighbour();
        zeichneSmiley(100);

        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                derSpringendePunkt();
            }
        }, 0, 17);
    }

    private void derSpringendePunkt() {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //     Log.i("MainActivity", LocalDateTime.now()
        //             + ": der springende Punkt");
        //  }

        // draw new field
        this.paint.setColor(Color.LTGRAY);
        this.canvas.drawRect(BB_Border_left, BB_Border_top, BB_Border_right, BB_Border_bottom, this.paint);

        // check if ball hits border
        if (ballX + ballRadius + 7 >= BB_Border_right) {
            ballSpeedX *= -1;
        } else if (ballX - ballRadius - 3 <= BB_Border_left) {
            ballSpeedX *= -1;
        }
        if (ballY + ballRadius + 3 >= BB_Border_bottom) {
            ballSpeedY *= -1;
        } else if (ballY - ballRadius - 3 <= BB_Border_top) {
            ballSpeedY *= -1;
        }

        // move ball
        ballX += ballSpeedX;
        ballY += ballSpeedY;


        // draw ball
        this.paint.setColor(Color.BLACK);
        this.canvas.drawCircle(ballX, ballY, ballRadius, this.paint);

        this.imageView.postInvalidate();
    }

    public void zeichneSmiley(int radius) {
        // Draw the head
        this.paint.setColor(Color.YELLOW);
        int circleX = this.WIDTH / 2;
        int circleY = this.HEIGHT / 3 + 10;
        this.canvas.drawCircle(circleX, circleY, radius, this.paint);

        // Draw the eyes
        float radiusEye = radius / 10;
        this.paint.setColor(Color.BLACK);
        this.canvas.drawCircle(circleX - radius / 2, circleY - radius / 2, radiusEye, this.paint);
        this.canvas.drawCircle(circleX + radius / 2, circleY - radius / 2, radiusEye, this.paint);

        // Draw the mouth
        this.paint.setColor(Color.RED);
        this.canvas.drawArc(circleX - radius / 2, circleY - radius / 2, circleX + radius / 2, circleY, -180, -180, true, this.paint);
    }
}