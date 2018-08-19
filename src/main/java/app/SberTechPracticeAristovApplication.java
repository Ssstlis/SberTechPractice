package app;

import controllers.GetController;
import controllers.PostController;
import daos.BillDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import services.BillService;
import services.TransactionService;

@SpringBootApplication
@ComponentScan(basePackageClasses = PostController.class)
@ComponentScan(basePackageClasses = GetController.class)
@ComponentScan(basePackageClasses = BillService.class)
@ComponentScan(basePackageClasses = TransactionService.class)
@ComponentScan(basePackageClasses = BillDAO.class)
@EnableMongoRepositories({"daos"})
public class SberTechPracticeAristovApplication {

	public static void main(String[] args) {
		SpringApplication.run(SberTechPracticeAristovApplication.class, args);
	}

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {

        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return new MongoTemplate(mongoDbFactory, converter);
    }
}
