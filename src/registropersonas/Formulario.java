package registropersonas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import java.time.LocalDate;


public class Formulario{
    
    private JFrame ventana;
    private JPanel panel;
    private JLabel labelNombre;
    private JTextField nombre;
    private JLabel labelApellido;
    private JTextField apellido;
    private JLabel labelDni;
    private JTextField dni;
    private JLabel labelFechaNacimiento;
    private JLabel labelFormatoFecha;
    private JTextField dia;
    private JLabel barra1;
    private JTextField mes;
    private JLabel barra2;
    private JTextField anio;
    private JButton registrar;
    private JButton listar;
   
    private static List<Persona> personas = new ArrayList<>();
        
    
    public void crearFormulario(){
        
        this.ventana = new JFrame();
        this.panel = new JPanel();
        
        this.ventana.setSize(300, 350);
        this.ventana.setTitle("Registro de personas");
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setResizable(false);
        this.ventana.add(panel);
          
        this.labelNombre = new JLabel("Introduce tu nombre: ");
        this.labelNombre.setFont(new Font("arial",Font.PLAIN,15));
        this.nombre = new JTextField(20);
        
        this.panel.add(labelNombre);
        this.panel.add(nombre);
       
        this.labelApellido = new JLabel("Introduce tu apellido: ");
        this.labelApellido.setFont(new Font("arial",Font.PLAIN,15));
        this.apellido = new JTextField(20);
       
        this.panel.add(labelApellido);
        this.panel.add(apellido);
        
        this.labelDni = new JLabel("Introduce tu número de DNI: ");    
        this.labelDni.setFont(new Font("arial",Font.PLAIN,15));
        this.dni = new JTextField(20);
    
        this.panel.add(labelDni);
        this.panel.add(dni);
        
        this.labelFechaNacimiento = new JLabel("Introduce tu fecha de nacimiento: ");
        this.labelFechaNacimiento.setFont(new Font("arial",Font.PLAIN,15));
        this.labelFormatoFecha = new JLabel("(dia/mes/año): ");
        this.labelFormatoFecha.setFont(new Font("arial",Font.PLAIN,15));
        
        this.panel.add(labelFechaNacimiento);
        this.panel.add(labelFormatoFecha);
        
        this.dia = new JTextField(2);
        this.barra1 = new JLabel("/");
        
        this.panel.add(dia);
        this.panel.add(barra1);
        
        this.mes = new JTextField(2);
        this.barra2 = new JLabel("/");
        
        this.panel.add(mes);
        this.panel.add(barra2);
        
        this.anio = new JTextField(4);
        this.panel.add(anio);        
        
        this.registrar = new JButton("Registrar persona");
        this.registrar.setFont(new Font("arial",Font.PLAIN,20));
        this.panel.add(registrar);
        
        this.listar = new JButton("Listar personas");
        this.listar.setFont(new Font("arial",Font.PLAIN,20));
        this.panel.add(listar);
       
        this.registrar.addActionListener(new GuardarDatos());
        this.listar.addActionListener(new ListarDatos());
        
        this.ventana.setVisible(true);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
   
    
    class GuardarDatos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          
            if(validarDatos()){
                JOptionPane.showMessageDialog(null, "No se ha podido registrar");
            }
            else{
                String fechaNacimiento = dia.getText() + "/" + mes.getText() + "/" + anio.getText();
                Persona persona = new Persona(nombre.getText(),apellido.getText(),dni.getText(),fechaNacimiento);
                
                personas.add(persona);
                
                nombre.setText(null);
                apellido.setText(null);
                dni.setText(null);
                dia.setText(null);
                mes.setText(null);
                anio.setText(null);
                
                JOptionPane.showMessageDialog(null, "Se ha registrado con exito");
            }
            
        }
        
        
        private boolean validarDatos(){
                  
            if(nombre.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "El campo nombre esta vacio, completelo");
                return true;
            }
            else if(apellido.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "El campo apellido esta vacio, completelo");
                return true;
            }
            else if(dni.getText().length() != 8 || !(isNumerica(dni.getText()))){
                
                if(dni.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "El campo dni esta vacio, completelo");
                }
                else{
                    JOptionPane.showMessageDialog(null, "El dni ingresado es incorrecto");
                }
                
                
                return true;
            }
            else if(validarFechaNacimiento(dia.getText(), mes.getText(), anio.getText())){
                
                if(dia.getText().isBlank() || mes.getText().isBlank() || anio.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "El campo fecha de nacimiento esta vacio o incompleto, completelo");
                }
                else{
                    JOptionPane.showMessageDialog(null, "La fecha de nacimiento ingresada es incorrecta");
                }
                
                
                return true;
            }
                  
            return false;
        }
        
        
        private boolean isNumerica(String str){
            return str != null && str.matches("[0-9.]+");
        }
        
      
        private boolean validarFechaNacimiento(String dia, String mes, String anio){
           
            boolean isCorrecta = true;
            boolean isBisiesto = false;
            int anioActual = LocalDate.now().getYear();
            
            if(isNumerica(dia) && isNumerica(mes) && isNumerica(anio)){
                int diaN = Integer.valueOf(dia);
                int mesN = Integer.valueOf(mes);
                int anioN = Integer.valueOf(anio);

                if((mesN >= 1 && mesN <= 12) && (anioN <= anioActual)){
                    
                        for(int i = 0; i <= anioActual; i += 4){
                            if(anioN == i){
                                isBisiesto = true;
                            }
                        }
                    
                    
                        switch(mesN){
                            case 1,3,5,7,8,10,12:
                                if(diaN > 0 && diaN <= 31){
                                    isCorrecta = false;
                                }
                                break;
                            case 2:
                                if(isBisiesto){
                                    if(diaN > 0 && diaN <= 29){
                                        isCorrecta = false;
                                    }
                                }
                                else{
                                    if(diaN > 0 && diaN <= 28){
                                        isCorrecta = false;
                                    }
                                }
                                break;
                            case 4,6,9,11:
                                if(diaN > 0 && diaN <= 30){
                                    isCorrecta = false;
                                }
                                break;
                        }
                }
                
            }
            
            return isCorrecta;
        }
             
    }
        
          
    class ListarDatos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          
            JFrame ventana = new JFrame();
            JPanel panel = new JPanel();
            
            DefaultTableModel modelo = new DefaultTableModel();
            
            JTable tabla;
            JScrollPane scroll;
            
            ventana.setBounds(900, 400, 500, 200);
            ventana.add(panel);
            
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("DNI");
            modelo.addColumn("Fecha de Nacimiento");
            
            for(Persona p: personas){
                modelo.addRow(p.datosPersona());
            }
            
            tabla = new JTable(modelo);
            tabla.setBounds(20, 20, 450, 200);
            
            panel.add(tabla);
            
            scroll = new JScrollPane(tabla,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setBounds(20, 20, 400, 200);
            
            panel.add(scroll);

            ventana.setVisible(true);
            
        }
    }
  
}    
    
    



