/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensisa.hassenforder.proximity.client;

import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.User;
import fr.ensisa.hassenforder.proximity.model.Preference;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author hassenforder
 */
public class Proximity extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Document document;
    private User selected;

    class CustomPanel extends javax.swing.JPanel implements java.awt.event.MouseListener {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CustomPanel() {
            this.addMouseListener(this);
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            int w = super.getWidth();
            int h = super.getHeight();
            int stepX = w/10;
            int stepY = h/10;
            int x = 0, y=0;
            g.setColor(Color.blue);
            for (int i=0;i < 11; ++i) {
                g.drawLine(0, y, w, y);
                g.drawLine(x, 0, x, h);
                x+=stepX;
                y+=stepY;
            }
            if (document == null) return;
            if (document.getMe() != null) {
                g.setColor(Color.red);
                User u = document.getMe();
                g.fillOval(u.getX()/10*stepX+stepX/4, u.getY()/10*stepY+stepY/4, stepX/2, stepY/2);
                if (selected == u) g.drawOval(u.getX()/10*stepX+stepX/4-3, u.getY()/10*stepY+stepY/4-3, stepX/2+6, stepY/2+6);
            }
            if (document.getOthers() != null) {
                g.setColor(Color.green);
                for (User u : document.getOthers()) {
                    g.fillOval(u.getX()/10*stepX+stepX/4, u.getY()/10*stepY+stepY/4, stepX/2, stepY/2);
                    if (selected == u) g.drawOval(u.getX()/10*stepX+stepX/4-3, u.getY()/10*stepY+stepY/4-3, stepX/2+6, stepY/2+6);
                }
            }
            selected = null;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (document == null) return;
            int w = super.getWidth();
            int h = super.getHeight();
            int stepX = w/10;
            int stepY = h/10;
            int x = e.getX()*10/stepX;
            int y = e.getY()*10/stepY;
            User u = document.getUser(x, y);
            selected = u;
            int id = document.getId(u);
            other_preferences.setId(id);
            updateOther(document.getUser(id));
            jComment.setText("preference id changed "+u);            
            validate();
            repaint ();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    class PreferenceTableModel extends javax.swing.table.AbstractTableModel {
        
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private int id;
        private String titles [] = { "Name", "Level", "Visibility" };
        private Class<?> classes [] = { String.class, Integer.class, Boolean.class };

        public PreferenceTableModel(int id) {
            this.id = id;
        }

        @Override
        public int getRowCount() {
            if (document == null) return 0;
            if (document.getUser(id) == null) return 0;
            return document.getUser(id).getPreferences().size();
        }

        @Override
        public int getColumnCount() {
            return titles.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (document == null) return null;
            if (document.getUser(id) == null) return null;
            Preference preference = document.getUser(id).getPreferenceByPosition(rowIndex);
            if (preference == null) return null;
            switch (columnIndex) {
            case 0 : return preference.getName();
            case 1 : return new Integer (preference.getLevel());
            case 2 : return new Boolean (preference.isVisibility());
            default : return null;
            }
        }
        
        @Override
        public String getColumnName(int col) {
            return titles[col];
        }

        @Override
        public Class<?> getColumnClass(int c) {
            return classes[c];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return (id == -1) && (col >= 1);
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int colIndex) {
            if (document == null) return ;
            if (document.getUser(id) == null) return ;
            Preference preference = document.getUser(id).getPreferenceByPosition(rowIndex);
            if (preference == null) return ;
            switch (colIndex) {
            case 0 : break;
            case 1 : document.doChangePreferenceLevel(id, rowIndex, ((Integer)value).intValue()); break;
            case 2 : document.doChangePreferenceVisibility(id, rowIndex, ((Boolean)value).booleanValue()); break;
            default : return;
            }
            my_preferences.fireTableDataChanged();
        }

        public void setId(int id) {
            this.id = id;
        }
        
    };
    
    private PreferenceTableModel my_preferences = new PreferenceTableModel (-1);
    
    private PreferenceTableModel other_preferences = new PreferenceTableModel (-2);

    public void setDocument(Document document) {
        this.document = document;
    }
    
    /**
     * Creates new form UI
     */
    public Proximity() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_My_Name = new javax.swing.JTextField();
        jConnect = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField_My_x = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_My_y = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_My_radius = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSpinner_My_mode = new javax.swing.JSpinner();
        jRefresh = new javax.swing.JButton();
        jmove = new javax.swing.JButton();
        jFind = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_My_preferences = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel_Other_Name = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel_Other_x = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel_Other_y = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel_Other_radius = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel_Other_mode = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Other_preferences = new javax.swing.JTable();
        jPanel3 = new CustomPanel();
        jComment = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");

        jTextField_My_Name.setText("hassen");

        jConnect.setText("connect");
        jConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConnectActionPerformed(evt);
            }
        });

        jLabel2.setText("X");

        jTextField_My_x.setText("100");

        jLabel3.setText("Y");

        jTextField_My_y.setText("100");

        jLabel6.setText("radius");

        jTextField_My_radius.setText("10");
        jTextField_My_radius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_My_radiusActionPerformed(evt);
            }
        });

        jLabel8.setText("Mode");

        jSpinner_My_mode.setModel(new javax.swing.SpinnerListModel(new String[] {"VISIBLE", "HIDDEN", "OCCUPIED"}));
        jSpinner_My_mode.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner_My_modeStateChanged(evt);
            }
        });

        jRefresh.setText("refresh");
        jRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshActionPerformed(evt);
            }
        });

        jmove.setText("move");
        jmove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmoveActionPerformed(evt);
            }
        });

        jFind.setText("find");
        jFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFindActionPerformed(evt);
            }
        });

        jLabel9.setText("Preferences");

        jTable_My_preferences.setModel(my_preferences);
        jScrollPane1.setViewportView(jTable_My_preferences);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTextField_My_Name))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jTextField_My_x, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_My_y, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jConnect, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jmove, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner_My_mode, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_My_radius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17)
                        .addComponent(jRefresh))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jFind))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_My_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jConnect)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_My_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_My_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jmove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_My_radius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jSpinner_My_mode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jFind)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setText("Name");

        jLabel_Other_Name.setText("__Other__");

        jLabel11.setText("X");

        jLabel_Other_x.setText("x");

        jLabel13.setText("Y");

        jLabel_Other_y.setText("y");

        jLabel15.setText("radius");

        jLabel_Other_radius.setText("r");

        jLabel17.setText("Mode");

        jLabel_Other_mode.setText("Mode");

        jLabel18.setText("Preferences");

        jTable_Other_preferences.setModel(other_preferences);
        jScrollPane2.setViewportView(jTable_Other_preferences);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_Other_mode))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_Other_x)
                                            .addComponent(jLabel_Other_y)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel_Other_Name))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_Other_radius)))
                        .addGap(0, 186, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel_Other_Name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel_Other_x))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel_Other_y))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel_Other_radius))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel_Other_mode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(452, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComment.setText("comments");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComment))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateUser (User user) {
    	jTextField_My_Name.setText(user.getName());
    	jTextField_My_x.setText(Integer.toString(user.getX()));
    	jTextField_My_y.setText(Integer.toString(user.getY()));
    	jTextField_My_radius.setText(Integer.toString(user.getRadius()));
    	jSpinner_My_mode.setValue(user.getMode().toString());
        my_preferences.fireTableDataChanged();
    }

    private void updateOther (User user) {
    	if (user == null) {
        	jLabel_Other_Name.setText("no name");
        	jLabel_Other_x.setText("x");
        	jLabel_Other_y.setText("y");
        	jLabel_Other_radius.setText("r");
        	jLabel_Other_mode.setText("???");
            other_preferences.fireTableDataChanged();
    	} else {
        	jLabel_Other_Name.setText(user.getName());
        	jLabel_Other_x.setText(Integer.toString(user.getX()));
        	jLabel_Other_y.setText(Integer.toString(user.getY()));
        	jLabel_Other_radius.setText(Integer.toString(user.getRadius()));
        	jLabel_Other_mode.setText(user.getMode().toString());
            other_preferences.fireTableDataChanged();
    	}
    }

    private void jConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConnectActionPerformed
    	boolean ok = false;
    	if (document.isConnected()) {
            ok = document.doDisconnect();
        } else {
            ok = document.doConnect(jTextField_My_Name.getText());
        }
        if (ok && document.isConnected()) {
           jConnect.setText("disconnect");
           jComment.setText("connect done");
           updateUser (document.getMe());
        } else {
           jConnect.setText("connect");
           jComment.setText("disconnect done");
        }
    }//GEN-LAST:event_jConnectActionPerformed

    private void jFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFindActionPerformed
        document.doFind();
        jPanel3.validate();
        jPanel3.repaint();
        jComment.setText("find done");
    }//GEN-LAST:event_jFindActionPerformed

    private void jRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshActionPerformed
        document.doGetState();
        updateUser (document.getMe());
        jComment.setText("state done");
    }//GEN-LAST:event_jRefreshActionPerformed

    private void jmoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmoveActionPerformed
        try {
            int x = Integer.parseInt(jTextField_My_x.getText());
            int y = Integer.parseInt(jTextField_My_y.getText());
            document.doMove(x, y);
            jComment.setText("move "+x+" "+y+" done");
        } catch (Exception e) {
            jComment.setText("move failed");
        }
    }//GEN-LAST:event_jmoveActionPerformed

    private void jSpinner_My_modeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner_My_modeStateChanged
        javax.swing.JSpinner src = (javax.swing.JSpinner) evt.getSource();
        try {
            Mode mode = Mode.valueOf((String) src.getValue());
            document.doChangeMode (mode);
            jComment.setText("mode changed to "+mode);
        } catch (Exception e) {
            jComment.setText("mode changed failed");
        }
    }//GEN-LAST:event_jSpinner_My_modeStateChanged

    private void jTextField_My_radiusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_My_radiusActionPerformed
        javax.swing.JTextField src = (javax.swing.JTextField) evt.getSource();
        try {
            int radius = Integer.parseInt(src.getText());
            document.doChangeRadius (radius);
            jComment.setText("radius changed to "+radius);            
        } catch (Exception e) {
            jComment.setText("radius changed failed ");            
        }

    }//GEN-LAST:event_jTextField_My_radiusActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseClicked

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
            java.util.logging.Logger.getLogger(Proximity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proximity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proximity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proximity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Proximity ui = new Proximity();
                ui.setDocument (new Document());
                ui.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jComment;
    private javax.swing.JButton jConnect;
    private javax.swing.JButton jFind;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Other_Name;
    private javax.swing.JLabel jLabel_Other_mode;
    private javax.swing.JLabel jLabel_Other_radius;
    private javax.swing.JLabel jLabel_Other_x;
    private javax.swing.JLabel jLabel_Other_y;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner_My_mode;
    private javax.swing.JTable jTable_My_preferences;
    private javax.swing.JTable jTable_Other_preferences;
    private javax.swing.JTextField jTextField_My_Name;
    private javax.swing.JTextField jTextField_My_radius;
    private javax.swing.JTextField jTextField_My_x;
    private javax.swing.JTextField jTextField_My_y;
    private javax.swing.JButton jmove;
    // End of variables declaration//GEN-END:variables
}
