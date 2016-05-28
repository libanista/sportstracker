package de.saring.exerciseviewer.parser;

/**
 * This class contains informations of an ExerciseParser implementation.
 *
 * @author Stefan Saring
 * @version 1.0
 */
public class ExerciseParserInfo {

    /**
     * The name of the parser.
     */
    private final String name;

    /**
     * List of exercise file suffixes which can be read by this parser.
     */
    private final String[] suffixes;

    /**
     * Creates a new ExerciseParserInfo instance.
     *
     * @param name list of exercise file suffixes which can be read by this parser
     * @param suffixes list of exercise file suffixes which can be read by this parser
     */
    public ExerciseParserInfo(String name, String[] suffixes) {
        this.name = name;
        this.suffixes = suffixes;
    }

    public String getName() {
        return name;
    }

    public String[] getSuffixes() {
        return suffixes;
    }
}

