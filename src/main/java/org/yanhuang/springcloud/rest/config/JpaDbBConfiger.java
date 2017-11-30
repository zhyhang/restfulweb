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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
        entityManagerFactoryRef="entityManagerFactoryB",
        transactionManagerRef="transactionManagerB",
        basePackages= { "org.yanhuang.springcloud.rest.jpa.b.repo" }) //package of repository
@EnableJpaAuditing
public class JpaDbBConfiger {
	
    @Autowired
    @Qualifier("datasourceB")
    private DataSource datasource;
    
    @Bean(name = "entityManagerB")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryB")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(datasource)
                .packages("org.yanhuang.springcloud.rest.jpa.b.domain") // package of domain entity
                .persistenceUnit("bPersistenceUnit")
                .build();
    }

    @Bean(name = "transactionManagerB")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
