package Clientes.Aplicacion;

import Clientes.Modelo.ConsultasMateriales;
import Clientes.Modelo.Materiales;
import Clientes.Vista.VistaMateriales;
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

public class ModeloVistaControladorMateriales implements ActionListener, MouseListener {

    VistaPrincipal frmControladorUsers;
    VistaPrincipalAdministrador frmControladorAdmin;
    Materiales modMateriales;
    ConsultasMateriales modConsultasMateriales;
    VistaMateriales frmMateriales;
    DefaultTableModel modelo;
    ArrayList<Materiales> lista;
    int id_mat;

    public ModeloVistaControladorMateriales(VistaPrincipalAdministrador frmControladorAdmin, VistaPrincipal frmControladorUsers) {
        this.frmControladorUsers = frmControladorUsers;
        this.frmControladorAdmin = frmControladorAdmin;
        this.modMateriales = new Materiales();
        this.modConsultasMateriales = new ConsultasMateriales();
        this.frmMateriales = new VistaMateriales();
        frmMateriales.nuevoMaterial.setVisible(false);
        frmMateriales.borrarMaterial.setVisible(false);
        frmMateriales.modificarMaterial.setVisible(false);
        frmMateriales.nuevoMaterial.addActionListener(this);
        frmMateriales.borrarMaterial.addActionListener(this);
        frmMateriales.modificarMaterial.addActionListener(this);
        frmMateriales.tabla.addMouseListener(this);
        frmMateriales.menInsMasc.addActionListener(this);
        frmMateriales.menBorrMasc.addActionListener(this);
        frmMateriales.menModMasc.addActionListener(this);
        frmMateriales.volverInicio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmMateriales.nuevoMaterial) {
            modelo.setRowCount(0);
            if (compCampos()) {
                try {
                    JOptionPane.showMessageDialog(frmMateriales, insertarMaterial());
                } catch (ParseException ex) {
                    Logger.getLogger(ModeloVistaControladorMateriales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(frmMateriales, "Debes de tener todos los campos completos.");
            }
            cargarMateriales();
            frmMateriales.nuevoMaterial.setVisible(false);
        }

        if (e.getSource() == frmMateriales.borrarMaterial) {
            modelo.setRowCount(0);
            JOptionPane.showMessageDialog(frmMateriales, borrarMaterial());
            cargarMateriales();
            frmMateriales.borrarMaterial.setVisible(false);
        }
        
        if (e.getSource() == frmMateriales.modificarMaterial) {
            modelo.setRowCount(0);
            JOptionPane.showMessageDialog(frmMateriales, modificarMaterial());
            cargarMateriales();
            frmMateriales.modificarMaterial.setVisible(false);
        }

        if (e.getSource() == frmMateriales.menInsMasc) {
            frmMateriales.nuevoMaterial.setVisible(true);
        }
        
        if (e.getSource() == frmMateriales.menBorrMasc) {
            frmMateriales.borrarMaterial.setVisible(true);
        }
        
        if (e.getSource() == frmMateriales.menModMasc) {
            frmMateriales.modificarMaterial.setVisible(true);
        }
        
        if (e.getSource() == frmMateriales.volverInicio) {
            frmMateriales.setVisible(false);
            if (frmControladorAdmin != null) {
                frmControladorAdmin.setVisible(true);
            }
            else{
                frmControladorUsers.setVisible(true);
            }
        }
    }

    public void inicio() {
        frmMateriales.setVisible(true);
        modelo = new DefaultTableModel();
        frmMateriales.tabla.setModel(modelo);
        cargarEncabezado(modelo);
        cargarMateriales();
        cargarComboPasillo();
    }

    private void cargarMateriales() {
        ArrayList<Materiales> listaMascotas = modConsultasMateriales.todoMateriales();
        for (Materiales listaUsuario : listaMascotas) {
            String[] auxMateriales = new String[]{
                String.valueOf(listaUsuario.getID_MAT()),
                listaUsuario.getNOMBRE(),
                listaUsuario.getDESCRIPCION(),
                String.valueOf(listaUsuario.getID_PAS())
            };
            modelo.addRow(auxMateriales);
        }
    }

    public String insertarMaterial() throws ParseException {
        int idCom =0;
        int idMax = modConsultasMateriales.recogerIdMatMax() + 1;
        idCom = modConsultasMateriales.recogerEnteroCom(frmMateriales.comboPasillo.getSelectedItem().toString());
        modMateriales = new Materiales(idMax, frmMateriales.txtNombre.getText(), frmMateriales.txtDescripcion.getText(), idCom);
        String res = modConsultasMateriales.insertar(modMateriales);
        return res;
    }

    public String modificarMaterial() {
        int idCom = 0;
        idCom = modConsultasMateriales.recogerEnteroCom(frmMateriales.comboPasillo.getSelectedItem().toString());
        modMateriales = new Materiales(id_mat, frmMateriales.txtNombre.getText(), frmMateriales.txtDescripcion.getText(), idCom);
        String res = modConsultasMateriales.modificarUsuario(modMateriales);
        return res;
    }

    public void buscarTrabajador(int cod) {
        Materiales m = modConsultasMateriales.buscarPorCod(cod);
        frmMateriales.txtNombre.setText(m.getNOMBRE());
        frmMateriales.txtDescripcion.setText(String.valueOf(m.getDESCRIPCION()));
        frmMateriales.comboPasillo.setSelectedItem(modConsultasMateriales.combPasilloString(m.getID_PAS()));
        setearImagen(m);
    }

    public String borrarMaterial() {
        String res = modConsultasMateriales.borrar(id_mat);
        return res;
    }

    private void cargarEncabezado(DefaultTableModel modelo) {
        modelo.addColumn("ID MATERIAL");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("ID_PASILLO");
    }

    public void cargarComboPasillo() {
        String[] españa = modConsultasMateriales.combPasillos();
        frmMateriales.comboPasillo.setModel(new javax.swing.DefaultComboBoxModel<>(españa));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int filas;
        filas = frmMateriales.tabla.getSelectedRow();
        id_mat = Integer.parseInt(frmMateriales.tabla.getValueAt(filas, 0).toString());
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
        if (frmMateriales.txtNombre.getText().trim().isEmpty()) {
            comp = false;
        }
        if (frmMateriales.txtDescripcion.getText().trim().isEmpty()) {
            comp = false;
        }
        return comp;
    }

    private void setearImagen(Materiales t) {
        frmMateriales.lblImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Clientes/imagenes/" + t.getID_MAT()+ ".jpg")));
    }

}
