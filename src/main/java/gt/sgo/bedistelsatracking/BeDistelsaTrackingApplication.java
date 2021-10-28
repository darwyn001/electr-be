package gt.sgo.bedistelsatracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class BeDistelsaTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeDistelsaTrackingApplication.class, args);
    }

}
