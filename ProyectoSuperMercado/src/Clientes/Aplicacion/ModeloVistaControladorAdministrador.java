package Clientes.Aplicacion;

import Clientes.Vista.VistaPrincipal;
import Clientes.Vista.VistaPrincipalAdministrador;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ModeloVistaControladorAdministrador implements MouseListener{

    VistaPrincipal frmControladorUsers;
    VistaPrincipalAdministrador frmControladorAdmin;
    ModeloVistaControladorMateriales modeloVistaControladorMateriales;
    ModeloVistaControladorPasillos modeloVistaControladorPasillos;
    ModeloVistaControladorTrabajadores modeloVistaControladorTrabajadores;

    public ModeloVistaControladorAdministrador() {
        frmControladorAdmin = new VistaPrincipalAdministrador();
        frmControladorAdmin.panPasillos.addMouseListener(this);
        frmControladorAdmin.panProductos.addMouseListener(this);
        frmControladorAdmin.panTrabajadores.addMouseListener(this);
    }

    public void inicio() {
        frmControladorAdmin.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == frmControladorAdmin.panPasillos) {
            frmControladorAdmin.setVisible(false);
            modeloVistaControladorPasillos = new ModeloVistaControladorPasillos(frmControladorAdmin, frmControladorUsers);
            modeloVistaControladorPasillos.inicio();
        }
        if (me.getSource() == frmControladorAdmin.panProductos) {
            frmControladorAdmin.setVisible(false);
            modeloVistaControladorMateriales = new ModeloVistaControladorMateriales(frmControladorAdmin, frmControladorUsers);
            modeloVistaControladorMateriales.inicio();
            
        }
        if (me.getSource() == frmControladorAdmin.panTrabajadores) {
            frmControladorAdmin.setVisible(false);
            modeloVistaControladorTrabajadores = new ModeloVistaControladorTrabajadores(frmControladorAdmin, frmControladorUsers);
            modeloVistaControladorTrabajadores.inicio();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    
}
