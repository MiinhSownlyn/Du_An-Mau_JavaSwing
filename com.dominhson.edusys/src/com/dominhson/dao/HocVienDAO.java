/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dominhson.dao;

import com.dominhson.model.HocVien;
import com.dominhson.model.KhoaHoc;
import com.dominhson.utils.DBConnect;
import com.dominhson.utils.JDBCHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

/**
 *
 * @author 24dom
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    String INSERT_SQL = "INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
    String UPDATE_SQL = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
    String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV=?";
    String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV=?";

    @Override
    public void add(HocVien entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem());
    }

    @Override
    public void update(HocVien entity, Integer id) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem(),
                entity.getMaHV());
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HocVien> getAll() {
        return this.getBySql(SELECT_ALL_SQL);
    }

    @Override
    public HocVien getByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HocVien> getBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getInt("MaHV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setDiem(rs.getDouble("Diem"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMK(HocVien entity, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HocVien getByMa(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HocVien> selectByMaKH(int makh) {
        String SQL = "SELECT * FROM HocVien WHERE MaKH = ?";
        return this.getBySql(SQL, makh);
    }

    public List<HocVien> getHVByMaKH(int maKH) {
        String sql = """
                   select * from HocVien where maKH=?
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maKH);
            ResultSet rs = ps.executeQuery();
            List<HocVien> listHV = new ArrayList<>();
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt(1));
                hv.setMaKH(rs.getInt(2));
                hv.setMaNH(rs.getString(3));
                hv.setDiem(rs.getDouble(4));
                listHV.add(hv);
            }
            return listHV;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<HocVien> getHVNgoaiKhoa(int maKH) {
        String sql = """
                   select * from hocVien where MaKH not in(select MaKH from HocVien where maKH=?)
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maKH);
            ResultSet rs = ps.executeQuery();
            List<HocVien> listHV = new ArrayList<>();
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt(1));
                hv.setMaKH(rs.getInt(2));
                hv.setMaNH(rs.getString(3));
                hv.setDiem(rs.getDouble(4));
                listHV.add(hv);
            }
            return listHV;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int update(HocVien hv) {
        String UPDATE_SQL = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_SQL);
            ps.setObject(1, hv.getMaKH());
            ps.setObject(2, hv.getMaNH());
            ps.setObject(3, hv.getDiem());
            ps.setObject(4, hv.getMaHV());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update1(HocVien hv, int mahV) {
        String UPDATE_SQL = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_SQL);
            ps.setObject(1, hv.getMaKH());
            ps.setObject(2, hv.getMaNH());
            ps.setObject(3, hv.getDiem());
            ps.setObject(4, mahV);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public HocVien getHocVienByID(int id) {
        HocVien hv = null;
        String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV=?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hv = new HocVien(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4));

            }
            return hv;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
