package extension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestExtension implements BeforeEachCallback , AfterEachCallback {
    private static final Logger logger = LogManager.getLogger(TestStatusExtension.class);

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        logger.info("Before each method a girdi ");

    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        logger.info("After each method a girdi ");

    }
}
