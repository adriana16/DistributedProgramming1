public class VisualCmmdc extends javax.swing.JFrame {
    
    public VisualCmmdc(){
        initComponents();
    }
    
    private long cmmdc(long m,long n){
      long r, c;
      do {
        c = n;
        r = m % n;
        m = n;
        n = r;
      }
      while (r != 0);
      return c;
    }

    private void initComponents() {//GEN-BEGIN:initComponents
        mLabel = new javax.swing.JLabel();
        mTextField = new javax.swing.JTextField();
        nLabel = new javax.swing.JLabel();
        nTextField = new javax.swing.JTextField();
        cmmdcButton = new javax.swing.JButton();
        cmmdcTextField = new javax.swing.JTextField();

        getContentPane().setLayout(new java.awt.GridLayout(3, 2));

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        mLabel.setText("Primul numar:");
        getContentPane().add(mLabel);

        mTextField.setText("1");
        getContentPane().add(mTextField);

        nLabel.setText("Al doilea numar:");
        getContentPane().add(nLabel);

        nTextField.setText("1");
        getContentPane().add(nTextField);

        cmmdcButton.setText("Calculeaza:");
        cmmdcButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmmdcButtonMouseClicked(evt);
            }
        });

        getContentPane().add(cmmdcButton);

        cmmdcTextField.setText("1");
        getContentPane().add(cmmdcTextField);

        pack();
    }//GEN-END:initComponents

    private void cmmdcButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmmdcButtonMouseClicked
        // Add your handling code here:
      try{  
        String sm=mTextField.getText();
        String sn=nTextField.getText();
        long m=Long.parseLong(sm);
        long n=Long.parseLong(sn);
        long c=cmmdc(m,n);
        String s=(new Long(c)).toString();
        cmmdcTextField.setText(s);
      }
      catch(Exception e){
        System.err.println("Exception : "+e.getMessage());
      }
    }//GEN-LAST:event_cmmdcButtonMouseClicked
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new VisualCmmdc().setVisible(true);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmmdcButton;
    private javax.swing.JLabel mLabel;
    private javax.swing.JLabel nLabel;
    private javax.swing.JTextField mTextField;
    private javax.swing.JTextField nTextField;
    private javax.swing.JTextField cmmdcTextField;
    // End of variables declaration//GEN-END:variables
    
}
