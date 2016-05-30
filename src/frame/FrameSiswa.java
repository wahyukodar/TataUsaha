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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tools.ComboItem;
import tools.MysqlConnect;

/**
 *
 * @author Wahyu
 */
public class FrameSiswa extends javax.swing.JInternalFrame {
FrameMain frameMain;
// Inisialisasi Tabel
private Object [][] dataSiswa = null;
private final String [] titleTable = {"No","NIS","Nama","Kelas","TTL","Jenis Kelamin","Biaya SPP Perbulan","Alamat"};
// Inisialisasi Database
MysqlConnect mysqlConnect = new MysqlConnect();
ResultSet rs;
Statement st;
// inisialisasi data
private String nis="",nama="",tempat_lahir="",tgl_lahir="",jenis_kelamin="",alamat="";
private int kelas=0,biaya=0;
// inisialisasi validasi
private boolean formkeisisemua = false;
// inisialisasi combobox vector
Vector Kelasmodel = new Vector();  
Vector SPPmodel = new Vector(); 
    public FrameSiswa(FrameMain frameMain) {
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
        JKelas();
        JSPP();
        JJenisKelamin();
        JTTL();
        getAllSiswa();
        //setTableView();
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
    
    private void JSPP(){
     try{
       String sql ="select * from spp order by biaya ASC";
       rs = st.executeQuery(sql); // meload query

       rs.beforeFirst();
       SPPmodel.addElement(new ComboItem(0,"Pilih Biaya SPP Perbulan"));
       while(rs.next()){
           SPPmodel.addElement(new ComboItem(rs.getInt("id_spp"),rs.getString("biaya")));
       }
         cSPP.setModel(new javax.swing.DefaultComboBoxModel(SPPmodel));
       }
       catch(SQLException ex){}
    } 
    
    private void JJenisKelamin(){
        cJenisKelamin.addItem("Pilih Jenis Kelamin");
        cJenisKelamin.addItem("Laki-laki");
        cJenisKelamin.addItem("Perempuan");
    }
    
    private void JTTL(){
        cTanggalLahir.addItem("Tanggal");
        cBulanLahir.addItem("Bulan");
        cTahunLahir.addItem("Tahun");
        for(int tgl=1;tgl<=31;tgl++){
        cTanggalLahir.addItem(tgl);
        }
        for(int bln=1;bln<=12;bln++){
            if(bln==1){cBulanLahir.addItem("Januari");}
            else if(bln==2){cBulanLahir.addItem("Februari");}
            else if(bln==3){cBulanLahir.addItem("Maret");}
            else if(bln==4){cBulanLahir.addItem("April");}
            else if(bln==5){cBulanLahir.addItem("Mei");}
            else if(bln==6){cBulanLahir.addItem("Juni");}
            else if(bln==7){cBulanLahir.addItem("Juli");}
            else if(bln==8){cBulanLahir.addItem("Agustus");}
            else if(bln==9){cBulanLahir.addItem("September");}
            else if(bln==10){cBulanLahir.addItem("Oktober");}
            else if(bln==11){cBulanLahir.addItem("November");}
            else if(bln==12){cBulanLahir.addItem("Desember");}
        }
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        for(int tgl=1990;tgl<=year;tgl++){
        cTahunLahir.addItem(tgl);
        }
    }

     private  final void setTableView(){
        int[] width={40,100,100,100,100,100,100,100};
        for(int i=0;i<titleTable.length;i++){
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
        }
     }
    
     private void getAllSiswa(){
       try{
       String sql ="select biaya,nama_kelas,nis,nama_siswa,tempat_lahir,"
               + "tgl_lahir,jenis_kelamin,alamat,"
               + "siswa.id_kelas=kelas.id_kelas,siswa.id_spp=spp.id_spp,siswa.id_kelas,siswa.id_spp "
               + "from siswa,kelas,spp "
               + "where siswa.id_kelas=kelas.id_kelas "
               + "AND siswa.id_spp=spp.id_spp "
               + "order by nama_kelas ASC, nama_siswa ASC";
       rs = st.executeQuery(sql); // meload query
       
       ResultSetMetaData md = rs.getMetaData();
       int kolom = md.getColumnCount();
       //kolom+=1; // jumlah kolom ditambahkan untuk menambahkan dataSiswa[x][2]
       int baris = 0;
       while(rs.next()){
           baris = rs.getRow(); // dapatkan jumlah datanya.
       }
       dataSiswa = new Object[baris][kolom]; // Buat objek sebanyak jumlah data
       
       // mulai memasukan data pada objek
       int x = 0;
       rs.beforeFirst();
       while(rs.next()){
           dataSiswa[x][0] = x+1; // untuk nomor
           dataSiswa[x][1] = rs.getString("nis");
           dataSiswa[x][2] = rs.getString("nama_siswa");
           dataSiswa[x][3] = rs.getString("nama_kelas");
           dataSiswa[x][4] = rs.getString("tempat_lahir")+", "+rs.getString("tgl_lahir");
           dataSiswa[x][5] = rs.getString("jenis_kelamin");
           dataSiswa[x][6] = rs.getString("biaya");
           dataSiswa[x][7] = rs.getString("alamat");
           dataSiswa[x][8] = rs.getInt("id_kelas");
           dataSiswa[x][9] = rs.getInt("id_spp");
           //dataSiswa[x][2] = rs.getInt("id_spp"); // tidak dimunculkan pada tabel, tapi ditransfer ke int id_spp
           x++;
       }
       jTable1.setModel(new DefaultTableModel(dataSiswa,titleTable));
       }
       catch(SQLException ex){} 
     }
     
     
     
     private void getDataToForm(){
       int row = jTable1.getSelectedRow();
       nis = (String)jTable1.getValueAt(row, 1);
       String [] tempatlahir = jTable1.getValueAt(row, 4).toString().split(",");
       String tempat_lahir = tempatlahir[0]; // misal Bogor
       String ttl_lengkap = tempatlahir[1]; // misal 1990-02-31
       String [] pecah_ttl = ttl_lengkap.split("-");
       String tahun_ttl_ada_spasi = pecah_ttl[0];
       String [] pecah_tahun_ttl_ada_spasi = tahun_ttl_ada_spasi.split(" ");
       int tahun_ttl = Integer.parseInt(pecah_tahun_ttl_ada_spasi[1]);
       String bulan_ttl = pecah_ttl[1];
       String bulan_lahir="";
       if(bulan_ttl.equals("01")){bulan_lahir="Januari";}else if(bulan_ttl.equals("02")){bulan_lahir="Februari";}
       else if(bulan_ttl.equals("03")){bulan_lahir="Maret";}else if(bulan_ttl.equals("04")){bulan_lahir="April";}
       else if(bulan_ttl.equals("05")){bulan_lahir="Mei";}else if(bulan_ttl.equals("06")){bulan_lahir="Juni";}
       else if(bulan_ttl.equals("07")){bulan_lahir="Juli";}else if(bulan_ttl.equals("08")){bulan_lahir="Agustus";}
       else if(bulan_ttl.equals("09")){bulan_lahir="September";}else if(bulan_ttl.equals("10")){bulan_lahir="Oktober";}
       else if(bulan_ttl.equals("11")){bulan_lahir="November";}else if(bulan_ttl.equals("12")){bulan_lahir="Desember";}
       int tanggal_ttl = Integer.parseInt(pecah_ttl[2]);
       jNis.setText((String)jTable1.getValueAt(row, 1));     
       jNama.setText((String)jTable1.getValueAt(row, 2));  
       jTempatLahir.setText(tempat_lahir);
       jAlamat.setText((String)jTable1.getValueAt(row, 7));
       cJenisKelamin.setSelectedItem((String)jTable1.getValueAt(row, 5));
       cTanggalLahir.setSelectedIndex(tanggal_ttl);
       cBulanLahir.setSelectedItem((String) bulan_lahir);
       cTahunLahir.setSelectedItem((int) tahun_ttl);
       
       Kelasmodel.removeAllElements();
       try{  
       int id_kelas =(int)dataSiswa[row][8]; 
       String sqls ="select * from kelas where id_kelas="+id_kelas+"";
       rs = st.executeQuery(sqls); // meload query
       //rs.beforeFirst();
       while(rs.next()){
           Kelasmodel.addElement(new ComboItem(rs.getInt("id_kelas"),rs.getString("nama_kelas")));
       }
       
       String sql ="select * from kelas where not id_kelas="+id_kelas+" order by CAST(nama_kelas as SIGNED INTEGER) ASC";
       rs = st.executeQuery(sql); // meload query

       rs.beforeFirst();
       while(rs.next()){
           Kelasmodel.addElement(new ComboItem(rs.getInt("id_kelas"),rs.getString("nama_kelas")));
       }
           cKelas.setModel(new javax.swing.DefaultComboBoxModel(Kelasmodel));
       }
       catch(SQLException ex){} 
       
       SPPmodel.removeAllElements();
       try{  
       int id_spp =(int)dataSiswa[row][9]; 
       String sqls ="select * from spp where id_spp="+id_spp+"";
       rs = st.executeQuery(sqls); // meload query
       //rs.beforeFirst();
       while(rs.next()){
           SPPmodel.addElement(new ComboItem(rs.getInt("id_spp"),rs.getString("biaya")));
       }
       
       String sql ="select * from spp where not id_spp="+id_spp+" order by biaya ASC";
       rs = st.executeQuery(sql); // meload query

       rs.beforeFirst();
       while(rs.next()){
           SPPmodel.addElement(new ComboItem(rs.getInt("id_spp"),rs.getString("biaya")));
       }
           cSPP.setModel(new javax.swing.DefaultComboBoxModel(SPPmodel));
       }
       catch(SQLException ex){} 
     }
     
     
     
     private void clear(){
        jNis.setText(null);
        jNama.setText(null);
        jTempatLahir.setText(null);
        jAlamat.setText(null);
        nis="";
        Save.setEnabled(true);
        Edit.setEnabled(false);
        Delete.setEnabled(false);
        cJenisKelamin.removeAllItems();
        cTanggalLahir.removeAllItems();
        cBulanLahir.removeAllItems();
        cTahunLahir.removeAllItems();
        cKelas.removeAllItems();
        cSPP.removeAllItems();
        JKelas();
        JSPP();
        JJenisKelamin();
        JTTL();
     }
   
  
   
     private void cekTextField(){
       nis = jNis.getText().toString();  
       nama = jNama.getText().toString();
       tempat_lahir = jTempatLahir.getText().toString();
       alamat = jAlamat.getText().toString();    
       
       ComboItem itemKelas = (ComboItem)cKelas.getSelectedItem();
       kelas = (int)itemKelas.getId();
       ComboItem itemSPP = (ComboItem)cSPP.getSelectedItem();
       biaya = (int)itemSPP.getId();
       jenis_kelamin = (String) cJenisKelamin.getSelectedItem();
       int tanggal_lahir;
       int tahun_lahir;
       
       
       
      
       String bulan_lahir = (String) cBulanLahir.getSelectedItem();
       String bln="";
       if(bulan_lahir.equals("Januari")){bln="01";}else if(bulan_lahir.equals("Februari")){bln="02";}
       else if(bulan_lahir.equals("Maret")){bln="03";}else if(bulan_lahir.equals("April")){bln="04";}
       else if(bulan_lahir.equals("Mei")){bln="05";}else if(bulan_lahir.equals("Juni")){bln="06";}
       else if(bulan_lahir.equals("Juli")){bln="07";}else if(bulan_lahir.equals("Agustus")){bln="08";}
       else if(bulan_lahir.equals("September")){bln="09";}else if(bulan_lahir.equals("Oktober")){bln="10";}
       else if(bulan_lahir.equals("November")){bln="11";}else if(bulan_lahir.equals("Desember")){bln="12";}
       
       
       
       if(nis.isEmpty()){
           JOptionPane.showMessageDialog(null,"NIS masih kosong");
           jNis.requestFocus();
       }
       else if(kelas==0){
           JOptionPane.showMessageDialog(null,"Anda Belum pilih kelas");
           cKelas.requestFocus();
       }
       else if(biaya==0){
           JOptionPane.showMessageDialog(null,"Anda Belum pilih biaya SPP perbulan");
           cSPP.requestFocus();
       }
       else if(jenis_kelamin.equals("Pilih Jenis Kelamin")){
           JOptionPane.showMessageDialog(null,"Anda Belum pilih jenis kelamin");
           cJenisKelamin.requestFocus();
       }
       else if(cTanggalLahir.getSelectedItem().equals("Tanggal")){
       JOptionPane.showMessageDialog(null,"Anda Belum pilih tanggal lahir");
       cTanggalLahir.requestFocus();
       }
       else if(bulan_lahir.equals("Bulan")){
           JOptionPane.showMessageDialog(null,"Anda Belum pilih bulan lahir");
           cBulanLahir.requestFocus();
       }
       else if(cTahunLahir.getSelectedItem().equals("Tahun")){
       JOptionPane.showMessageDialog(null,"Anda Belum pilih tahun lahir");
       cTahunLahir.requestFocus();
       }
       else if(nama.isEmpty()){
           JOptionPane.showMessageDialog(null,"Nama masih kosong");
           jNama.requestFocus();
       }
       else if(tempat_lahir.isEmpty()){
           JOptionPane.showMessageDialog(null,"Tempat Lahir masih kosong");
           jTempatLahir.requestFocus();
       }
       else if(alamat.isEmpty()){
           JOptionPane.showMessageDialog(null,"Alamat masih kosong");
           jAlamat.requestFocus();
       }
       else{
               tanggal_lahir = (int) cTanggalLahir.getSelectedItem();
               tahun_lahir = (int) cTahunLahir.getSelectedItem(); 
               tgl_lahir = tahun_lahir+"-"+bln+"-"+tanggal_lahir;
               formkeisisemua=true;
        }
     }
   
   
     private void delete(){
       try{
       String sql = "DELETE from siswa WHERE nis='"+nis+"'";
       st.executeUpdate(sql);
       getAllSiswa();
       clear();
       }
       catch(SQLException ex){}
     }
    
     private void edit(){
        cekTextField();
       if(formkeisisemua==true){
           try{
           String sql = "UPDATE siswa SET nis='"+nis+"', nama_siswa='"+nama+"', id_kelas='"+kelas+"',"
                   + "id_spp='"+biaya+"', tempat_lahir='"+tempat_lahir+"', tgl_lahir='"+tgl_lahir+"', "
                   + "alamat='"+alamat+"', jenis_kelamin='"+jenis_kelamin+"' "
                   + "WHERE nis='"+nis+"'";
           st.executeUpdate(sql);
           getAllSiswa();
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
           String sql = "INSERT INTO siswa (nis,nama_siswa,id_kelas,id_spp,tempat_lahir,tgl_lahir,alamat,jenis_kelamin) "
                   + "VALUES ('"+nis+"','"+nama+"','"+kelas+"','"+biaya+"','"+tempat_lahir+"','"+tgl_lahir+"','"+alamat+"','"+jenis_kelamin+"')";
           st.executeUpdate(sql);
           getAllSiswa();
           clear();
           formkeisisemua=false;
           }
           catch(SQLException ex){}
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

        jComboBox4 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jNama = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jNis = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cKelas = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cSPP = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jTempatLahir = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cTanggalLahir = new javax.swing.JComboBox();
        cBulanLahir = new javax.swing.JComboBox();
        cTahunLahir = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jAlamat = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        cJenisKelamin = new javax.swing.JComboBox();
        New = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        Delete = new javax.swing.JButton();

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Siswa");
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

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setLayout(new java.awt.GridLayout(4, 8, 20, 8));

        jLabel2.setText("Nama");
        jPanel2.add(jLabel2);
        jPanel2.add(jNama);

        jLabel1.setText("Nis");
        jPanel2.add(jLabel1);
        jPanel2.add(jNis);

        jLabel6.setText("Kelas");
        jPanel2.add(jLabel6);

        jPanel2.add(cKelas);

        jLabel7.setText("Biaya SPP perbulan");
        jPanel2.add(jLabel7);

        jPanel2.add(cSPP);

        jLabel8.setText("Tempat Lahir");
        jPanel2.add(jLabel8);
        jPanel2.add(jTempatLahir);

        jLabel9.setText("Tanggal Lahir");
        jPanel2.add(jLabel9);

        jPanel3.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        jPanel3.add(cTanggalLahir);

        jPanel3.add(cBulanLahir);

        jPanel3.add(cTahunLahir);

        jPanel2.add(jPanel3);

        jLabel10.setText("Alamat");
        jPanel2.add(jLabel10);

        jAlamat.setColumns(20);
        jAlamat.setRows(5);
        jScrollPane2.setViewportView(jAlamat);

        jPanel2.add(jScrollPane2);

        jLabel11.setText("Jenis Kelamin");
        jPanel2.add(jLabel11);

        jPanel2.add(cJenisKelamin);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(New, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(New)
                    .addComponent(Save)
                    .addComponent(Edit)
                    .addComponent(Delete))
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        frameMain.setTampilJFrameSiswa(false);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        getDataToForm();
        Save.setEnabled(false);
        Edit.setEnabled(true);
        Delete.setEnabled(true);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton Edit;
    private javax.swing.JButton New;
    private javax.swing.JButton Save;
    private javax.swing.JComboBox cBulanLahir;
    private javax.swing.JComboBox cJenisKelamin;
    private javax.swing.JComboBox cKelas;
    private javax.swing.JComboBox cSPP;
    private javax.swing.JComboBox cTahunLahir;
    private javax.swing.JComboBox cTanggalLahir;
    private javax.swing.JTextArea jAlamat;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jNama;
    private javax.swing.JTextField jNis;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTempatLahir;
    // End of variables declaration//GEN-END:variables
}
