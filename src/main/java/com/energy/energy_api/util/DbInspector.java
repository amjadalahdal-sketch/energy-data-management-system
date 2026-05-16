package com.energy.energy_api.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbInspector {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream in = DbInspector.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in == null) {
                System.err.println("application.properties not found on classpath");
                System.exit(2);
            }
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
        }

        String url = props.getProperty("spring.datasource.url");
        String user = props.getProperty("spring.datasource.username");
        String pass = props.getProperty("spring.datasource.password");
        String driver = props.getProperty("spring.datasource.driver-class-name", "oracle.jdbc.OracleDriver");

        System.out.println("Using JDBC URL: " + url);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC driver not found on classpath: " + driver);
            e.printStackTrace();
            // continue; Driver may register via SPI
        }

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            DatabaseMetaData md = conn.getMetaData();
            String schema = conn.getSchema();
            System.out.println("Connected. Current schema: " + schema);
            System.out.println("Listing tables (TABLE, VIEW) for schema pattern: " + (schema == null ? "%" : schema));

            try (ResultSet rs = md.getTables(null, schema == null ? null : schema, "%", new String[]{"TABLE", "VIEW"})) {
                boolean found = false;
                while (rs.next()) {
                    String tCatalog = rs.getString("TABLE_CAT");
                    String tSchema = rs.getString("TABLE_SCHEM");
                    String tName = rs.getString("TABLE_NAME");
                    String tType = rs.getString("TABLE_TYPE");
                    System.out.printf("%s.%s (%s)\n", tSchema, tName, tType);
                    found = true;
                }
                if (!found) System.out.println("No tables found with that schema pattern.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect or query the database:");
            e.printStackTrace();
            System.exit(4);
        }
    }
}
