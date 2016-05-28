package de.saring.exerciseviewer.data;


/**
 * This class contains all speed data of a lap of an exercise.
 *
 * @author Stefan Saring
 * @version 1.0
 */
public final class LapSpeed {
    /**
     * Speed at end of lap (km/h).
     */
    private float speedEnd;
    /**
     * Average speed of lap (km/h).
     */
    private float speedAVG;
    /**
     * Distance of lap (in meters) from the beginning of the exercise, not from the beginning of the lap!
     */
    private int distance;
    /**
     * Cadence at the end of the lap (rpm).
     */
    private short cadence;

    public float getSpeedEnd() {
        return speedEnd;
    }

    public void setSpeedEnd(float speedEnd) {
        this.speedEnd = speedEnd;
    }

    public float getSpeedAVG() {
        return speedAVG;
    }

    public void setSpeedAVG(float speedAVG) {
        this.speedAVG = speedAVG;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public short getCadence() {
        return cadence;
    }

    public void setCadence(short cadence) {
        this.cadence = cadence;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append(LapSpeed.class.getName()).append(":\n");
        sBuilder.append(" [speedEnd=").append(this.speedEnd).append("\n");
        sBuilder.append("  speedAVG=").append(this.speedAVG).append("\n");
        sBuilder.append("  distance=").append(this.distance).append("\n");
        sBuilder.append("  cadence=").append(this.cadence).append("]\n");

        return sBuilder.toString();
    }
}
