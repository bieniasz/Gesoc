package validadorTransparencia;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.List;

public class SchedulerJob implements Job {

    private ValidadorDeTransparencia validadorDeTransparencia;

    public void setValidadorDeTransparencia(ValidadorDeTransparencia validador) {
        this.validadorDeTransparencia = validador;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        validadorDeTransparencia.ValidarEgresos();
    }
}
