package Clientes.Aplicacion;

import Clientes.Modelo.ConsultasMateriales;
import Clientes.Modelo.ConsultasPasillos;
import Clientes.Modelo.Materiales;
import Clientes.Modelo.Pasillos;
import Clientes.Vista.VistaMateriales;
import Clientes.Vista.VistaPasillos;
import Clientes.Vista.VistaPrincipal;
import Clientes.Vista.VistaPrincipalAdministrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModeloVistaControladorPasillos implements ActionListener, MouseListener {

    VistaPrincipal frmControladorUsers;
    VistaPrincipalAdministrador frmControladorAdmin;
    Pasillos modPasillos;
    ConsultasPasillos modConsultasPasillos;
    VistaPasillos frmPasillos;
    DefaultTableModel modelo;
    ArrayList<Materiales> lista;
    int id_mat;

    public ModeloVistaControladorPasillos(VistaPrincipalAdministrador frmControladorAdmin, VistaPrincipal frmControladorUsers) {
        this.frmControladorUsers = frmControladorUsers;
        this.frmControladorAdmin = frmControladorAdmin;
        this.modPasillos = new Pasillos();
        this.modConsultasPasillos = new ConsultasPasillos();
        this.frmPasillos = new VistaPasillos();
        frmPasillos.nuevoPasillo.addActionListener(this);
        frmPasillos.borrarPasillo.addActionListener(this);
        frmPasillos.modificarPasillo.addActionListener(this);
        frmPasillos.tabla.addMouseListener(this);
        frmPasillos.volverInicio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmPasillos.nuevoPasillo) {
            modelo.setRowCount(0);
            if (compCampos()) {
                try {
                    JOptionPane.showMessageDialog(frmPasillos, insertarMaterial());
                } catch (ParseException ex) {
                    Logger.getLogger(ModeloVistaControladorPasillos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(frmPasillos, "Debes de tener todos los campos completos.");
            }
            cargarMateriales();
            frmPasillos.nuevoPasillo.setVisible(false);
        }

        if (e.getSource() == frmPasillos.borrarPasillo) {
            modelo.setRowCount(0);
            JOptionPane.showMessageDialog(frmPasillos, borrarMaterial());
            cargarMateriales();
            frmPasillos.borrarPasillo.setVisible(false);
        }
        
        if (e.getSource() == frmPasillos.modificarPasillo) {
            modelo.setRowCount(0);
            JOptionPane.showMessageDialog(frmPasillos, modificarMaterial());
            cargarMateriales();
            frmPasillos.modificarPasillo.setVisible(false);
        }
        if (e.getSource() == frmPasillos.volverInicio) {
            frmPasillos.setVisible(false);
            if (frmControladorAdmin != null) {
                frmControladorAdmin.setVisible(true);
            }
            else{
                frmControladorUsers.setVisible(true);
            }
        }
    }

    public void inicio() {
        frmPasillos.setVisible(true);
        modelo = new DefaultTableModel();
        frmPasillos.tabla.setModel(modelo);
        cargarEncabezado(modelo);
        cargarMateriales();
    }

    private void cargarMateriales() {
        ArrayList<Pasillos> listaMascotas = modConsultasPasillos.todoPasillos();
        for (Pasillos listaUsuario : listaMascotas) {
            String[] auxMateriales = new String[]{
                String.valueOf(listaUsuario.getID_PAS()),
                listaUsuario.getDESCRIPCION()
            };
            modelo.addRow(auxMateriales);
        }
    }

    public String insertarMaterial() throws ParseException {
        int idMax = modConsultasPasillos.recogerIdMatMax() + 1;
        modPasillos = new Pasillos(idMax, frmPasillos.txtDescripcion.getText());
        String res = modConsultasPasillos.insertar(modPasillos);
        return res;
    }

    public String modificarMaterial() {
        int idCom = 0;
        modPasillos = new Pasillos(id_mat, frmPasillos.txtDescripcion.getText());
        String res = modConsultasPasillos.modificarUsuario(modPasillos);
        return res;
    }

    public void buscarTrabajador(int cod) {
        Pasillos m = modConsultasPasillos.buscarPorCod(cod);
        frmPasillos.txtDescripcion.setText(m.getDESCRIPCION());
    }

    public String borrarMaterial() {
        String res = modConsultasPasillos.borrar(id_mat);
        return res;
    }

    private void cargarEncabezado(DefaultTableModel modelo) {
        modelo.addColumn("ID_PASILLO");
        modelo.addColumn("DESCRIPCION");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int filas;
        filas = frmPasillos.tabla.getSelectedRow();
        id_mat = Integer.parseInt(frmPasillos.tabla.getValueAt(filas, 0).toString());
        buscarTrabajador(id_mat);
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
        if (frmPasillos.txtDescripcion.getText().trim().isEmpty()) {
            comp = false;
        }
        return comp;
    }
}
