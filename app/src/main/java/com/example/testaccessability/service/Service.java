package com.example.testaccessability.service;

import android.accessibilityservice.AccessibilityService;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.testaccessability.R;

public class Service extends AccessibilityService {

    private WindowManager windowManager;


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        initWindowManager();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {


    }


    @Override
    public void onInterrupt() {

    }


    private void initWindowManager() {
        windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        windowManagerAddVIew(provideParams());

    }


    private void windowManagerAddVIew(WindowManager.LayoutParams layoutParams) {
        FrameLayout frameLayout = provideView();
        windowManager.addView(frameLayout, layoutParams);

        VideoView videoView = frameLayout.findViewById(R.id.video_view);

        videoView.setVideoURI(Uri.parse(provideUriPath()));
        videoView.start();
    }


    private ImageView provideImagerView() {
        ImageView imageView = new ImageView(this);
        imageView.setBackground(getDrawable(R.drawable.ic_launcher_background));
        return imageView;
    }

    private FrameLayout provideView() {
        return (FrameLayout) LayoutInflater.from(this).inflate(R.layout.activity_main, null);
    }

    private String provideUriPath() {
        return "android.resource://" + getPackageName() + "/" + R.raw.test_video;
    }


    private WindowManager.LayoutParams provideParams() {
        return new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
    }

}
