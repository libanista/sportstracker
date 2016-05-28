package de.saring.exerciseviewer.core;

import de.saring.util.unitcalc.FormatUtils.SpeedView;
import de.saring.util.unitcalc.FormatUtils.UnitSystem;

/**
 * This interface defines all required options by the ExerciseViewer application.
 * The implementation must be done by the application which uses ExerciseViewer.
 *
 * @author Stefan Saring
 * @version 1.0
 */
public interface EVOptions {
    /**
     * Returns the unit system to be used in the GUI.
     *
     * @return the unit system to be used in the GUI
     */
    public UnitSystem getUnitSystem();

    /**
     * The returned flag is true, when the second diagram graph should
     * always be displayed in the ExerciseViewer diagram panel (data must be
     * available).
     *
     * @return the flag for displaying the second diagram
     */
    public boolean isDisplaySecondDiagram();

    /**
     * Returns the speed view system to be used in GUI.
     *
     * @return the speed view system to be used in the GUI
     */
    public SpeedView getSpeedView();
}

