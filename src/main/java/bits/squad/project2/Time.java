package bits.squad.project2;

public class Time {
    private int minute;
    private int second;

    public Time(int minute, int second) {
        this.minute = minute;
        this.second = second;
    }

    public Time(String currentTime) {
        String[] time = currentTime.split(":");
        minute = Integer.parseInt(time[1]);
        second = Integer.parseInt(time[2]);
    }

    public void oneSecondPassed() {
        second++;
        if (second == 60) {
            minute++;
            second = 0;
        }
    }

    public void minusSecondPassed() {
        second--;
        if (second == -1) {
            minute--;
            second = 59;
        }
    }

    public String getCurrentTime() {
        return (minute > 10 ? minute : ("0" + minute)) + ":" + (second > 10 ? second : ("0" + second));
    }

    public int getSecond() {
        return second;
    }
}