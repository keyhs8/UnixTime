import com.ripplex.unixtime.UnixTime;

public class Main {

    public static void main(String[] args) {
        UnixTime ut = new UnixTime();
        int timeNow = (int)(System.currentTimeMillis() / 1000L);
        ut.setUnixTime(timeNow);

        System.out.println(ut.getUnixTime());
    }
}