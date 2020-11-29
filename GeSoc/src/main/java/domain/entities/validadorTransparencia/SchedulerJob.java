package domain.entities.validadorTransparencia;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SchedulerJob implements Job {

    private ValidadorDeTransparencia validadorDeTransparencia = new ValidadorDeTransparencia();

    public void setValidadorDeTransparencia(ValidadorDeTransparencia validador) {
        this.validadorDeTransparencia = validador;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        validadorDeTransparencia.ValidarEgresos();
    }
}
