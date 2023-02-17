package ua.hillel.todolistdemo.db;

import org.apache.ibatis.jdbc.ScriptRunner;
import ua.hillel.todolistdemo.exception.DbInitException;

import java.io.*;
import java.sql.Connection;

public class DbSchemaInitializer {
    public static void init(Connection connection, String filePath) throws DbInitException {
        try (Reader reader = new BufferedReader(new FileReader(filePath))) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);

            scriptRunner.runScript(reader);
        } catch (IOException e) {
            throw new DbInitException("Unable to init database: " + e.getMessage(), e);
        }
    }
}
