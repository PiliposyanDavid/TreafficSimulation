package schedulers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import services.LightsTimerRegenerateService;

@Configuration
@EnableScheduling
public class AppScheduler {

    @Autowired
    private LightsTimerRegenerateService lightsTimerRegenerateService;

    @Scheduled(cron = "45 * * * * *")
    public void scheduleInterestsUpdate() {
        lightsTimerRegenerateService.regenerateLightTime();
    }
}
