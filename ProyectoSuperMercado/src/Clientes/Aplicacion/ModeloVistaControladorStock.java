package Clientes.Aplicacion;

import Clientes.Modelo.ConsultasMateriales;
import Clientes.Modelo.ConsultasReponenPasillos;
import Clientes.Modelo.Materiales;
import Clientes.Modelo.ReponenPasillos;
import Clientes.Vista.VistaMateriales;
import Clientes.Vista.VistaPrincipal;
import Clientes.Vista.VistaPrincipalAdministrador;
import Clientes.Vista.VistaStock;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModeloVistaControladorStock implements ActionListener, MouseListener {

    VistaPrincipal frmControladorUsers;
    VistaPrincipalAdministrador frmControladorAdmin;
    ReponenPasillos modStock;
    ConsultasReponenPasillos modConsultasReponenPasillos;
    VistaStock frmStock;
    DefaultTableModel modelo;
    ArrayList<Materiales> lista;
    int id_mat;
    String dni_Rep;
    java.sql.Date fecha_Rep;

    public ModeloVistaControladorStock(VistaPrincipalAdministrador frmControladorAdmin, VistaPrincipal frmControladorUsers) {
        this.frmControladorUsers = frmControladorUsers;
        this.frmControladorAdmin = frmControladorAdmin;
        this.modStock = new ReponenPasillos();
        this.modConsultasReponenPasillos = new ConsultasReponenPasillos();
        this.frmStock = new VistaStock();
        frmStock.nuevoStock.setVisible(false);
        frmStock.borrarStock.setVisible(false);
        frmStock.modificarStock.setVisible(false);
        frmStock.nuevoStock.addActionListener(this);
        frmStock.borrarStock.addActionListener(this);
        frmStock.modificarStock.addActionListener(this);
        frmStock.tabla.addMouseListener(this);
        frmStock.menInsMasc.addActionListener(this);
        frmStock.menBorrMasc.addActionListener(this);
        frmStock.menModMasc.addActionListener(this);
        frmStock.volverInicio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmStock.nuevoStock) {
            modelo.setRowCount(0);
            if (compCampos()) {
                try {
                    JOptionPane.showMessageDialog(frmStock, insertarStock());
                } catch (ParseException ex) {
                    Logger.getLogger(ModeloVistaControladorStock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(frmStock, "Debes de tener todos los campos completos.");
            }
            cargarStock();
            frmStock.nuevoStock.setVisible(false);
        }

        if (e.getSource() == frmStock.borrarStock) {
            modelo.setRowCount(0);
            JOptionPane.showMessageDialog(frmStock, borrarStock());
            cargarStock();
            frmStock.borrarStock.setVisible(false);
        }
        
        if (e.getSource() == frmStock.modificarStock) {
            modelo.setRowCount(0);
            JOptionPane.showMessageDialog(frmStock, modificarStock());
            cargarStock();
            frmStock.modificarStock.setVisible(false);
            frmStock.comboProducto.setEnabled(true);
            frmStock.jcDate.setEnabled(true);
            frmStock.comboReponedor.setEnabled(true);
        }

        if (e.getSource() == frmStock.menInsMasc) {
            frmStock.nuevoStock.setVisible(true);
        }
        
        if (e.getSource() == frmStock.menBorrMasc) {
            frmStock.borrarStock.setVisible(true);
        }
        
        if (e.getSource() == frmStock.menModMasc) {
            frmStock.modificarStock.setVisible(true);
            frmStock.comboProducto.setEnabled(false);
            frmStock.jcDate.setEnabled(false);
            frmStock.comboReponedor.setEnabled(false);
        }
        
        if (e.getSource() == frmStock.volverInicio) {
            frmStock.setVisible(false);
            if (frmControladorAdmin != null) {
                frmControladorAdmin.setVisible(true);
            }
            else{
                frmControladorUsers.setVisible(true);
            }
        }
    }

    public void inicio() {
        frmStock.setVisible(true);
        modelo = new DefaultTableModel();
        frmStock.tabla.setModel(modelo);
        cargarEncabezado(modelo);
        cargarStock();
        cargarComboReponedor();
        cargarComboProductos();
    }

    private void cargarStock() {
        ArrayList<ReponenPasillos> listaReponenPasillos = modConsultasReponenPasillos.todoStock();
        for (ReponenPasillos listaStock : listaReponenPasillos) {
            String[] auxMateriales = new String[]{
                String.valueOf(listaStock.getID_MAT()),
                listaStock.getREP_DNI(),
                String.valueOf(listaStock.getCANTIDAD()),
                String.valueOf(listaStock.getFecha())
            };
            modelo.addRow(auxMateriales);
        }
    }

    public String insertarStock() throws ParseException {
        String dni = frmStock.comboReponedor.getSelectedItem().toString();
        int idComMat =0;
        idComMat = modConsultasReponenPasillos.recogerEnteroComMat(frmStock.comboProducto.getSelectedItem().toString());
        long fecha = frmStock.jcDate.getDate().getTime();
        java.sql.Date fechaFinal = new Date(fecha);
        modStock = new ReponenPasillos(idComMat, dni, Integer.parseInt(frmStock.txtCantidad.getText()), fechaFinal);
        String res = modConsultasReponenPasillos.insertar(modStock);
        return res;
    }

    public String modificarStock() {
        modStock = new ReponenPasillos(id_mat, dni_Rep, Integer.parseInt(frmStock.txtCantidad.getText()), fecha_Rep);
        String res = modConsultasReponenPasillos.modificarStock(modStock);
        return res;
    }

    public void buscarStock(int cod, String dni, java.sql.Date fecha) {
        ReponenPasillos m = modConsultasReponenPasillos.buscarPorCod(cod, dni, fecha);
        frmStock.txtCantidad.setText(String.valueOf(m.getCANTIDAD()));
        frmStock.comboProducto.setSelectedItem(modConsultasReponenPasillos.recogerNomComMat(m.getID_MAT()));
        frmStock.comboReponedor.setSelectedItem(m.getREP_DNI());
        frmStock.jcDate.setDate(m.getFecha());
        setearImagen(m);
    }

    public String borrarStock() {
        String res = modConsultasReponenPasillos.borrar(id_mat, dni_Rep, fecha_Rep);
        return res;
    }

    private void cargarEncabezado(DefaultTableModel modelo) {
        modelo.addColumn("ID MATERIAL");
        modelo.addColumn("DNI");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("FECHA REPOSICION");
    }

    public void cargarComboReponedor() {
        String[] españa = modConsultasReponenPasillos.comReponedor();
        frmStock.comboReponedor.setModel(new javax.swing.DefaultComboBoxModel<>(españa));
    }
    
    public void cargarComboProductos() {
        String[] españa = modConsultasReponenPasillos.comProductos();
        frmStock.comboProducto.setModel(new javax.swing.DefaultComboBoxModel<>(españa));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            int filas;
            filas = frmStock.tabla.getSelectedRow();
            id_mat = Integer.parseInt(frmStock.tabla.getValueAt(filas, 0).toString());
            dni_Rep = frmStock.tabla.getValueAt(filas, 1).toString();
            long fecha = new SimpleDateFormat("yyyy-MM-dd").parse(frmStock.tabla.getValueAt(filas, 3).toString()).getTime();
            fecha_Rep = new Date(fecha);
            buscarStock(id_mat, dni_Rep, fecha_Rep);
        } catch (ParseException ex) {
            Logger.getLogger(ModeloVistaControladorStock.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (frmStock.txtCantidad.getText().trim().isEmpty()) {
            comp = false;
        }
        
        if (frmStock.jcDate.getDate() == null) {
            comp = false;
        }
        
        return comp;
    }

    private void setearImagen(ReponenPasillos t) {
        frmStock.lblImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Clientes/imagenes/" + t.getID_MAT()+ ".jpg")));
    }

}
