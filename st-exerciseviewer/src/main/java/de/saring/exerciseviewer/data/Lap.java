package de.saring.exerciseviewer.data;


/**
 * This class contains all data of a lap of an exercise.
 *
 * @author Stefan Saring
 * @version 1.0
 */
public final class Lap {
    /**
     * Lap split time (in 1/10 seconds).
     */
    private int timeSplit;
    /**
     * Heartrate at lap split time.
     */
    private short heartRateSplit;
    /**
     * Average heartrate at lap.
     */
    private short heartRateAVG;
    /**
     * Maximum heartrate at lap.
     */
    private short heartRateMax;
    /**
     * Lap speed data (if recorded).
     */
    private LapSpeed speed;
    /**
     * Lap altitude data (if recorded).
     */
    private LapAltitude altitude;
    /**
     * Lap temperature.
     */
    private LapTemperature temperature;
    /**
     * The geographical location at lap split time (optional).
     */
    private Position positionSplit;


    public int getTimeSplit() {
        return timeSplit;
    }

    public void setTimeSplit(int timeSplit) {
        this.timeSplit = timeSplit;
    }

    public short getHeartRateSplit() {
        return heartRateSplit;
    }

    public void setHeartRateSplit(short heartRateSplit) {
        this.heartRateSplit = heartRateSplit;
    }

    public short getHeartRateAVG() {
        return heartRateAVG;
    }

    public void setHeartRateAVG(short heartRateAVG) {
        this.heartRateAVG = heartRateAVG;
    }

    public short getHeartRateMax() {
        return heartRateMax;
    }

    public void setHeartRateMax(short heartRateMax) {
        this.heartRateMax = heartRateMax;
    }

    public LapSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(LapSpeed speed) {
        this.speed = speed;
    }

    public LapAltitude getAltitude() {
        return altitude;
    }

    public void setAltitude(LapAltitude altitude) {
        this.altitude = altitude;
    }

    public LapTemperature getTemperature() {
        return temperature;
    }

    public void setTemperature(LapTemperature temperature) {
        this.temperature = temperature;
    }

    public Position getPositionSplit() {
        return positionSplit;
    }

    public void setPositionSplit(Position positionSplit) {
        this.positionSplit = positionSplit;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append(Lap.class.getName()).append(":\n");
        sBuilder.append(" [timeSplit=").append(this.timeSplit).append("\n");
        sBuilder.append("  heartRateSplit=").append(this.heartRateSplit).append("\n");
        sBuilder.append("  heartRateAVG=").append(this.heartRateAVG).append("\n");
        sBuilder.append("  heartRateMax=").append(this.heartRateMax).append("\n");
        sBuilder.append("  speed=").append(this.speed).append("\n");
        sBuilder.append("  altitude=").append(this.altitude).append("\n");
        sBuilder.append("  temperature=").append(this.temperature).append("\n");
        sBuilder.append("  positionSplit=").append(this.positionSplit).append("]\n");

        return sBuilder.toString();
    }
}
