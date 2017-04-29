import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * Created by pmcgi on 4/29/2017.
 */
public class UpdateRecords extends JFrame {
    private JPanel UpdatePanel;
    private JTable CubeTimes;
    private JTextField textField1;
    private JTextField textField2;
    private JButton submitButton;


    protected UpdateRecords(PreparedStatement psInsert, Statement statement, Connection conn, Scanner numberScanner, Scanner stringScanner) {
        setContentPane(UpdatePanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
