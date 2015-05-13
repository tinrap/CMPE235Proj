package sensorproj.sjsu.com.cmpe235proj;

/**
 * Created by Parnit on 5/12/2015.
 */
public class SensorData {

    private String data,status,power;
    private long time;
    public SensorData(){

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
