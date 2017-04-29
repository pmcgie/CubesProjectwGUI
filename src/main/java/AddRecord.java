import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by pmcgi on 4/29/2017.
 */
public class AddRecord extends JFrame {

    private JPanel AddPanel;
    private JTextField RecordName;
    private JTextField RecordTime;
    private JButton submitButton;

    public AddRecord(PreparedStatement psInsert) throws SQLException {

        setContentPane(AddPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String Name = RecordName.getText();
                    double Time = Double.parseDouble(RecordTime.getText());

                    //place into table
                    psInsert.setString(1, Name);
                    psInsert.setDouble(2, Time);
                    psInsert.execute();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }
}
