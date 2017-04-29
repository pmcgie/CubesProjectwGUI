import javax.swing.*;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by pmcgi on 4/29/2017.
 */

public class MainSelection extends JFrame {
    private JButton submitButton;
    private JRadioButton addNewRecordRadioButton;
    private JRadioButton updateRecordRadioButton;
    private JRadioButton deleteRecordRadioButton;
    private JPanel MainPanel;
    private JTextField DelRecName;
    private JTextField UpdateRecName;
    private JRadioButton quitProgramRadioButton;


    protected MainSelection(PreparedStatement psInsert) {

        setContentPane(MainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainProcess(psInsert);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    //Run Main Process
    protected void MainProcess(PreparedStatement psInsert) throws SQLException {

        //Choose next option and GUI
        if (addNewRecordRadioButton.isSelected()) {
            setVisible(false);
            NewID(psInsert);
        } else if (updateRecordRadioButton.isSelected()) {
            setVisible(false);
            //UpdateID(statement,numberScanner,stringScanner);
        } else if (deleteRecordRadioButton.isSelected()) {
            //setVisible(false);
            //DeleteID(statement,numberScanner,stringScanner);
        } else if (quitProgramRadioButton.isSelected()) {
            setVisible(false);
            //QuitProcess(statement,conn);
        }

    }


    private void NewID(PreparedStatement psInsert) throws SQLException {
        AddRecord NewInfo = new AddRecord(psInsert);
    }
}

    /*private static void UpdateID(Statement statement, Scanner numberScanner, Scanner stringScanner) throws SQLException {
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
    }

    private static void DeleteID(Statement statement, Scanner numberScanner, Scanner stringScanner) throws SQLException {
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
    }

    protected static void QuitProcess (Statement statement, Connection conn) throws SQLException {
        // psInsert.close();
        statement.close();
        conn.close();
        System.exit(-1);
    }

}*/
