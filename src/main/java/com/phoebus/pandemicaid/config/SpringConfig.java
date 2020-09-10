package com.phoebus.pandemicaid.config;

import java.util.Arrays;
import javax.sql.DataSource;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.phoebus.pandemicaid.entity.ResourceEntity;
import com.phoebus.pandemicaid.repository.ResourceRepository;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build();
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
    dataSource.setUsername("sa");
    dataSource.setPassword("sa");

    return dataSource;
  }

  @Bean
  public ApplicationRunner initializer(ResourceRepository resourceRepository) {
    return args -> resourceRepository
        .saveAll(Arrays.asList(new ResourceEntity(Long.valueOf(1), "Médico", Integer.valueOf(3)),
            new ResourceEntity(Long.valueOf(2), "Enfermeiro", Integer.valueOf(3)),
            new ResourceEntity(Long.valueOf(3), "Respirador", Integer.valueOf(5)),
            new ResourceEntity(Long.valueOf(4), "Tomógrafo", Integer.valueOf(12)),
            new ResourceEntity(Long.valueOf(5), "Ambulância", Integer.valueOf(10))));

  }

}
