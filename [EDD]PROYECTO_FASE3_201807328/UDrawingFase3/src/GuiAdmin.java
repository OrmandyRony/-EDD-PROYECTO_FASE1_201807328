/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author orman
 */
public class GuiAdmin extends javax.swing.JDialog {
    Admin admin;
    private ImageIcon image;
    private Icon icono;

    /**
     * Creates new form GuiAdmin
     * @param parent
     * @param modal
     * @param adminV
     */
    public GuiAdmin(java.awt.Frame parent, boolean modal, Admin adminV) {
        super(parent, modal);
        this.setLocationRelativeTo(this);
        admin = adminV;
        initComponents();
    }
    
    

    private GuiAdmin(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnGraficar = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombreInsertar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dpiInsertar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        passwwordInsertar = new javax.swing.JTextField();
        Insertar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        dpiBuscar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnBuscarDPI = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        eliminarDPI = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        buscarDpiDatos = new javax.swing.JTextField();
        btnBuscarDatos = new javax.swing.JButton();
        cerrasSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Carga Masiva");

        btnBuscar.setText("Carga Masiva");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("Graficar Arbol B");

        btnGraficar.setText("Graficar");
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });

        imagen.setText("                                                                                                                                 No disponible");
        imagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Insertar");

        nombreInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreInsertarActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre:");

        jLabel5.setText("DPI:");

        jLabel6.setText("Contraseña:");

        Insertar.setText("Insertar");
        Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarActionPerformed(evt);
            }
        });

        jLabel7.setText("Modificar");

        jLabel8.setText("DPI:");

        btnBuscarDPI.setText("Modificar");
        btnBuscarDPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDPIActionPerformed(evt);
            }
        });

        jLabel9.setText("Eliminar");

        jLabel10.setText("DPI:");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel12.setText("Buscar");

        jLabel11.setText("DPI:");

        btnBuscarDatos.setText("Buscar");
        btnBuscarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDatosActionPerformed(evt);
            }
        });

        cerrasSesion.setText("Cerrar Sesion");
        cerrasSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrasSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(btnBuscar))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(btnGraficar))))
                            .addComponent(cerrasSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(243, 243, 243)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dpiBuscar))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(dpiInsertar)
                                            .addComponent(nombreInsertar)
                                            .addComponent(passwwordInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(104, 104, 104)
                                        .addComponent(Insertar)))
                                .addGap(98, 98, 98)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eliminarDPI))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel12))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buscarDpiDatos)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnBuscarDPI)
                                .addComponent(btnEliminar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btnBuscarDatos)))
                        .addGap(109, 109, 109))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(btnGraficar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nombreInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(dpiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(btnBuscarDPI)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dpiInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(passwwordInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(eliminarDPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Insertar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(buscarDpiDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarDatos)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(cerrasSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String ruta = "";
        JFileChooser fc = new JFileChooser();
        int op = fc.showOpenDialog(this);
        if (op == JFileChooser.APPROVE_OPTION) {
            ruta = fc.getSelectedFile().toString();
            //System.out.println("La ruta es: " + ruta);
        }

        File doc = new File(ruta);
        BufferedReader obj = null;
        try {
            obj = new BufferedReader(new FileReader(doc));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GuiAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        String strng;
        ruta = "";

        try {
            while ((strng = obj.readLine()) != null) {
                ruta += strng;
            }
        } catch (IOException ex) {
            Logger.getLogger(GuiAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONParser parser = new JSONParser();
        Object obj1 = null;
        try {
            obj1 = parser.parse(ruta);
        } catch (ParseException ex) {
            Logger.getLogger(GuiAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray json = (JSONArray) obj1;
        long numeroDpi;
        String dpi;
        String nombreCliente;
        String password;
        
        for (int i = 0; i < json.size(); i++) {
            JSONObject object = (JSONObject) json.get(i);
            dpi = object.get("dpi").toString();
            numeroDpi = Long.parseLong(dpi);
            nombreCliente = object.get("nombre_cliente").toString();
            password = object.get("password").toString();
            //System.out.println("Numero: " + numeroDpi + "NombreCliente: " + nombreCliente + "Password: " + password);
            admin.arbolBClientes.insertarNodo(numeroDpi, nombreCliente, password);
        }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
        // TODO add your handling code here:
        admin.arbolBClientes.graficar();
        this.pintarImagen(imagen, "grafica\\ArbolB.png");
        imagen.setText("");
        
    }//GEN-LAST:event_btnGraficarActionPerformed

    private void nombreInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreInsertarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreInsertarActionPerformed

    private void InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarActionPerformed
        // TODO add your handling code here:
        System.out.println("Insertar");
        admin.arbolBClientes.insertarNodo(Long.parseLong(dpiInsertar.getText()), nombreInsertar.getText(), passwwordInsertar.getText());
        dpiInsertar.setText("");
        nombreInsertar.setText("");
        passwwordInsertar.setText("");
    }//GEN-LAST:event_InsertarActionPerformed

    private void btnBuscarDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDPIActionPerformed
        // TODO add your handling code here:
        NodoB nodoCliente = admin.arbolBClientes.buscar(admin.arbolBClientes.raiz, Long.parseLong(dpiBuscar.getText()));
        if (nodoCliente != null) {
            String nuevoNombre = JOptionPane.showInputDialog("El nombre es: " + nodoCliente.cliente.nombre);
            nodoCliente.cliente.nombre = nuevoNombre;
            String nuevoPassword = JOptionPane.showInputDialog("El password es: " + nodoCliente.cliente.password);
            nodoCliente.cliente.password = nuevoPassword;
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no puedo ser encontrado");
        }
    }//GEN-LAST:event_btnBuscarDPIActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (admin.arbolBClientes.eliminar(Long.parseLong(eliminarDPI.getText()))) {
            JOptionPane.showMessageDialog(null, "El cliente a sido eliminado");
        } else {
            JOptionPane.showMessageDialog(null, "El cliente no pudo ser eleiminado");
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDatosActionPerformed
        // TODO add your handling code here:
        NodoB nodoCliente = admin.arbolBClientes.buscar(admin.arbolBClientes.raiz, Long.parseLong(buscarDpiDatos.getText()));
        if (nodoCliente != null) {
            JOptionPane.showMessageDialog(null, "El nombre es: " + nodoCliente.cliente.nombre 
                    + "\nEl password es: " + nodoCliente.cliente.password  + 
                    "La cantida de albumes es:" + nodoCliente.cliente.listaAlbumes.size
                    + "La catidad de imagenes es: " + nodoCliente.cliente.avlImagenes.size);
           
        } else {
            JOptionPane.showMessageDialog(null, "El cliente no puedo ser encontrado");
        }
    }//GEN-LAST:event_btnBuscarDatosActionPerformed

    private void cerrasSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrasSesionActionPerformed
        // TODO add your handling code here:
        Login login = new Login(this.admin);
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_cerrasSesionActionPerformed

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
            java.util.logging.Logger.getLogger(GuiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuiAdmin dialog = new GuiAdmin(new javax.swing.JFrame(), true);
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
    
    private void pintarImagen(JLabel label, String ruta) {
       this.image = new ImageIcon(ruta);
       this.icono = new ImageIcon(this.image.getImage().getScaledInstance(
               label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(this.icono);
        repaint();
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Insertar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarDPI;
    private javax.swing.JButton btnBuscarDatos;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGraficar;
    private javax.swing.JTextField buscarDpiDatos;
    private javax.swing.JButton cerrasSesion;
    private javax.swing.JTextField dpiBuscar;
    private javax.swing.JTextField dpiInsertar;
    private javax.swing.JTextField eliminarDPI;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nombreInsertar;
    private javax.swing.JTextField passwwordInsertar;
    // End of variables declaration//GEN-END:variables
}
