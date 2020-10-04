package validadorTransparencia;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SchedulerJobDummy implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.print("SchedulerJobDummy executed. ");
    }
}
