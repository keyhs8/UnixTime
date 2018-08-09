import com.ripplex.unixtime.UnixTime;

public class Main {

    public static void main(String[] args) {
        UnixTime ut = new UnixTime();
        int timeNow = (int)(System.currentTimeMillis() / 1000L);
        ut.setUnixTime(94608000);
        // ut.setUnixTime(0);
    }
}
