package entidades;

public class ConversorDataMes {
	String mes = "";
	
	public ConversorDataMes(String mes) {
		this.mes = mes;
	}

	public static int toIntMes(String x) {
		int mes = 0;
		switch(x) {
		case "Jan":
			 mes = 1;
			 break;
		case "Feb":
			 mes = 2;
			 break;
		case "Mar":
			 mes = 3;
			 break;
		case "Apr":
			 mes = 4;
			 break;
		case "May":
			 mes = 5;
			 break;
		case "Jun":
			 mes = 6;
			 break;
		case "Jul":
			 mes = 7;
			 break;
		case "Aug":
			 mes = 8;
			 break;
		case "Sep":
			 mes = 9;
			 break;
		case "Oct":
			 mes = 10;
			 break;
		case "Nov":
			 mes = 11;
			 break;
		case "Dec":
			 mes = 12;
			 break;
		default:
			mes =0;
		}
		return mes;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
}
