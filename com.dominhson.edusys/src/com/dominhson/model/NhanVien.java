/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dominhson.model;

import com.dominhson.dao.EduSysDAO;

/**
 *
 * @author 24dom
 */
public class NhanVien {

    private String maNV;
    private String matKhau;
    private String hoTen;
    private boolean vaiTro;

    public NhanVien() {
    }

    public NhanVien(String maNV, String matKhau, String hoTen, boolean vaiTro) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
    }

    public NhanVien(String maNV, String matKhau, boolean vaiTro) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }
    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Object[] toDaTaRow() {
        return new Object[]{
            this.maNV, this.matKhau, this.hoTen, this.vaiTro
        };
    }

    @Override
    public String toString() {
        return this.hoTen;
    }
}
