package de.ergovia;

import java.io.*;

import android.app.Fragment;
import android.widget.Toast;
import com.afollestad.materialcamera.MaterialCamera;
import com.afollestad.materialcamera.internal.CameraIntentKey;
import com.afollestad.materialcamera.internal.PlaybackVideoFragment;
import de.ergovia.stepfolio.merle.R;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.Environment;
import android.util.Log;

/**
 * CordovaLandscapeVideoCapture plugin for Android
 */
public class CordovaLandscapeVideoCapture extends CordovaPlugin {

    private static final String TAG = "CordovaLandscapeVideoCapture";

    private final static int CAMERA_RQ = 6969;
    private final static int PLAYER_RQ = 6868;
    private final static int RESULT_OK = -1;
    private final static int PERMISSION_RQ = 84;

    private CallbackContext callback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "execute method starting");

        this.callback = callbackContext;

        if (action.equals("captureVideo")) {
            this.captureVideo(args);
            return true;
        } else if (action.equals("playVideo")) {
            this.playVideo(args);
            return true;
        }

        return false;
    }

    private void captureVideo(JSONArray args) throws JSONException {

        File saveDir = saveDir = new File(Environment.getExternalStorageDirectory(), "MaterialCamera");
        saveDir.mkdirs();

        cordova.setActivityResultCallback(this);

        new MaterialCamera(this.cordova.getActivity())
                .saveDir(saveDir)
                .countdownMinutes(3f)
                .start(CAMERA_RQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Received recording or error from MaterialCamera
        if (requestCode == CAMERA_RQ) {
            if (resultCode == RESULT_OK) {
                final File file = new File(data.getData().getPath());
                Toast.makeText(this.cordova.getActivity().getBaseContext(), "Saved to: %" +
                        file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                callback.success(file.getAbsolutePath());
            } else if (data != null) {
                Exception e = (Exception) data.getSerializableExtra(MaterialCamera.ERROR_EXTRA);
                if (e != null) {
                    e.printStackTrace();
                    Toast.makeText(this.cordova.getActivity().getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    callback.error(e.getMessage());
                }
            }
        }
    }


    private void playVideo(JSONArray args) throws JSONException {

        new VideoPlayer(this.cordova.getActivity()).start(PLAYER_RQ);


        /*Fragment frag = PlaybackVideoFragment.newInstance("file:///storage/emulated/0/MaterialCamera/VID_20160517_141241.mp4", false,
                cordova.getActivity().getIntent().getIntExtra(CameraIntentKey.PRIMARY_COLOR, 0));
        cordova.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.container, frag)
                .commit();*/

    }

}
