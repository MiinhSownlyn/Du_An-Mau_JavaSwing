/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dominhson.dao;

import com.dominhson.model.ChuyenDe;
import com.dominhson.utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 24dom
 */
public class ChuyenDeDAO {

    Connection con = null;
    String sql = "";
    PreparedStatement ps = null;
    ResultSet rs = null;

    final String INSERT_SQL = "insert into ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa)  values(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD=?,HocPhi=?,ThoiLuong=?,Hinh=?,MoTa=? WHERE MaCD=?";
    final String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD=?";
    final String SELECT_ALL_SQL = "SELECT* FROM CHUYENDE";
    final String SELECT_BY_ID_SQL = "select MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa from ChuyenDe where MaCD=?";

    public List<ChuyenDe> SELECT_ALL() {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SELECT_ALL_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe(rs.getString(1), rs.getString(2), rs.getDouble(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6));
                list.add(cd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int INSERT(ChuyenDe cd) {
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(INSERT_SQL);
            ps.setObject(1, cd.getMaCD());
            ps.setObject(2, cd.getTenCD());
            ps.setObject(3, cd.getHocPhi());
            ps.setObject(4, cd.getThoiLuong());
            ps.setObject(5, cd.getHinh());
            ps.setObject(6, cd.getMoTa());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int DELETE(String ID) {
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(DELETE_SQL);
            ps.setObject(1, ID);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int UPDATE(ChuyenDe cd, String ID) {
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(UPDATE_SQL);
            ps.setObject(6, cd.getMaCD());
            ps.setObject(1, cd.getTenCD());
            ps.setObject(2, cd.getHocPhi());
            ps.setObject(3, cd.getThoiLuong());
            ps.setObject(4, cd.getHinh());
            ps.setObject(5, cd.getMoTa());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ChuyenDe getCDByMaCD(String id) {
        ChuyenDe cd = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(SELECT_BY_ID_SQL);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                cd.setMaCD(rs.getString(1));
                cd.setTenCD(rs.getString(2));
                cd.setHocPhi(rs.getDouble(3));
                cd.setThoiLuong(rs.getInt(4));
                cd.setHinh(rs.getString(5));
                cd.setMoTa(rs.getString(6));
                return cd;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ChuyenDe getCDByTenCD(String tenCD) {
        String sql = """
                   select MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa from ChuyenDe where TenCD=?
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenCD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe();
                cd.setMaCD(rs.getString(1));
                cd.setTenCD(rs.getString(2));
                cd.setHocPhi(rs.getDouble(3));
                cd.setThoiLuong(rs.getInt(4));
                cd.setHinh(rs.getString(5));
                cd.setMoTa(rs.getString(6));
                return cd;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
