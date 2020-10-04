package validadorTransparencia;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class TimerValidadorTransparencia {
    private ValidadorDeTransparencia validadorDeTransparencia;
    private JobDetail job;
    private Scheduler sched;

    public TimerValidadorTransparencia() throws SchedulerException {
        this.job = newJob(SchedulerJob.class)
                .withIdentity("jobValidador", "groupValidador")
                .build();

        SchedulerFactory sf = new StdSchedulerFactory();
        this.sched = sf.getScheduler();
    }

    public void executeJob(String scheduleConfig) throws SchedulerException, InterruptedException {
        CronTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule(scheduleConfig))
                .build();

        sched.scheduleJob(this.job, trigger);
        sched.start();
    }

    public void closeScheduler() throws SchedulerException {
        this.sched.shutdown(true);
    }

    public void SetDummyJobForTest(){
        this.job = newJob(SchedulerJobDummy.class)
                .withIdentity("jobDummy", "dummy")
                .build();
    }
}
