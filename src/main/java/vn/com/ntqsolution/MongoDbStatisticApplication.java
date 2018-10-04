package vn.com.ntqsolution;

import com.mongodb.DB;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import vn.com.ntqsolution.config.MongoConfig;
import vn.com.ntqsolution.dao.StfDAO;
import vn.com.ntqsolution.model.TimeRangeOption;

@Slf4j
@AllArgsConstructor
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableScheduling
public class MongoDbStatisticApplication implements CommandLineRunner {

    @Autowired
    MongoConfig mongoConfig;
    @Autowired
    StfDAO stfDAO;

    public static void main(String[] args) {
        SpringApplication.run(MongoDbStatisticApplication.class, args);
    }

    // DATE FORMAT: "dd-M-yyyy hh:mm:ss" Example: "22-01-2015 10:20:56";
    @Override
    public void run(String... args) throws Exception {
        MongoDBManager.createConnection(mongoConfig);

        stfDAO.mapreduceWith(new TimeRangeOption("22-01-2018 10:20:56","4-10-2018 10:20:56"));
    }
}
