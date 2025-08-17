/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JFrame;

public class Game extends javax.swing.JFrame {
    
    int veryCloseRange, closeRange, moderateRange, farRange;
    JFrame fr = new JFrame();
    
    int target,range,attemptCount;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Game.class.getName());

    /**
     * Creates new form Game
     * @param range
     * @param msg
     */
    public Game(int range, String msg) {

        initComponents(); // Initialize GUI components

        this.range = range; // Store the selected range
        setRange(msg, range); // Set feedback thresholds based on difficulty and range
        target = generateRandom(1, range); // Generate the target number to guess
        attemptCount = 0; // Reset attempt counter

        // L1 and L2 are JLabel components used for displaying difficulty and range
        L1.setText(L1.getText() + " " + msg);   // Show selected difficulty
        L2.setText(L2.getText() + "" + range);  // Show selected range
        L3.setText("Enter your guess below 👇"); // Reset feedback label (if applicable)

        // b1 and b2 are JButton components
        b1.setEnabled(true);  // Enable the "Submit" button
        b2.setEnabled(false); // Disable the "Play Again" button until game ends

        // fr is a JFrame used to anchor JOptionPane dialogs at a fixed location
        fr.setUndecorated(true);      // Make the frame invisible (no borders)
        fr.setSize(0, 0);             // Set size to zero so it's not visible
        fr.setLocation(500, 300);     // Position it at center of your 1000x600 game window
        fr.setVisible(true);          // Must be visible to anchor dialogs
    }
    private static final Random rand = new Random(); // Shared instance

    public static int generateRandom(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum.");
        }
        return rand.nextInt(max - min + 1) + min;
    }

    
    public final void setRange(String difficulty, int maxRange) {
        veryCloseRange = Math.max(1, maxRange / 50);   // ~2%
        closeRange     = maxRange / 20;                // ~5%
        moderateRange  = maxRange / 7;                 // ~15%
        farRange       = maxRange / 3;                 // ~30%
    }
    
    public void check(int val){
        attemptCount += 1;

        if (val == target) {
            JLabel messageLabel = new JLabel("<html><div style='text-align:center;'>"
                + "🎉 <b>Congratulations!</b> You guessed the correct number in "
                + attemptCount + " attempt" + ((attemptCount != 1) ? "s" : "") + "!<br><br>"
                + "You're sharper than a binary search and faster than recursion!<br><br>"
                + "<i>Want to play again and test your luck?</i> 🍀"
                + "</div></html>");
            messageLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JOptionPane.showMessageDialog(fr, messageLabel, "Victory!", JOptionPane.INFORMATION_MESSAGE);
            b2.setEnabled(true);
            b1.setEnabled(false);
            t1.setEnabled(false);
        } else {
            int diff = Math.abs(val - target);
            String directionHint, rangeHint;

            // Directional hint based on how far off the guess is
            if (val > target) {
                if (diff <= veryCloseRange) {
                    directionHint = "➡ <b>Just a bit too high!</b><br><i>You're almost there!</i>";
                } else if (diff <= closeRange) {
                    directionHint = "➡ <b>Too high!</b><br><i>Try a smaller number.</i>";
                } else if (diff <= moderateRange) {
                    directionHint = "➡ <b>Quite high!</b><br><i>You're overshooting — dial it down!</i>";
                } else {
                    directionHint = "➡ <b>Way too high!</b><br><i>You're in the stratosphere — come back down!</i> 🚀";
                }
            } else {
                if (diff <= veryCloseRange) {
                    directionHint = "⬅ <b>Just a bit too low!</b><br><i>You're almost there!</i>";
                } else if (diff <= closeRange) {
                    directionHint = "⬅ <b>Too low!</b><br><i>Try a bigger number.</i>";
                } else if (diff <= moderateRange) {
                    directionHint = "⬅ <b>Quite low!</b><br><i>You're undershooting — aim higher!</i>";
                } else {
                    directionHint = "⬅ <b>Way too low!</b><br><i>You're in the basement — climb up!</i> 🧗";
                }
            }

            // Range-based hint
            if (diff <= veryCloseRange) {
                rangeHint = "<i>🔥 Very Close!</i><br>🔥<b>You're burning hot!</b> Just a tiny nudge away!<br><br>";
            } else if (diff <= closeRange) {
                rangeHint = "<i>😊 Getting Warm!</i><br>😮<b>So close!</b> You're just a few steps away from victory.<br><br>"
                          + "<i>Give it another shot — your lucky number might be next!</i> 🍀";
            } else if (diff <= moderateRange) {
                rangeHint = "<i>️Still Off!</i><br>🤔<b>Not quite there yet...</b><br><br>"
                          + "<i>You're circling the target — keep narrowing it down!</i>";
            } else if (diff <= farRange) {
                rangeHint = "❄ <b>Ice cold!</b><br><br>"
                            + "<i>Your guess is freezing — better warm up with a new strategy!</i> ❄️";
            } else {
                rangeHint = "🌌 <b>You're way off!</b><br><br>"
                          + "<i>Time to recalibrate your sensors — the target's in another galaxy!</i> 🚀";
            }

            JLabel messageLabel = new JLabel("<html><div style='text-align:center;'>"
                + directionHint + "<br><br>"
                + rangeHint + "<br><br>"
                + "Attempt #" + attemptCount
                + "</div></html>");

            messageLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JOptionPane.showMessageDialog(fr, messageLabel, "Hint", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Game() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        L1 = new javax.swing.JLabel();
        L2 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        L3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        L1.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        L1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L1.setText("Difficulty :");

        L2.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        L2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L2.setText("Range : 1 - ");

        t1.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ActionPerformed(evt);
            }
        });
        t1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                t1PropertyChange(evt);
            }
        });

        b1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        b1.setText("Guess");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        b2.setText("Retry");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        jButton2.setText("<--");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        L3.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        L3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(390, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(L1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b1)
                            .addComponent(b2)
                            .addComponent(L3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(379, 379, 379))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {b1, b2, t1});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(L1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(L2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(L3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(b1)
                .addGap(25, 25, 25)
                .addComponent(b2)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(fr, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Show goodbye message and exit\
            JLabel messageLabel = new JLabel("<html><center>Thanks for playing <b>Guess The Number</b>! 🎮<br>" +
                "Whether it was logic, luck, or a little bit of both—you did great! 🌟<br><br>" +
                "Come back soon for more challenges. Until then…<br>keep guessing and keep winning! 🍀</center></html>");

            // Set custom font
            messageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // You can try "SansSerif", "Monospaced", etc.
            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JOptionPane.showMessageDialog(fr, messageLabel, "Goodbye!", JOptionPane.PLAIN_MESSAGE);
            this.setVisible(false);
            System.exit(0);
        }        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ActionPerformed

    private void t1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_t1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_t1PropertyChange

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        check(Integer.parseInt(t1.getText()));
        t1.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        t1.setEnabled(true);             // Enable input field
        t1.setText("");                  // Clear previous input
        target = generateRandom(1, range); // Generate new target
        attemptCount = 0;                // Reset attempt counter        

        b2.setEnabled(false);            // Disable "Play Again"
        b1.setEnabled(true);             // Enable "Submit"
        // TODO add your handling code here:
    }//GEN-LAST:event_b2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        WelcomePage ob = new WelcomePage();
        ob.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Game().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel L1;
    private javax.swing.JLabel L2;
    private javax.swing.JLabel L3;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField t1;
    // End of variables declaration//GEN-END:variables
}