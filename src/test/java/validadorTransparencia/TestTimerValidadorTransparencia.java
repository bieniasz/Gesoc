package validadorTransparencia;

import org.junit.Test;
import org.quartz.SchedulerException;

public class TestTimerValidadorTransparencia {

    @Test
    public void prueba1() throws InterruptedException, SchedulerException {
        TimerValidadorTransparencia timer = new TimerValidadorTransparencia();

        //ss mm hh * * *
        //http://www.quartz-scheduler.org/api/2.2.3/index.html
        timer.executeJob("0 15 10 * * ?");

        Thread.sleep(5000);
        timer.closeScheduler();
    }
}
