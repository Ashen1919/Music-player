/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package music_player;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.io.File;
import music_player.music_interface.Node;

/**
 *
 * @author Asus
 */
public class playlist extends javax.swing.JFrame {

    private DefaultListModel<String> listModel;
    private final String SONG_FILE = "songs.txt";
    private music_interface musicInterface;

    private Node head, tail, current;

    /**
     * Creates new form playlist
     */
    public playlist() {
        initComponents();
        listModel = new DefaultListModel<>();
        jList1.setModel(listModel);
        loadSongsFromFile();
    }

    private void addSongToLinkedList(String songPath) {
        Node newNode = new Node(songPath);
        if (head == null) {
            head = tail = newNode;
            System.out.println("Added first node: " + songPath);
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            System.out.println("Added node: " + songPath);
        }
    }

    private void populateLinkedList() {
        head = tail = current = null;
        for (int i = 0; i < listModel.size(); i++) {
            addSongToLinkedList(listModel.getElementAt(i));
        }
        current = head; // Set the current pointer to the head of the list
        System.out.println("LinkedList populated. Current head: " + (head != null ? head.songPath : "None"));
    }

    public void setMusicInterface(music_interface mi) {
        this.musicInterface = mi;
    }

    void nextSongActionPerformed(java.awt.event.ActionEvent evt) {
        if (current != null && current.next != null) {
            current = current.next; // Move to the next song
            musicInterface.setSelectedSong(current.songPath); // Set the new song
            musicInterface.playSong(); // Start playing the new song
        } else {
            JOptionPane.showMessageDialog(this, "No more songs in the playlist.", "End of Playlist", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    void previousSongActionPerformed(java.awt.event.ActionEvent evt) {
        if (current != null && current.prev != null) {
            current = current.prev; // Move to the previous song
            musicInterface.setSelectedSong(current.songPath); // Set the new song
            musicInterface.playSong(); // Start playing the new song
        } else {
            JOptionPane.showMessageDialog(this, "No previous song in the playlist.", "Start of Playlist", JOptionPane.INFORMATION_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        remove_songs_btn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollBar2 = new javax.swing.JScrollBar();
        add_songs_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("playlistFrame"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(525, 500));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remove_songs_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        remove_songs_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-close-25.png"))); // NOI18N
        remove_songs_btn.setText("Remove Song");
        remove_songs_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        remove_songs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_songs_btnActionPerformed(evt);
            }
        });
        jPanel1.add(remove_songs_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 140, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-close-25.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 11, -1, -1));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 105, 460, 269));
        jPanel1.add(jScrollBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, 260));

        add_songs_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add_songs_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-26.png"))); // NOI18N
        add_songs_btn.setText("Add Songs");
        add_songs_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_songs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_songs_btnActionPerformed(evt);
            }
        });
        jPanel1.add(add_songs_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        saveSongsToFile();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void remove_songs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_songs_btnActionPerformed
        String selectedSong = jList1.getSelectedValue();
        if (selectedSong != null) {

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove this song?",
                    "Remove Song",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                listModel.removeElement(selectedSong);

                saveSongsToFile();
            }
        } else {

            JOptionPane.showMessageDialog(this,
                    "Please select a song to remove.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_remove_songs_btnActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        String selectedSong = jList1.getSelectedValue();
        if (selectedSong != null && musicInterface != null) {
            musicInterface.setSelectedSong(selectedSong);
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void add_songs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_songs_btnActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for (File file : selectedFiles) {
                String filePath = file.getAbsolutePath();
                if (!listModel.contains(filePath)) { // Avoid duplicates
                    listModel.addElement(filePath);
                }
            }
            populateLinkedList(); // Update the linked list
        }
    }//GEN-LAST:event_add_songs_btnActionPerformed

    private void saveSongsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SONG_FILE))) {
            for (int i = 0; i < listModel.size(); i++) {
                writer.write(listModel.getElementAt(i));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving songs: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Current song: " + (current != null ? current.songPath : "None"));

    }

    private void loadSongsFromFile() {
        File file = new File(SONG_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    listModel.addElement(line);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading songs: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            populateLinkedList(); // Ensure linked list is populated
        }
        System.out.println("Current song: " + (current != null ? current.songPath : "None"));

    }

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
            java.util.logging.Logger.getLogger(playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new playlist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_songs_btn;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton remove_songs_btn;
    // End of variables declaration//GEN-END:variables

}
