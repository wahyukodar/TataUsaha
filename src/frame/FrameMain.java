package frame;

import framecheck.CheckKelas;
import framecheck.CheckLaporanSPP;
import framecheck.CheckPembayaranSPP;
import framecheck.CheckSPP;
import framecheck.CheckSiswa;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/**
 *
 * @author Wahyu
 */
public class FrameMain extends javax.swing.JFrame {
boolean TampilJFrameUser = false, TampilJFrameKelas = false, TampilJFrameSiswa = false,
        TampilJFrameSPP = false, TampilJFramePembayaranSPP = false, 
        TampilJFrameLaporanSPP= false;
private FrameUser frameUser;
private FrameKelas frameKelas;
private FrameSiswa frameSiswa;
private FrameSPP frameSPP;
private FramePembayaranSPP framePembayaranSPP;
private FrameLaporanSPP frameLaporanSPP;
    public FrameMain() {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    
    public JDesktopPane getjDesktopPane(){
        return jDesktopPane1;
    }

    // =================== USER ========================
    public boolean getTampilJFrameUser(){
        return TampilJFrameUser;
    }
    
    public void setTampilJFrameUser(boolean TampilJFrameUser){
        this.TampilJFrameUser = TampilJFrameUser;
    }
    
    public FrameUser getObjectFrameUser(){
        return frameUser;
    }
    
    public void setObjectFrameUser(FrameUser frameUser){
        this.frameUser=frameUser;
    }
    // =================== USER ========================
    
    // =================== KELAS ========================
    public boolean getTampilJFrameKelas(){
        return TampilJFrameKelas;
    }
    
    public void setTampilJFrameKelas(boolean TampilJFrameKelas){
        this.TampilJFrameKelas = TampilJFrameKelas;
    }
    
    public FrameKelas getObjectFrameKelas(){
        return frameKelas;
    }
    
    public void setObjectFrameKelas(FrameKelas frameKelas){
        this.frameKelas=frameKelas;
    }
    // =================== END KELAS =====================
    
    // =================== SISWA ==========================
    public boolean getTampilJFrameSiswa(){
        return TampilJFrameSiswa;
    }
    
    public void setTampilJFrameSiswa(boolean TampilJFrameSiswa){
        this.TampilJFrameSiswa = TampilJFrameSiswa;
    }
    
    public FrameSiswa getObjectFrameSiswa(){
        return frameSiswa;
    }
    
    public void setObjectFrameSiswa(FrameSiswa frameSiswa){
        this.frameSiswa=frameSiswa;
    }
    // =================== END SISWA ========================
    
    // =================== SPP ==============================
    public boolean getTampilJFrameSPP(){
        return TampilJFrameSPP;
    }
    
    public void setTampilJFrameSPP(boolean TampilJFrameSPP){
        this.TampilJFrameSPP = TampilJFrameSPP;
    }
    
    public FrameSPP getObjectFrameSPP(){
        return frameSPP;
    }
    
    public void setObjectFrameSPP(FrameSPP frameSPP){
        this.frameSPP=frameSPP;
    }
    // =================== END SPP ===========================
    
    // =================== PEMBAYARAN SPP ====================
    public boolean getTampilJFramePembayaranSPP(){
        return TampilJFramePembayaranSPP;
    }
    
    public void setTampilJFramePembayaranSPP(boolean TampilJFramePembayaranSPP){
        this.TampilJFramePembayaranSPP = TampilJFramePembayaranSPP;
    }
    
    public FramePembayaranSPP getObjectFramePembayaranSPP(){
        return framePembayaranSPP;
    }
    
    public void setObjectFramePembayaranSPP(FramePembayaranSPP framePembayaranSPP){
        this.framePembayaranSPP=framePembayaranSPP;
    }
    // =================== END PEMBAYARAN SPP =================
    
    // =================== LAPORAN SPP ========================
    public boolean getTampilJFrameLaporanSPP(){
        return TampilJFrameLaporanSPP;
    }
    
    public void setTampilJFrameLaporanSPP(boolean TampilJFrameLaporanSPP){
        this.TampilJFrameLaporanSPP = TampilJFrameLaporanSPP;
    }
    
    public FrameLaporanSPP getObjectFrameLaporanSPP(){
        return frameLaporanSPP;
    }
    
    public void setObjectFrameLaporanSPP(FrameLaporanSPP frameLaporanSPP){
        this.frameLaporanSPP=frameLaporanSPP;
    }
    // =================== END LAPORAN SPP =====================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuKelas = new javax.swing.JMenuItem();
        menuSiswa = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuSPP = new javax.swing.JMenuItem();
        menuPembayaranSPP = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuLaporanSPP = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Tata Usaha Pembayaran dan Rekap Pembayaran SPP SDIT DAARUL FIKRI DEPOK");

        jDesktopPane1.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Created by Wahyu Kodar");

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Aplikasi Tata Usaha CopyRight 2014");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Data Siswa");

        menuKelas.setText("Kelas");
        menuKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKelasActionPerformed(evt);
            }
        });
        jMenu1.add(menuKelas);

        menuSiswa.setText("Siswa");
        menuSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSiswaActionPerformed(evt);
            }
        });
        jMenu1.add(menuSiswa);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Data SPP");

        menuSPP.setText("SPP");
        menuSPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSPPActionPerformed(evt);
            }
        });
        jMenu2.add(menuSPP);

        menuPembayaranSPP.setText("Pembayaran SPP");
        menuPembayaranSPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPembayaranSPPActionPerformed(evt);
            }
        });
        jMenu2.add(menuPembayaranSPP);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Laporan");

        menuLaporanSPP.setText("Laporan SPP");
        menuLaporanSPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLaporanSPPActionPerformed(evt);
            }
        });
        jMenu3.add(menuLaporanSPP);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Keluar");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKelasActionPerformed
        // TODO add your handling code here:
        new CheckKelas(this);
    }//GEN-LAST:event_menuKelasActionPerformed

    private void menuSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSiswaActionPerformed
        // TODO add your handling code here:
        new CheckSiswa(this);
    }//GEN-LAST:event_menuSiswaActionPerformed

    private void menuSPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSPPActionPerformed
        // TODO add your handling code here:
        new CheckSPP(this);
    }//GEN-LAST:event_menuSPPActionPerformed

    private void menuPembayaranSPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPembayaranSPPActionPerformed
        // TODO add your handling code here:
        new CheckPembayaranSPP(this);
    }//GEN-LAST:event_menuPembayaranSPPActionPerformed

    private void menuLaporanSPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLaporanSPPActionPerformed
        // TODO add your handling code here:
        new CheckLaporanSPP(this);
    }//GEN-LAST:event_menuLaporanSPPActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new FrameLogin(null,true).setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem menuKelas;
    private javax.swing.JMenuItem menuLaporanSPP;
    private javax.swing.JMenuItem menuPembayaranSPP;
    private javax.swing.JMenuItem menuSPP;
    private javax.swing.JMenuItem menuSiswa;
    // End of variables declaration//GEN-END:variables
}
