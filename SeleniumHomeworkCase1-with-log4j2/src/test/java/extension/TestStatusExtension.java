package extension;

import com.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;



public class TestStatusExtension extends BaseTest implements TestWatcher  {


    private static final Logger logger = LogManager.getLogger(TestStatusExtension.class);

    public void testDisabled(ExtensionContext context, Optional<String> reason) { logger.info(" Test Disabled edildi "); }

    public void testSuccessful(ExtensionContext context) {
        logger.info(" Test Success oldu ");
    }

    public void testAborted(ExtensionContext context, Throwable cause) { logger.info(" Test abort edildi "); }

    public void testFailed(ExtensionContext context, Throwable cause) { logger.info(" Test Fail oldu ");






    }



}
