/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frame;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import report.Pembayaran;
import report.ReportLaporanPembayaran;
import tools.ComboItem;
import tools.MysqlConnect;

/**
 *
 * @author Wahyu
 */
public class FramePembayaranSPP extends javax.swing.JInternalFrame {
FrameMain frameMain;
// Inisialisasi Tabel
private Object [][] dataPembayaran = null;
private final String [] titleTable = {"No","NIS","Nama","Kelas","Tanggal Pembayaran","Pembayaran untuk Bulan"};
// Inisialisasi Database
MysqlConnect mysqlConnect = new MysqlConnect();
ResultSet rs;
Statement st;
// inisialisasi data
private String nis=null,tgl_pembayaran="",pembayaran_bulan="",bulan_pembayaran_full="";
private int kelas=0,id_pembayaran=0;
// inisialisasi validasi
private boolean formkeisisemua = false;
// inisialisasi combobox vector
Vector Kelasmodel = new Vector();  
Vector Siswamodel = new Vector(); 
    public FramePembayaranSPP(FrameMain frameMain) {
        this.frameMain = frameMain;
        setVisible(true);
        mysqlConnect.createDatabase(); // Buka Database
        try{
        st = mysqlConnect.conn.createStatement();
        }
        catch(SQLException ex){}
        initComponents();
        Edit.setEnabled(false);
        Delete.setEnabled(false);
        Print.setEnabled(false);
        cSiswa.setVisible(false);
        JKelas();
        JTanggal();
        getAllPembayaran();
    }
    
    private void JTanggal(){
        cBulan.addItem("Bulan");
        cTahun.addItem("Tahun");
        for(int bln=1;bln<=12;bln++){
            if(bln==1){cBulan.addItem("Januari");}
            else if(bln==2){cBulan.addItem("Februari");}
            else if(bln==3){cBulan.addItem("Maret");}
            else if(bln==4){cBulan.addItem("April");}
            else if(bln==5){cBulan.addItem("Mei");}
            else if(bln==6){cBulan.addItem("Juni");}
            else if(bln==7){cBulan.addItem("Juli");}
            else if(bln==8){cBulan.addItem("Agustus");}
            else if(bln==9){cBulan.addItem("September");}
            else if(bln==10){cBulan.addItem("Oktober");}
            else if(bln==11){cBulan.addItem("November");}
            else if(bln==12){cBulan.addItem("Desember");}
        }
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        for(int tgl=(year-2);tgl<=(year+2);tgl++){
        cTahun.addItem(tgl);
        }
    }
    
    private void JKelas(){
       try{
       String sql ="select * from kelas order by CAST(nama_kelas as SIGNED INTEGER) ASC";
       rs = st.executeQuery(sql); // meload query

       rs.beforeFirst();
       Kelasmodel.addElement(new ComboItem(0,"Pilih Kelas"));
       while(rs.next()){
           Kelasmodel.addElement(new ComboItem(rs.getInt("id_kelas"),rs.getString("nama_kelas")));
       }
         cKelas.setModel(new javax.swing.DefaultComboBoxModel(Kelasmodel));
       }
       catch(SQLException ex){}
    } 
    
    private void JSiswa(){
       ComboItem itemKelas = (ComboItem)cKelas.getSelectedItem();
       kelas = (int)itemKelas.getId();
       cSiswa.removeAllItems();
       try{
       String sql ="select * from siswa where id_kelas='"+kelas+"' order by nama_siswa ASC";
       rs = st.executeQuery(sql); // meload query

       rs.beforeFirst();
       Siswamodel.addElement(new ComboItem(0,"Pilih Siswa"));
       while(rs.next()){
       Siswamodel.addElement(new ComboItem(rs.getString("nis"),rs.getString("nama_siswa")));
       }
       cSiswa.setModel(new javax.swing.DefaultComboBoxModel(Siswamodel));
       }
       catch(SQLException ex){}
    } 
    
    private void JSiswaEdit(){
       ComboItem itemKelas = (ComboItem)cKelas.getSelectedItem();
       kelas = (int)itemKelas.getId();
       cSiswa.removeAllItems();
       try{
       String sqls ="select * from siswa where id_kelas='"+kelas+"' AND nis='"+nis+"' order by nama_siswa ASC";
       rs = st.executeQuery(sqls); // meload query
       //rs.beforeFirst();
       while(rs.next()){
         Siswamodel.addElement(new ComboItem(rs.getString("nis"),rs.getString("nama_siswa")));
       }
       
       String sql ="select * from siswa where id_kelas="+kelas+" AND not nis='"+nis+"' order by nama_siswa ASC";
       rs = st.executeQuery(sql); // meload query

       rs.beforeFirst();
       while(rs.next()){
         Siswamodel.addElement(new ComboItem(rs.getString("nis"),rs.getString("nama_siswa")));
       }
           cSiswa.setModel(new javax.swing.DefaultComboBoxModel(Siswamodel));
       }
       catch(SQLException ex){} 
    } 
    
    
    private void getAllPembayaran(){
          try{
       String sql ="select id_pembayaran,nama_kelas,nama_siswa,pembayaran_untuk,tgl_pembayaran,siswa.nis=pembayaran.nis,siswa.id_kelas=pembayaran.id_kelas,siswa.id_kelas=kelas.id_kelas,siswa.id_kelas,siswa.nis from siswa,pembayaran,kelas where siswa.id_kelas=pembayaran.id_kelas AND siswa.id_kelas=kelas.id_kelas AND siswa.nis=pembayaran.nis";
       rs = st.executeQuery(sql); // meload query
       
       ResultSetMetaData md = rs.getMetaData();
       //int kolom = md.getColumnCount();
       int kolom = 8;
       //kolom+=2; // jumlah kolom ditambahkan untuk menambahkan dataPembayaran[x][5]
       int baris = 0;
       while(rs.next()){
           baris = rs.getRow(); // dapatkan jumlah datanya.
       }
       dataPembayaran = new Object[baris][kolom]; // Buat objek sebanyak jumlah data
       
       // mulai memasukan data pada objek
       int x = 0;
       rs.beforeFirst();
       while(rs.next()){
           dataPembayaran[x][0] = x+1; // untuk nomor
           dataPembayaran[x][1] = rs.getString("nis");
           dataPembayaran[x][2] = rs.getString("nama_siswa");
           dataPembayaran[x][3] = rs.getString("nama_kelas");
           dataPembayaran[x][4] = rs.getString("tgl_pembayaran");
           dataPembayaran[x][5] = rs.getString("pembayaran_untuk");
           dataPembayaran[x][6] = rs.getInt("id_pembayaran");
           dataPembayaran[x][7] = rs.getInt("id_kelas");
           x++;
       }
       jTable1.setModel(new DefaultTableModel(dataPembayaran,titleTable));
       }
       catch(SQLException ex){} 
    }

    private void getDataToForm(){
       int row = jTable1.getSelectedRow();
       id_pembayaran = (int)dataPembayaran[row][6];
       nis = (String)jTable1.getValueAt(row, 1);
       bulan_pembayaran_full = jTable1.getValueAt(row, 5).toString();
       String [] pembayaran_untuk = jTable1.getValueAt(row, 5).toString().split("-");
       int tahun = Integer.parseInt(pembayaran_untuk[0]);
       String bulan_pembayaran = pembayaran_untuk[1];
       String bulan="";
       if(bulan_pembayaran.equals("01")){bulan="Januari";}else if(bulan_pembayaran.equals("02")){bulan="Februari";}
       else if(bulan_pembayaran.equals("03")){bulan="Maret";}else if(bulan_pembayaran.equals("04")){bulan="April";}
       else if(bulan_pembayaran.equals("05")){bulan="Mei";}else if(bulan_pembayaran.equals("06")){bulan="Juni";}
       else if(bulan_pembayaran.equals("07")){bulan="Juli";}else if(bulan_pembayaran.equals("08")){bulan="Agustus";}
       else if(bulan_pembayaran.equals("09")){bulan="September";}else if(bulan_pembayaran.equals("10")){bulan="Oktober";}
       else if(bulan_pembayaran.equals("11")){bulan="November";}else if(bulan_pembayaran.equals("12")){bulan="Desember";}
       cBulan.setSelectedItem((String) bulan);
       cTahun.setSelectedItem((int) tahun);
      
       try{  
       int id_kelas =(int)dataPembayaran[row][7]; 
       String pilihan="";
       String sqls ="select * from kelas where id_kelas="+id_kelas+"";
       rs = st.executeQuery(sqls); // meload query
       //rs.beforeFirst();
       while(rs.next()){
        pilihan = rs.getString("nama_kelas");
       }
       setSelectedValue(cKelas,pilihan);
       }
       catch(SQLException ex){}
       cSiswa.setVisible(true);
       cSiswa.setEnabled(true);
       JSiswaEdit();
     }
     
     
     
     private void clear(){
        nis="";
        Save.setEnabled(true);
        Edit.setEnabled(false);
        Delete.setEnabled(false);
        Print.setEnabled(false);
        cBulan.removeAllItems();
        cTahun.removeAllItems();
        cSiswa.removeAllItems();
        setSelectedValue(cKelas,"Pilih Kelas");
        cSiswa.setVisible(false);
        cSiswa.setEnabled(false);
        JTanggal();
     }
     
   private void setSelectedValue(JComboBox comboBox, String value){
       ComboItem item;
       for(int i=0;i<comboBox.getItemCount();i++){
           item=(ComboItem)comboBox.getItemAt(i);
           if(item.getDescription().equals(value)){
             comboBox.setSelectedIndex(i);
             break;
           }
       }
   }
    
   private void cekTextField(){    
       
       ComboItem itemKelas = (ComboItem)cKelas.getSelectedItem();
       kelas = (int)itemKelas.getId();
       
       int tahun;

       String bulan = (String) cBulan.getSelectedItem();
       String bln="";
       if(bulan.equals("Januari")){bln="01";}else if(bulan.equals("Februari")){bln="02";}
       else if(bulan.equals("Maret")){bln="03";}else if(bulan.equals("April")){bln="04";}
       else if(bulan.equals("Mei")){bln="05";}else if(bulan.equals("Juni")){bln="06";}
       else if(bulan.equals("Juli")){bln="07";}else if(bulan.equals("Agustus")){bln="08";}
       else if(bulan.equals("September")){bln="09";}else if(bulan.equals("Oktober")){bln="10";}
       else if(bulan.equals("November")){bln="11";}else if(bulan.equals("Desember")){bln="12";}

       if(kelas==0){
           JOptionPane.showMessageDialog(null,"Anda Belum pilih kelas");
           cKelas.requestFocus();
       }
       else if(cSiswa.getSelectedItem().toString().equals("Pilih Siswa")){
           JOptionPane.showMessageDialog(null,"Anda Belum pilih siswa");
           cSiswa.requestFocus();
       }
       else if(bulan.equals("Bulan")){
           JOptionPane.showMessageDialog(null,"Anda Belum pilih bulan pembayaran");
           cBulan.requestFocus();
       }
       else if(cTahun.getSelectedItem().equals("Tahun")){
       JOptionPane.showMessageDialog(null,"Anda Belum pilih tahun pembayaran");
       cTahun.requestFocus();
       }
       else{
               ComboItem itemSiswa= (ComboItem)cSiswa.getSelectedItem();
               nis = (String)itemSiswa.getDescriptions();
               tahun = (int) cTahun.getSelectedItem(); 
               pembayaran_bulan = tahun+"-"+bln+"-"+01;
               formkeisisemua=true;
        }
     }
   
   
     private void delete(){
       try{
       String sql = "DELETE from pembayaran WHERE id_pembayaran='"+id_pembayaran+"'";
       st.executeUpdate(sql);
       getAllPembayaran();
       clear();
       }
       catch(SQLException ex){}
     }
    
     private void edit(){
       cekTextField();
       if(formkeisisemua==true){
           try{
           Calendar now = Calendar.getInstance();
           int year = now.get(Calendar.YEAR);
           int month= now.get(Calendar.MONTH)+1;
           int day = now.get(Calendar.DAY_OF_MONTH);
           tgl_pembayaran=year+"-"+month+"-"+day;
           String sql = "UPDATE pembayaran SET nis='"+nis+"', id_kelas='"+kelas+"', tgl_pembayaran='"+tgl_pembayaran+"',"
                   + " pembayaran_untuk='"+pembayaran_bulan+"' "
                   + "WHERE id_pembayaran='"+id_pembayaran+"'";
           st.executeUpdate(sql);
           getAllPembayaran();
           clear();
           formkeisisemua=false;
           }
           catch(SQLException ex){}
       }
     }
   
     private void save(){
       cekTextField();
       if(formkeisisemua==true){
           try{
           Calendar now = Calendar.getInstance();
           int year = now.get(Calendar.YEAR);
           int month= now.get(Calendar.MONTH)+1;
           int day = now.get(Calendar.DAY_OF_MONTH);
           tgl_pembayaran=year+"-"+month+"-"+day;
           String sql = "INSERT INTO pembayaran (nis,id_kelas,pembayaran_untuk,tgl_pembayaran) "
                   + "VALUES ('"+nis+"','"+kelas+"','"+pembayaran_bulan+"','"+tgl_pembayaran+"')";
           st.executeUpdate(sql);
           getAllPembayaran();
           clear();
           formkeisisemua=false;
           }
           catch(SQLException ex){}
       }
     }
     
     private void print(){
         new Pembayaran(nis,bulan_pembayaran_full);
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        New = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Print = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cKelas = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cSiswa = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cBulan = new javax.swing.JComboBox();
        cTahun = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pembayaran SPP");
        setToolTipText("");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        New.setText("New");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Print.setText("Print");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 20, 10));

        jLabel1.setText("Pilih Kelas");
        jPanel2.add(jLabel1);

        cKelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cKelasItemStateChanged(evt);
            }
        });
        jPanel2.add(cKelas);

        jLabel2.setText("Pilih Siswa");
        jPanel2.add(jLabel2);

        jPanel2.add(cSiswa);

        jLabel3.setText("Pembayaran Untuk");
        jPanel2.add(jLabel3);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jPanel3.add(cBulan);

        jPanel3.add(cTahun);

        jPanel2.add(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(New, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Print, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(New)
                    .addComponent(Save)
                    .addComponent(Edit)
                    .addComponent(Delete)
                    .addComponent(Print))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        frameMain.setTampilJFramePembayaranSPP(false);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        getDataToForm();
        Save.setEnabled(false);
        Edit.setEnabled(true);
        Delete.setEnabled(true);
        Print.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_NewActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_SaveActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        // TODO add your handling code here:
        edit();
    }//GEN-LAST:event_EditActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_DeleteActionPerformed

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:
        print();
    }//GEN-LAST:event_PrintActionPerformed

    private void cKelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cKelasItemStateChanged
        // TODO add your handling code here:
          JSiswa();
          JComboBox comboBox = (JComboBox)evt.getSource();  
          ComboItem item; 
          item = (ComboItem)comboBox.getSelectedItem();

           if(item.getId()==0){
           cSiswa.setEnabled(false);   
           cSiswa.setVisible(false); 
           }
           else{
           cSiswa.setVisible(true);
           cSiswa.setEnabled(true);
           }
           
    }//GEN-LAST:event_cKelasItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton Edit;
    private javax.swing.JButton New;
    private javax.swing.JButton Print;
    private javax.swing.JButton Save;
    private javax.swing.JComboBox cBulan;
    private javax.swing.JComboBox cKelas;
    private javax.swing.JComboBox cSiswa;
    private javax.swing.JComboBox cTahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
