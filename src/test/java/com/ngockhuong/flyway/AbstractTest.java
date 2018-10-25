package com.ngockhuong.flyway;

import com.ngockhuong.flyway.annotation.AnnotationUtils;
import com.ngockhuong.flyway.annotation.Migration;
import com.ngockhuong.flyway.util.FlywayUtils;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;
import java.util.Properties;

public abstract class AbstractTest {
    private static Flyway flyway;
    private static FluentConfiguration fluentConfiguration;

    @BeforeAll
    public static void init() throws IOException {
        fluentConfiguration = initFlyway();
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        buildMigration(testInfo.getDisplayName().replace("()", ""));
    }

    @AfterEach
    void tearDown() {
        cleanDB();
    }

    private void cleanDB() {
        flyway.clean();
    }

    private void loadDataAndMigrate(String path) {
        fluentConfiguration.locations(path);
        flyway = fluentConfiguration.load();
        flyway.migrate();
    }

    private void buildMigration(String testMethodName) {
        // build annotation
        Migration m = AnnotationUtils.findAnnotation(Migration.class, testMethodName, this.getClass());

        if (m != null) {
            loadDataAndMigrate(FlywayUtils.getLocation(m.value()));
        }
    }

    private static FluentConfiguration initFlyway() throws IOException {
        Properties props = FlywayUtils.getFlywayConfiguration("config/test/flyway.properties");
        return Flyway.configure()
                .dataSource(props.getProperty("flyway.url"), props.getProperty("flyway.user"), props.getProperty("flyway.password"))
                .schemas(props.getProperty("flyway.schemas"));
    }
}
