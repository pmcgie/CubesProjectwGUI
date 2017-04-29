import java.sql.*;
import java.util.Scanner;

/**
 * Created by pmcgie on 4/7/2017.
 */
public class cubesDB {

    //Set up
    static Scanner stringScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cubes";     //Connection string – where's the database?
    static final String USER = "pmcgie";   //TODO replace with your username
    static final String PASSWORD = System.getenv("SQL_PW");   //TODO remember to set the environment variable
    // static final String PASSWORD = "password";   // If on lab PC, uncomment this line and replace "password" with your own password

    //Create Connections and statements
    public static void main(String[] args) {
        System.out.println("Cubes Database Program");

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            //Create a table in a database. Stores ID and time taken
            String createTableSQL = "CREATE TABLE IF NOT EXISTS cuberecords (Name varchar(30),Record double)";
            statement.execute(createTableSQL);
            System.out.println("Created cuberecords table");

            String InsertStatement = "INSERT INTO cuberecords VALUES ( ? , ? )";
            PreparedStatement psInsert = conn.prepareStatement(InsertStatement);

            mainmenu(psInsert);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(-1);
        }}

        //Run GUIs
        private static void mainmenu(PreparedStatement psInsert) throws SQLException {
            MainSelection mainoptions = new MainSelection(psInsert);
        }

    }
