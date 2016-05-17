package de.ergovia;

import android.app.Fragment;
import android.support.annotation.NonNull;
import com.afollestad.materialcamera.internal.BaseCaptureActivity;
import com.afollestad.materialcamera.internal.Camera2Fragment;
import com.afollestad.materialcamera.internal.CameraIntentKey;
import com.afollestad.materialcamera.internal.PlaybackVideoFragment;
import de.ergovia.stepfolio.merle.R;

/**
 * Created by ehmm on 17.05.2016.
 */
public class VideoPlaybackActivity extends BaseCaptureActivity {
    public VideoPlaybackActivity() {
    }

    @NonNull
    public Fragment getFragment() {

        return PlaybackVideoFragment.newInstance("file:///storage/emulated/0/MaterialCamera/VID_20160517_141241.mp4", false,
                getIntent().getIntExtra(CameraIntentKey.PRIMARY_COLOR, 0));
    }
}
