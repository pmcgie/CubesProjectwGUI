import java.sql.*;
import java.util.Scanner;

/**
 * Created by pmcgie on 4/7/2017.
 */
public class cubesDB {

    static Scanner stringScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cubes";     //Connection string – where's the database?
    static final String USER = "pmcgie";   //TODO replace with your username
    static final String PASSWORD = System.getenv("SQL_PW");   //TODO remember to set the environment variable
    // static final String PASSWORD = "password";   // If on lab PC, uncomment this line and replace "password" with your own password

    public static void main(String[] args) {
        System.out.println("Cubes Database Program");

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
             Statement statement = conn.createStatement()) {

            //Create a table in a database. Stores ID and time taken
            String createTableSQL = "CREATE TABLE IF NOT EXISTS cuberecords (Name varchar(30),Record double)";
            statement.execute(createTableSQL);
            System.out.println("Created cuberecords table");

            String InsertStatement = "INSERT INTO cuberecords VALUES ( ? , ? )";
            PreparedStatement psInsert = conn.prepareStatement(InsertStatement);

            /*String [] Name = {"Cubestormer II robot","Fakhri Raihaan (feet)","Ruxin Liu (age 3)","Mats Valk (human)"};
            Double [] RecordedTime = {5.270,27.93,99.33,6.27};

            for (int i = 0; i <Name.length; i++) {
                String sql = "SELECT * FROM cuberecords WHERE Name = '" + Name[i]+"'";
                ResultSet ResultID = statement.executeQuery(sql);
                psInsert.setString(1,Name[i]);
                psInsert.setDouble(2,RecordedTime[i]);
                psInsert.execute();
            }

            System.out.println("Added data to database");*/

            //Ask user if they want to enter in record
            String NewInfoResponse = "Y";
            while (NewInfoResponse.equals("Y")) {
                //Check to see if user wants to enter new info
                System.out.println("Do you want to enter a new record (Y/N)?");
                NewInfoResponse = stringScanner.nextLine();

                //If yes enter in new information
                if (NewInfoResponse.equals("Y")) {
                    System.out.println("Enter in new ID");
                    String NewID = stringScanner.nextLine();

                    System.out.println("Enter in time");
                    Double NewTime = numberScanner.nextDouble();

                    //place into table
                   psInsert.setString(1,NewID);
                   psInsert.setDouble(2,NewTime);
                   psInsert.execute();

                }

            }

            //Ask user if they want to update record
            String UpdateInfoResponse = "Y";
            while (UpdateInfoResponse.equals("Y")) {
                System.out.println("Do you want to update a record (Y/N)?");
                UpdateInfoResponse = stringScanner.nextLine();

                if (UpdateInfoResponse.equals("Y")) {
                    System.out.println("Type in name that you want to update");
                    String SearchName = stringScanner.nextLine();

                    //Execute search
                    System.out.println("Here is a list of the names with your search criteria");

                    String SQLNameSearch = "SELECT * FROM cuberecords WHERE Name = '" + SearchName + "'";
                    ResultSet rsNameSearch = statement.executeQuery(SQLNameSearch);

                    while (rsNameSearch.next()) {
                        System.out.println(rsNameSearch.getString("Name") + " is " + rsNameSearch.getDouble("Record"));
                    }

                    System.out.println("See Generated List. Enter Exact Name to Update Time.");
                    String ChangeName = stringScanner.nextLine();

                    System.out.println("Enter new time.");
                    Double ChangeTime = numberScanner.nextDouble();

                    String ChangeNameCode = "UPDATE cuberecords SET Record = '" + ChangeTime + "'" + "WHERE Name = '" + ChangeName + "'";
                    statement.executeUpdate(ChangeNameCode);
                }
            }

            //Ask user if they want to update record
            String DeleteInfo = "Y";
            while (DeleteInfo.equals("Y")) {
                System.out.println("Do you want to delete a record (Y/N)?");
                DeleteInfo = stringScanner.nextLine();

                if (DeleteInfo.equals("Y")) {
                    System.out.println("Type in name that you want to delete");
                    String SearchName = stringScanner.nextLine();

                    //Execute search
                    System.out.println("Here is a list of the names with your search criteria");

                    String SQLNameSearch = "SELECT * FROM cuberecords WHERE Name = '" + SearchName + "'";
                    ResultSet rsNameSearch = statement.executeQuery(SQLNameSearch);

                    while (rsNameSearch.next()) {
                        System.out.println(rsNameSearch.getString("Name") + " is " + rsNameSearch.getDouble("Record"));
                    }

                    System.out.println("See Generated List. Enter Exact Name to Delete.");
                    String DeleteName = stringScanner.nextLine();

                    String ChangeNameCode = "DELETE FROM cuberecords WHERE NAME LIKE '" + DeleteName + "'";
                    statement.executeUpdate(ChangeNameCode);
                }
            }

           // psInsert.close();
            statement.close();
            conn.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(-1);
        }

    }

}
