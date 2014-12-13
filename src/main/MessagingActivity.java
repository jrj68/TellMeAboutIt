package com.example.tellmeaboutit;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.EngineInfo;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.messaging.Message;
import com.sinch.android.rtc.messaging.MessageClient;
import com.sinch.android.rtc.messaging.MessageClientListener;
import com.sinch.android.rtc.messaging.MessageDeliveryInfo;
import com.sinch.android.rtc.messaging.MessageFailureInfo;
import com.sinch.android.rtc.messaging.WritableMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MessagingActivity extends Activity implements OnInitListener {

    private String recipientId;
    private EditText messageBodyField;
    private String messageBody;
    
    private ListView messagesList;
    private String currentUserId;

    //text to speech 
    private TextToSpeech tts;
    
    private String Lang = "ENGLISH";
    private String Lang1 = "ENGLISH";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messaging);

        //bindService(new Intent(this, MessageService.class), serviceConnection, BIND_AUTO_CREATE);

        Intent intent = getIntent();
        //Bundle extra = getIntent().getExtras();
        //recipientId = extra.getString("RECIPIENT_ID");
        
        recipientId = intent.getStringExtra("RECIPIENT_ID");
        
        //recipientId = "gM4LI6dw1S";
        currentUserId = ParseUser.getCurrentUser().getObjectId();

        
        messageBodyField = (EditText) findViewById(R.id.messageBodyField);

        ///Language Spinners 
        Spinner spinner = (Spinner) findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ((Spinner) findViewById(R.id.language_spinner)).setOnItemSelectedListener(new OnItemSelectedListener() {
            
        	public void onItemSelected(AdapterView<?> parent, View view, 
                    int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                Lang= (String) parent.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        
      
        Spinner spinner2 = (Spinner) findViewById(R.id.alanguage_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        ((Spinner) findViewById(R.id.alanguage_spinner)).setOnItemSelectedListener(new OnItemSelectedListener() {
            
        	public void onItemSelected(AdapterView<?> parent, View view, 
                    int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                Lang1= (String) parent.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
///Text to SPeech
        tts = new TextToSpeech(this, this);
        ((Button) findViewById(R.id.bSpeak)).setOnClickListener(new OnClickListener() {
             
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	if (Lang.equals("GERMAN")) {
               	 tts.setLanguage(Locale.GERMAN);
               	
       	       }
       	       if (Lang.equals("ENGLISH")) {
       	    	   tts.setLanguage(Locale.ENGLISH);
       	       }
       	       if (Lang.equals("CHINESE")) {
       	    	   tts.setLanguage(Locale.CHINESE);
       	       }
       	       if (Lang.equals("JAPANESE")) {
       	    	  tts.setLanguage(Locale.JAPANESE);
       	       }
       	       if (Lang.equals("FRENCH")) {
       	    	  tts.setLanguage(Locale.FRENCH);
       	       }
       	       if (Lang.equals("KOREAN")) {
       	    	   tts.setLanguage(Locale.KOREAN);
       	       }
       	       if (Lang.equals("ITALIAN")) {
       	    	   tts.setLanguage(Locale.ITALIAN);
       	       }
       	    if (((TextView) findViewById(R.id.tvTranslatedText)).getText().toString().equals("")){
       	    	speakOut(((TextView) findViewById(R.id.txtMessage1)).getText().toString());
        }else{
        	speakOut(((TextView) findViewById(R.id.tvTranslatedText)).getText().toString());
        }
            }
        });
        ///engine information
       /* 
        EngineInfo EI = new TextToSpeech.EngineInfo();
        
        String engineInfo = EI.toString();
        
        Toast.makeText(this, engineInfo, Toast.LENGTH_LONG).show();
        */
        /////////////////////
        ((Button) findViewById(R.id.aSpeak)).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	if (Lang1.equals("GERMAN")) {
               	 tts.setLanguage(Locale.GERMAN);
               	
       	       }
       	       if (Lang1.equals("ENGLISH")) {
       	    	   tts.setLanguage(Locale.ENGLISH);
       	       }
       	       if (Lang1.equals("CHINESE")) {
       	    	   tts.setLanguage(Locale.CHINESE);
       	       }
       	       if (Lang1.equals("JAPANESE")) {
       	    	  tts.setLanguage(Locale.JAPANESE);
       	       }
       	       if (Lang1.equals("FRENCH")) {
       	    	  tts.setLanguage(Locale.FRENCH);
       	       }
       	       if (Lang1.equals("KOREAN")) {
       	    	   tts.setLanguage(Locale.KOREAN);
       	       }
       	       if (Lang1.equals("ITALIAN")) {
       	    	   tts.setLanguage(Locale.ITALIAN);
       	       }
       	       if (((TextView) findViewById(R.id.tTranslatedText)).getText().toString().equals("")){
       	    	speakOut(((TextView) findViewById(R.id.txtMessage)).getText().toString());
        }else{
        	speakOut(((TextView) findViewById(R.id.tTranslatedText)).getText().toString());
        }
                
            }
        });
        
        
///////////////////////translate buttons 
((Button) findViewById(R.id.bTranslate)).setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
  // TODO Auto-generated method stub
   
   
   
  class bgStuff extends AsyncTask<Void, Void, Void>{
       
      String translatedText = "";
      @Override
      protected Void doInBackground(Void... params) {
          // TODO Auto-generated method stub
          try {
              String text = ((TextView) findViewById(R.id.txtMessage1)).getText().toString();
              translatedText = translate(text,Lang);
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
              translatedText = e.toString();
          }
           
          return null;
      }

      @Override
      protected void onPostExecute(Void result) {
          // TODO Auto-generated method stub
          ((TextView) findViewById(R.id.tvTranslatedText)).setText(translatedText);
          super.onPostExecute(result);
      }
       
  }
   
  new bgStuff().execute();
}
});

//////////////other translate button
((Button) findViewById(R.id.aTranslate)).setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
  // TODO Auto-generated method stub
   
   
   
  class agStuff extends AsyncTask<Void, Void, Void>{
       
      String translatedText1 = "";
      @Override
      protected Void doInBackground(Void... params) {
          // TODO Auto-generated method stub
          try {
              String text1 = ((TextView) findViewById(R.id.txtMessage)).getText().toString();
              translatedText1 = translate(text1,Lang1);
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
              translatedText1 = e.toString();
          }
           
          return null;
      }

      @Override
      protected void onPostExecute(Void result) {
          // TODO Auto-generated method stub
          ((TextView) findViewById(R.id.tTranslatedText)).setText(translatedText1);
          super.onPostExecute(result);
      }
       
  }
   
  new agStuff().execute();
}
});
        
        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }


    private void sendMessage() {
        messageBody = messageBodyField.getText().toString();
        if (messageBody == null) {// isEmpty();
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_LONG).show();
            return;
        }
        
        Toast.makeText(this, "Message " +messageBody, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "sent to " +recipientId, Toast.LENGTH_LONG).show();
        
        
       messageBodyField.setText("");
    }


   
    //text to speech 
    
	private void speakOut(String text) {
		
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
	          
	        int result = 0;
	        //result = tts.setLanguage(Locale.GERMAN);
	        if (Lang.equals("GERMAN")) {
	        	result = tts.setLanguage(Locale.GERMAN);
	        	
		       }
		       if (Lang.equals("ENGLISH")) {
		    	   result = tts.setLanguage(Locale.ENGLISH);
		       }
		       if (Lang.equals("CHINESE")) {
		    	   result = tts.setLanguage(Locale.CHINESE);
		       }
		       if (Lang.equals("JAPANESE")) {
		    	   result = tts.setLanguage(Locale.JAPANESE);
		       }
		       if (Lang.equals("FRENCH")) {
		    	   result = tts.setLanguage(Locale.FRENCH);
		       }
		       if (Lang.equals("KOREAN")) {
		    	   result = tts.setLanguage(Locale.KOREAN);
		       }
		       if (Lang.equals("ITALIAN")) {
		    	   result = tts.setLanguage(Locale.ITALIAN);
		       }
		       
		       
	        if (result == TextToSpeech.LANG_MISSING_DATA
	                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
	            Log.e("TTS", "This Language is not supported");
	            Toast.makeText(this, "This Language is not supported", Toast.LENGTH_LONG).show();
	        } else {
	             
	            //speakOut("Ich");
	        }
	 
	    } else {
	        Log.e("TTS", "Initilization Failed!");
	    }
	}
	
	///translator 
	 public String translate(String text, String Lang) throws Exception{
	       
	        
		    // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
		       Translate.setClientId("MY1stApp"); //Change this
		       Translate.setClientSecret("Wk/am4ZEpzgPWSl9vzewRmMnW5W9aqnrETblvH/K2Xw="); //change
		        
		        
		       String translatedText = "";
		       if (Lang.equals("GERMAN")) {
		    	   translatedText = Translate.execute(text,Language.GERMAN);
		       }
		       if (Lang.equals("ENGLISH")) {
		    	   translatedText = Translate.execute(text,Language.ENGLISH);
		       }
		       if (Lang.equals("CHINESE")) {
		    	   translatedText = Translate.execute(text,Language.CHINESE_SIMPLIFIED);
		       }
		       if (Lang.equals("JAPANESE")) {
		    	   translatedText = Translate.execute(text,Language.JAPANESE);
		       }
		       if (Lang.equals("FRENCH")) {
		    	   translatedText = Translate.execute(text,Language.FRENCH);
		       }
		       if (Lang.equals("KOREAN")) {
					translatedText = Translate.execute(text,Language.KOREAN);
		       }
		       if (Lang.equals("ITALIAN")) {
		    	   translatedText = Translate.execute(text,Language.ITALIAN);
		       }
		       
		       //translatedText = Translate.execute(text,Language.GERMAN);
		        
		       return translatedText;
		   }
		 
}
