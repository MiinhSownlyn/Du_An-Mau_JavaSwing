/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dominhson.dao;

import com.dominhson.model.NguoiHoc;
import com.dominhson.utils.DBConnect;
import com.dominhson.utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author 24dom
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String SQL = "";

    public List<NguoiHoc> SELECT_ALL() {
        List<NguoiHoc> list = new ArrayList<>();
        SQL = "SELECT MANH,HOTEN,GIOITINH,NGAYSINH,DIENTHOAI,EMAIL,MANv,NGAYDK,GHICHU FROM NguoiHoc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiHoc ng = new NguoiHoc(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getDate(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9));
                list.add(ng);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int INSERT(NguoiHoc nh) {
        try {
            SQL = "INSERT INTO NGUOIHOC(MANH,HOTEN,GIOITINH,NGAYSINH,DIENTHOAI,EMAIL,MANv,NGAYDK,GHICHU)  VALUES(?,?,?,?,?,?,?,?,?)";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setObject(1, nh.getMaNH());
            ps.setObject(2, nh.getHoTen());
            ps.setObject(3, nh.isGioiTinh());
            ps.setObject(4, nh.getNgaySinh());
            ps.setObject(5, nh.getDienThoai());
            ps.setObject(6, nh.getEmail());
            ps.setObject(7, nh.getMaNV());
            ps.setObject(8, nh.getNgayDK());
            ps.setObject(9, nh.getGhiChu());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int DELETE(String id) {
        SQL = "DELETE FROM NGUOIHOC WHERE MANH=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setObject(1, id);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int UPDATE(NguoiHoc nh, String id) {
        SQL = "UPDATE NGUOIHOC SET HOTEN=?,GIOITINH=?,NGAYSINH=?,DIENTHOAI=?,EMAIL=?,MANv=?,NGAYDK=?,GHICHU=? WHERE  MANH=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setObject(9, nh.getMaNH());
            ps.setObject(1, nh.getHoTen());
            ps.setObject(2, nh.isGioiTinh());
            ps.setObject(3, nh.getNgaySinh());
            ps.setObject(4, nh.getDienThoai());
            ps.setObject(5, nh.getEmail());
            ps.setObject(6, nh.getMaNV());
            ps.setObject(7, nh.getNgayDK());
            ps.setObject(8, nh.getGhiChu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<NguoiHoc> FIND_BY_NAME(String Name) {
        List<NguoiHoc> list = new ArrayList<>();
        SQL = "SELECT MaNH,HOTEN,GIOITINH,NGAYSINH,DIENTHOAI,EMAIL,MANv,NGAYDK,GHICHU FROM NguoiHoc WHERE HoTen like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setObject(1, Name);
            rs = ps.executeQuery();
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getDate(4), rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8), rs.getString(9));
                list.add(nh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public NguoiHoc find_id(String id) {
        NguoiHoc nh = null;
        SQL = "SELECT MANH,HOTEN,GIOITINH,NGAYSINH,DIENTHOAI,EMAIL,MANv,NGAYDK,GHICHU FROM NguoiHoc WHERE MANH=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                nh = new NguoiHoc(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getDate(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9));

            }
            return nh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String SQL = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND "
                + "MaNH NOT IN(SELECT MaNH FROM HocVien WHERE MaKH = ?)";
        return this.getBySql(SQL, "%" + keyword + "%", makh);
    }

    public List<NguoiHoc> selectNoInCourse(int makh) {
        String SQL = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND "
                + "MaNH NOT IN(SELECT MaNH FROM HocVien WHERE MaKH = ?)";
        return this.getBySql(SQL, "%" + "%", makh);
    }

    public List<NguoiHoc> NotInCourse(int makh) {
        String SQL = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND "
                + "MaNH NOT IN(SELECT MaNH FROM HocVien WHERE MaKH = ?)";
        return this.getBySql(SQL, makh);
    }

    public NguoiHoc getNHByMa(String maNH) {
        String sql = """
                   select* from nguoiHoc where maNH=?
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maNH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString(1));
                nh.setHoTen(rs.getString(2));
                nh.setGioiTinh(rs.getBoolean(3));
                nh.setNgaySinh(rs.getDate(4));
                nh.setDienThoai(rs.getString(5));
                nh.setEmail(rs.getString(6));
                nh.setGhiChu(rs.getString(7));
                nh.setMaNV(rs.getString(8));
                nh.setNgayDK(rs.getDate(9));
                return nh;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<NguoiHoc> getNHByMaKH(int maKH) {
        String sql = "SELECT MAHV,HOCVIEN.MaNH,HoTen,Diem from NguoiHoc "
                + "JOIN HocVien ON NguoiHoc.MaNH=HocVien.MaNH Where MaKH =?";
        return getBySql(sql, maKH);
    }

    @Override
    public List<NguoiHoc> getBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(NguoiHoc entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(NguoiHoc entity, String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NguoiHoc> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NguoiHoc getByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateMK(NguoiHoc entity, String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NguoiHoc getByMa(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<NguoiHoc> selectByCourse(Integer makh) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return getBySql(sql, makh);
    }
    public List<NguoiHoc> selectByCourse1(Integer makh) {
        String sql = "SELECT Hoten FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return getBySql(sql, makh);
    }

}
