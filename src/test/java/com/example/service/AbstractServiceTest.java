package com.example.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = {"classpath:db/populate_db.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractServiceTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());
}