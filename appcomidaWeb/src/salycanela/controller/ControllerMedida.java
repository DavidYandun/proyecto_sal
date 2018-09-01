package salycanela.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import salycanela.model.entities.TabInvMedida;
import salycanela.model.manager.ManagerMedidas;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerMedida {
	private Integer cpidmedida;
	private String cpnombremedida;
	private List<TabInvMedida> lista;
	@EJB
	private ManagerMedidas managerMedidas;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();
	}

	public void AgregaMedida() {
		try {
			managerMedidas.agregarMedida(cpnombremedida);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Par�metro registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarMedida(TabInvMedida medida) {
		cpidmedida=medida.getCpidmedida();
		cpnombremedida = medida.getCpnombremedida();
	}

	public void EditarMedida() {
		try {
			managerMedidas.editarMedida(cpidmedida, cpnombremedida);
			JSFUtil.crearMensajeInfo("Unidad de medida " + cpnombremedida + " editada correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void vaciarCampos() {
		lista = managerMedidas.findAllMedidas();
		cpnombremedida = null;
		cpidmedida = null;
	}

	public String getCpnombremedida() {
		return cpnombremedida;
	}

	public void setCpnombremedida(String cpnombremedida) {
		this.cpnombremedida = cpnombremedida;
	}

	public Integer getCpidmedida() {
		return cpidmedida;
	}

	public void setCpidmedida(Integer cpidmedida) {
		this.cpidmedida = cpidmedida;
	}

	public List<TabInvMedida> getLista() {
		return lista;
	}

	public void setLista(List<TabInvMedida> lista) {
		this.lista = lista;
	}

}