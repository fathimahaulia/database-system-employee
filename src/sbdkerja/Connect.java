package sbdkerja;

import java.sql.*;

public class Connect{
    public String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public String url = "jdbc:sqlserver://LAPTOP-4GBUNFG3:1433;databaseName=DBKerja;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    
    Connect(){
        try{
            Class.forName(driverClass);
            System.out.println("Driver Loaded");
            Connection conn = DriverManager.getConnection(url);
            
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version : " + dm.getDatabaseProductVersion());
            }
            System.out.println("Connected");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Connect();
    }
}