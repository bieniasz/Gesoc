package domain.controllers;

import domain.entities.validadorTransparencia.TimerValidadorTransparencia;
import org.quartz.SchedulerException;

public class SchedulerControlle {
    private TimerValidadorTransparencia timer;

    public SchedulerControlle() throws SchedulerException, InterruptedException {
        this.timer = new TimerValidadorTransparencia();
        //-------------------------------- ss mm hh * * *
        this.timer.executeJob("0 15 10 * * ?");
    }


}
