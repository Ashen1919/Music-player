/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package music_player;

import javazoom.jl.player.Player;
import javax.swing.*;
import java.io.FileInputStream;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import java.io.BufferedInputStream;

/**
 *
 * @author Asus
 */
public class music_interface extends javax.swing.JFrame {

    private playlist playlistFrame;
    private Player mp3Player;
    private String selectedSong;
    private Player player;
    private Thread playerThread;
    private boolean isPaused = false;
    private BufferedInputStream bufferedInputStream;
    private long pauseLocation;
    private long totalSongLength;
    private String filePath;
    private Node head;
    private Node tail;
    private Node current;

    public static class Node {

        String songPath;
        Node next;
        Node prev;

        public Node(String songPath) {
            this.songPath = songPath;
            this.next = null;
            this.prev = null;
        }
    }

    public void setPlaylistFrame(playlist playlist) {
        this.playlistFrame = playlist;
    }

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (playlistFrame != null) {
            playlistFrame.nextSongActionPerformed(evt);  // Call the next song method in playlist
        }
    }

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (playlistFrame != null) {
            playlistFrame.previousSongActionPerformed(evt);  // Call the previous song method in playlist
        }
    }

    /**
     * Creates new form music_interface
     */
    public music_interface() {
        initComponents();
    }

    public void setSelectedSong(String songPath) {
        selectedSong = songPath;

        try {
            Mp3File mp3File = new Mp3File(songPath);

            String songTitle = "Unknown Title";
            String artistName = "Unknown Artist";
            String songLength = "Unknown Length";

            int lengthInSeconds = (int) mp3File.getLengthInSeconds();
            int minutes = lengthInSeconds / 60;
            int seconds = lengthInSeconds % 60;
            songLength = String.format("%02d:%02d", minutes, seconds);

            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                songTitle = id3v2Tag.getTitle() != null ? id3v2Tag.getTitle() : "Unknown Title";
                artistName = id3v2Tag.getArtist() != null ? id3v2Tag.getArtist() : "Unknown Artist";

            } else if (mp3File.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
                songTitle = id3v1Tag.getTitle() != null ? id3v1Tag.getTitle() : "Unknown Title";
                artistName = id3v1Tag.getArtist() != null ? id3v1Tag.getArtist() : "Unknown Artist";
            }

            jLabel4.setText(songTitle);
            jLabel5.setText(artistName);
            endPoint_lable.setText(songLength);
            jSlider1.setMinimum(0);
            jSlider1.setMaximum(lengthInSeconds);
            

        } catch (Exception e) {
            jLabel4.setText("Unknown Title");
            jLabel5.setText("Unknown Artist");
            endPoint_lable.setText("Unknown Length");
            System.err.println("Error reading metadata: " + e.getMessage());
        }
    }

    private String formatTime(int lengthInSeconds) {
        int minutes = lengthInSeconds / 60;
        int seconds = lengthInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private Timer sliderTimer;
    private int elapsedTimeInSeconds = 0;

    void playSong() {
        if (selectedSong == null || selectedSong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No song selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (mp3Player != null) {
                mp3Player.close();
            }

            FileInputStream fis = new FileInputStream(selectedSong);
            bufferedInputStream = new BufferedInputStream(fis);
            mp3Player = new Player(bufferedInputStream);

            // Reset elapsed time
            elapsedTimeInSeconds = 0;

            // Timer to update the slider
            if (sliderTimer != null) {
                sliderTimer.stop();
            }
            sliderTimer = new Timer(1000, e -> updateSlider());
            sliderTimer.start();

            new Thread(() -> {
                try {
                    mp3Player.play();
                    sliderTimer.stop(); // Stop the timer when the song ends
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error playing song: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }).start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading song: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSlider() {
        // Increment elapsed time
        elapsedTimeInSeconds++;

        // Update the slider value and time label
        jSlider1.setValue(elapsedTimeInSeconds);
        jLabel3.setText(formatTime(elapsedTimeInSeconds));

        // Stop the timer if the song has reached its end
        if (elapsedTimeInSeconds >= jSlider1.getMaximum()) {
            sliderTimer.stop();
        }
    }

    private void pauseSong() {
        if (selectedSong == null || selectedSong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No song selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (mp3Player != null) {
                mp3Player.close();
            }

            FileInputStream fis = new FileInputStream(selectedSong);
            mp3Player = new Player(fis);

            new Thread(() -> {
                try {
                    mp3Player.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error Pausing song: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }).start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading song: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        playlist_btn = new javax.swing.JButton();
        play_btn = new javax.swing.JButton();
        previous_btn = new javax.swing.JButton();
        next_btn = new javax.swing.JButton();
        shuffle_btn = new javax.swing.JButton();
        fav_btn = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        pause_btn = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        endPoint_lable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 670));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 600));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setText("Mp3 Player");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 280, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unnamed.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 270, 260));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 350, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 330, 30));

        playlist_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-playlist-35.png"))); // NOI18N
        playlist_btn.setBorder(null);
        playlist_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playlist_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playlist_btnActionPerformed(evt);
            }
        });
        jPanel2.add(playlist_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 550, 50, 40));

        play_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-play-70.png"))); // NOI18N
        play_btn.setBorder(null);
        play_btn.setBorderPainted(false);
        play_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        play_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play_btnActionPerformed(evt);
            }
        });
        jPanel2.add(play_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 70, 70));

        previous_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-previous-35.png"))); // NOI18N
        previous_btn.setBorder(null);
        previous_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        previous_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previous_btnActionPerformed(evt);
            }
        });
        jPanel2.add(previous_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 30, 30));

        next_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-next-35.png"))); // NOI18N
        next_btn.setBorder(null);
        next_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        next_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_btnActionPerformed(evt);
            }
        });
        jPanel2.add(next_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 30, 30));

        shuffle_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-shuffle-35.png"))); // NOI18N
        shuffle_btn.setBorder(null);
        shuffle_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(shuffle_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 40, -1));

        fav_btn.setBackground(new java.awt.Color(242, 242, 242));
        fav_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-heart-35.png"))); // NOI18N
        fav_btn.setBorder(null);
        fav_btn.setBorderPainted(false);
        fav_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fav_btn.setFocusPainted(false);
        fav_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fav_btnActionPerformed(evt);
            }
        });
        jPanel2.add(fav_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 550, 40, 40));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-previous-35.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 30, 30));

        pause_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pause-70.png"))); // NOI18N
        pause_btn.setBorder(null);
        pause_btn.setBorderPainted(false);
        pause_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pause_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pause_btnActionPerformed(evt);
            }
        });
        jPanel2.add(pause_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 70, 70));

        jSlider1.setForeground(new java.awt.Color(0, 51, 255));
        jSlider1.setValue(0);
        jSlider1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jPanel2.add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 490, 270, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("00:00");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 482, 40, 30));

        endPoint_lable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(endPoint_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, 50, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fav_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fav_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fav_btnActionPerformed

    private void playlist_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playlist_btnActionPerformed
        if (playlistFrame == null) {
            playlistFrame = new playlist(); // Create the playlist frame
            playlistFrame.setMusicInterface(this); // Link to the music_interface
        }
        playlistFrame.setVisible(true);
    }//GEN-LAST:event_playlist_btnActionPerformed

    private void play_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play_btnActionPerformed
        playSong();
    }//GEN-LAST:event_play_btnActionPerformed

    private void pause_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pause_btnActionPerformed
        pauseSong();
    }//GEN-LAST:event_pause_btnActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        if (jSlider1.getValueIsAdjusting()) {
            int seekPosition = jSlider1.getValue();
            long skipBytes = (seekPosition * totalSongLength) / jSlider1.getMaximum();

            try {
                bufferedInputStream.close();
                FileInputStream fis = new FileInputStream(selectedSong);
                bufferedInputStream = new BufferedInputStream(fis);
                fis.skip(skipBytes);
                mp3Player.close();
                mp3Player = new Player(bufferedInputStream);

                new Thread(() -> {
                    try {
                        mp3Player.play();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Error seeking song: " + e.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }).start();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error seeking: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jSlider1StateChanged

    private void next_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_btnActionPerformed
        if (playlistFrame != null) { // Ensure the playlistFrame reference is set
            playlistFrame.nextSongActionPerformed(evt); // Call the next song method
        } else {
            JOptionPane.showMessageDialog(this, "Playlist is not connected.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_next_btnActionPerformed

    private void previous_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previous_btnActionPerformed
        if (playlistFrame != null) { // Ensure the playlistFrame reference is set
            playlistFrame.previousSongActionPerformed(evt); // Call the previous song method
        } else {
            JOptionPane.showMessageDialog(this, "Playlist is not connected.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_previous_btnActionPerformed

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
            java.util.logging.Logger.getLogger(music_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(music_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(music_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(music_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new music_interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel endPoint_lable;
    private javax.swing.JButton fav_btn;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JButton next_btn;
    private javax.swing.JButton pause_btn;
    private javax.swing.JButton play_btn;
    private javax.swing.JButton playlist_btn;
    private javax.swing.JButton previous_btn;
    private javax.swing.JButton shuffle_btn;
    // End of variables declaration//GEN-END:variables

    private void close() {
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
