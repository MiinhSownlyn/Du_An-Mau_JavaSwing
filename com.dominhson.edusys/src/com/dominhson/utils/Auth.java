/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dominhson.utils;

import com.dominhson.model.NhanVien;

/**
 *
 * @author 24dom
 */
public class Auth {

    public static NhanVien user = null;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static String maNhanVien() {
        return user.getMaNV();
    }

    public static boolean isManager() {
        return Auth.isLogin() && user.isVaiTro();
    }
}
