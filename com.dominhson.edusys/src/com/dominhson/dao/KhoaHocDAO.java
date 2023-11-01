/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dominhson.dao;

import com.dominhson.model.KhoaHoc;
import com.dominhson.utils.DBConnect;
import com.dominhson.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 24dom
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {
    
    Connection con = null;
    String sql = "";
    PreparedStatement ps = null;
    ResultSet rs = null;
    String INSERT_SQL = "INSERT INTO KhoaHoc (MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao) VALUES (  ?, ?, ?, ?,?,?)";
    String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH=?";
    String SELECT_ALL_SQL = "SELECT MaKH,MaCD,HocPhi,ThoiLuong,NgayKG,MaNV,NgayTao,GhiChu FROM KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH=?";
    String getByMaCD = """
                    select MaKH,MaCD,HocPhi,ThoiLuong,NgayKG,MaNV,NgayTao,GhiChu from KhoaHoc where MaCD=?
                    """;
    
    public List<KhoaHoc> SELECT_ALL() {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SELECT_ALL_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                        rs.getDate(5), rs.getString(6), rs.getDate(7), rs.getString(8));
                list.add(kh);
            }
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int INSERT(KhoaHoc kh) {
        String INSERT_SQL = "INSERT INTO KhoaHoc (MACD,HocPhi,ThoiLuong,NgayKG,MaNV,NgayTao,GhiChu) VALUES ( ?, ?, ?, ?, ?,?,?)";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(INSERT_SQL);
            ps.setObject(1, kh.getMaCD());
            
            ps.setObject(2, kh.getHocPhi());
            ps.setObject(3, kh.getThoiLuong());
            ps.setObject(4, kh.getNgayKG());
            ps.setObject(7, kh.getGhiChu());
            ps.setObject(5, kh.getMaNV());
            ps.setObject(6, kh.getNgayTao());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int DELETE(int id) {
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(DELETE_SQL);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int UPDATE(KhoaHoc kh, int id) {
        String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, MaNV=?,NgayTao=?, GhiChu=? WHERE MaKH=?";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(UPDATE_SQL);
            ps.setObject(1, kh.getMaCD());
            ps.setObject(2, kh.getHocPhi());
            ps.setObject(3, kh.getThoiLuong());
            ps.setObject(4, kh.getNgayKG());
            ps.setObject(7, kh.getGhiChu());
            ps.setObject(5, kh.getMaNV());
            ps.setObject(6, kh.getNgayTao());
            ps.setObject(8, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public KhoaHoc getKHByTenNgay(String maCD, String ngay) {
        
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(getByMaCD)) {
            ps.setObject(1, maCD);
            ps.setObject(2, ngay);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt(1));
                kh.setMaCD(rs.getString(2));
                kh.setHocPhi(rs.getDouble(3));
                kh.setThoiLuong(rs.getInt(4));
                kh.setNgayKG(rs.getDate(5));
                kh.setGhiChu(rs.getString(6));
                kh.setMaNV(rs.getString(7));
                kh.setNgayTao(rs.getDate(8));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public List<KhoaHoc> SELECT_BY_MACD(String maCd) {
        List<KhoaHoc> list = new ArrayList<>();
        sql = "SELECT MAKH,MACD,HocPhi,ThoiLuong,NgayKG,MaNV,NgayTao,GhiChu FROM KHOAHOC WHERE MACD=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maCd);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
                        rs.getDate(5), rs.getString(6), rs.getDate(7), rs.getString(8));
                list.add(kh);
                
            }
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void add(KhoaHoc entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void update(KhoaHoc entity, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public KhoaHoc getByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void updateMK(KhoaHoc entity, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public KhoaHoc getByMa(Integer id) {
        List<KhoaHoc> list = getBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public List<KhoaHoc> getByMaCD(String maCD) {
        return getBySql(getByMaCD, maCD);
        
    }
    
    @Override
    public List<KhoaHoc> getAll() {
        return getBySql(SELECT_ALL_SQL);
    }

//    public List<KhoaHoc> selectByChuyenDe(String macd) {
//        String SQL = "SELECT * FROM KhoaHoc WHERE MaCD = ?";
//        return this.getBySql(SQL, macd);
//    }
    public List<Integer> selectYears() {
        String SQL = "SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(SQL);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    @Override
    public List<KhoaHoc> getBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaCD(rs.getString("MaCD"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                list.add(entity);
            }
            //rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public KhoaHoc getNguoiTao(String maNV) {
        KhoaHoc kh = null;
        sql = "SELECT MACD,HOCPHI,THOILUONG,NGAYKG,MANV,NGAYTAO FROM KhoaHoc WHERE MANV=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                //(int maKH, String maCD, double hocPhi, int thoiLuong, Date ngayKG, String ghiChu, String maNV, Date ngayTao)
                kh = new KhoaHoc(rs.getString(1), rs.getDouble(2), rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getDate(7));
            }
            return kh;
        } catch (Exception e) {
            return null;
        }
        
    }
    
}
