package utils;

import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;

import static appium.steps.AppiumSetup.driver;

public class VideoManager {

    public static void startRecording(){
        ((CanRecordScreen) driver).startRecordingScreen();
    }

    public static void stopRecording(String scenarioName) {
        String media = ((CanRecordScreen) driver).stopRecordingScreen();
        String dirPath = "Android"+ File.separator +"Videos";
        File videoDir = new File(dirPath);

        synchronized(videoDir){
            if(!videoDir.exists()) {
                videoDir.mkdirs();
            }
        }

        try (FileOutputStream stream=new FileOutputStream(videoDir + File.separator + scenarioName + ".mp4")) {
            stream.write(Base64.decodeBase64(media));
        } catch (Exception e) {
        }
    }
}

