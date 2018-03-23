/**
 */
package com.example;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.OnfidoFactory;
import com.onfido.android.sdk.capture.ui.camera.face.FaceCaptureStep;
import com.onfido.android.sdk.capture.ui.camera.face.FaceCaptureVariant;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.android.sdk.capture.utils.OnfidoApiUtil;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.data.Address;
import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.Check;
import com.onfido.api.client.data.ErrorData;
import com.onfido.api.client.data.Report;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.util.Log;
import android.app.Activity;
import java.util.Date;

public class MyCordovaPlugin extends CordovaPlugin {
  private static final String TAG = "MyCordovaPlugin";
  private Onfido client;
  private OnfidoAPI onfidoAPI;

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing MyCordovaPlugin");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals("startSdk")) {
      Activity context=this.cordova.getActivity();
      client = OnfidoFactory.create(context).getClient();

      final FlowStep[] defaultStepsWithWelcomeScreen = new FlowStep[]{
        FlowStep.WELCOME,
        FlowStep.CAPTURE_DOCUMENT,
        FlowStep.CAPTURE_FACE,
        FlowStep.FINAL
    };

OnfidoConfig onfidoConfig = getTestOnfidoConfigBuilder()
        .withCustomFlow(defaultStepsWithWelcomeScreen)
        .build();

onfidoAPI = OnfidoApiUtil.createOnfidoApiClient(context, onfidoConfig);

client.startActivityForResult(context, 1, onfidoConfig);

      // An example of returning data back to the web layer
      final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
      callbackContext.sendPluginResult(result);
    }
    return true;
  }

  private Applicant getTestApplicant() {
    return Applicant.builder()
            .withFirstName("Ionic")
            .withLastName("User")
            .withToken("YOUR_MOBILE_TOKEN")
            .build();
  }

  private OnfidoConfig.Builder getTestOnfidoConfigBuilder() {
      return OnfidoConfig.builder()
              .withApplicant(getTestApplicant());
  }
}
