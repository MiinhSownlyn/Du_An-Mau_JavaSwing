/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.dominhson.view;

import com.dominhson.dao.ThongKeDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import com.dominhson.dao.*;
import com.dominhson.model.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class ThongKeJDialog extends javax.swing.JDialog {

    ThongKeDAO tkDao = new ThongKeDAO();
    KhoaHocDAO khDao = new KhoaHocDAO();
    DefaultTableModel BDModel = new DefaultTableModel();
    DefaultTableModel NHModel = new DefaultTableModel();
    DefaultTableModel DCDModel = new DefaultTableModel();
    DefaultTableModel DTModel = new DefaultTableModel();
    SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form ThongKeJDialog
     */
    void init() {
        setLocationRelativeTo(null);
        setTitle("Báo Cáo Thống Kê EduSys");
        fillCbbKhoaHoc();
        fillCbbDoanhThu();

        // fillComboYear();
        fillCbbKhoaHoc();
        //fillTableBangDiem();
        showDataDiemChuyenDe();
        showDataNguoiHoc();
        showDataDoanhThu();
//        this.selectTab(0);
//        if (!Auth.isManager()) {
//            tabs.remove(3);
//        
//    }
    }

    public ThongKeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        BDModel = (DefaultTableModel) tblBangDiem.getModel();
        NHModel = (DefaultTableModel) NguoiHocTable.getModel();
        DCDModel = (DefaultTableModel) DiemChuyenDeTable.getModel();
        DTModel = (DefaultTableModel) DoanhThuTable.getModel();
//        
        String header1[] = {"Mã NH", "Tên", "Điểm", "Xếp Loại"};
        BDModel.setColumnIdentifiers(header1);
//        
        String header2[] = {"Năm", "Số Người Học", "Đầu Tiên", "Sau Cùng"};
        NHModel.setColumnIdentifiers(header2);
//        
        String header3[] = {"Chuyên Đề", "Tổng Số HV", "Cao Nhất", "Thấp Nhất", "Điểm TB"};
        DCDModel.setColumnIdentifiers(header3);
//        
        String header4[] = {"Chuyên Đề", "Số Khóa", "Số HV", "Doanh Thu", "HP Cao Nhất", "HP Thấp Nhất", "HP Trung Bình"};
        DTModel.setColumnIdentifiers(header4);
        showDataNguoiHoc();
        showDataDiemChuyenDe();

    }

    private void fillCbbKhoaHoc() {
        CbbKhoaHoc.removeAllItems();
        List<KhoaHoc> list = new ArrayList<>();
        list = khDao.SELECT_ALL();
        for (KhoaHoc kh : list) {
            CbbKhoaHoc.addItem(kh);
        }
    }

    void fillTableBangDiem() {
        DefaultTableModel model = (DefaultTableModel) tblBangDiem.getModel();
        model.setRowCount(0);
        KhoaHoc kh = (KhoaHoc) CbbKhoaHoc.getSelectedItem();
        List<Object[]> list = tkDao.getBangDiem(kh.getMaKH());
        for (Object[] row : list) {
            double score = (double) row[2];
            model.addRow(new Object[]{row[0], row[1], score, getXepLoai(score)});
        }
    }

    void loatDataBangDiem() {
        DefaultTableModel model = (DefaultTableModel) tblBangDiem.getModel();
        model.setRowCount(0);
        KhoaHoc kh = (KhoaHoc) CbbKhoaHoc.getSelectedItem();
        if (kh == null) {
            return;
        }
        List<Object[]> list = tkDao.getBangDiem(kh.getMaKH());
        for (Object[] row : list) {
            double score = (double) row[2];
            // model.addRow(row);
            model.addRow(new Object[]{row[0], row[1], score, getXepLoai(score)});
        }
    }

    String getXepLoai(double diem) {
        if (diem < 5) {
            return "Not Qualified";
        } else if (diem < 6.5) {
            return "Average";
        } else if (diem < 7.5) {
            return "Above average";
        } else if (diem < 9) {
            return "Good";
        }
        return "Excellent";
    }

    private void showDataNguoiHoc() {
        NHModel.setRowCount(0);
        List<Object[]> list = new ArrayList<>();
        list = tkDao.getLuongNguoiHoc();
        for (Object[] row : list) {
            NHModel.addRow(row);
        }
    }

    private void showDataDiemChuyenDe() {
        DCDModel.setRowCount(0);
        List<Object[]> list = new ArrayList<>();
        list = tkDao.getDiemChuyenDe();
        for (Object[] row : list) {
            DCDModel.addRow(row);
        }
    }

    private void fillCbbDoanhThu() {
        CbbDoanhThu.removeAllItems();
        int i;
        for (i = 2022; i <= 2025; i++) {
            CbbDoanhThu.addItem(i + "");
        }
    }

    private void showDataDoanhThu() {
        DTModel.setRowCount(0);
        String year = (String) CbbDoanhThu.getSelectedItem();
        if (year != null) {
            int nam = Integer.parseInt(year);
            List<Object[]> list = new ArrayList<>();
            list = tkDao.getDoanhThu(nam);
            for (Object[] row : list) {
                DTModel.addRow(row);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CbbKhoaHoc = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangDiem = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        NguoiHocTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DiemChuyenDeTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        CbbDoanhThu = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        DoanhThuTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Tổng Hợp Thống Kê");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Khóa Học:");

        CbbKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbbKhoaHocActionPerformed(evt);
            }
        });

        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblBangDiem);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbbKhoaHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CbbKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("Bảng Điểm", jPanel1);

        NguoiHocTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(NguoiHocTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Người Học", jPanel2);

        DiemChuyenDeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(DiemChuyenDeTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
        );

        tabs.addTab("Điểm Chuyên Đề", jPanel3);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Năm:");

        CbbDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CbbDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbbDoanhThuActionPerformed(evt);
            }
        });

        DoanhThuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(DoanhThuTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbbDoanhThu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CbbDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        tabs.addTab("Doanh Thu", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(tabs))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CbbKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbbKhoaHocActionPerformed
//        try {
//            // TODO add your handling code here:\
//            showDataBangDiem();
//            //fillTableBangDiem();
//        } catch (ParseException ex) {
//            Logger.getLogger(ThongKeJDialog.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //fillTableBangDiem();
        //fillTableBangDiem();
        loatDataBangDiem();
    }//GEN-LAST:event_CbbKhoaHocActionPerformed

    private void CbbDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbbDoanhThuActionPerformed
        // TODO add your handling code here:
        showDataDoanhThu();

    }//GEN-LAST:event_CbbDoanhThuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongKeJDialog dialog = new ThongKeJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbbDoanhThu;
    private javax.swing.JComboBox<KhoaHoc> CbbKhoaHoc;
    private javax.swing.JTable DiemChuyenDeTable;
    private javax.swing.JTable DoanhThuTable;
    private javax.swing.JTable NguoiHocTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblBangDiem;
    // End of variables declaration//GEN-END:variables
}
