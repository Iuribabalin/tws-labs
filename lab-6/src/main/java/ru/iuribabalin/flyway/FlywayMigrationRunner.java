package ru.iuribabalin.flyway;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import ru.iuribabalin.config.BaseConfigReader;

@Slf4j
public class FlywayMigrationRunner {
    private final BaseConfigReader configReader;

    public FlywayMigrationRunner() {
        this.configReader = new BaseConfigReader();
    }

    public void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(configReader.getUrl(), configReader.getUser(), configReader.getPassword())
                .schemas(configReader.getSchemas().split(","))
                .locations(configReader.getLocations().split(","))
                .load();
        log.info("\n\tFlyway connect to:\n\turl: {}\n\tuser: {}\n\tschemas: {}\n\tload migrations: {}",
                configReader.getUrl(), configReader.getUser(), configReader.getPassword(),
                configReader.getLocations().split(",")
        );
        flyway.migrate();
        log.info("\n\tFlyway migration completed.");
    }
}
