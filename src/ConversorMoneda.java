import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ConversorMoneda {

	private String[] monedas = {"Dólares","Euros", "Yenes", "Wones", "Libras", "Pesos"};

	private JComboBox<String> listaDivisas;
	private String cuadroDialogDivisa;
	
	private JComboBox<String> addCurrencyList() {
		listaDivisas = new JComboBox<String>(monedas);
		return listaDivisas;
	}
	
	private String addCuadroDialog(){
		cuadroDialogDivisa = JOptionPane.showInputDialog(null, "Ingrese el valor a convertir");
		return cuadroDialogDivisa;
	}
		
	public Object getListasMonedas() {
		return addCurrencyList();
	}
	
	public String getCuadroDialog() {
		return addCuadroDialog();
	}

		
}
