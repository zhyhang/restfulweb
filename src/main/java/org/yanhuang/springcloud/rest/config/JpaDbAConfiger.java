/**
 * 
 */
package org.yanhuang.springcloud.rest.config;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Jpa datasource and transaction config
 * @author zhyhang
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryA",
        transactionManagerRef="transactionManagerA",
        basePackages= { "org.yanhuang.springcloud.rest.jpa.a.repo" }) //package of repository
public class JpaDbAConfiger {
	
    @Autowired
    @Qualifier("datasourceA")
    private DataSource datasource;
    
    @Bean(name = "entityManagerA")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryA")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(datasource)
                .packages("org.yanhuang.springcloud.rest.jpa.a.domain") // package of domain entity
                .persistenceUnit("aPersistenceUnit")
                .build();
    }

    @Bean(name = "transactionManagerA")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
