package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.ResultSet;
import java.sql.Statement;

public class V0_0_0_3__TestHotFix extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        try (Statement select = context.getConnection().createStatement()) {
            try (ResultSet rows = select.executeQuery("SELECT * FROM categories")) {
                while (rows.next()) {
                    int id = rows.getInt(0);
                    String name = "Category " + id;

                    try (Statement update = context.getConnection().createStatement()) {
                        update.execute("ALTER TABLE `categories` \n" +
                                "CHANGE COLUMN `name` `name` VARCHAR(255) NOT NULL ;\n");
                    }
                }
            }
        }
    }
}
