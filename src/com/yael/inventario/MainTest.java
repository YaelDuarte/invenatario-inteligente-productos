package com.yael.inventario;

import java.sql.Connection;

import com.yael.inventario.config.ConexionBD;

public class MainTest {

    public static void main(String[] args) {

        try {
            Connection conexion = ConexionBD.getConnection();

            if (conexion != null) {
                System.out.println("✅ Conexión a la base de datos exitosa");
            } else {
                System.out.println("❌ No se pudo conectar a la base de datos");
            }

            conexion.close();

        } catch (Exception e) {
            System.out.println("❌ Error al conectar con la base de datos");
            e.printStackTrace();
        }

    }
}