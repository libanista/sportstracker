package de.saring.sportstracker.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.saring.sportstracker.storage.IStorage;
import de.saring.sportstracker.storage.XMLStorage;
import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import de.saring.exerciseviewer.gui.EVContext;
import de.saring.sportstracker.core.STException;
import de.saring.sportstracker.core.STOptions;
import de.saring.util.gui.javafx.WindowBoundsPersistence;
import de.saring.util.gui.mac.PlatformUtils;
import de.saring.util.unitcalc.FormatUtils;

/**
 * This is the main class of SportsTracker which starts the entire application.
 *
 * @author Stefan Saring
 */
public class STApplication extends Application {

    private static final Logger LOGGER = Logger.getLogger(STApplication.class.getName());

    private STDocument document;
    private STContext context;
    private STController controller;

    private Stage primaryStage;

    @Override
    public void init() throws Exception {

        // setup EasyDI for dependency injection
        final EasyDI easyDI = new EasyDI();
        easyDI.bindInstance(STApplication.class, this);
        easyDI.bindInterface(IStorage.class, XMLStorage.class);
        easyDI.bindInterface(STContext.class, STContextImpl.class);
        easyDI.bindInterface(EVContext.class, STContextImpl.class);
        easyDI.bindInterface(STDocument.class, STDocumentImpl.class);
        easyDI.bindInterface(STController.class, STControllerImpl.class);

        // initialize the document
        document = easyDI.getInstance(STDocument.class);
        document.evaluateCommandLineParameters(getParameters().getRaw());
        document.loadOptions();

        // initialize the context (set format utils for current configuration)
        context = easyDI.getInstance(STContext.class);
        final STOptions options = document.getOptions();
        context.setFormatUtils(new FormatUtils(options.getUnitSystem(), options.getSpeedView()));

        controller = easyDI.getInstance(STController.class);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        WindowBoundsPersistence.addWindowBoundsPersistence(primaryStage, "SportsTracker");

        // initialize and start the main application window
        controller.initApplicationWindow();

        primaryStage.setOnShown(this::onShown);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        document.storeOptions();
        LOGGER.info("Exiting application...");
        super.stop();
    }

    /**
     * Returns the primary Stage of the JavaFX application.
     *
     * @return Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * This method is called after the application window has been shown, final UI setup can be done here.
     *
     * @param event window event
     */
    private void onShown(final WindowEvent event) {

        // create application directory
        try {
            document.createApplicationDirectory();
        } catch (STException se) {
            LOGGER.log(Level.SEVERE, "Failed to create the application directory!", se);
            context.showMessageDialog(primaryStage, Alert.AlertType.ERROR, "common.error", "st.main.error.create_dir");
        }

        // load application data (executed in background)
        controller.loadApplicationData();
    }

    /**
     * Starts the SportsTracker application.
     *
     * @param args command line parameters
     */
    public static void main(final String args[]) {

        // set format for java.util.logging, a log statement must be printed to a single line
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %2$s %5$s %6$s%n");

        // TODO remove when using a JavaFX map viewer
        if (!PlatformUtils.isMacOSX()) {
            // initialize system look&feel for the Swing components (JXMapViewer viewer in ExerciseViewer)
            // => needs to be done at startup, otherwise deadlock at ExerciseViewer start in Linux
            // => not needed on Mac OS X, system look&feel is default there (enabling causes problems)
            final String lookAndFeelClassName = javax.swing.UIManager.getSystemLookAndFeelClassName();
            try {
                javax.swing.UIManager.setLookAndFeel(lookAndFeelClassName);
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Failed to set look&feel to " + lookAndFeelClassName + "!", e);
            }
        }

        launch(args);
    }
}
