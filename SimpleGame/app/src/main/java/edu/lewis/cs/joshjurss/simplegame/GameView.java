package edu.lewis.cs.joshjurss.simplegame;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by joshjurss on 4/25/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Paint backgroundPaint;
    private Paint circlePaint;
    private Paint textPaint;

    private Point myCircle;
    private int direction;
    private int radius;

    private GameThread gameThread;

    private int screenWidth;
    private boolean touchCircle = false;

    private GestureDetector gestureDetector;

    private SoundPool soundPool;
    private ArrayList<Integer> soundArray;
    private Context parent;

    private int score = 0;
    private double totalElapsedTime = 0;

    public GameView(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLUE);

        circlePaint = new Paint();
        circlePaint.setColor(Color.MAGENTA);
        circlePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);

        myCircle = new Point();
        myCircle.x = 0;
        myCircle.y = 300;
        direction = 1;
        radius = 30;

        DoubleTapListener doubleTapListener = new DoubleTapListener();
        gestureDetector = new GestureDetector(getContext(), doubleTapListener);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes.Builder attributesBuilder = new AudioAttributes.Builder();
            attributesBuilder.setUsage(AudioAttributes.USAGE_GAME);
            AudioAttributes attributes = attributesBuilder.build();
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(1);
            builder.setAudioAttributes(attributes);
            soundPool = builder.build();

        }
        else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        soundArray = new ArrayList<>();
        parent = getContext();
        soundArray.add(soundPool.load(parent, R.raw.blocker_hit, 1));
        soundArray.add(soundPool.load(parent, R.raw.cannon_fire, 1));
        soundArray.add(soundPool.load(parent, R.raw.target_hit, 1));
    }

    public void drawGameElements(Canvas canvas){
        canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawCircle(myCircle.x, myCircle.y, radius, circlePaint);
        canvas.drawText(String.format("Score: %1$d", score), 30, 100, textPaint );
    }

    private void updatePositions(double elapsedTime){
        myCircle.x += elapsedTime/2*direction;
        if(myCircle.x > screenWidth){
            myCircle.x = screenWidth;
            direction = -1;
            soundPool.play(soundArray.get(0), 1, 1, 0, 0, 1);
            score++;
        } else if(myCircle.x <= 0){
            myCircle.x = 0;
            direction = 1;
            soundPool.play(soundArray.get(1), 1, 1, 0, 0, 1);
            score++;
        }

        if(score >=5){
            gameThread.setThreadRunning(false);
            showGameOver();
        }
    }

    public void moveCircle(int x, int y){
        myCircle.x = x;
        myCircle.y = y;
    }

    public boolean inCircle(int x,int y){
        double square_dist=Math.pow(myCircle.x-x,2)+Math.pow(myCircle.y-y,2);
        return square_dist<Math.pow(radius,2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventX=(int)event.getX();
        int eventY=(int)event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(inCircle(eventX,eventY))
                    touchCircle=true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(touchCircle)
                    moveCircle(eventX,eventY);
                break;
            case MotionEvent.ACTION_UP:
                touchCircle=false;
                break;
            default:
                return false;
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    public void stopGame(){
        if(gameThread != null){
            gameThread.setThreadRunning(false);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameThread = new GameThread(surfaceHolder);
        gameThread.setThreadRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        textPaint.setTextSize(i1/20);
        radius=i1/30;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        gameThread.setThreadRunning(false);
    }

    private void showGameOver() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("GameOver");
        builder.setCancelable(false);
        String message = String.format("TotalTime:%1$.1f", totalElapsedTime / 1000);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        soundPool.play(soundArray.get(2), 1, 1, 0, 1, 1);
        Activity parent = (Activity)getContext();
        parent.runOnUiThread(new Runnable() {
            public void run() {
                builder.show();
            }
        });
    }

    private class DoubleTapListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            int x = (int)e.getX();
            int y = (int)e.getY();

            moveCircle(x, y);
            return true;
        }
    }

    private class GameThread extends Thread{
        private final SurfaceHolder surfaceHolder;
        private boolean threadRunning = true;

        public GameThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
            setName("GameThread");
        }

        public void setThreadRunning(boolean state){
            threadRunning = state;
        }

        @Override
        public void run() {
            Canvas canvas = null;

            long previousFrameTime = System.currentTimeMillis();
            long currentTime;
            double elapsedTimeMs;

            while (threadRunning){
                try {
                    //obtain a lock
                    canvas = surfaceHolder.lockCanvas(null);
                    //exclusive access
                    synchronized (surfaceHolder){
                        currentTime = System.currentTimeMillis();
                        elapsedTimeMs = currentTime - previousFrameTime;
                        previousFrameTime = currentTime;
                        updatePositions(elapsedTimeMs);
                        drawGameElements(canvas);
                        totalElapsedTime += elapsedTimeMs;
                    }
                }finally {
                    //unlock the canvas
                    if(canvas != null){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
