import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTable_Arama extends JPanel {

    private String[] columnNames
            = {"Marka", "Model", "Yıl", "Şanzıman","Fiyat"};

    private Object[][] data = {
        {"Audi", "A4", 2019, "Otomatik","410,000"},
        {"Volkswagen", "Golf", 2020, "Otomatik","231,000"},
        {"Volvo", "S60", 2019, "Otomatik","250,000"},
        {"Fiat", "Egea", 2021, "Manuel","160,000"},
        {"Ford", "Focus", 2019, "Manuel","250,000"},
        {"Peugeot", "3008", 2020, "Otomatik","360,000"},
        {"Renault", "Megane", 2021, "Otomatik","320,000"},
        {"Volvo", "S90", 2020, "Otomatik","450,000"},
        {"Tesla", "Model S", 2016, "Otomatik","1,000,000"},
        {"Bmw", "5.20d", 2019, "Otomatik","510,000"},
        {"Bmw", "4.30i", 2020, "Otomatik","450,000"},
        {"Mercedes-Benz", "E 220d", 2020, "Otomatik","440,510"},
        {"Peugeot", "208", 2020, "Otomatik","240,000"},
        {"Renault", "Clio", 2016, "Manuel","130,000"},
        {"Audi", "Q7", 2021, "Manuel","890,000"},
        {"Renault", "Clio", 2017, "Otomatik","147,000"},
        {"Hyundai", "i20", 2019, "Otomatik","190,000"},
        {"Bmw", "3.20i ed", 2021, "Otomatik","320,000"},
        {"Honda", "Civic", 2018, "Manuel","280,000"},
        {"Alfa Romeo", "Giulietta", 2015, "Otomatik","154,000"},
        {"Suzuki", "Swift", 2019, "Manuel","246,000"},
        {"Subaru", "İmpreza", 2006, "Manuel","251,000"},
        {"Skoda", "Octavia", 2015, "Manuel","199,000"},
        {"Fiat", "Linea", 2013, "Manuel","91,000"},
        {"Kia", "Picanto", 2017, "Otomatik","168,000"},
        {"Volkswagen", "Touareg", 2017, "Otomatik","428,000"},
        {"Dacia", "Sandero", 2015, "Manuel","125,000"},

    };

    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable jTable = new JTable(model);

    private TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(jTable.getModel());

    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("filtrele");

    public JTable_Arama() {
        jTable.setRowSorter(rowSorter);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Aramak istediğiniz kelime : "),
                BorderLayout.WEST);
        panel.add(jtfFilter, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(jTable), BorderLayout.CENTER);

        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Ürün bulunmadı!!"); 
            }

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
               JFrame frame = new JFrame("Araç Listesi");
               frame.add(new JTable_Arama());
               frame.pack();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setLocationRelativeTo(null);
               frame.setVisible(true);
            }

        });
    }
}