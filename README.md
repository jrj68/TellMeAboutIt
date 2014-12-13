TellMeAboutIt
=============

Android Translating Messaging Application 

Read Me 

This application is geared to creating a medium-fidelity prototype for a revolutionary messaging translation application. It was created to show the ease of creating usernames and password using a backend named Parse. It also has the capability to translate messages into seven different languages, English, German, French, Italian, Japanese, Chinese, and Korean. The application also transforms the text into speech via a speak button.

This system requires the use of several services in order to work properly. The first of these services is Parse, the backend database that stores the names of the users of the application. You must  go to parse.com/apps/quickstart to set up the SDK. After that you will receive an app-ID and client-key. These need to be coded in the MyApplication class of your application. Next you will need to sign-up for a free translating service through Microsoft Azure Marketplace. Go to https://datamarket.azure.com/dataset/1899a118-d202-492c-aa16-ba21c33c06cb and sign up for a free 2,000,000 characters per month. Then be sure to input the right the client-ID and client secret into the MessageActivity class of your project. Once you have altered your source to accommodate the services, make sure to add the appropriate permissions to the android manifest. 

Now that you have signed up for the appropriate services, its time to install and build paths to your API and lib. Make sure all of the library have been copied over correctly and make sure that you have built a path for them. You can get the following library from these websites. 
Microsoft-translator-java-api.jar
https://code.google.com/p/microsoft-translator-java-api/downloads/detail?name=microsoft-translator-java-api-0.6.2-jar-with-dependencies.jar
Parse-1.8.0.jar
https://parse.com/apps/quickstart#parse_data/mobile/android/native/existing

Now you can run the application on you Android device or emulator 

