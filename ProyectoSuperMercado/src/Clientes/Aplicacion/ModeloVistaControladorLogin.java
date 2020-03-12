package Clientes.Aplicacion;

import Clientes.Modelo.ConsultasLogin;
import Vista.VistaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ModeloVistaControladorLogin implements ActionListener {

    ConsultasLogin modConsultasLogin;
    VistaLogin frmLogin;
    ModeloVistaControladorAdministrador modControladorAdmin;
    ModeloVistaControladorUsuarios modeloVistaControladorUsuarios;

    public ModeloVistaControladorLogin(ConsultasLogin modConsultasUsuario, VistaLogin frmLogin) {
        this.modConsultasLogin = modConsultasUsuario;
        this.frmLogin = frmLogin;
        frmLogin.btnEntrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmLogin.btnEntrar) {
            if (compCampos()) {
                char[] pass = frmLogin.textPass.getPassword();
                String passParseda = new String();
                for (int i = 0; i < pass.length; i++) {
                    passParseda += pass[i];
                }
                String user = frmLogin.textUsuario.getText().trim();
                if (modConsultasLogin.compLog(user, passParseda)) {
                    JOptionPane.showMessageDialog(frmLogin, "El logea ha sucedido complacientemente.");
                    frmLogin.setVisible(false);
                    if (user.equals("admin") && passParseda.equals("admin")) {
                        modControladorAdmin = new ModeloVistaControladorAdministrador();
                        modControladorAdmin.inicio();
                    }
                    else{
                        modeloVistaControladorUsuarios = new  ModeloVistaControladorUsuarios();
                        modeloVistaControladorUsuarios.inicio();
                    }
                } else {
                    JOptionPane.showMessageDialog(frmLogin, "Los credenciales son incorrectos.");
                }
            } else {
                JOptionPane.showMessageDialog(frmLogin, "Debes de introducir todos los campos.");
            }

        }
    }

    public void inicio() {
        frmLogin.setVisible(true);
    }

    private boolean compCampos() {
        boolean comp = true;
        if (frmLogin.textPass.getText().trim().isEmpty()) {
            comp = false;
        }
        if (frmLogin.textUsuario.getText().trim().isEmpty()) {
            comp = false;
        }
        return comp;
    }

}
