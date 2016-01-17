/****************************************************************************
Copyright (c) 2008-2010 Ricardo Quesada
Copyright (c) 2010-2012 cocos2d-x.org
Copyright (c) 2011      Zynga Inc.
Copyright (c) 2013-2014 Chukong Technologies Inc.
 
http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package org.cocos2dx.cpp;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

import com.kakao.abcd.R;

import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

public class AppActivity extends Cocos2dxActivity {
	static AppActivity currentContext;
	
	private KakaoLink kakaoLink;
	public KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;
	private String imageSrc = "http://cid.dothome.co.kr/images/cid_icon4.png";
	private String linkTxt = "text";
	private String appButton = "market://details?id=com.kakao.sdk.sample ";
	private String webButton = "http://cid.dothome.co.kr";
	
	// jni로 쓰기 위함
	public static Activity actInstance;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		currentContext = this;
		super.onCreate(savedInstanceState);
		
		Log.d("onCreate","onCreate");
		
		// jni로 사용하기 위함
		actInstance = this;
		
		/*try {
			kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
			kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();	    	
		} catch (KakaoParameterException e) {
			alert(e.getMessage());
		}
		
		try {		
			kakaoTalkLinkMessageBuilder.addText(linkTxt)
										.addImage(imageSrc, 100, 100)
										//.addAppButton("앱으로 이동", new AppActionBuilder().setUrl(appButton).build())
										//.addWebButton("웹으로 이동",webButton)
										.build();
		    
		    kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
		    kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
		} catch (KakaoParameterException e) { 	
			alert(e.getMessage());
		}*/
	}
	
	// jni로 사용하기 위함
	public static Object getJavaActivity()
	{
		return actInstance;
	}
	
	private void alert(String message) {
		
		Log.d("alert","alert");
		
		   new AlertDialog.Builder(this)
		   .setIcon(android.R.drawable.ic_dialog_alert)
		   .setTitle(R.string.app_name)
		   .setMessage(message)
		   .setPositiveButton(android.R.string.ok, null)
		   .create().show();
	} 
	
	public static void asdf() {

		Log.d("asdf","asdf");
	}
	
	public void kakaoLink() {
		
		try {
			kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
			kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();	    	
		} catch (KakaoParameterException e) {
			alert(e.getMessage());
		}
		
		try {		
			kakaoTalkLinkMessageBuilder.addText(linkTxt)
										.addImage(imageSrc, 100, 100)
										//.addAppButton("앱으로 이동", new AppActionBuilder().setUrl(appButton).build())
										//.addWebButton("웹으로 이동",webButton)
										.build();
		    
		    kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
		    kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
		} catch (KakaoParameterException e) {
			alert(e.getMessage());
		}
	}
}
