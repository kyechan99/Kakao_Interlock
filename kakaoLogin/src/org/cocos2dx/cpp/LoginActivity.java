
package org.cocos2dx.cpp;
 
import java.security.MessageDigest;

import com.kaka.abcd.R;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

public class LoginActivity extends Activity {
	private SessionCallback callback;
	public static Activity actInstance;
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate","onCreate");

		actInstance = this;
		
		try {
	        PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	            MessageDigest md;
	            md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	            String something = new String(Base64.encode(md.digest(), 0));
	            Log.d("Hash key", something);
	        }
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        Log.e("name not found", e.toString());
	    }
		
        KakaoSDK.init(new KakaoSDKAdapter());
        
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        if (!Session.getCurrentSession().checkAndImplicitOpen()) {
            setContentView(R.layout.login);
        }
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult","onActivityResult");
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

	@Override
    protected void onDestroy() {
        Log.d("onDestroy","onDestroy");
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

	private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Log.d("onSessionOpened","onSessionOpened");
        	redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d("onSessionOpenFailed","onSessionOpenFailed");
            if(exception != null) {
                Logger.e(exception);
            }
            Log.d("setContentView","setContentView");

           // setContentView(R.layout.login);
        }
    }
	
	protected void redirectSignupActivity() {
		Log.d("redirectSignupActivity","redirectSignupActivity"); 		
        final Intent intent = new Intent(this, AppActivity.class);
        startActivity(intent);
        finish();
    }

    public static Activity getCurrentActivity() {
        Logger.d("++ currentActivity : " + (actInstance != null ? actInstance.getClass().getSimpleName() : ""));
        return actInstance;
    }
    
    public static Activity getGlobalApplicationContext() {
        if(actInstance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return actInstance;
    }
	

    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
    	LoginActivity.actInstance = currentActivity;
    }

    // jni로 사용하기 위함
 	public static Object getJavaActivity()
 	{
 		return actInstance;
 	}
 	
}