/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dominhson.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 24dom
 */
public class JDBCHelper {

    public static PreparedStatement getPS(String sql, Object... args) throws SQLException {
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = null;
        if (sql.trim().startsWith("{")) {
            ps = con.prepareCall(sql);
        } else {
            ps = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        return ps;
    }

    public static boolean add(String sql, Object... args) {
        int check = 0;
        try (PreparedStatement ps = getPS(sql, args);) {
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static boolean update(String sql, Object... args) {
        int check = 0;
        try (PreparedStatement ps = getPS(sql, args);) {
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static boolean xoa(String sql, Object... args) {
        int check = 0;
        try (PreparedStatement ps = getPS(sql, args);) {
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement ps = getPS(sql, args);
            return ps.executeQuery();
        } catch (SQLException e) {
//            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
