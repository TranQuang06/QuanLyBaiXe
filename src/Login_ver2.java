import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_ver2 {
    JFrame jf_login = new JFrame("Quản lí bãi xe");
    JPanel jp_login_r = new JPanel();

    JLabel lb_DangNhapVaoHeThong = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
    JLabel lb_BaiGiuXe = new JLabel("QUẢN LÍ BÃI GIỮ XE");
    JLabel lb_KhoangTrong = new JLabel("╔═══════ ═ ĐĂNG NHẬP ═ ═══════╗");
    JLabel lb_TenDangNhap = new JLabel("Tên đăng nhập");
    JLabel lb_MatKhau = new JLabel("Mật khẩu");
    JLabel lb_info = new JLabel("Copyright © 2024 Tran Nguyen Nhat Quang");
    JLabel lb_info_02 = new JLabel("Số điện thoại: 0827456689");
    JLabel lb_info_03 = new JLabel("Email: quangtnn.24ce@vku.udn.vn");

    JButton btn_DangNhap = new JButton("Đăng nhập");
    JButton btn_DangKy = new JButton("Đăng ký");

    JTextField tf_TenDangNhap = new JTextField(15);
    JPasswordField tf_MatKhau = new JPasswordField(15);

    ImageIcon img = new ImageIcon("src/img/bg.PNG");
    JLabel lb_img = new JLabel(img);
    ImageIcon img_02 = new ImageIcon("src/img/bg_vku.PNG");
    JLabel lb_img_02 = new JLabel(img_02);

    Font font_01 = new Font("UTM Avo", Font.BOLD, 25);
    Font font_02 = new Font("UTM Neo Sans Intel", Font.PLAIN, 16);
    Font font_03 = new Font("Arial", Font.PLAIN, 12);
    Font font_05 = new Font("UTM Neo Sans Intel", Font.BOLD, 18);

    DBAccess db = new DBAccess();

    public Login_ver2() {
        jf_login.setLayout(new GridLayout(1, 2));
        jf_login.setSize(1000, 500);
        jf_login.setLocationRelativeTo(null);
        jf_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf_login.setVisible(true);

        jp_login_r.setLayout(null);
        jp_login_r.add(lb_img_02);
        jp_login_r.add(lb_DangNhapVaoHeThong);
        jp_login_r.add(lb_BaiGiuXe);
        jp_login_r.add(lb_KhoangTrong);
        jp_login_r.add(lb_TenDangNhap);
        jp_login_r.add(tf_TenDangNhap);
        jp_login_r.add(lb_MatKhau);
        jp_login_r.add(tf_MatKhau);
        jp_login_r.add(btn_DangNhap);
        jp_login_r.add(btn_DangKy);
        jp_login_r.add(lb_info);
        jp_login_r.add(lb_info_02);
        jp_login_r.add(lb_info_03);
        lb_img_02.setBounds(56, 5, 400, 63);
        lb_DangNhapVaoHeThong.setBounds(65, 80, 400, 40);
        lb_BaiGiuXe.setBounds(119, 110, 400, 40);
        lb_KhoangTrong.setBounds(110, 150, 300, 40);
        lb_TenDangNhap.setBounds(122, 177, 200, 25);
        tf_TenDangNhap.setBounds(122, 205, 250, 25);
        lb_MatKhau.setBounds(122, 243, 200, 25);
        tf_MatKhau.setBounds(122, 270, 250, 25);
        btn_DangNhap.setBounds(92, 320, 150, 35);
        btn_DangKy.setBounds(252, 320, 150, 35);
        lb_info.setBounds(140, 410, 400, 15);
        lb_info_02.setBounds(140, 425, 400, 15);
        lb_info_03.setBounds(140, 440, 400, 15);

        lb_DangNhapVaoHeThong.setFont(font_01);
        lb_BaiGiuXe.setFont(font_01);
        lb_KhoangTrong.setFont(new Font("Arial", Font.BOLD, 14));
        lb_TenDangNhap.setFont(font_02);
        lb_MatKhau.setFont(font_02);
        tf_TenDangNhap.setFont(font_03);
        tf_MatKhau.setFont(font_03);
        btn_DangNhap.setFont(font_05);
        btn_DangNhap.setBackground(new Color(66, 103, 178));
        btn_DangNhap.setForeground(Color.WHITE);
        btn_DangNhap.setOpaque(true);
        btn_DangNhap.setBorderPainted(false);
        btn_DangKy.setFont(font_05);
        btn_DangKy.setBackground(new Color(66, 103, 178));
        btn_DangKy.setForeground(Color.WHITE);
        btn_DangKy.setOpaque(true);
        btn_DangKy.setBorderPainted(false);

        jp_login_r.setBackground(Color.WHITE);
        jf_login.setBackground(Color.WHITE);

        btn_DangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_TenDangNhap.setBackground(Color.WHITE);
                tf_MatKhau.setBackground(Color.WHITE);
                ImageIcon customIcon = new ImageIcon("src/img/checked.png");
                String sql = "SELECT * FROM login WHERE User = ?";
                db.getConnection();
                try {
                    PreparedStatement ps = db.conn.prepareStatement(sql);
                    ps.setString(1, tf_TenDangNhap.getText());
                    ResultSet rs = ps.executeQuery();
                    String pass = null;
                    if (rs.next()) {
                        pass = rs.getString("Pass");
                        if (tf_MatKhau.getText().equals(pass)) {
                            JOptionPane.showMessageDialog(jf_login,
                                    "Đăng nhập thành công",
                                    "Thông báo",
                                    JOptionPane.PLAIN_MESSAGE, customIcon);
                            jf_login.setVisible(false);
                            new main_ver2();
                        } else {
                            JOptionPane.showMessageDialog(jf_login, "Mật khẩu không đúng",
                                    "Thông báo",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(jf_login,"Không tìm thấy tài khoản","Thông báo",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btn_DangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new reg();
            }
        });

        jf_login.add(lb_img);
        jf_login.add(jp_login_r);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new Login_ver2();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}
