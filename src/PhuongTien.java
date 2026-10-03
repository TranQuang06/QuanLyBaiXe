public class PhuongTien {
    private String BienSo;
    private String LoaiXe;
    private String MaThe;
    private String HangXe;

    public PhuongTien(String maThe, String bienSo, String loaiXe, String hangXe) {
        BienSo = bienSo;
        LoaiXe = loaiXe;
        MaThe = maThe;
        HangXe = hangXe;
    }

    public String getHangXe() {
        return HangXe;
    }

    public void setHangXe(String hangXe) {
        HangXe = hangXe;
    }

    public String getBienSo() {
        return BienSo;
    }

    public void setBienSo(String bienSo) {
        BienSo = bienSo;
    }

    public String getLoaiXe() {
        return LoaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        LoaiXe = loaiXe;
    }

    public String getMaThe() {
        return MaThe;
    }

    public void setMaThe(String maThe) {
        MaThe = maThe;
    }
}
