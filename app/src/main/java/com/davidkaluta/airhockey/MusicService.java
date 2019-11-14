package com.davidkaluta.airhockey;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

/**
 * A service to play the game's music
 * @author  Leonid Shafran (modified by David Kaluta)
 * @version 18
 * @since   8
 */
public class MusicService extends Service {

    @SuppressWarnings("unused")
    private final String TAG = "MusicService";

    private static final int NOTIFICATION_ID = 1;
    private MediaPlayer mPlayer;
    private int mStartID;

    HeadsetIntentReceiver receiver = new HeadsetIntentReceiver();

    @Override
    public void onCreate() {
        super.onCreate();

        // Set up the Media Player
        mPlayer = MediaPlayer.create(this, R.raw.happy);

        if (null != mPlayer) {

            mPlayer.setLooping(true);

            // Stop Service when music has finished playing
            mPlayer.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {

                    // stop Service if it was started with this ID
                    // Otherwise let other start commands proceed
                    stopSelf(mStartID);

                }
            });

            IntentFilter receiverFilter = new IntentFilter(
                    Intent.ACTION_HEADSET_PLUG);
            registerReceiver(receiver, receiverFilter);
        }

    }

    public void setLawVolume(){
        mPlayer.setVolume(0.2f, 0.2f);
    }

    public void resetLawVolume(){
        mPlayer.setVolume(1f, 1f);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        if (null != mPlayer) {

            // ID for this start command
            mStartID = startid;

            if (mPlayer.isPlaying()) {

                // Rewind to beginning of song
                mPlayer.seekTo(0);

            } else {

                // Start playing song
                mPlayer.start();

            }

        }

        // Don't automatically restart this Service if it is killed
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        if (null != mPlayer) {

            mPlayer.stop();
            mPlayer.release();

        }
    }

    // Can't bind to this Service
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }


//	@Override
//	protected void onStart() {
//		super.onStart();
//		IntentFilter receiverFilter = new IntentFilter(
//				Intent.ACTION_HEADSET_PLUG);
//		registerReceiver(receiver, receiverFilter);
//	}


//	@Override
//	protected void onStop() {
//		super.onStop();
//		unregisterReceiver(receiver);
//	}

    private void updateState(String state) {
        // textViewHeadsetState.setText(state);
        if(state.equals("Plugged")){
            setLawVolume();
        }
        else{
            resetLawVolume();
        }
    }

    /**
     * A BroadcastReceiver for volume control
     * @author  Leonid Shafran
     * @version 18
     * @since   8
     */
    public class HeadsetIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case (0):
                        updateState("Unplugged");
                        break;
                    case (1):
                        updateState("Plugged");
                        break;
                    default:
                        updateState("Error");
                }
            }
        }
    }


}
