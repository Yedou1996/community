  package life.lxl.community.community;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

  @SpringBootApplication
public class CommunityApplication {

    public static void main(String[] args) {
//         Logger log = (Logger) LoggerFactory.getLogger(CommunityApplication.class);
//        try {
            SpringApplication.run(CommunityApplication.class, args);
//        }catch (Exception e){
//        log.info("cn.linkPower.CommunityApplication----->"+String.valueOf(e));
//        }


    }

}
