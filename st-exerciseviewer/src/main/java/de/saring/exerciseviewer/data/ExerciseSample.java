package de.saring.exerciseviewer.data;


/**
 * This class contains all data recorded each interval. The altitude, speed,
 * cadence and power is optional and may be not recorded.
 *
 * @author Stefan Saring
 * @version 1.0
 */
public final class ExerciseSample {

    /**
     * Timestamp since exercise start of this sample (in 1/1000 sec).
     */
    private long timestamp;
    /**
     * Heartrate at record moment.
     */
    private short heartRate;
    /**
     * Altitude at record moment.
     */
    private short altitude;
    /**
     * Speed at record moment (in km/h).
     */
    private float speed;
    /**
     * Cadence at record moment (in rpm).
     */
    private short cadence;
    /**
     * Distance at record moment (in meters).
     */
    private int distance;
    /**
     * Temperature at record moment (in degrees celcius, optional). (Relevant for HAC4.)
     */
    private short temperature;
    /**
     * The geographical location of this sample in the exercise track (optional).
     */
    private Position position;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public short getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(short heartRate) {
        this.heartRate = heartRate;
    }

    public short getAltitude() {
        return altitude;
    }

    public void setAltitude(short altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public short getCadence() {
        return cadence;
    }

    public void setCadence(short cadence) {
        this.cadence = cadence;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public short getTemperature() {
        return temperature;
    }

    public void setTemperature(short temperature) {
        this.temperature = temperature;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append(ExerciseSample.class.getName()).append(":\n");
        sBuilder.append(" [timestamp=").append(this.timestamp).append("\n");
        sBuilder.append("  heartRate=").append(this.heartRate).append("\n");
        sBuilder.append("  altitude=").append(this.altitude).append("\n");
        sBuilder.append("  speed=").append(this.speed).append("\n");
        sBuilder.append("  cadence=").append(this.cadence).append("\n");
        sBuilder.append("  distance=").append(this.distance).append("\n");
        sBuilder.append("  temperature=").append(this.temperature).append("\n");
        sBuilder.append("  position=").append(this.position).append("]\n");

        return sBuilder.toString();
    }
}
