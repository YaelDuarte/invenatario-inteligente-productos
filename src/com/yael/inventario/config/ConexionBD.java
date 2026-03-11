package com.yael.inventario.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.yael.inventario.excepciones.ExcepcionConexionErroneaBD;

public class ConexionBD {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public static void getProperties() throws ExcepcionConexionErroneaBD {

        Properties p = new Properties();

        try {
            InputStream is = ConexionBD.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (is == null) {
                throw new ExcepcionConexionErroneaBD("No se encontró el archivo config.properties");
            }

            p.load(is);

            URL = p.getProperty("url.config");
            USER = p.getProperty("user.config");
            PASSWORD = p.getProperty("pwd.config");

        } catch (Exception e) {
            throw new ExcepcionConexionErroneaBD("Conexión fallida, verificar datos: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        getProperties();
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}