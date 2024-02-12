import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;

public class Window extends JFrame implements ActionListener {
    JFrame frame = new JFrame();


    JPanel top_panel = new JPanel();
    JPanel bottom_panel = new JPanel();


    JLabel name_label = new JLabel("Full Name");
    JLabel address_label = new JLabel("Address");
    JLabel phone_label = new JLabel("Phone Number");


    JTextField name_textfield = new JTextField();
    JTextField address_textfield = new JTextField();
    JTextField phone_textfield = new JTextField();
    JButton add_button = new JButton("Add Contact");
    JButton remove_button = new JButton("Remove");


    ArrayList<String[]> data = new ArrayList<>();
    String[] column_names = {"Name", "Address", "Phone Number"};
    DefaultTableModel tableModel = new DefaultTableModel(column_names, 0);
    JTable table = new JTable(tableModel);




    Window(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2,1));

        top_panel.setLayout(new GridLayout(4,2));
        bottom_panel.setLayout(new BorderLayout());

        name_label.setHorizontalAlignment(JLabel.CENTER);
        address_label.setHorizontalAlignment(JLabel.CENTER);
        phone_label.setHorizontalAlignment(JLabel.CENTER);

        top_panel.add(name_label); top_panel.add(name_textfield);
        top_panel.add(address_label); top_panel.add(address_textfield);
        top_panel.add(phone_label); top_panel.add(phone_textfield);
        top_panel.add(add_button); top_panel.add(remove_button);

        bottom_panel.add(new JScrollPane(table));

        frame.add(top_panel);
        frame.add(bottom_panel);
        pack();

        add_button.addActionListener(this);
        remove_button.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==add_button){
            addData();
        } else if (e.getSource()==remove_button) {
            removeData();
        }
    }

    public void addData(){
        String name = name_textfield.getText();
        String address = address_textfield.getText();
        String phone_no = phone_textfield.getText();

        String[] rowData = {name, address, phone_no};

        tableModel.addRow(rowData);

        name_textfield.setText("");
        address_textfield.setText("");
        phone_textfield.setText("");
    }
    public void removeData(){
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1) tableModel.removeRow(selectedRow);
        else JOptionPane.showMessageDialog(this, "Please select a row to remove.");
    }
}
