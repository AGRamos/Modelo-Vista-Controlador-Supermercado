package Clientes.Aplicacion;

import Clientes.Vista.VistaPrincipal;
import Clientes.Vista.VistaPrincipalAdministrador;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ModeloVistaControladorUsuarios implements MouseListener{

    VistaPrincipal frmControladorUsers;
    VistaPrincipalAdministrador frmControladorAdmin;
    ModeloVistaControladorMateriales modeloVistaControladorMateriales;
    ModeloVistaControladorPasillos modeloVistaControladorPasillos;
    ModeloVistaControladorStock modeloVistaControladorStock;

    public ModeloVistaControladorUsuarios() {
        frmControladorUsers = new VistaPrincipal();
        frmControladorUsers.panPasillos.addMouseListener(this);
        frmControladorUsers.panProductos.addMouseListener(this);
        frmControladorUsers.panPedidos.addMouseListener(this);
    }

    public void inicio() {
        frmControladorUsers.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == frmControladorUsers.panPasillos) {
            frmControladorUsers.setVisible(false);
            modeloVistaControladorPasillos = new ModeloVistaControladorPasillos(frmControladorAdmin, frmControladorUsers);
            modeloVistaControladorPasillos.inicio();
        }
        if (me.getSource() == frmControladorUsers.panProductos) {
            frmControladorUsers.setVisible(false);
            modeloVistaControladorMateriales = new ModeloVistaControladorMateriales(frmControladorAdmin, frmControladorUsers);
            modeloVistaControladorMateriales.inicio();
            
        }
        if (me.getSource() == frmControladorUsers.panPedidos) {
            frmControladorUsers.setVisible(false);
            modeloVistaControladorStock = new ModeloVistaControladorStock(frmControladorAdmin, frmControladorUsers);
            modeloVistaControladorStock.inicio();
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
