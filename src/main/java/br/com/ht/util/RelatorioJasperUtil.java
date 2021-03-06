package br.com.ht.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.ht.filters.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class RelatorioJasperUtil {

	public static final int RELATORIO_PDF = 1;
	public static final int RELATORIO_EXCEL = 2;
	public static final int RELATORIO_HTML = 3;
	public static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
	public static final int RELATORIO_DOCX = 5;
	public static final int RELATORIO_CSV = 6;
	private StreamedContent stream;

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StreamedContent geraRel(HashMap parametros, String nome_entrada, String nome_saida, int tipo)
			throws Exception {
		StreamedContent retorno = null;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Connection conex = Conexao.getConexao();
			String caminho_rel = context.getExternalContext().getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case RelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter exporterPdf = new JRPdfExporter();
				extensao_exportada = "pdf";
				exporterPdf.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterPdf.exportReport();
				break;
			case RelatorioJasperUtil.RELATORIO_DOCX:
				JRDocxExporter exporterWord = new JRDocxExporter();
				extensao_exportada = "docx";
				exporterWord.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterWord.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterWord.exportReport();
				break;
			}
			retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
					"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
			conex.close();
		} catch (JRException e) {
			throw new Exception("Não foi possível gerar o relatório.", e);
		} catch (FileNotFoundException e) {
			throw new Exception("Arquivo do relatório não encontrado.", e);

		}
		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] geraRel3(Map parametros, String nome_entrada, String nome_saida, int tipo) throws Exception {
		@SuppressWarnings("unused")
		StreamedContent retorno = null;
		byte[] b;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Connection conex = Conexao.getConexao();
			String caminho_rel = context.getExternalContext().getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case RelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter exporterPdf = new JRPdfExporter();
				extensao_exportada = "pdf";
				exporterPdf.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterPdf.exportReport();
				break;
			case RelatorioJasperUtil.RELATORIO_DOCX:
				JRDocxExporter exporterWord = new JRDocxExporter();
				extensao_exportada = "docx";
				exporterWord.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterWord.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterWord.exportReport();
				break;
			}
			retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
					"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
			conex.close();
		} catch (JRException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível gerar o relatório." + e, ""));
			throw new Exception("Não foi possível gerar o relatório.", e);
		} catch (FileNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível gerar o relatório." + e, ""));
			throw new Exception("Arquivo do relatório não encontrado.", e);
		}
		return b;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRel2(Map parametros, String nome_entrada, String nome_saida, int tipo) throws Exception {
		StreamedContent retorno = null;
		byte[] b;
		try {
			Connection conex = Conexao.getConexao();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros, conex);
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case RelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter tipo_exportado = new JRPdfExporter();
				extensao_exportada = "pdf";
				tipo_exportado.setExporterInput(new SimpleExporterInput(impressora_jasper));
				tipo_exportado.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				tipo_exportado.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream = retorno;
				break;
			case RelatorioJasperUtil.RELATORIO_EXCEL:
				JRXlsExporter exporter = new JRXlsExporter();

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setWhitePageBackground(false);
				configuration.setDetectCellType(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				configuration.setFontSizeFixEnabled(true);

				exporter.setConfiguration(configuration);

				Map<String, String> map = new HashMap<>();
				map.put("whitePageBackground", "false");
				extensao_exportada = "xls";

				exporter.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporter.exportReport();
				retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
						"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
				this.stream = retorno;
				break;
			}
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] geraRelatorioCSV3(Map parametros, String nome_entrada, String nome_saida)
			throws Exception {
		StreamedContent retorno = null;

		byte[] b = null;
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_rel = servletContext.getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";

			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);

			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros);

			String extensao_exportada = "";

			ByteArrayOutputStream output = new ByteArrayOutputStream();

			JRCsvExporter tipo_exportado = new JRCsvExporter();

			extensao_exportada = "csv";

			tipo_exportado.setExporterInput(new SimpleExporterInput(impressora_jasper));
			tipo_exportado.setExporterOutput(new SimpleWriterExporterOutput(output));

			tipo_exportado.exportReport();

			retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
					"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
			this.stream = retorno;
		} catch (Exception e) {
			throw new Exception("Não foi possível gerar o relatorio.", e);
		}
		return b;
	}
	

}
