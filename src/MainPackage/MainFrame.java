/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MainPackage;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mohaned
 */
public class MainFrame extends javax.swing.JFrame {
    
    
    
    
    static User signed_user=null;
    
    public static void setSignedUser(int id,String first_name,String user_name,String password,String email,String type){
        
        switch (type){
             case "Student":
                    signed_user=new Student(id,first_name,user_name,password,email,type);
                    break;
             case "Job Seeker":
                    signed_user=new JobSeeker(id,first_name,user_name,password,email,type);
                    break;
             case "Tourist":
                    signed_user=new Tourist(id,first_name,user_name,password,email,type);
                    break;
             case "Business Person":
                    signed_user=new BusinessPerson(id,first_name,user_name,password,email,type);
                    break;
             case "Admin":
                    signed_user=new Admin(id,first_name,user_name,password,email,type);
                    break;
        }
               
               
        
    }
    
    /**
     * Creates new form Home_frame
     */
     HashMap<String,JLabel> labels_map;
    
    public MainFrame() throws SQLException, IOException {
        
        initComponents();
        
        
        JFrame f = this;
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.jpeg"))))); //sets background
        } catch (IOException e) {
            
        }
        
        
        
        start_pannel.setVisible(true);
        signin_pannel.setVisible(false);
        register_pannel.setVisible(false);
        side_pannel.setVisible(false);
        admin_pannel.setVisible(false);
                
        initAttractions();
        initBusTable();
        initCentersTable();
        initHotelTable();
        initIndustriesTable();
        initJobsTable();
        initLibTable();
        initUniTable();
        
        
        bus_pannel.setVisible(false);
        attractions_pannel.setVisible(false);
        jobs_pannel.setVisible(false);
        library_pannel.setVisible(false);
        uni_pannel.setVisible(false);
        Bcenter_pannel.setVisible(false);
        hotels_pannel.setVisible(false);
        industries_pannel.setVisible(false);
        
      
        
         labels_map = new HashMap<>();
        
        labels_map.put("library", library);
        labels_map.put("uni", uni);
        labels_map.put("bus", bus);
        labels_map.put("hotels", hotels);       
        labels_map.put("industries", industries);
        labels_map.put("jobs", av_jobs);
        labels_map.put("attractions", att_spots);
        labels_map.put("bcenters", bcenters);
        
        
        for (JLabel value : labels_map.values()) {
                value.setVisible(false);
        }
  
        
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs, Vector<String> columnNames) throws SQLException {
        

    ResultSetMetaData metaData = rs.getMetaData();

  
    int columnCount = metaData.getColumnCount();
 

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            vector.add(rs.getObject(columnNames.get(columnIndex)));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}
    
    private void initBusTable() throws SQLException{
        String sql = "SELECT * FROM BusRoute ";
        PreparedStatement st = null;
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        
        Vector<String> columnNames = new Vector<String>();
       
        
        columnNames.add("Source");
        columnNames.add("Destination");
        columnNames.add("Time");
        columnNames.add("Bus_Number");
        
    
       bus_tbl.setModel(buildTableModel(rs,columnNames));
    }
    
    private void initUniTable() throws SQLException{
        
         String sql = "SELECT * FROM University Name ";
        PreparedStatement st = null;
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        
        Vector<String> columnNames = new Vector<String>();
       
        columnNames.add("University_Name");
        columnNames.add("Location");
        columnNames.add("Email");
        
    
       uni_tbl.setModel(buildTableModel(rs,columnNames));
    
    }
     
    private void initLibTable() throws SQLException{
           String sql = "SELECT * FROM Library ";
        PreparedStatement st = null;
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        
        Vector<String> columnNames = new Vector<String>();
       
        columnNames.add("Library_Name");
        columnNames.add("Address");
        columnNames.add("Phone");
        
    
       library_tbl.setModel(buildTableModel(rs,columnNames));
    
    }
        
    private void initHotelTable() throws SQLException {
         String sql = "SELECT * FROM Hotel ";
        PreparedStatement st = null;
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        
        Vector<String> columnNames = new Vector<String>();
       
        columnNames.add("Hotel_name");
        columnNames.add("Price_per_night");
        columnNames.add("Address");
        columnNames.add("Phone");
        
    
        hotel_tbl.setModel(buildTableModel(rs,columnNames));
    
    }
    
    private void initAttractions() throws IOException, SQLException{
     
        BufferedImage img = null;
        String sql = "SELECT * FROM Attraction";
        PreparedStatement st = null;
        
        
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
               
        while(rs.next()){
               JLabel l = new JLabel();
            img = ImageIO.read(new File(rs.getString("Image")));
            Image dimg = img.getScaledInstance(200, 200,Image.SCALE_SMOOTH);

            ImageIcon imageIcon = new ImageIcon(dimg);

            l.setIcon(imageIcon);
            l.setForeground(Color.WHITE);
            l.setFont(new Font("Tahoma", Font.PLAIN, 12));
            l.setText(rs.getString("Attraction_name")+"     "+rs.getString("Description"));
            l.setIconTextGap(30); 
            
            putme.add(l);
            putme.add(Box.createVerticalStrut(50));
        }
        
         
        
      
        
    }
    
    private void initIndustriesTable() throws SQLException{
         String sql = "SELECT * FROM Industry ";
        PreparedStatement st = null;
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        
        Vector<String> columnNames = new Vector<String>();
       
        columnNames.add("Company_Name");
        columnNames.add("Field");
        columnNames.add("Sector");
        columnNames.add("Number_of_Employees");
        columnNames.add("Address");
    
        industries_tbl.setModel(buildTableModel(rs,columnNames));
    }
    
    private void initJobsTable() throws SQLException{
        
         String sql = "SELECT * FROM Job ";
        PreparedStatement st = null;
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        
        Vector<String> columnNames = new Vector<String>();
       
        columnNames.add("Company_Name");
        columnNames.add("Job_Title");
        columnNames.add("Salary");
        columnNames.add("Closing_Date");
        columnNames.add("Recruiters_Email");
    
        jobs_tbl.setModel(buildTableModel(rs,columnNames));
    }
    
    private void initCentersTable() throws SQLException{
        String sql = "SELECT * FROM Business_Center ";
        PreparedStatement st = null;
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        
        Vector<String> columnNames = new Vector<String>();
       
        columnNames.add("Center_Name");
        columnNames.add("Address");
       
    
        bcenter_tbl.setModel(buildTableModel(rs,columnNames));
    }
    
    private  void renderContentPage(){
        
        if(signed_user.getType().equals("Admin")){
            jPanel1.setVisible(false);
            admin_pannel.setVisible(true);
            return;
        }
        
        side_pannel.setVisible(true);
        empty_pannel.setVisible(true);
        
        as_label.setText("Signed in as, "+ signed_user.getUser_name());
        LinkedList<String> contents =  signed_user.showAvailableContent();
        
        
        for(String content : contents){
            labels_map.get(content).setVisible(true);
        }    
    } 
    
    
    public static void printTable(String table_name) throws SQLException{
        String sql = "SELECT * FROM "+table_name;
        PreparedStatement st = null;
        
        
        
        st = getDBconnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnsNumber = rsmd.getColumnCount();
        for(int i=1; i<=columnsNumber ; i++){
            System.out.println(rsmd.getColumnClassName(i));
            System.out.println(rsmd.getColumnName(i));
            
        }

        
        while(rs.next()){
            System.out.println(rs.getString("ID"));
        }
    }

    private static Connection getDBconnection(){
        try{
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
              Connection connection= DriverManager.getConnection("jdbc:ucanaccess://records.accdb");//Establishing Connection
              System.out.println("Connected Successfully");
              return connection;

          }catch(Exception e){
              System.out.println("Error in connection");
              return null;

          } 
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        admin_pannel = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        tables_combo = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        chosen_tbl = new javax.swing.JTable();
        field1 = new javax.swing.JTextField();
        field2 = new javax.swing.JTextField();
        field3 = new javax.swing.JTextField();
        field5 = new javax.swing.JTextField();
        field4 = new javax.swing.JTextField();
        search_btn = new javax.swing.JButton();
        add_btn1 = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        field6 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        msg_lbl = new javax.swing.JTextArea();
        register_pannel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lab9 = new javax.swing.JLabel();
        name_field_reg = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        user_field_reg = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        register_btn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        type_field = new javax.swing.JComboBox<>();
        email_field_reg = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        reg_error_label = new javax.swing.JLabel();
        pass_field_reg = new javax.swing.JPasswordField();
        signin_pannel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lab8 = new javax.swing.JLabel();
        user_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        signin_btn = new javax.swing.JButton();
        sign_error_label = new javax.swing.JLabel();
        password_field = new javax.swing.JPasswordField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        start_pannel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        open_siign_btn = new javax.swing.JButton();
        open_reg_btn = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        empty_pannel = new javax.swing.JPanel();
        attractions_pannel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        putme = new javax.swing.JPanel();
        side_pannel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        hotels = new javax.swing.JLabel();
        bcenters = new javax.swing.JLabel();
        library = new javax.swing.JLabel();
        av_jobs = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        bus = new javax.swing.JLabel();
        industries = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        att_spots = new javax.swing.JLabel();
        uni = new javax.swing.JLabel();
        as_label = new javax.swing.JLabel();
        jobs_pannel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jobs_tbl = new javax.swing.JTable();
        library_pannel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        library_tbl = new javax.swing.JTable();
        uni_pannel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        uni_tbl = new javax.swing.JTable();
        Bcenter_pannel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        bcenter_tbl = new javax.swing.JTable();
        hotels_pannel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        hotel_tbl = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        industries_pannel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        industries_tbl = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        bus_pannel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bus_tbl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Smart City");
        setAlwaysOnTop(true);
        setBackground(java.awt.Color.white);
        setResizable(false);
        setSize(new java.awt.Dimension(661, 585));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });

        admin_pannel.setBackground(new java.awt.Color(41, 49, 56));
        admin_pannel.setToolTipText("admin");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Choose a table to manage:");

        tables_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "BusRoute", "Hotel", "Job", "Library", "University", "Business_Center", "Industry" }));
        tables_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listSelected(evt);
            }
        });

        jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane9.setOpaque(false);

        chosen_tbl.setBackground(new java.awt.Color(51, 51, 51));
        chosen_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chosen_tbl.setForeground(new java.awt.Color(255, 255, 255));
        chosen_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        chosen_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        chosen_tbl.setRowHeight(20);
        chosen_tbl.setSelectionBackground(new java.awt.Color(62, 226, 141));
        chosen_tbl.setShowHorizontalLines(false);
        chosen_tbl.setShowVerticalLines(false);
        jScrollPane9.setViewportView(chosen_tbl);

        field1.setToolTipText("");
        field1.setEnabled(false);
        field1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field1ActionPerformed(evt);
            }
        });

        field2.setEnabled(false);
        field2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field2ActionPerformed(evt);
            }
        });

        field3.setEnabled(false);
        field3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field3ActionPerformed(evt);
            }
        });

        field5.setEnabled(false);
        field5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field5ActionPerformed(evt);
            }
        });

        field4.setEnabled(false);
        field4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field4ActionPerformed(evt);
            }
        });

        search_btn.setBackground(new java.awt.Color(54, 70, 78));
        search_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        search_btn.setForeground(new java.awt.Color(255, 255, 255));
        search_btn.setText("Search");
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        add_btn1.setBackground(new java.awt.Color(54, 70, 78));
        add_btn1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add_btn1.setForeground(new java.awt.Color(255, 255, 255));
        add_btn1.setText("Add");
        add_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btn1ActionPerformed(evt);
            }
        });

        delete_btn.setBackground(new java.awt.Color(54, 70, 78));
        delete_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        delete_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn.setText("Delete");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        update_btn.setBackground(new java.awt.Color(54, 70, 78));
        update_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        update_btn.setForeground(new java.awt.Color(255, 255, 255));
        update_btn.setText("Update");
        update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Admin Mode");

        field6.setEnabled(false);
        field6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field6ActionPerformed(evt);
            }
        });

        msg_lbl.setBackground(new java.awt.Color(41, 49, 56));
        msg_lbl.setColumns(20);
        msg_lbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        msg_lbl.setForeground(new java.awt.Color(255, 255, 255));
        msg_lbl.setRows(5);
        msg_lbl.setBorder(null);
        jScrollPane10.setViewportView(msg_lbl);

        javax.swing.GroupLayout admin_pannelLayout = new javax.swing.GroupLayout(admin_pannel);
        admin_pannel.setLayout(admin_pannelLayout);
        admin_pannelLayout.setHorizontalGroup(
            admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_pannelLayout.createSequentialGroup()
                .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(admin_pannelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(tables_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(admin_pannelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(admin_pannelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10)
                            .addGroup(admin_pannelLayout.createSequentialGroup()
                                .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(field1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(field2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                .addGap(116, 116, 116)
                                .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(admin_pannelLayout.createSequentialGroup()
                                        .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(admin_pannelLayout.createSequentialGroup()
                                        .addComponent(field3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(field5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_pannelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(add_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(admin_pannelLayout.createSequentialGroup()
                    .addGap(286, 286, 286)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(312, Short.MAX_VALUE)))
        );
        admin_pannelLayout.setVerticalGroup(
            admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_pannelLayout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(tables_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(admin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(admin_pannelLayout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(jLabel25)
                    .addContainerGap(575, Short.MAX_VALUE)))
        );

        register_pannel.setBackground(new java.awt.Color(41, 49, 56));
        register_pannel.setToolTipText("Reg pannel");
        register_pannel.setPreferredSize(new java.awt.Dimension(780, 644));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SMART CITY");

        jPanel5.setBackground(new java.awt.Color(54, 70, 78));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        lab9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lab9.setForeground(new java.awt.Color(255, 255, 255));
        lab9.setText("Register");

        name_field_reg.setBackground(new java.awt.Color(153, 153, 153));
        name_field_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_field_regActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Name:");

        user_field_reg.setBackground(new java.awt.Color(153, 153, 153));
        user_field_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_field_regActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("User Name:");

        register_btn.setBackground(new java.awt.Color(54, 70, 78));
        register_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        register_btn.setForeground(new java.awt.Color(255, 255, 255));
        register_btn.setText("Register");
        register_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                register_btnActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("User type:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Password:");

        type_field.setBackground(new java.awt.Color(153, 153, 153));
        type_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Type", "Student", "Job Seeker", "Tourist", "Business Person" }));

        email_field_reg.setBackground(new java.awt.Color(153, 153, 153));
        email_field_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_field_regActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Email:");

        reg_error_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reg_error_label.setForeground(new java.awt.Color(255, 51, 51));

        pass_field_reg.setBackground(new java.awt.Color(153, 153, 153));
        pass_field_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_field_regActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout register_pannelLayout = new javax.swing.GroupLayout(register_pannel);
        register_pannel.setLayout(register_pannelLayout);
        register_pannelLayout.setHorizontalGroup(
            register_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(register_pannelLayout.createSequentialGroup()
                .addGroup(register_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(register_pannelLayout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addGroup(register_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(user_field_reg)
                            .addComponent(name_field_reg)
                            .addComponent(type_field, 0, 220, Short.MAX_VALUE)
                            .addComponent(email_field_reg)
                            .addGroup(register_pannelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(pass_field_reg))
                        .addGap(0, 367, Short.MAX_VALUE))
                    .addGroup(register_pannelLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(reg_error_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(register_pannelLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addGroup(register_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab9, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        register_pannelLayout.setVerticalGroup(
            register_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(register_pannelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(reg_error_label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name_field_reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user_field_reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass_field_reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(type_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email_field_reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        signin_pannel.setBackground(new java.awt.Color(41, 49, 56));
        signin_pannel.setToolTipText("Sign pannel");
        signin_pannel.setPreferredSize(new java.awt.Dimension(780, 644));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SMART CITY");

        jPanel4.setBackground(new java.awt.Color(54, 70, 78));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        lab8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lab8.setForeground(new java.awt.Color(255, 255, 255));
        lab8.setText("Sign in");

        user_field.setBackground(new java.awt.Color(153, 153, 153));
        user_field.setText("admin");
        user_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_fieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");

        signin_btn.setBackground(new java.awt.Color(54, 70, 78));
        signin_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        signin_btn.setForeground(new java.awt.Color(255, 255, 255));
        signin_btn.setText("Sign in");
        signin_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signin_btnActionPerformed(evt);
            }
        });

        sign_error_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sign_error_label.setForeground(new java.awt.Color(255, 51, 51));

        password_field.setBackground(new java.awt.Color(153, 153, 153));
        password_field.setText("admin");

        jTextArea1.setBackground(new java.awt.Color(41, 49, 56));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("*NOTE* login details:\nAdmin:   \tadmin\tadmin\nTourist:   \tuser1\tuser\nStudent:   \tuser2\tuser\nTourist:   \tuser3\tuser\nTourist:   \tuser4\tuser");
        jScrollPane11.setViewportView(jTextArea1);

        javax.swing.GroupLayout signin_pannelLayout = new javax.swing.GroupLayout(signin_pannel);
        signin_pannel.setLayout(signin_pannelLayout);
        signin_pannelLayout.setHorizontalGroup(
            signin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signin_pannelLayout.createSequentialGroup()
                .addGroup(signin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signin_pannelLayout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addGroup(signin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(user_field, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(lab8, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addGroup(signin_pannelLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(signin_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sign_error_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(signin_pannelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        signin_pannelLayout.setVerticalGroup(
            signin_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signin_pannelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(sign_error_label)
                .addGap(18, 18, 18)
                .addComponent(lab8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(user_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(signin_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        start_pannel.setBackground(new java.awt.Color(41, 49, 56));
        start_pannel.setToolTipText("start pannel");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("SMART CITY");

        jPanel6.setBackground(new java.awt.Color(54, 70, 78));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Your comprehensive city guide. ");

        open_siign_btn.setBackground(new java.awt.Color(54, 70, 78));
        open_siign_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        open_siign_btn.setForeground(new java.awt.Color(255, 255, 255));
        open_siign_btn.setText("Sign in");
        open_siign_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open_siign_btnActionPerformed(evt);
            }
        });

        open_reg_btn.setBackground(new java.awt.Color(54, 70, 78));
        open_reg_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        open_reg_btn.setForeground(new java.awt.Color(255, 255, 255));
        open_reg_btn.setText("Register");
        open_reg_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open_reg_btnActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("New to smart city?");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Already a user?");

        javax.swing.GroupLayout start_pannelLayout = new javax.swing.GroupLayout(start_pannel);
        start_pannel.setLayout(start_pannelLayout);
        start_pannelLayout.setHorizontalGroup(
            start_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(start_pannelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(open_siign_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addComponent(open_reg_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
            .addGroup(start_pannelLayout.createSequentialGroup()
                .addGroup(start_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(start_pannelLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addGroup(start_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(start_pannelLayout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, start_pannelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(118, 118, 118))
            .addGroup(start_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(start_pannelLayout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addComponent(jLabel14)
                    .addContainerGap(581, Short.MAX_VALUE)))
        );
        start_pannelLayout.setVerticalGroup(
            start_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(start_pannelLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(start_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(open_siign_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(open_reg_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(145, 145, 145))
            .addGroup(start_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, start_pannelLayout.createSequentialGroup()
                    .addContainerGap(465, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(187, 187, 187)))
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setToolTipText("pannel text");

        empty_pannel.setBackground(new java.awt.Color(51, 51, 51));
        empty_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        javax.swing.GroupLayout empty_pannelLayout = new javax.swing.GroupLayout(empty_pannel);
        empty_pannel.setLayout(empty_pannelLayout);
        empty_pannelLayout.setHorizontalGroup(
            empty_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        empty_pannelLayout.setVerticalGroup(
            empty_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
        );

        attractions_pannel.setBackground(new java.awt.Color(51, 51, 51));
        attractions_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Attraction Spots");

        jScrollPane3.setBackground(new java.awt.Color(51, 51, 51));

        putme.setBackground(new java.awt.Color(51, 51, 51));
        putme.setLayout(new javax.swing.BoxLayout(putme, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane3.setViewportView(putme);

        javax.swing.GroupLayout attractions_pannelLayout = new javax.swing.GroupLayout(attractions_pannel);
        attractions_pannel.setLayout(attractions_pannelLayout);
        attractions_pannelLayout.setHorizontalGroup(
            attractions_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
            .addGroup(attractions_pannelLayout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );
        attractions_pannelLayout.setVerticalGroup(
            attractions_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attractions_pannelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel19)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
        );

        side_pannel.setBackground(new java.awt.Color(54, 70, 78));
        side_pannel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(54, 70, 78));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hotels.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        hotels.setForeground(new java.awt.Color(255, 255, 255));
        hotels.setText("Hotels");
        hotels.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hotelsMouseClicked(evt);
            }
        });
        jPanel8.add(hotels, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 20));

        bcenters.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        bcenters.setForeground(new java.awt.Color(255, 255, 255));
        bcenters.setText("Business Centers");
        bcenters.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bcentersMouseClicked(evt);
            }
        });
        jPanel8.add(bcenters, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 20));

        library.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        library.setForeground(new java.awt.Color(255, 255, 255));
        library.setText("Libraries");
        library.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libraryMouseClicked(evt);
            }
        });
        jPanel8.add(library, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 20));

        av_jobs.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        av_jobs.setForeground(new java.awt.Color(255, 255, 255));
        av_jobs.setText("Available Jobs");
        av_jobs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                av_jobsMouseClicked(evt);
            }
        });
        jPanel8.add(av_jobs, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 20));

        side_pannel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        jPanel10.setBackground(new java.awt.Color(54, 70, 78));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bus.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        bus.setForeground(new java.awt.Color(255, 255, 255));
        bus.setText(" Bus Routes");
        bus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                busMouseClicked(evt);
            }
        });
        jPanel10.add(bus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 20));

        industries.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        industries.setForeground(new java.awt.Color(255, 255, 255));
        industries.setText("Industries");
        industries.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                industriesMouseClicked(evt);
            }
        });
        jPanel10.add(industries, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 20));

        side_pannel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 230, 40));

        jPanel12.setBackground(new java.awt.Color(54, 70, 78));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        att_spots.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        att_spots.setForeground(new java.awt.Color(255, 255, 255));
        att_spots.setText("Attraction Spots");
        att_spots.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                att_spotsMouseClicked(evt);
            }
        });
        jPanel12.add(att_spots, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 20));

        uni.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        uni.setForeground(new java.awt.Color(255, 255, 255));
        uni.setText("Universitites and edu. Institutes");
        uni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uniMouseClicked(evt);
            }
        });
        jPanel12.add(uni, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 140, 20));

        side_pannel.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 230, 40));

        as_label.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        as_label.setForeground(new java.awt.Color(255, 255, 255));
        as_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        as_label.setText("Signed in as, Mohaned");
        as_label.setToolTipText("");
        side_pannel.add(as_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, 50));

        jobs_pannel.setBackground(new java.awt.Color(51, 51, 51));
        jobs_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("To apply for a job send your cv to the recruiter's email.");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Available Job Vacancies");

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane7.setOpaque(false);

        jobs_tbl.setBackground(new java.awt.Color(51, 51, 51));
        jobs_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jobs_tbl.setForeground(new java.awt.Color(255, 255, 255));
        jobs_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lex Fontier", "EA", "TY"},
                {"2", "Ryan Reynold", "US", "NY"},
                {"3", "Jackie Wuan", "CH", "HK"},
                {"4", "Monty Rattler", "UK", "LD"}
            },
            new String [] {
                "ID", "NAME", "COUNTRY", "STATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jobs_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        jobs_tbl.setRowHeight(20);
        jobs_tbl.setSelectionBackground(new java.awt.Color(62, 226, 141));
        jobs_tbl.setShowHorizontalLines(false);
        jobs_tbl.setShowVerticalLines(false);
        jScrollPane7.setViewportView(jobs_tbl);

        javax.swing.GroupLayout jobs_pannelLayout = new javax.swing.GroupLayout(jobs_pannel);
        jobs_pannel.setLayout(jobs_pannelLayout);
        jobs_pannelLayout.setHorizontalGroup(
            jobs_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jobs_pannelLayout.createSequentialGroup()
                .addGroup(jobs_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jobs_pannelLayout.createSequentialGroup()
                        .addGroup(jobs_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jobs_pannelLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jobs_pannelLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jobs_pannelLayout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jobs_pannelLayout.setVerticalGroup(
            jobs_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jobs_pannelLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel21)
                .addGap(32, 32, 32)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        library_pannel.setBackground(new java.awt.Color(51, 51, 51));
        library_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Libraries");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane1.setOpaque(false);

        library_tbl.setBackground(new java.awt.Color(51, 51, 51));
        library_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        library_tbl.setForeground(new java.awt.Color(255, 255, 255));
        library_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lex Fontier", "EA", "TY"},
                {"2", "Ryan Reynold", "US", "NY"},
                {"3", "Jackie Wuan", "CH", "HK"},
                {"4", "Monty Rattler", "UK", "LD"}
            },
            new String [] {
                "ID", "NAME", "COUNTRY", "STATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        library_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        library_tbl.setRowHeight(20);
        library_tbl.setSelectionBackground(new java.awt.Color(62, 226, 141));
        library_tbl.setShowHorizontalLines(false);
        library_tbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(library_tbl);

        javax.swing.GroupLayout library_pannelLayout = new javax.swing.GroupLayout(library_pannel);
        library_pannel.setLayout(library_pannelLayout);
        library_pannelLayout.setHorizontalGroup(
            library_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(library_pannelLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
            .addGroup(library_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, library_pannelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        library_pannelLayout.setVerticalGroup(
            library_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(library_pannelLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel16)
                .addContainerGap(554, Short.MAX_VALUE))
            .addGroup(library_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, library_pannelLayout.createSequentialGroup()
                    .addContainerGap(183, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(270, Short.MAX_VALUE)))
        );

        uni_pannel.setBackground(new java.awt.Color(51, 51, 51));
        uni_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Universities and Educational Inst.");

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane4.setOpaque(false);

        uni_tbl.setBackground(new java.awt.Color(51, 51, 51));
        uni_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uni_tbl.setForeground(new java.awt.Color(255, 255, 255));
        uni_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lex Fontier", "EA", "TY"},
                {"2", "Ryan Reynold", "US", "NY"},
                {"3", "Jackie Wuan", "CH", "HK"},
                {"4", "Monty Rattler", "UK", "LD"}
            },
            new String [] {
                "ID", "NAME", "COUNTRY", "STATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        uni_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        uni_tbl.setRowHeight(20);
        uni_tbl.setSelectionBackground(new java.awt.Color(62, 226, 141));
        uni_tbl.setShowHorizontalLines(false);
        uni_tbl.setShowVerticalLines(false);
        jScrollPane4.setViewportView(uni_tbl);

        javax.swing.GroupLayout uni_pannelLayout = new javax.swing.GroupLayout(uni_pannel);
        uni_pannel.setLayout(uni_pannelLayout);
        uni_pannelLayout.setHorizontalGroup(
            uni_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uni_pannelLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(uni_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(uni_pannelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        uni_pannelLayout.setVerticalGroup(
            uni_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uni_pannelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel15)
                .addContainerGap(579, Short.MAX_VALUE))
            .addGroup(uni_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(uni_pannelLayout.createSequentialGroup()
                    .addGap(226, 226, 226)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(227, Short.MAX_VALUE)))
        );

        Bcenter_pannel.setBackground(new java.awt.Color(51, 51, 51));
        Bcenter_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Business Centers");

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane8.setOpaque(false);

        bcenter_tbl.setBackground(new java.awt.Color(51, 51, 51));
        bcenter_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bcenter_tbl.setForeground(new java.awt.Color(255, 255, 255));
        bcenter_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lex Fontier", "EA", "TY"},
                {"2", "Ryan Reynold", "US", "NY"},
                {"3", "Jackie Wuan", "CH", "HK"},
                {"4", "Monty Rattler", "UK", "LD"}
            },
            new String [] {
                "ID", "NAME", "COUNTRY", "STATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        bcenter_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        bcenter_tbl.setRowHeight(20);
        bcenter_tbl.setSelectionBackground(new java.awt.Color(62, 226, 141));
        bcenter_tbl.setShowHorizontalLines(false);
        bcenter_tbl.setShowVerticalLines(false);
        jScrollPane8.setViewportView(bcenter_tbl);

        javax.swing.GroupLayout Bcenter_pannelLayout = new javax.swing.GroupLayout(Bcenter_pannel);
        Bcenter_pannel.setLayout(Bcenter_pannelLayout);
        Bcenter_pannelLayout.setHorizontalGroup(
            Bcenter_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Bcenter_pannelLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Bcenter_pannelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        Bcenter_pannelLayout.setVerticalGroup(
            Bcenter_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Bcenter_pannelLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel22)
                .addGap(121, 121, 121)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(325, Short.MAX_VALUE))
        );

        hotels_pannel.setBackground(new java.awt.Color(51, 51, 51));
        hotels_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane5.setOpaque(false);

        hotel_tbl.setBackground(new java.awt.Color(51, 51, 51));
        hotel_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hotel_tbl.setForeground(new java.awt.Color(255, 255, 255));
        hotel_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lex Fontier", "EA", "TY"},
                {"2", "Ryan Reynold", "US", "NY"},
                {"3", "Jackie Wuan", "CH", "HK"},
                {"4", "Monty Rattler", "UK", "LD"}
            },
            new String [] {
                "ID", "NAME", "COUNTRY", "STATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        hotel_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        hotel_tbl.setRowHeight(20);
        hotel_tbl.setSelectionBackground(new java.awt.Color(62, 226, 141));
        hotel_tbl.setShowHorizontalLines(false);
        hotel_tbl.setShowVerticalLines(false);
        jScrollPane5.setViewportView(hotel_tbl);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Hotels");

        javax.swing.GroupLayout hotels_pannelLayout = new javax.swing.GroupLayout(hotels_pannel);
        hotels_pannel.setLayout(hotels_pannelLayout);
        hotels_pannelLayout.setHorizontalGroup(
            hotels_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hotels_pannelLayout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
            .addGroup(hotels_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(hotels_pannelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        hotels_pannelLayout.setVerticalGroup(
            hotels_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hotels_pannelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel17)
                .addContainerGap(565, Short.MAX_VALUE))
            .addGroup(hotels_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(hotels_pannelLayout.createSequentialGroup()
                    .addGap(226, 226, 226)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(227, Short.MAX_VALUE)))
        );

        industries_pannel.setBackground(new java.awt.Color(51, 51, 51));
        industries_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jScrollPane6.setOpaque(false);

        industries_tbl.setBackground(new java.awt.Color(51, 51, 51));
        industries_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        industries_tbl.setForeground(new java.awt.Color(255, 255, 255));
        industries_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lex Fontier", "EA", "TY"},
                {"2", "Ryan Reynold", "US", "NY"},
                {"3", "Jackie Wuan", "CH", "HK"},
                {"4", "Monty Rattler", "UK", "LD"}
            },
            new String [] {
                "ID", "NAME", "COUNTRY", "STATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        industries_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        industries_tbl.setRowHeight(20);
        industries_tbl.setSelectionBackground(new java.awt.Color(62, 226, 141));
        industries_tbl.setShowHorizontalLines(false);
        industries_tbl.setShowVerticalLines(false);
        jScrollPane6.setViewportView(industries_tbl);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Industries");

        javax.swing.GroupLayout industries_pannelLayout = new javax.swing.GroupLayout(industries_pannel);
        industries_pannel.setLayout(industries_pannelLayout);
        industries_pannelLayout.setHorizontalGroup(
            industries_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(industries_pannelLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
            .addGroup(industries_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
        );
        industries_pannelLayout.setVerticalGroup(
            industries_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(industries_pannelLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel18)
                .addContainerGap(574, Short.MAX_VALUE))
            .addGroup(industries_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(industries_pannelLayout.createSequentialGroup()
                    .addGap(226, 226, 226)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(171, Short.MAX_VALUE)))
        );

        bus_pannel.setBackground(new java.awt.Color(51, 51, 51));
        bus_pannel.setPreferredSize(new java.awt.Dimension(570, 666));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Bus Routes");

        bus_tbl.setBackground(new java.awt.Color(51, 51, 51));
        bus_tbl.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        bus_tbl.setForeground(new java.awt.Color(255, 255, 255));
        bus_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        bus_tbl.setGridColor(new java.awt.Color(255, 255, 255));
        bus_tbl.setRowHeight(45);
        jScrollPane2.setViewportView(bus_tbl);

        javax.swing.GroupLayout bus_pannelLayout = new javax.swing.GroupLayout(bus_pannel);
        bus_pannel.setLayout(bus_pannelLayout);
        bus_pannelLayout.setHorizontalGroup(
            bus_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bus_pannelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(bus_pannelLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bus_pannelLayout.setVerticalGroup(
            bus_pannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bus_pannelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel12)
                .addGap(158, 158, 158)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(side_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jobs_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 216, Short.MAX_VALUE)
                    .addComponent(library_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 214, Short.MAX_VALUE)
                    .addComponent(uni_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 210, Short.MAX_VALUE)
                    .addComponent(Bcenter_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 217, Short.MAX_VALUE)
                    .addComponent(hotels_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 213, Short.MAX_VALUE)
                    .addComponent(attractions_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 215, Short.MAX_VALUE)
                    .addComponent(industries_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 214, Short.MAX_VALUE)
                    .addComponent(bus_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 213, Short.MAX_VALUE)
                    .addComponent(empty_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(side_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jobs_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(library_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(uni_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Bcenter_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(hotels_pannel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(attractions_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(industries_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bus_pannel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(empty_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 847, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(register_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(signin_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(start_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(admin_pannel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(register_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(signin_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(start_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(admin_pannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void user_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_fieldActionPerformed

    private void signin_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signin_btnActionPerformed
       
        String user_in=user_field.getText();
        String pass_in=password_field.getText();
        
      
        
        if(user_in.isEmpty() || pass_in.isEmpty())  {
            sign_error_label.setText("Username or Password cant be empty.");
            return;
        }
        
        String sql = "SELECT * FROM User WHERE User_name = ? AND Hashed_Password = ? ";
        PreparedStatement st = null;
        
        try {
            st = getDBconnection().prepareStatement(sql);
            st.setString(1, user_in);
            st.setString(2, ""+pass_in.hashCode());
            
            ResultSet rs = st.executeQuery();
            
            
        if (rs.next()) {
            System.out.println("loggedin");

            setSignedUser(rs.getInt("ID"),
                    rs.getString("First_name"),
                    rs.getString("User_name"),
                    pass_in,
                    rs.getString("Email"),
                    rs.getString("User_type") );
            
            
            signin_pannel.setVisible(false);
            renderContentPage();
            
            
            
            
            
                
            
        }
        else{
            sign_error_label.setText("Username or password entered are incorrect.");
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }//GEN-LAST:event_signin_btnActionPerformed

    private void name_field_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_field_regActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_field_regActionPerformed

    private void user_field_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_field_regActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_field_regActionPerformed

    private void register_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_register_btnActionPerformed
        // TODO add your handling code here:
            String type_in=(String) type_field.getSelectedItem();
            String name_in=name_field_reg.getText();
            String user_in=user_field_reg.getText();
            String password_in=pass_field_reg.getText();
            String email_in=email_field_reg.getText();
            int new_id=(int) (Math.random()*1000);
            
            
            if(type_in.equals("Choose Type") || name_in.isEmpty() || user_in.isEmpty() || password_in.isEmpty() || email_in.isEmpty()){
                reg_error_label.setText("Please fill in all the required fields");
                return;
            }
            
            
        
       
        
           String sql = "SELECT * FROM User WHERE User_name = ?";
           PreparedStatement st = null;
        try {
            st = getDBconnection().prepareStatement(sql);
            st.setString(1, user_in);
            
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                reg_error_label.setText("User name already exists.");
                return;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
             sql = "insert into User(ID, First_name, User_name, Hashed_Password, User_type, Email) values(?,?,?,?,?,?)";
             st = null;
            
        try {
            st = getDBconnection().prepareStatement(sql);
            
            
            
            st.setString(1, ""+new_id);
            st.setString(2, name_in);
            st.setString(3, user_in );
            st.setString(4, ""+password_in.hashCode() );
            st.setString(5,type_in);
            st.setString(6, email_in);   
           
            st.executeUpdate();
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        setSignedUser(new_id,name_in,user_in,password_in,email_in,type_in); 
        register_pannel.setVisible(false);
        renderContentPage();
         
            
        
    
        
        
    }//GEN-LAST:event_register_btnActionPerformed

    private void email_field_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_field_regActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_field_regActionPerformed

    private void open_siign_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_open_siign_btnActionPerformed
        // TODO add your handling code here:
        start_pannel.setVisible(false);
        signin_pannel.setVisible(true);
        
    }//GEN-LAST:event_open_siign_btnActionPerformed

    private void open_reg_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_open_reg_btnActionPerformed
        // TODO add your handling code here:
        start_pannel.setVisible(false);
        register_pannel.setVisible(true);
    }//GEN-LAST:event_open_reg_btnActionPerformed

    private void pass_field_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_field_regActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_field_regActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formHierarchyChanged

    private void hotelsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hotelsMouseClicked
        // TODO add your handling code here:
        hotels.getParent().setBackground(new Color(366766));
        bus.getParent().setBackground(side_pannel.getBackground());
        att_spots.getParent().setBackground(side_pannel.getBackground());
        
        
        empty_pannel.setVisible(false);
        bus_pannel.setVisible(false);
        attractions_pannel.setVisible(false);
        hotels_pannel.setVisible(true);
    }//GEN-LAST:event_hotelsMouseClicked

    private void bcentersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bcentersMouseClicked
        // TODO add your handling code here:
        bcenters.getParent().setBackground(new Color(366766));
        industries.getParent().setBackground(side_pannel.getBackground());
        
        empty_pannel.setVisible(false);
        industries_pannel.setVisible(false);
        Bcenter_pannel.setVisible(true);
    }//GEN-LAST:event_bcentersMouseClicked

    private void libraryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libraryMouseClicked
        // TODO add your handling code here:
        library.getParent().setBackground(new Color(366766));
        uni.getParent().setBackground(side_pannel.getBackground());
        bus.getParent().setBackground(side_pannel.getBackground());
        
        empty_pannel.setVisible(false);
        uni_pannel.setVisible(false);
        bus_pannel.setVisible(false);
        library_pannel.setVisible(true);
    }//GEN-LAST:event_libraryMouseClicked

    private void av_jobsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_av_jobsMouseClicked
        // TODO add your handling code here:
        av_jobs.getParent().setBackground(new Color(366766));
        industries.getParent().setBackground(side_pannel.getBackground());
        
        empty_pannel.setVisible(false);
        industries_pannel.setVisible(false);
        jobs_pannel.setVisible(true);
    }//GEN-LAST:event_av_jobsMouseClicked

    private void busMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_busMouseClicked
        // TODO add your handling code here:
        bus.getParent().setBackground(new Color(366766));
        library.getParent().setBackground(side_pannel.getBackground());
        uni.getParent().setBackground(side_pannel.getBackground());
        hotels.getParent().setBackground(side_pannel.getBackground());
        att_spots.getParent().setBackground(side_pannel.getBackground());
        
        empty_pannel.setVisible(false);
        library_pannel.setVisible(false);
        uni_pannel.setVisible(false);
        hotels_pannel.setVisible(false);
        attractions_pannel.setVisible(false);
        bus_pannel.setVisible(true);
    }//GEN-LAST:event_busMouseClicked

    private void industriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_industriesMouseClicked
        // TODO add your handling code here:
        industries.getParent().setBackground(new Color(366766));
        av_jobs.getParent().setBackground(side_pannel.getBackground());
        bcenters.getParent().setBackground(side_pannel.getBackground());
        
        empty_pannel.setVisible(false);
        Bcenter_pannel.setVisible(false);
        jobs_pannel.setVisible(false);
       industries_pannel.setVisible(true);
    }//GEN-LAST:event_industriesMouseClicked

    private void att_spotsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_att_spotsMouseClicked
        // TODO add your handling code here:
        att_spots.getParent().setBackground(new Color(366766));
        bus.getParent().setBackground(side_pannel.getBackground());
        hotels.getParent().setBackground(side_pannel.getBackground());
        
        empty_pannel.setVisible(false);
        hotels_pannel.setVisible(false);
        bus_pannel.setVisible(false);
        attractions_pannel.setVisible(true);
    }//GEN-LAST:event_att_spotsMouseClicked

    private void uniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uniMouseClicked
        // TODO add your handling code here:
        uni.getParent().setBackground(new Color(366766));
        library.getParent().setBackground(side_pannel.getBackground());
        bus.getParent().setBackground(side_pannel.getBackground());
        
        empty_pannel.setVisible(false);
        library_pannel.setVisible(false);
        bus_pannel.setVisible(false);
        uni_pannel.setVisible(true);
    }//GEN-LAST:event_uniMouseClicked

    private void field1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field1ActionPerformed

    private void field2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field2ActionPerformed

    private void field3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field3ActionPerformed

    private void field5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field5ActionPerformed

    private void field4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field4ActionPerformed

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        // TODO add your handling code here:
       
        
        String table_name=(String) tables_combo.getSelectedItem();
        String sql="SELECT *  FROM "+table_name+" WHERE ";
        
        if(field1.isEnabled() && !field1.getText().isEmpty()) sql+= field1.getToolTipText()+" = '"+field1.getText()+"' AND ";
        if(field2.isEnabled() && !field2.getText().isEmpty()) sql+= field2.getToolTipText()+" = '"+field2.getText()+"' AND ";
        if(field3.isEnabled() && !field3.getText().isEmpty()) sql+= field3.getToolTipText()+" = '"+field3.getText()+"' AND ";
        if(field4.isEnabled() && !field4.getText().isEmpty()) sql+= field4.getToolTipText()+" = '"+field4.getText()+"' AND ";
        if(field5.isEnabled() && !field5.getText().isEmpty()) sql+= field5.getToolTipText()+" = '"+field5.getText()+"' AND ";
        if(field6.isEnabled() && !field6.getText().isEmpty()) sql+= field6.getToolTipText()+" = '"+field6.getText()+"' AND ";
        
        sql=sql.substring(0, sql.length() - 5);
        System.out.println(sql);
        PreparedStatement st=null;
        ResultSet rs=null;
        
        try {
            st=getDBconnection().prepareStatement(sql);
            rs=st.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            String result= "";
            int count=0;
            while(rs.next()){
                count++;
                result+="[ ";
                for(int i=1; i<=resultSetMetaData.getColumnCount() ; i++){
                    result+=rs.getString(i)+", ";
                }
                result+="]"+'\n';
            }
            msg_lbl.setForeground(Color.WHITE);
            System.out.println(result);
            msg_lbl.setText("Found "+count+" results: \n"+ result);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }//GEN-LAST:event_search_btnActionPerformed

    private void add_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btn1ActionPerformed
       String table_name=(String) tables_combo.getSelectedItem();
       if(field1.getText().isEmpty() || field2.getText().isEmpty() || field2.getText().isEmpty() || 
             (field3.isEnabled() && field3.getText().isEmpty()) || (field4.isEnabled() && field4.getText().isEmpty()) ||
               (field5.isEnabled() && field5.getText().isEmpty()) || (field6.isEnabled() && field6.getText().isEmpty())){
           msg_lbl.setForeground(Color.red);
           msg_lbl.setText("Please fill all the required fields");
           return;
       }
       
       
       
           String values="";
           
           values+="'"+field1.getText()+"', ";
           values+="'"+field2.getText()+"', ";
           if(field3.isEnabled()) values+="'"+field3.getText()+"', ";
           if(field4.isEnabled()) values+="'"+field4.getText()+"', ";
           if(field5.isEnabled()) values+="'"+field5.getText()+"', ";
           if(field6.isEnabled()) values+="'"+field6.getText()+"', ";

           values=values.substring(0, values.length() - 2);
             String  sql="";
            if(table_name=="User"){
                 sql =  "insert into User(ID, First_name, User_name, Hashed_Password, User_type, Email) values("+ values+")";
            }else{
                  sql = "insert into "+table_name+" values("+values+")";
            }
            
            System.out.println(sql);
            PreparedStatement st = null;
        try {
            st=getDBconnection().prepareStatement(sql);
            st.executeUpdate();
            msg_lbl.setForeground(Color.WHITE);
            msg_lbl.setText("Added item to "+table_name+" Table successfully");
            listSelected(evt);
            
            
        } catch (SQLException ex) {
            msg_lbl.setForeground(Color.red);
           msg_lbl.setText("Item already exists or the default values of the input fields are not changed");
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_btn1ActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        // TODO add your handling code here:
        
        if(chosen_tbl.getSelectionModel().isSelectionEmpty()){
             msg_lbl.setForeground(Color.red);
             msg_lbl.setText("Please choose a field to delete");
             return;
        }
        
        String table_name=(String) tables_combo.getSelectedItem();
        String field1=chosen_tbl.getColumnName(0);
        String field2=chosen_tbl.getColumnName(1);
        String value1=(String) chosen_tbl.getValueAt(chosen_tbl.getSelectedRow(), 0);
        String value2=(String) chosen_tbl.getValueAt(chosen_tbl.getSelectedRow(), 1);
        
        String sql="DELETE FROM "+table_name+" WHERE "+field1+" = '"+value1+ "' AND "+field2+ " = '"+value2+"'";
        System.out.println(sql);
        PreparedStatement st=null;
        try {
            st=getDBconnection().prepareStatement(sql);
            st.executeUpdate();
            msg_lbl.setForeground(Color.WHITE);
            msg_lbl.setText("Deleted item from "+table_name+" Table successfully");
            listSelected(evt);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_delete_btnActionPerformed

    private void update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnActionPerformed
        // TODO add your handling code here:
        
        if(chosen_tbl.getSelectionModel().isSelectionEmpty()){
             msg_lbl.setForeground(Color.red);
             msg_lbl.setText("Please choose a field to update");
             return;
        }
        
        String table_name=(String) tables_combo.getSelectedItem();
        String sql="UPDATE "+table_name+" SET ";
        String col1=chosen_tbl.getColumnName(0);
        String col2=chosen_tbl.getColumnName(1);
        String value1=(String) chosen_tbl.getValueAt(chosen_tbl.getSelectedRow(), 0);
        String value2=(String) chosen_tbl.getValueAt(chosen_tbl.getSelectedRow(), 1);
        
           if(field1.getText().isEmpty() || field2.getText().isEmpty() || field2.getText().isEmpty() || 
             (field3.isEnabled() && field3.getText().isEmpty()) || (field4.isEnabled() && field4.getText().isEmpty()) ||
               (field5.isEnabled() && field5.getText().isEmpty()) || (field6.isEnabled() && field6.getText().isEmpty())){
           msg_lbl.setForeground(Color.red);
           msg_lbl.setText("Please fill all the required fields");
           return;
       }
           
           
           
        sql+= field1.getToolTipText()+" = '"+field1.getText()+"', ";
        sql+= field2.getToolTipText()+" = '"+field2.getText()+"', ";
        if(field3.isEnabled()) sql+= field3.getToolTipText()+" = '"+field3.getText()+"', ";
        if(field4.isEnabled()) sql+= field4.getToolTipText()+" = '"+field4.getText()+"', ";
        if(field5.isEnabled()) sql+= field5.getToolTipText()+" = '"+field5.getText()+"', ";
        if(field6.isEnabled()) sql+= field6.getToolTipText()+" = '"+field6.getText()+"', ";
        
        sql=sql.substring(0, sql.length() - 2);
        sql+=" WHERE "+col1+" = '"+value1+ "' AND "+col2+ " = '"+value2+"'";
        System.out.println(sql);
        
        PreparedStatement st=null;
        try {
            st=getDBconnection().prepareStatement(sql);
            st.executeUpdate();
            msg_lbl.setText("Updated item successfully");
            listSelected(evt);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }//GEN-LAST:event_update_btnActionPerformed

    
    
    
    private void listSelected(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listSelected
        try {
            // TODO add your handling code here:
            String table_name=(String) tables_combo.getSelectedItem();
            
        
            switch(table_name){
                case "User":
                    String sql = "SELECT * FROM User ";
                    PreparedStatement st = null;
                    ResultSetMetaData metaData= null;
                    ResultSet rs= null;
                    
                    
                    {
                        try {
                            st = getDBconnection().prepareStatement(sql);
                            rs = st.executeQuery();
                            metaData = rs.getMetaData();
                        } catch (SQLException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    
                    Vector<String> columnNames = new Vector<String>();
                    
                    columnNames.add("ID");
                    columnNames.add("First_name");
                    columnNames.add("User_name");
                    columnNames.add("Hashed_Password");
                    columnNames.add("User_type");
                    columnNames.add("Email");
                    
                   
                    chosen_tbl.setModel(buildTableModel(rs,columnNames));
                    
                    field1.setEnabled(true); field1.setText("User ID"); field1.setToolTipText("ID");
                    field2.setEnabled(true); field2.setText("First Name"); field2.setToolTipText("First_name");
                    field3.setEnabled(true); field3.setText("User Name"); field3.setToolTipText("User_name");
                    field4.setEnabled(true); field4.setText("User Password");  field4.setToolTipText("Hashed_Password");
                    field5.setEnabled(true); field5.setText("User Type");  field5.setToolTipText("User_type");
                    field6.setEnabled(true); field6.setText("User Email");  field6.setToolTipText("Email");
                    
                    break;
                    
                    
                case "Library":
                    initLibTable();
                    chosen_tbl.setModel(library_tbl.getModel());
                    field1.setEnabled(true); field1.setText("Librarys Address");  field1.setToolTipText("Address");
                    field2.setEnabled(true); field2.setText("Library Phone");     field2.setToolTipText("Phone");
                    field3.setEnabled(true); field3.setText("Library Name");      field3.setToolTipText("Library_Name");
                    field4.setEnabled(false); field4.setText("");
                    field5.setEnabled(false); field5.setText("");
                    field6.setEnabled(false); field6.setText("");
                    break;
                    
                    
                case "Business_Center":
                    initCentersTable();
                    chosen_tbl.setModel(bcenter_tbl.getModel());
                    field1.setEnabled(true); field1.setText("Centers Name");        field1.setToolTipText("Center_Name");
                    field2.setEnabled(true); field2.setText("Center Address");      field2.setToolTipText("Address");
                    field3.setEnabled(false); field3.setText("");
                    field4.setEnabled(false); field4.setText("");
                    field5.setEnabled(false); field5.setText("");
                    field6.setEnabled(false); field6.setText("");
                    break;
                    
                case "BusRoute":
                    initBusTable();
                    chosen_tbl.setModel(bus_tbl.getModel());
                    field2.setEnabled(true); field1.setText("Trip Source");         field1.setToolTipText("Source");     
                    field3.setEnabled(true); field2.setText("Trip Destination");    field2.setToolTipText("Destination");
                    field4.setEnabled(true); field3.setText("Trip Time");           field3.setToolTipText("Time");
                    field1.setEnabled(true); field4.setText("Bus Num");             field4.setToolTipText("Bus_Number");
                    field5.setEnabled(false); field5.setText("");
                    field6.setEnabled(false); field6.setText("");
                    break;
                    
                case "Hotel":
                    initHotelTable();
                    chosen_tbl.setModel(hotel_tbl.getModel());
                    field1.setEnabled(true); field1.setText("Hotel Address");       field1.setToolTipText("Address");
                    field2.setEnabled(true); field2.setText("Hotel Phone");         field2.setToolTipText("Phone");
                    field3.setEnabled(true); field3.setText("Hotel Name");          field3.setToolTipText("Hotel_name");
                    field4.setEnabled(true); field4.setText("Price/night");         field4.setToolTipText("Price_per_night");
                    field5.setEnabled(false); field5.setText("");
                    field6.setEnabled(false); field6.setText("");
                    break;

                    
                case "Industry":
                    initIndustriesTable();
                    chosen_tbl.setModel(industries_tbl.getModel()); 
                    field1.setEnabled(true); field1.setText("Company Field");       field1.setToolTipText("Field");
                    field2.setEnabled(true); field2.setText("Company Sector");      field2.setToolTipText("Sector");
                    field3.setEnabled(true); field3.setText("Company Name");        field3.setToolTipText("Company_Name");
                    field4.setEnabled(true); field4.setText("Company Address");     field4.setToolTipText("Address");
                    field5.setEnabled(true); field5.setText("No. of Employees");    field5.setToolTipText("Number_of_Employees");
                    field6.setEnabled(false); field6.setText("");
                    break;
                    
                case "Job":
                    initJobsTable();
                    chosen_tbl.setModel(jobs_tbl.getModel());
                    field1.setEnabled(true); field1.setText("Jobs Company Name");   field1.setToolTipText("Company_Name");
                    field2.setEnabled(true); field2.setText("Job title");           field2.setToolTipText("Job_Title");
                    field3.setEnabled(true); field3.setText("Job Salary");          field3.setToolTipText("Salary");
                    field4.setEnabled(true); field4.setText("Job Closing Date");    field4.setToolTipText("Closing_Date");
                    field5.setEnabled(true); field5.setText("Recruiters Address");  field5.setToolTipText("Recruiters_Email");
                    field6.setEnabled(false); field6.setText("");   
                    break;
                    
                case "University":
                    initUniTable();
                    chosen_tbl.setModel(uni_tbl.getModel());
                    field1.setEnabled(true); field1.setText("Universitys Name");    field1.setToolTipText("University_Name");
                    field2.setEnabled(true); field2.setText("Uni Location");        field2.setToolTipText("Location");
                    field3.setEnabled(true); field3.setText("Uni Email");           field3.setToolTipText("Email");
                    field4.setEnabled(false); field4.setText("");
                    field5.setEnabled(false); field5.setText("");
                    field6.setEnabled(false); field6.setText("");
                    break;
                    
                    
            }} catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listSelected

    private void field6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field6ActionPerformed

    
    
    
  
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bcenter_pannel;
    private javax.swing.JButton add_btn1;
    private javax.swing.JPanel admin_pannel;
    private javax.swing.JLabel as_label;
    private javax.swing.JLabel att_spots;
    private javax.swing.JPanel attractions_pannel;
    private javax.swing.JLabel av_jobs;
    private javax.swing.JTable bcenter_tbl;
    private javax.swing.JLabel bcenters;
    private javax.swing.JLabel bus;
    private javax.swing.JPanel bus_pannel;
    private javax.swing.JTable bus_tbl;
    private javax.swing.JTable chosen_tbl;
    private javax.swing.JButton delete_btn;
    private javax.swing.JTextField email_field_reg;
    private javax.swing.JPanel empty_pannel;
    private javax.swing.JTextField field1;
    private javax.swing.JTextField field2;
    private javax.swing.JTextField field3;
    private javax.swing.JTextField field4;
    private javax.swing.JTextField field5;
    private javax.swing.JTextField field6;
    private javax.swing.JTable hotel_tbl;
    private javax.swing.JLabel hotels;
    private javax.swing.JPanel hotels_pannel;
    private javax.swing.JLabel industries;
    private javax.swing.JPanel industries_pannel;
    private javax.swing.JTable industries_tbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel jobs_pannel;
    private javax.swing.JTable jobs_tbl;
    private javax.swing.JLabel lab8;
    private javax.swing.JLabel lab9;
    private javax.swing.JLabel library;
    private javax.swing.JPanel library_pannel;
    private javax.swing.JTable library_tbl;
    private javax.swing.JTextArea msg_lbl;
    private javax.swing.JTextField name_field_reg;
    private javax.swing.JButton open_reg_btn;
    private javax.swing.JButton open_siign_btn;
    private javax.swing.JPasswordField pass_field_reg;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JPanel putme;
    private javax.swing.JLabel reg_error_label;
    private javax.swing.JButton register_btn;
    private javax.swing.JPanel register_pannel;
    private javax.swing.JButton search_btn;
    private javax.swing.JPanel side_pannel;
    private javax.swing.JLabel sign_error_label;
    private javax.swing.JButton signin_btn;
    private javax.swing.JPanel signin_pannel;
    private javax.swing.JPanel start_pannel;
    private javax.swing.JComboBox<String> tables_combo;
    private javax.swing.JComboBox<String> type_field;
    private javax.swing.JLabel uni;
    private javax.swing.JPanel uni_pannel;
    private javax.swing.JTable uni_tbl;
    private javax.swing.JButton update_btn;
    private javax.swing.JTextField user_field;
    private javax.swing.JTextField user_field_reg;
    // End of variables declaration//GEN-END:variables
}
