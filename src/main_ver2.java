import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class main_ver2 {
    JFrame jf_main = new JFrame("Quản lí bãi xe");
    JPanel jp_center = new JPanel();
    JPanel jp_center_top = new JPanel();
    JPanel jp_center_under = new JPanel();
    JPanel jp_top_01 = new JPanel();
    JPanel jp_top_02 = new JPanel();
    JPanel jp_top_03 = new JPanel();
    JPanel jp_top_02_01 = new JPanel();
    JPanel jp_top_02_02 = new JPanel();
    JPanel jp_top_02_03 = new JPanel();

    ImageIcon img = new ImageIcon("src/img/bg_main.PNG");
    JLabel lb_img = new JLabel(img);

    ImageIcon customIcon_02 = new ImageIcon("src/img/bill.png");

    JLabel lb_ThongTinPhuongTien = new JLabel("Thông tin phương tiện");
    JLabel lb_BienSo = new JLabel("Biển số");
    JLabel lb_LoaiXe = new JLabel("Loại Xe");
    JLabel lb_ThongTinThe = new JLabel("Thông tin thẻ");
    JLabel lb_MaThe = new JLabel("Mã thẻ");
    JLabel lb_XeRaBai = new JLabel("Xe ra bãi");
    JLabel lb_XeVaoBai = new JLabel("Xe vào bãi");
    JLabel lb_time = new JLabel("01/01/1999 00:00:00",JLabel.CENTER);
    JLabel lb_ConTrong_01 = new JLabel("Xe máy còn: 20/20 chỗ",JLabel.RIGHT);
    JLabel lb_ConTrong_02 = new JLabel("Ô tô còn: 10/10 chỗ", JLabel.CENTER);

    TextField tf_BienSo = new TextField(20);
    TextField tf_LoaiXe = new TextField(20);
    TextField tf_MaThe = new TextField(20);

    JButton btn_TraCuu = new JButton("Tra cứu");
    JButton btn_NhapXeVaoBai = new JButton("Nhập xe vào bãi");
    JButton btn_ThanhToan = new JButton("Tính tiền đỗ xe");
    JButton btn_Thoat = new JButton("Thoát");

    JRadioButton rbt_XeMay = new JRadioButton("Xe máy");
    JRadioButton rbt_Oto = new JRadioButton("Xe ô tô");
    ButtonGroup btng_LoaiXe = new ButtonGroup();

    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);

    Font font_01 = new Font("Calibri", Font.PLAIN, 18);
    Font font_02 = new Font("Calibri", Font.PLAIN, 16);
    Font font_03 = new Font("Calibri", Font.BOLD, 22);
    Font font_04 = new Font("Calibri", Font.BOLD, 16);

    SimpleDateFormat formatTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Color color_01 = new Color(173, 216, 230);
    Color color_02 = new Color(255, 182, 182);

    DBAccess db = new DBAccess();

    public main_ver2() {
        //setup jf_main
        jf_main.setSize(1000,690);
        jf_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf_main.setLocationRelativeTo(null);
        jf_main.setVisible(true);

        //set layout phần trung tâm
        jf_main.setLayout(new BorderLayout(5,5));
        jf_main.add(lb_img, BorderLayout.NORTH);
        jf_main.add(jp_center, BorderLayout.CENTER);
        //add jpanel và set layout phần trung tâm
        jp_center.setLayout(new GridLayout(2,1,5,5));
        jp_center.add(jp_center_top);
        jp_center.add(jp_center_under);
        jp_center_top.setLayout(new GridLayout(1,3));
        jp_center_top.add(jp_top_01);
        jp_center_top.add(jp_top_02);
        jp_center_top.add(jp_top_03);
        jp_top_01.setLayout(null);
        TitledBorder titledBorder_01 = BorderFactory.createTitledBorder("Thông tin phương tiện");
        titledBorder_01.setTitleFont(font_01);
        jp_top_01.setBorder(titledBorder_01);
        jp_top_02.setLayout(new GridLayout(3,1));
        TitledBorder titledBorder_02_01 = BorderFactory.createTitledBorder("Thông tin thẻ");
        titledBorder_02_01.setTitleFont(font_01);
        jp_top_02_01.setBorder(titledBorder_02_01);
        jp_top_02_01.setLayout(new BorderLayout());
        TitledBorder titledBorder_02_02 = BorderFactory.createTitledBorder("Trạng thái");
        jp_top_02_02.setBorder(titledBorder_02_02);
        titledBorder_02_02.setTitleFont(font_01);
        jp_top_02_02.setLayout(new GridLayout(1,2));
        TitledBorder titledBorder_02_03 = BorderFactory.createTitledBorder("Thời gian hiện tại");
        jp_top_02_03.setBorder(titledBorder_02_03);
        titledBorder_02_03.setTitleFont(font_01);
        jp_top_02_03.setLayout(new FlowLayout());
        jp_top_03.setLayout(null);
        TitledBorder titledBorder_03 = BorderFactory.createTitledBorder("Chức năng của hệ thống");
        titledBorder_03.setTitleFont(font_01);
        jp_top_03.setBorder(titledBorder_03);
        jp_center_under.setLayout(new GridLayout(1,1));
        TitledBorder titledBorder_04 = BorderFactory.createTitledBorder("Dữ liệu của hệ thống");
        titledBorder_04.setTitleFont(font_01);
        jp_center_under.setBorder(titledBorder_04);

        //setup jp_top_01
        jp_top_01.add(lb_BienSo);
        jp_top_01.add(lb_LoaiXe);
        jp_top_01.add(tf_BienSo);
        jp_top_01.add(tf_LoaiXe);
        btng_LoaiXe.add(rbt_XeMay);
        btng_LoaiXe.add(rbt_Oto);
        jp_top_01.add(rbt_XeMay);
        jp_top_01.add(rbt_Oto);
        lb_BienSo.setBounds(20,30,270,30);
        tf_BienSo.setBounds(20,60,270,30);
        lb_LoaiXe.setBounds(20,90,270,30);
        tf_LoaiXe.setBounds(20,120,270,30);
        rbt_XeMay.setBounds(55,175,100,30);
        rbt_Oto.setBounds(165,175,100,30);
        //setup jp_top_02
        jp_top_02.add(jp_top_02_01);
        jp_top_02_01.add(lb_MaThe, BorderLayout.NORTH);
        jp_top_02_01.add(tf_MaThe, BorderLayout.CENTER);
        jp_top_02.add(jp_top_02_02);
        jp_top_02_02.add(lb_ConTrong_01);
        jp_top_02_02.add(lb_ConTrong_02);
        jp_top_02.add(jp_top_02_03);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e ) {
                String time = formatTime.format(new java.util.Date());
                lb_time.setText(time);
            }
        });
        timer.start();
        jp_top_02_03.add(lb_time);
        //setyp jp_top_03
        jp_top_03.add(lb_XeVaoBai);
        jp_top_03.add(btn_NhapXeVaoBai);
        jp_top_03.add(lb_XeRaBai);
        jp_top_03.add(btn_ThanhToan);
        jp_top_03.add(btn_Thoat);
        jp_top_03.add(btn_TraCuu);
        lb_XeVaoBai.setBounds(20,30,270,30);
        btn_NhapXeVaoBai.setBounds(20,60,270,30);
        lb_XeRaBai.setBounds(20,90,270,30);
        btn_ThanhToan.setBounds(20,120,270,30);
        btn_TraCuu.setBounds(20,165,270,30);
        btn_Thoat.setBounds(20,210,270,30);

        //connectionDB
        db.getConnection();

        //setup jp_center_under
        loadData();
        loadViTri();
        JScrollPane scrollTable = new JScrollPane(table);
        jp_center_under.add(scrollTable);

        //action btn_NhapXe
        btn_NhapXeVaoBai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String LoaiXe;
                if(rbt_XeMay.isSelected()) {
                    LoaiXe = "Xe máy";
                }else {
                    LoaiXe = "Xe ô tô";
                }
                PhuongTien pt = new PhuongTien(tf_MaThe.getText(),tf_BienSo.getText(),LoaiXe, tf_LoaiXe.getText());
                db.add(pt);
                loadViTri();
                loadData();
            }
        });
        //action btn_TraCuu
        btn_TraCuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new search();
            }
        });
        //action btn_ThanhToan
        btn_ThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThanhToan();
                loadViTri();
                loadData();
            }
        });
        //action btn_Thoat
        btn_Thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    jf_main.setVisible(false);
                    new Login_ver2();
                }
            }
        });

        //set font
        lb_ThongTinPhuongTien.setFont(font_01);
        lb_BienSo.setFont(font_01);
        lb_LoaiXe.setFont(font_01);
        lb_ThongTinThe.setFont(font_01);
        lb_MaThe.setFont(font_01);
        btn_TraCuu.setFont(font_01);
        lb_XeVaoBai.setFont(font_01);
        btn_NhapXeVaoBai.setFont(font_01);
        lb_XeRaBai.setFont(font_01);
        btn_ThanhToan.setFont(font_01);
        btn_Thoat.setFont(font_01);
        table.setFont(font_02);
        table.setRowHeight(25);
        tf_MaThe.setFont(font_02);
        tf_LoaiXe.setFont(font_02);
        tf_BienSo.setFont(font_02);
        rbt_XeMay.setFont(font_01);
        rbt_Oto.setFont(font_01);
        lb_time.setFont(font_03);
        lb_ConTrong_01.setFont(font_04);
        lb_ConTrong_02.setFont(font_04);
        btn_NhapXeVaoBai.setBackground(color_01);
        btn_ThanhToan.setBackground(color_01);
        btn_TraCuu.setBackground(color_01);
        btn_Thoat.setBackground(color_02);
    }

    public void loadData() {
        String sql = "SELECT * FROM dulieu ORDER BY ThoiGianVao DESC";
        try {
            PreparedStatement ps = db.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            tableModel.setColumnCount(0);
            tableModel.setRowCount(0);
            tableModel.addColumn("Mã thẻ");
            tableModel.addColumn("Biển số xe");
            tableModel.addColumn("Loại xe");
            tableModel.addColumn("Hãng xe");
            tableModel.addColumn("Thời gian vào bãi");
            tableModel.addColumn("Thời gian ra khỏi bãi");
            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("MaThe");
                row[1] = rs.getString("BienSoXe");
                row[2] = rs.getString("LoaiXe");
                row[3] = rs.getString("HangXe");
                row[4] = rs.getTimestamp("ThoiGianVao");
                row[5] = rs.getTimestamp("ThoiGianRa");
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadViTri() {
        String sql = "SELECT LoaiXe,COUNT(MaThe) AS 'Soluong' FROM trangthaibaixe WHERE TrangThai = '1' GROUP BY LoaiXe";
        try {
            PreparedStatement ps = db.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lb_ConTrong_01.setText("Xe máy còn: 20/20");
            lb_ConTrong_02.setText("Ô tô còn: 10/10");
            while (rs.next()) {
                String LoaiXe = rs.getString("LoaiXe");
                int n = rs.getInt("SoLuong");
                if (LoaiXe.equals("Xe máy")) {
                    lb_ConTrong_01.setText("Xe máy còn: " + (20 - n) + "/20");
                }
                if (LoaiXe.equals("Xe ô tô")) {
                    lb_ConTrong_02.setText("Ô tô còn: " + (10 - n) + "/10");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ThanhToan() {
            try {
                int rowSelect = table.getSelectedRow();
                Timestamp time = (Timestamp) tableModel.getValueAt(rowSelect,4);
                LocalDateTime time_start = time.toLocalDateTime();
                LocalDateTime time_out = LocalDateTime.of(2024,1,12,20,30,00);
                int day = time_out.getDayOfYear() - time_start.getDayOfYear();
                int hour = 0;
                if(day == 0) {
                    hour = time_out.getHour() - time_start.getHour();
                }
                String LoaiXe = (String) tableModel.getValueAt(rowSelect,2);
                long TinhTien = 0;
                if(LoaiXe.equals("Xe ô tô")) {
                    TinhTien = day * 30 + hour * 4 + 5;
                } else if (LoaiXe.equals("Xe máy")) {
                    TinhTien = day * 10 + hour * 2 + 5;
                }
                JOptionPane.showMessageDialog(jf_main,
                        "Tính tiền thành công\nTổng tiền phải thanh toán là : " + TinhTien + ".000 VNĐ",
                        "Thông báo",
                        JOptionPane.PLAIN_MESSAGE,customIcon_02);
                db.update((String) tableModel.getValueAt(rowSelect,0));
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null,"Vui lòng chọn 1 phương tiện","Lỗi",JOptionPane.ERROR_MESSAGE);
            }

    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        new main_ver2();
    }
}
