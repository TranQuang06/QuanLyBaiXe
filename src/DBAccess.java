import com.mysql.jdbc.Driver;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;

public class DBAccess {
   Connection conn;

   public void getConnection() {
       try {
           String url = "JDBC:mySQL://localhost:3306/quanlibaixe";
           String user = "root";
           String pass = "";
           DriverManager.registerDriver(new Driver());
           conn = DriverManager.getConnection(url,user,pass);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
   public void disConnnection() {
       if (conn != null) {
           try {
               conn.close();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
   }

    public void add(PhuongTien pt) {
        try {
            getConnection();
            String sql_02 = "UPDATE trangthaibaixe SET TrangThai = 1, LoaiXe = ? WHERE MaThe = ? AND TrangThai = 0";
            PreparedStatement ps_02 = conn.prepareStatement(sql_02);
            ps_02.setString(1,pt.getLoaiXe());
            ps_02.setString(2,pt.getMaThe());
            if(ps_02.executeUpdate() == 1) {
                String sql = "INSERT INTO dulieu(MaThe,BienSoXe,LoaiXe,HangXe,ThoiGianVao,ThoiGianRa) VALUES(?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                LocalDateTime now = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(now);
                ps.setString(1,pt.getMaThe());
                ps.setString(2,pt.getBienSo());
                ps.setString(3,pt.getLoaiXe());
                ps.setString(4,pt.getHangXe());
                ps.setTimestamp(5,timestamp);
                ps.setTimestamp(6,null);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Thêm phương tiện thành công","Thành công",JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null,"Thẻ hoặc phương tiện này đã có trong bãi, vui lòng kiểm tra lại thông tin","Lỗi",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Lỗi khi thêm phương tiện","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(String MaThe) {
        try {
            getConnection();
            String sql = "UPDATE dulieu SET ThoiGianRa = ? WHERE MaThe = ?";
            String sql_02 = "UPDATE trangthaibaixe SET TrangThai = '0' AND LoaiXe = null WHERE MaThe = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps_02 = conn.prepareStatement(sql_02);
            LocalDateTime now = LocalDateTime.of(2024,1,12,20,30,00);
            Timestamp timestamp = Timestamp.valueOf(now);
            ps.setTimestamp(1,timestamp);
            ps.setString(2,MaThe);
            ps_02.setString(1,MaThe);
            ps.executeUpdate();
            ps_02.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Lỗi không thể cập nhật dữ liệu","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
}
