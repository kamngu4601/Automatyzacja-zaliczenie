package selenium.test.automation.utils.video;


import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import selenium.test.automation.utils.BaseConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;


public class CustomRecorder extends ScreenRecorder {

    private static ScreenRecorder screenRecorder;

    //konfiguracja
    private static final GraphicsConfiguration cfg = GraphicsEnvironment.getLocalGraphicsEnvironment().
            getDefaultScreenDevice()
            .getDefaultConfiguration();
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int width = screenSize.width;
    private static final int height = screenSize.height;
    private static final Rectangle captureArea = new Rectangle(0,0, width, height);
    private static final Format fileFormat = new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI);
    private static final Format screenFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey,
            ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f,
            KeyFrameIntervalKey, 15*16);
    private static final Format mouseFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30));
    private static final Format audioFormat = null;
    private static final File movieFolder = new File(BaseConfiguration.RECORDING_PATH);


    public CustomRecorder()
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
    }


    public static void startRecord() throws Exception {
        screenRecorder = new CustomRecorder();
        screenRecorder.start();
    }

    public static void stopRecord() throws Exception {
        screenRecorder.stop();
    }

    public static void deleteRecord() {
        if ( movieFolder!= null) {
            String[] recordedFiles = movieFolder.list();
            new File(movieFolder, recordedFiles[recordedFiles.length-1]).delete();
            System.out.println("Test succeeded. Deleted this " + movieFolder + "//" + recordedFiles[recordedFiles.length-1]);
        }
    }

    public static void deleteIfSuccess(boolean condition) throws Exception {
        stopRecord();
        if (condition)
            deleteRecord();
        else
            System.out.println("Test failed. Recording is saved for further investigation.");
    }


}
