package pl.touk.jenkins.spark;

public class SparkApiFactory {

    private static SparkApi instance;

    public static SparkApi getInstance(String accessToken, String deviceId) {
        if (instance == null) {
            instance = new SparkApi(accessToken, deviceId);
        }
        return instance;
    }
}
