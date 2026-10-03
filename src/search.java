import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class search {
    JFrame jf_search = new JFrame("Tìm kiếm");
    JPanel jp_top = new JPanel();
    JPanel jp_botton = new JPanel();
    JPanel jp_under = new JPanel();

    JLabel lb_TimKiem = new JLabel("Tìm kiếm: ");

    JTextField tf_TimKiem = new JTextField(50);

    JButton btn_TimKiem = new JButton("Tìm kiếm");
    JButton btn_Thoat = new JButton("Thoát");

    DefaultTableModel tableModel_QLTK = new DefaultTableModel();
    JTable table_QLTK = new JTable(tableModel_QLTK);

    String[] search = {"Mã thẻ", "Biển số xe", "Loại xe", "Hãng xe", "Thời gian vào", "Thời gian ra"
    };
    JComboBox<String> list_search = new JComboBox<>(search);

    DBAccess db = new DBAccess();

    Color color_01 = new Color(173, 216, 230);
    Color color_02 = new Color(255, 182, 182);

    public search() {
        db.getConnection();
        jf_search.setSize(800, 300);
        jf_search.setLayout(new BorderLayout(15, 15));
        jf_search.setLocationRelativeTo(null);
        jf_search.setVisible(true);

        jf_search.add(jp_top, BorderLayout.NORTH);
        jf_search.add(jp_under, BorderLayout.CENTER);

        jp_top.setLayout(new BorderLayout(15, 15));
        jp_top.add(lb_TimKiem, BorderLayout.WEST);
        jp_top.add(tf_TimKiem, BorderLayout.CENTER);
        jp_top.add(jp_botton, BorderLayout.EAST);

        jp_botton.setLayout(new GridLayout(1, 3, 10, 10));
        jp_botton.add(list_search);
        jp_botton.add(btn_TimKiem);
        jp_botton.add(btn_Thoat);

        jp_under.setLayout(new GridLayout(1, 1));
        loadData();
        JScrollPane scrollPane_QLTK = new JScrollPane(table_QLTK);
        jp_under.add(scrollPane_QLTK);

        //setAction
        btn_TimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData_search();
            }
        });
        btn_Thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    jf_search.setVisible(false);
                }
            }
        });
    }
    public void loadData() {
        String sql = "SELECT * FROM dulieu ORDER BY ThoiGianVao DESC";
        try {
            PreparedStatement ps = db.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            tableModel_QLTK.setColumnCount(0);
            tableModel_QLTK.setRowCount(0);
            tableModel_QLTK.addColumn("Mã thẻ");
            tableModel_QLTK.addColumn("Biển số xe");
            tableModel_QLTK.addColumn("Loại xe");
            tableModel_QLTK.addColumn("Hãng xe");
            tableModel_QLTK.addColumn("Thời gian vào bãi");
            tableModel_QLTK.addColumn("Thời gian ra khỏi bãi");
            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("MaThe");
                row[1] = rs.getString("BienSoXe");
                row[2] = rs.getString("LoaiXe");
                row[3] = rs.getString("HangXe");
                row[4] = rs.getTimestamp("ThoiGianVao");
                row[5] = rs.getTimestamp("ThoiGianRa");
                tableModel_QLTK.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadData_search() {
        if (tf_TimKiem.getText().equals("")) {
            JOptionPane.showMessageDialog(jf_search, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }else {
            String list = " ";
            if (list_search.getSelectedItem().toString().equals("Mã thẻ")) {
                list = "MaThe";
            } else if (list_search.getSelectedItem().toString().equals("Hãng xe")) {
                list = "HangXe";
            } else if (list_search.getSelectedItem().toString().equals("Loại xe")) {
                list = "LoaiXe";
            } else if (list_search.getSelectedItem().toString().equals("Biển số xe")) {
                list = "BienSoXe";
            } else if (list_search.getSelectedItem().toString().equals("Thời gian vào")) {
                list = "ThoiGianVao";
            } else if (list_search.getSelectedItem().toString().equals("Thời gian ra")) {
                list = "ThoiGianRa";
            }
            String sql = "SELECT * FROM dulieu WHERE " + list + " LIKE ? COLLATE utf8mb4_general_ci";
            try {
                PreparedStatement ps = db.conn.prepareStatement(sql);
                ps.setString(1,"%" + tf_TimKiem.getText() + "%");
                ResultSet rs = ps.executeQuery();
                tableModel_QLTK.setColumnCount(0);
                tableModel_QLTK.setRowCount(0);
                tableModel_QLTK.addColumn("Mã thẻ");
                tableModel_QLTK.addColumn("Biển số xe");
                tableModel_QLTK.addColumn("Loại xe");
                tableModel_QLTK.addColumn("Hãng xe");
                tableModel_QLTK.addColumn("Thời gian vào");
                tableModel_QLTK.addColumn("Thời gian ra");
                int count = 0;
                while (rs.next()) {
                    Object[] row = new Object[6];
                    row[0] = rs.getString("MaThe");
                    row[1] = rs.getString("BienSoXe");
                    row[2] = rs.getString("LoaiXe");
                    row[3] = rs.getString("HangXe");
                    row[4] = rs.getString("ThoiGianVao");
                    row[5] = rs.getString("ThoiGianRa");
                    tableModel_QLTK.addRow(row);
                    count++;
                }
                JOptionPane.showMessageDialog(jf_search,"Có " + count + " bản ghi được tìm thấy","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        new search();
    }
}
