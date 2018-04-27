# onfido-sdk-ionic-sample-app
Sample app showcasing the integration of the Onfido SDK on an Ionic app

## This project consists of 2 modules:
- `onfidoSampleApp` contains the sample app code
- `cordova-plugin-template` contains the Cordova plugin that wraps the Onfido Android SDK. Based on [this template](https://github.com/ionic-team/cordova-plugin-template)

## How to run:
Install the project, link the plugin to the sample app and add android platform

```
cd onfidoSampleApp
npm install
cordova plugin add --link ../cordova-plugin-template
cordova platform add android
```


## Edit for adding the SDK dependency (According to the [Android SDK Documentation](https://github.com/onfido/onfido-android-sdk#2-adding-the-sdk-dependency)):
```
platforms/android/app/build.gradle
```

```
repositories {
	maven {
		url  "https://dl.bintray.com/onfido/maven"
	}
}

dependencies {
	compile 'com.onfido.sdk.capture:onfido-capture-sdk:+'
}
```

## Build and start the app
```
ionic cordova build android
cordova emulate android
```


## TODO:
- Complete the e2e integration between the Ionic application and the Cordova plugin, with callbacks to the application.