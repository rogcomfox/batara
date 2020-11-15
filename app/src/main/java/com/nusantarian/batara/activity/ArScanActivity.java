package com.nusantarian.batara.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.nusantarian.batara.R;
import com.nusantarian.batara.ar.CustomArFragment;
import com.nusantarian.batara.databinding.ActivityArScanBinding;

import java.util.Collection;
import java.util.Objects;

public class ArScanActivity extends AppCompatActivity implements Scene.OnUpdateListener, View.OnClickListener {

    private ActivityArScanBinding binding;
    private CustomArFragment arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArScanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(this);

        arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.camera_view);
        Objects.requireNonNull(arFragment).getArSceneView().getScene().addOnUpdateListener(this);
    }

    public void setupDatabase(Config config, Session session) {
        Bitmap sbyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.surabaya);
        Bitmap jogjaBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jogja);
        Bitmap bdgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bandung);
        Bitmap torajaBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.toraja);
        Bitmap pdgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.padang);
        AugmentedImageDatabase aid = new AugmentedImageDatabase(session);
        aid.addImage("surabaya", sbyBitmap);
        aid.addImage("jogja", jogjaBitmap);
        aid.addImage("bandung", bdgBitmap);
        aid.addImage("toraja", torajaBitmap);
        aid.addImage("padang", pdgBitmap);
        config.setAugmentedImageDatabase(aid);
    }

    @Override
    public void onUpdate(FrameTime frameTime) {
        Frame frame = arFragment.getArSceneView().getArFrame();
        Collection<AugmentedImage> images = Objects.requireNonNull(frame).getUpdatedTrackables(AugmentedImage.class);
        Handler handler = new Handler();
        handler.postDelayed(this::raiseResult, 10000);

        for (AugmentedImage image : images) {
            if (image.getTrackingState() == TrackingState.TRACKING) {
                if (image.getName().equals("surabaya")) {
                    Anchor anchor = image.createAnchor(image.getCenterPose());
                    createModel(anchor);
                }
            }
        }
    }

    private void createModel(Anchor anchor) {
        ModelRenderable.builder()
                .setSource(this, R.drawable.surabaya)
                .build()
                .thenAccept(modelRenderable -> placeModel(modelRenderable, anchor));
    }

    private void placeModel(ModelRenderable modelRenderable, Anchor anchor) {
        AnchorNode node = new AnchorNode(anchor);
        node.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(node);
        raiseResult();
    }

    public void raiseResult() {
        binding.tvCompleteScan.setVisibility(View.VISIBLE);
        binding.cardInfo.setVisibility(View.VISIBLE);
        binding.btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view:
                startActivity(new Intent(this, InfoArActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }
}