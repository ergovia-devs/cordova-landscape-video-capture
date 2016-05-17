package de.ergovia;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.afollestad.materialcamera.CaptureActivity;
import com.afollestad.materialcamera.CaptureActivity2;
import com.afollestad.materialcamera.util.CameraUtil;

/**
 * Created by ehmm on 17.05.2016.
 */
public class VideoPlayer {

    private Activity mContext;

    public VideoPlayer(@NonNull Activity context) {
        this.mContext = context;
    }

    public Intent getIntent() {
        return new Intent(this.mContext, VideoPlaybackActivity.class);
    }

    public void start(int requestCode) {
        this.mContext.startActivityForResult(this.getIntent(), requestCode);
    }

}
