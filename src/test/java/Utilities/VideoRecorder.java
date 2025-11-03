package Utilities;

import org.monte.screenrecorder.ScreenRecorder;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import java.io.File;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

import org.monte.media.math.Rational;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorder extends ScreenRecorder {
  public VideoRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, 
                       Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder) throws Exception {
    super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
  }

  // Método para iniciar la grabación desde la prueba
  public static ScreenRecorder startRecording(String testName) throws Exception {
    File file = new File("./videos");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);
    GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                  .getDefaultScreenDevice()
                                  .getDefaultConfiguration();

    ScreenRecorder screenRecorder = new VideoRecorder(gc, captureSize, 
      new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
      new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                 CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                 QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
      new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
      null, file);

    screenRecorder.start();
    return screenRecorder;
  }

  // Método para detener la grabación desde la prueba
  public static void stopRecording(ScreenRecorder screenRecorder) throws Exception {
    screenRecorder.stop();
  }
}
