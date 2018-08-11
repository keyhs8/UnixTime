import com.ripplex.unixtime.UnixTime;

public class Main {

    public static void main(String[] args) {
        UnixTime ut = new UnixTime();
        int timeNow = (int)(System.currentTimeMillis() / 1000L);
<<<<<<< HEAD
        ut.setUnixTime(-2147483647);
=======
        ut.setUnixTime(-2082844800);
>>>>>>> develop
        // ut.setUnixTime(0);
    }
}
