package copyleft.by.pc.common.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import copyleft.by.pc.jobs.crawlergatry.CrawlerGatryJobConfiguration;

@Configuration
@EnableBatchProcessing(modular=true)
public class AppConfig {
    
    @Bean
    public ApplicationContextFactory crawlerGatryJob(){
    	return new GenericApplicationContextFactory(CrawlerGatryJobConfiguration.class);
    }
    
    /*
    @Bean
    public ApplicationContextFactory newEpisodesNotificationJobs(){
    	return new GenericApplicationContextFactory(NotifySubscribersJobConfiguration.class);
    }    
    */
}