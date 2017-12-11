package br.com.ht.managedbeans;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;

import br.com.ht.dao.ImagemDAO;

@RequestScoped
@ManagedBean(name = "imgMB")
public class ImagemService {

	public long getDate() {
		return System.currentTimeMillis();
	}

	public DefaultStreamedContent getFoto() {
		DefaultStreamedContent content = null;
		try {
			ImagemDAO im = new ImagemDAO();
			FacesContext context = FacesContext.getCurrentInstance();
			if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				content = this.retornaSemFoto();
			} else {
				String id = context.getExternalContext().getRequestParameterMap().get("id");
				if (id != null && !id.equals("")) {
					byte[] image = im.pesquisaFoto(Integer.valueOf(id));
					if (image != null) {
						content = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/jpeg");
					} else {
						content = this.retornaSemFoto();
					}
				} else {
					content = this.retornaSemFoto();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public DefaultStreamedContent getFoto2() {
		DefaultStreamedContent content = null;
		try {
			ImagemDAO im = new ImagemDAO();
			FacesContext context = FacesContext.getCurrentInstance();
			if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				content = this.retornaSemFoto();
			} else {
				String id = context.getExternalContext().getRequestParameterMap().get("id");
				if (id != null && !id.equals("")) {
					byte[] image = im.pesquisaFoto2(Integer.valueOf(id));
					if (image != null) {
						content = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/jpeg");
					} else {
						content = this.retornaSemFoto();
					}
				} else {
					content = this.retornaSemFoto();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public DefaultStreamedContent retornaSemFoto() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		File file = new File(servletContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens"
				+ File.separator + "placeholders.png");
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			return new DefaultStreamedContent(new ByteArrayInputStream(bFile), "image/jpeg");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
