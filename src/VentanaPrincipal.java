import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaPrincipal {
	private JFrame ventana;
	private JPanel paneles;
	private JOptionPane principalWindow;
	private JPanel currencyWindow;
	private JOptionPane temperatureWindow;
	private JLabel etiqueta;
	ImageIcon iconoVentana = new ImageIcon("src/Images/alfa.png");
	
	private void playWindow() {
		crearpanelInicio();
	}
	
	public JOptionPane getWindow() {
		playWindow();
		return principalWindow;
	}
	
	private JOptionPane crearpanelInicio() {
		
		Object[] opciones = {"Conversor de Temperatura", "Conversor de Divisas"};
		int opcionSeleccionada = JOptionPane.showOptionDialog(null, "¿Cómo puedo ayudarte hoy?", "Conversor Alura", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, iconoVentana, opciones, opciones[0]);
		
		if (opcionSeleccionada == 0) {
			crearPanelConversorTemperatura();
		} else if (opcionSeleccionada == 1) {
			crearPanelConversorDivisas();
		}
		return principalWindow;
	}
	
	private JPanel crearPanelConversorDivisas() {
		etiqueta = new JLabel("Ingresa el valor que deseas convertir");
		JTextField ingresoDeValores = new JTextField();
		Dimension dimension = new Dimension(200, 30);
		JButton convertirDivisas = new JButton("Convertir");
		
		
		ConversorMoneda conversorMoneda = new ConversorMoneda();
		@SuppressWarnings("unchecked")
		JComboBox<String> monedaOrigen = (JComboBox<String>) conversorMoneda.getListasMonedas();
		
		@SuppressWarnings("unchecked")
		JComboBox<String> monedaDestino = (JComboBox<String>) conversorMoneda.getListasMonedas();
		
		
		crearVentanas();
		crearPaneles();
		
		ingresoDeValores.setPreferredSize(dimension);
		ventana.add(paneles);
		ventana.setTitle("Conversor de Divisas Alura");
		paneles.add(etiqueta);
		paneles.add(ingresoDeValores);
		paneles.add(monedaOrigen);
		paneles.add(monedaDestino);
		paneles.add(convertirDivisas);
		ventana.setLocationRelativeTo(null);
		ventana.pack();
		
		convertirDivisas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoIngresoValores = ingresoDeValores.getText();
				if (textoIngresoValores.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes ingresar un valor válido.");
				    return; 
				}
				try {
		            double numeroIngresado = Double.parseDouble(textoIngresoValores);
		            String textoMonedaOrigen = (String) monedaOrigen.getSelectedItem();
		            String textoMonedaDestino = (String) monedaDestino.getSelectedItem();

		            double tasaConversion = TABLA_CONVERSION.get(textoMonedaOrigen + textoMonedaDestino);
		            double resultadoObtenido = numeroIngresado * tasaConversion;

		            String mensaje = "El resultado es " + String.format("%.3f", resultadoObtenido) + " " + textoMonedaDestino;
		            JOptionPane.showMessageDialog(null, mensaje);
		            
		            Object[] options = {"Sí, Por favor", "No, Gracias"};

		            int result = JOptionPane.showOptionDialog(null, "¿Quieres continuar?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				    iconoVentana,
				    options, 
				    options[1]);
		            

		            if (result == 0) {
		            	ventana.dispose();
		                crearpanelInicio();
		            } else if (result == 1) {
		                ventana.dispose();
		            } else {
		              System.out.println("El usuario cerró el cuadro de diálogo sin seleccionar ninguna opción.");
		            }
		            

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Debes ingresar un número válido.");
		        }
				
			}
		});

		return currencyWindow;
		
	}
	
	private JOptionPane crearPanelConversorTemperatura() {

		ConversorTemperatura conversorTemperatura = new ConversorTemperatura();
		
		etiqueta = new JLabel("Ingresa el valor que deseas convertir");
		JTextField ingresoDeValores = new JTextField();
		Dimension dimension = new Dimension(200, 30);
		JButton convertirTemperaturas = new JButton("Convertir");

		@SuppressWarnings("unchecked")
		JComboBox<String> temperaturaOrigen = (JComboBox<String>) conversorTemperatura.getListasTemperatura();
		
		@SuppressWarnings("unchecked")
		JComboBox<String> temperaturaDestino = (JComboBox<String>) conversorTemperatura.getListasTemperatura();
		
		
		crearVentanas();
		crearPaneles();
		
		ingresoDeValores.setPreferredSize(dimension);
		ventana.add(paneles);
		ventana.setTitle("Conversor de Temperatura Alura");
		paneles.add(etiqueta);
		paneles.add(ingresoDeValores);
		paneles.add(temperaturaOrigen);
		paneles.add(temperaturaDestino);
		paneles.add(convertirTemperaturas);
		ventana.setLocationRelativeTo(null);
		ventana.pack();
		
		convertirTemperaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoIngresoValores = ingresoDeValores.getText();
				if (textoIngresoValores.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes ingresar un valor válido.");
				    return; 
				}
				try {
		            double numeroIngresado = Double.parseDouble(textoIngresoValores);
		            String textoTemperaturaOrigen = (String) temperaturaOrigen.getSelectedItem();
		            String textoTemperaturaDestino = (String) temperaturaDestino.getSelectedItem();
		            
		            double resultadoObtenido = calcularTemperatura(numeroIngresado, textoTemperaturaOrigen, textoTemperaturaDestino);

		            String mensaje = "El resultado es " + String.format("%.3f", resultadoObtenido) + " " + textoTemperaturaDestino;
		            JOptionPane.showMessageDialog(null, mensaje);
		            
		            Object[] options = {"Sí, Por favor", "No, Gracias"};

		            int result = JOptionPane.showOptionDialog(null, "¿Quieres continuar?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				    iconoVentana,
				    options,
				    options[1]);

		            if (result == 0) {
		            	ventana.dispose();
		                crearpanelInicio();
		            } else if (result == 1) {
		                ventana.dispose();
		            } else {
		              System.out.println("El usuario cerró el cuadro de diálogo sin seleccionar ninguna opción.");
		            }
		            
		            

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Debes ingresar un número válido.");
		        }
				
			}
		});
		
		return temperatureWindow;
		
	}
	
	private JFrame crearVentanas() {
		ventana = new JFrame();
		ventana.setResizable(false);
		ventana.setVisible(true);
		
		return ventana;
		
	}
	private JPanel crearPaneles() {
		paneles = new JPanel(new GridLayout(0, 1));
		paneles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return paneles;
		
	}
	
	private static final Map<String, Double> TABLA_CONVERSION = new HashMap<>();
	static {
		TABLA_CONVERSION.put("DólaresDólares", 1.00);
		TABLA_CONVERSION.put("DólaresEuros", 0.93);
	    TABLA_CONVERSION.put("DólaresWones", 1308.75);
	    TABLA_CONVERSION.put("DólaresYenes", 131.7085);
	    TABLA_CONVERSION.put("DólaresLibras", 0.82);
	    TABLA_CONVERSION.put("DólaresPesos", 18.85);
	    
	    TABLA_CONVERSION.put("EurosEuros", 1.00);
	    TABLA_CONVERSION.put("EurosDólares", 1.075);
	    TABLA_CONVERSION.put("EurosWones", 1398.46);
	    TABLA_CONVERSION.put("EurosYenes", 140.84);
	    TABLA_CONVERSION.put("EurosLibras", 0.88);
	    TABLA_CONVERSION.put("EurosPesos", 20.14);
	    
	    TABLA_CONVERSION.put("YenesYenes", 1.00);
	    TABLA_CONVERSION.put("YenesDólares", 0.00764);
	    TABLA_CONVERSION.put("YenesEuros", 0.0071);
	    TABLA_CONVERSION.put("YenesWones", 9.92);
	    TABLA_CONVERSION.put("YenesLibras", 0.0062);
	    TABLA_CONVERSION.put("YenesPesos", 0.14);
	    
	    TABLA_CONVERSION.put("WonesWones", 1.00);
	    TABLA_CONVERSION.put("WonesYenes", 0.10);
	    TABLA_CONVERSION.put("WonesDólares", 0.00076);
	    TABLA_CONVERSION.put("WonesEuros", 0.00072);
	    TABLA_CONVERSION.put("WonesLibras", 0.00063);
	    TABLA_CONVERSION.put("WonesPesos", 0.014);
	    
	    TABLA_CONVERSION.put("LibrasLibras", 1.00);
	    TABLA_CONVERSION.put("LibrasWones", 1595.25);
	    TABLA_CONVERSION.put("LibrasYenes", 161.19);
	    TABLA_CONVERSION.put("LibrasDólares", 1.22);
	    TABLA_CONVERSION.put("LibrasEuros", 1.14);
	    TABLA_CONVERSION.put("LibrasPesos", 22.98);
	    
	    TABLA_CONVERSION.put("PesosPesos", 1.00);
	    TABLA_CONVERSION.put("PesosWones", 69.42);
	    TABLA_CONVERSION.put("PesosYenes", 7.02);
	    TABLA_CONVERSION.put("PesosDólares", 0.053);
	    TABLA_CONVERSION.put("PesosEuros", 0.050);
	    TABLA_CONVERSION.put("PesosLibras", 0.044);
	    
	}

	private double calcularTemperatura(double numeroIngresado, String textoTemperaturaOrigen, String textoTemperaturaDestino) {
	    if(textoTemperaturaOrigen.equals(textoTemperaturaDestino)) {
	        return numeroIngresado;
	    } else if (textoTemperaturaOrigen.equals("Fahrenheit") && textoTemperaturaDestino.equals("Celcius")) {
	        return (numeroIngresado - 32) * 5/9;
	    } else if (textoTemperaturaOrigen.equals("Celcius") &&textoTemperaturaDestino.equals("Fahrenheit")){
	        return (numeroIngresado*9/5)+ 32;
	    }else {
	    	return 0;
	    }
	}

}
