package br.com.ht.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatacaoUtil {

	private static final DecimalFormat DECIMAL = new DecimalFormat("#,##0.00",
			new DecimalFormatSymbols(new Locale("pt", "BR")));
	private static final SimpleDateFormat DATA = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat DATA_HORA = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static String formatarMoedaParaString(double valor) {
		return DECIMAL.format(valor);
	}

	public static double formatarMoedaParaDouble(String valor) throws ParseException {
		valor = valor.replace("R$", "");
		valor = valor.trim();
		return DECIMAL.parse(valor).doubleValue();
	}

	public static String formatarData(Date data) {
		return DATA.format(data);
	}

	public static String formatarDataHora(Date dataHora) {
		return DATA_HORA.format(dataHora);
	}

}
