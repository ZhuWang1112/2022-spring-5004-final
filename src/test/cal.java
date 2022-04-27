package test;

public class cal {
    public static void main(String[] args) {
        int power = 4;
        int angle = 30;

        double x = Math.cos(angle / 180.0 * Math.PI);
        double xSpeed =  (Math.cos(angle) * power);
        double ySpeed = (Math.sin(angle / 180.0 * Math.PI) * power);
    }
}
