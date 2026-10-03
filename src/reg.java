import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class reg {
    JFrame jf_reg = new JFrame("Đăng Ký Tài Khoản");

    JLabel lb_Phone = new JLabel("Số điện thoại:");
    JLabel lb_Username = new JLabel("Tên đăng nhập:");
    JLabel lb_Password = new JLabel("Mật khẩu:");
    JLabel lb_ConfirmPassword = new JLabel("Nhập lại mật khẩu:");

    JTextField tf_Phone = new JTextField();
    JTextField tf_Username = new JTextField();

    JPasswordField pf_Password = new JPasswordField();
    JPasswordField pf_ConfirmPassword = new JPasswordField();

    JButton btn_Register = new JButton("Đăng ký");
    JButton btn_exit = new JButton("Thoát");

    DBAccess db = new DBAccess();

    public reg() {
        jf_reg.setSize(450, 300);
        jf_reg.setLocationRelativeTo(null);
        jf_reg.setLayout(null);
        jf_reg.setVisible(true);

        jf_reg.add(lb_Phone);
        jf_reg.add(tf_Phone);
        jf_reg.add(lb_Username);
        jf_reg.add(tf_Username);
        jf_reg.add(lb_Password);
        jf_reg.add(pf_Password);
        jf_reg.add(lb_ConfirmPassword);
        jf_reg.add(pf_ConfirmPassword);
        jf_reg.add(btn_Register);
        jf_reg.add(btn_exit);

        lb_Phone.setBounds(50, 30, 100, 25);
        tf_Phone.setBounds(160, 30, 200, 25);

        lb_Username.setBounds(50, 70, 100, 25);
        tf_Username.setBounds(160, 70, 200, 25);

        lb_Password.setBounds(50, 110, 100, 25);
        pf_Password.setBounds(160, 110, 200, 25);

        lb_ConfirmPassword.setBounds(50, 150, 150, 25);
        pf_ConfirmPassword.setBounds(160, 150, 200, 25);

        btn_Register.setBounds(110, 190, 100, 30);
        btn_exit.setBounds(250, 190, 100, 30);

        btn_Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tf_Phone.getText().isEmpty() || tf_Username.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(jf_reg,"Vui lòng điền đủ thông tin","Lỗi",JOptionPane.ERROR_MESSAGE);
                }else if (!Arrays.equals(pf_Password.getPassword(), pf_ConfirmPassword.getPassword())) {
                    JOptionPane.showMessageDialog(jf_reg,"Mật khẩu không khớp","Lỗi",JOptionPane.ERROR_MESSAGE);
                }else {
                    String sql = "INSERT INTO login(SoDienThoai,User,Pass) VALUES(?,?,?)";
                    db.getConnection();
                    try {
                        PreparedStatement ps = db.conn.prepareStatement(sql);
                        ps.setString(1, tf_Phone.getText());
                        ps.setString(2, tf_Username.getText());
                        ps.setString(3, pf_Password.getText());
                        if (ps.executeUpdate() == 1) {
                            ImageIcon customIcon = new ImageIcon("src/img/checked.png");
                            JOptionPane.showMessageDialog(jf_reg,"Đăng kí thành công","Thành công",JOptionPane.PLAIN_MESSAGE, customIcon);
                        };
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(jf_reg,"Đăng kí thất bại, tên đăng nhập đã tồn tại","Lỗi",JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf_reg.setVisible(false);

            }
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new reg();
    }
}
