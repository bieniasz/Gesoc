package validadorTransparencia;

import org.junit.Test;
import org.quartz.SchedulerException;

public class TestTimerValidadorTransparencia {

    @Test
    public void prueba1() throws InterruptedException, SchedulerException {
        TimerValidadorTransparencia timer = new TimerValidadorTransparencia();
        timer.SetDummyJobForTest();

        //http://www.quartz-scheduler.org/api/2.2.3/index.html
        //ss mm hh * * *
        //"0 15 10 * * ?" a X hora cada dia
        //"0/20 * * * * ?" cada X segundos
        timer.executeJob("0/2 * * * * ?");

        Thread.sleep(4000);
        timer.closeScheduler();
    }
}
