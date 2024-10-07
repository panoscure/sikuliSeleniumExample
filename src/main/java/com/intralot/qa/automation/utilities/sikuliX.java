package com.intralot.qa.automation.utilities;

import com.intralot.qa.automation.core.utilities.Log;
import org.sikuli.script.*;

import java.io.File;


public class sikuliX {

    public String takeScreenshotReturnText(String imagePath) throws InterruptedException, FindFailed {

        // Create a SikuliX Screen object
        Screen screen = new Screen();
        // Capture the entire screen
        ScreenImage screenImage = screen.capture();

        // Save the captured image to a file
        //String imagePath = "C:\\Users\\maurogiannopoulos\\IdeaProjects\\operations-tlc\\src\\test\\java\\com\\intralot\\qa\\automation\\test\\suites\\desktopweb\\winningColumnsEntry\\sikuliImages";
        screenImage.save(imagePath);

        // Get the list of files in the desired directory
        File directory = new File(imagePath);
        File[] files = directory.listFiles();

        // Find the most recent file (the one just saved by SikuliX)
        long lastModified = Long.MIN_VALUE;
        File chosenFile = null;
        for (File file : files) {
            if (file.lastModified() > lastModified) {
                chosenFile = file;
                lastModified = file.lastModified();
            }
        }

        // Rename the chosen file with the desired filename
        String desiredFileName = "sikuliximage.png";
        File desiredFile = new File(directory, desiredFileName);
        if (chosenFile != null && chosenFile.renameTo(desiredFile)) {
            System.out.println("Image saved successfully with desired name.");
        } else {
            System.out.println("Failed to save image with desired name.");
        }

        Generic.delay(8);

        Pattern imagePattern = new Pattern(imagePath+"\\sikuliximage.png");

        // Find the text within the image
        Match match = screen.find(imagePattern);

        // Extract the text from the matched region
        String extractedText = OCR.readText(match);

        if (desiredFile != null) {
            desiredFile.delete();
            // Print the extracted text
            Log.info("Original image file deleted.");
        }


        return extractedText;
    }

}
