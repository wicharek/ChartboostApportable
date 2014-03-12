ChartboostApportable
====================

Basic Chartboost SDK support for Apportable applications. Currently doesn't support interstitial places and more apps.

Usage
-------------

*NOTE:* See ChartboostApportableDemo folder for an example application.

Steps to implement, asuming you already have Chartboost in iOS version of your project:

1. Run apportable load on your project if you haven't yet.
2. Move ChartboostApportable folder to your project folder.
3. Edit _*.approj.configuration.json_. Add the following code to add_params section (paths are relative to root folder, make sure they are correct):

        "sources": [
          "./ChartboostApportable/Chartboost_Android.m"
        ],
        "java_sources": [
          "./ChartboostApportable/Java/com/f17y/ChartboostWrapper.java",
          "./ChartboostApportable/Java/com/f17y/ChartboostWrapperActivity.java"
      	],
        "java_sourcepaths": [
          "./ChartboostApportable/Java"
        ],
        "java_libs": [
      	  "../ChartboostApportable/chartboost.jar"
      	]

That's it. You should be all set. Chartboost API calls should be working. Make sure you have created the separate application in Chartboost console and set correct id and signature in your code.