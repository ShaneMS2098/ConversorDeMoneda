import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ConversorTemperatura {
	
	private String[] escalasTemperatura = {"Celcius", "Fahrenheit"};
	private JComboBox<String> listaTemperatura;
	private String cuadroDialogTemperature;
	
	private JComboBox<String> addTemperatureList() {
		listaTemperatura = new JComboBox<String>(escalasTemperatura);
		return listaTemperatura;
	}
	
	private String addCuadroDialog(){
		cuadroDialogTemperature = JOptionPane.showInputDialog(null, "Ingrese el valor a convertir");
		return cuadroDialogTemperature;
	}
	
	public String getCuadroDialog() {
		return addCuadroDialog();
	}
	

	public Object getListasTemperatura() {
		return addTemperatureList();
	}
	
}
