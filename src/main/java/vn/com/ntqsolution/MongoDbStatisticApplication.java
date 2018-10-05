package vn.com.ntqsolution;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import vn.com.ntqsolution.config.MongoConfig;
import vn.com.ntqsolution.dao.StfDAO;
import vn.com.ntqsolution.model.TimeRangeOption;
import vn.com.ntqsolution.processor.MongoProcessor;
import vn.com.ntqsolution.util.FileIOHelper;

@Slf4j
@SpringBootConfiguration
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableScheduling
public class MongoDbStatisticApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(MongoDbStatisticApplication.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        FileIOHelper.initFolder();

        MongoProcessor mongoProcessor = new MongoProcessor();
        mongoProcessor.statisticApiRequestToStf(new TimeRangeOption("22-01-2018 10:20:56","4-10-2018 10:20:56"));
    }
    // DATE FORMAT: "dd-M-yyyy hh:mm:ss" Example: "22-01-2015 10:20:56";
}
