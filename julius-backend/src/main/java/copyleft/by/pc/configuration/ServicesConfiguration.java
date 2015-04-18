package copyleft.by.pc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import copyleft.by.pc.common.dao.GenericDao;

@Configuration
public class ServicesConfiguration {

	
	@Bean
	public GenericDao genericDao(){
		return new GenericDao();
	}
	
	/*
	@Bean
	public SyndFeedService syndFeedService(){
		return new SyndFeedServiceImpl();
	}
	
	@Bean
	public PodcastAndEpisodeAttributesService podcastAndEpisodeAttributesService(){
		return new PodcastAndEpisodeAttributesServiceImpl();
	}
	
	@Bean
	public EmailNotificationService emailNotificationService1(){
		return new EmailNotificationServiceImpl();
	}
	
	@Bean
	public SocialMediaService socialMediaService(){
		return new SocialMediaServiceImpl();
	}
	
	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(){
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(100);
		
		return poolingHttpClientConnectionManager;
	}
	
	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException{
	    VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
	    Properties props = new Properties();
	    props.put("resource.loader", "class");
	    props.put("class.resource.loader.class", 
	              "org.apache.velocity.runtime.resource.loader." + 
	              "ClasspathResourceLoader");
	    factory.setVelocityProperties(props);
	    
	    return factory.createVelocityEngine();
	}
	*/
}