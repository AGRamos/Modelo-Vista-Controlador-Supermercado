package Clientes.Aplicacion;

import Clientes.Modelo.ConsultasReponedores;
import Clientes.Modelo.Materiales;
import Clientes.Modelo.Reponedores;
import Clientes.Vista.VistaPrincipal;
import Clientes.Vista.VistaPrincipalAdministrador;
import Clientes.Vista.VistaTrabajadores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModeloVistaControladorTrabajadores implements ActionListener, MouseListener {

    VistaPrincipal frmControladorUsers;
    VistaPrincipalAdministrador frmControladorAdmin;
    Reponedores modReponedores;
    ConsultasReponedores modConsultasRepondores;
    VistaTrabajadores frmReponedores;
    DefaultTableModel modelo;
    ArrayList<Materiales> lista;
    String dni;

    public ModeloVistaControladorTrabajadores(VistaPrincipalAdministrador frmControladorAdmin, VistaPrincipal frmControladorUsers) {
        this.frmControladorUsers = frmControladorUsers;
        this.frmControladorAdmin = frmControladorAdmin;
        this.modReponedores = new Reponedores();
        this.modConsultasRepondores = new ConsultasReponedores();
        this.frmReponedores = new VistaTrabajadores();
        frmReponedores.nuevoReponedor.setVisible(false);
        frmReponedores.borrarReponedor.setVisible(false);
        frmReponedores.modificarReponedor.setVisible(false);
        frmReponedores.nuevoReponedor.addActionListener(this);
        frmReponedores.borrarReponedor.addActionListener(this);
        frmReponedores.modificarReponedor.addActionListener(this);
        frmReponedores.tabla.addMouseListener(this);
        frmReponedores.menInsPas.addActionListener(this);
        frmReponedores.menBorrPas.addActionListener(this);
        frmReponedores.menModPas.addActionListener(this);
        frmReponedores.volverInicio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmReponedores.nuevoReponedor) {
            modelo.setRowCount(0);
            if (compCampos()) {
                try {
                    JOptionPane.showMessageDialog(frmReponedores, insertarReponedor());
                } catch (ParseException ex) {
                    Logger.getLogger(ModeloVistaControladorTrabajadores.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(frmReponedores, "Debes de tener todos los campos completos.");
            }
            cargarMateriales();
            frmReponedores.nuevoReponedor.setVisible(false);
        }

        if (e.getSource() == frmReponedores.borrarReponedor) {
            modelo.setRowCount(0);
            JOptionPane.showMessageDialog(frmReponedores, borrarReponedor());
            cargarMateriales();
            frmReponedores.borrarReponedor.setVisible(false);
        }

        if (e.getSource() == frmReponedores.modificarReponedor) {
            modelo.setRowCount(0);
            JOptionPane.showMessageDialog(frmReponedores, modificarReponedor());
            cargarMateriales();
            frmReponedores.modificarReponedor.setVisible(false);
        }

        if (e.getSource() == frmReponedores.menInsPas) {
            frmReponedores.nuevoReponedor.setVisible(true);
        }

        if (e.getSource() == frmReponedores.menBorrPas) {
            frmReponedores.borrarReponedor.setVisible(true);
        }

        if (e.getSource() == frmReponedores.menModPas) {
            frmReponedores.modificarReponedor.setVisible(true);
        }

        if (e.getSource() == frmReponedores.volverInicio) {
            frmReponedores.setVisible(false);
            if (frmControladorAdmin != null) {
                frmControladorAdmin.setVisible(true);
            } else {
                frmControladorUsers.setVisible(true);
            }
        }
    }

    public void inicio() {
        frmReponedores.setVisible(true);
        modelo = new DefaultTableModel();
        frmReponedores.tabla.setModel(modelo);
        cargarEncabezado(modelo);
        cargarMateriales();
    }

    private void cargarMateriales() {
        ArrayList<Reponedores> listaReponedores = modConsultasRepondores.todoReponedores();
        for (Reponedores listaRep : listaReponedores) {
            String[] auxMateriales = new String[]{
                listaRep.getDNI(),
                listaRep.getNOMBRE(),
                String.valueOf(listaRep.getFecha())
            };
            modelo.addRow(auxMateriales);
        }
    }

    public String insertarReponedor() throws ParseException {

        long fecha = frmReponedores.jcDate.getDate().getTime();
        java.sql.Date fechaMod = new Date(fecha);
        modReponedores = new Reponedores(frmReponedores.txtDNI.getText(), frmReponedores.txtNombre.getText(), fechaMod);
        String res = modConsultasRepondores.insertar(modReponedores);
        return res;
    }

    public String modificarReponedor() {
        long fecha = frmReponedores.jcDate.getDate().getTime();
        java.sql.Date fechaMod = new Date(fecha);
        modReponedores = new Reponedores(frmReponedores.txtDNI.getText(), frmReponedores.txtNombre.getText(), fechaMod);
        String res = modConsultasRepondores.modificarUsuario(modReponedores);
        return res;
    }

    public void buscarTrabajador(String cod) {
        Reponedores m = modConsultasRepondores.buscarPorCod(cod);
        frmReponedores.txtDNI.setText(m.getDNI());
        frmReponedores.txtNombre.setText(m.getNOMBRE());
        frmReponedores.jcDate.setDate(m.getFecha());
    }

    public String borrarReponedor() {
        String res = modConsultasRepondores.borrar(dni);
        return res;
    }

    private void cargarEncabezado(DefaultTableModel modelo) {
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("FECHA NACIMIENTO");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int filas;
        filas = frmReponedores.tabla.getSelectedRow();
        dni = frmReponedores.tabla.getValueAt(filas, 0).toString();
        buscarTrabajador(dni);
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

    private boolean compCampos() {
        boolean comp = true;
        if (frmReponedores.txtNombre.getText().trim().isEmpty()) {
            comp = false;
        }

        if (frmReponedores.txtDNI.getText().trim().isEmpty()) {
            comp = false;
        }

        if (frmReponedores.jcDate.getDate() == null) {
            comp = false;
        }
        return comp;
    }
}
