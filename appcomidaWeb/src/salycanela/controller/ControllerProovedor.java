package salycanela.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import salycanela.model.entities.TabCpProveedore;
import salycanela.model.manager.ManagerProveedores;
import salycanela.view.util.JSFUtil;;

@ManagedBean
@SessionScoped
public class ControllerProovedor {

	private String cpidproveedor, cpnombreproveedor, cptelefonoproveedor, cpcelularproveedor, cpdireccionproveedor,
			cpcorreoproveedor, cpresponsableproveedor;
	private Boolean cpestadoproveedor;
	private List<TabCpProveedore> lista;
	@EJB
	private ManagerProveedores managerProveedor;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();
	}

	public void vaciarCampos() {
		cpidproveedor = null;
		cpnombreproveedor = null;
		cptelefonoproveedor = null;
		cpcelularproveedor = null;
		cpdireccionproveedor = null;
		cpcorreoproveedor = null;
		cpresponsableproveedor = null;
		cpestadoproveedor = null;
		lista=managerProveedor.findAllProveedores();
	}

	public void AgregarProveedor() {
		try {
			managerProveedor.agregarProveedor(cpidproveedor, cpnombreproveedor, cptelefonoproveedor, cpcelularproveedor,
					cpdireccionproveedor, cpcorreoproveedor, cpresponsableproveedor, true);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Parámetro registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarProveedor(TabCpProveedore p) {
		cpidproveedor = p.getCpidproveedor();
		cpnombreproveedor = p.getCpnombreproveedor();
		cptelefonoproveedor = p.getCptelefonoproveedor();
		cpcelularproveedor = p.getCpcelularproveedor();
		cpdireccionproveedor = p.getCpdireccionproveedor();
		cpcorreoproveedor = p.getCpcorreoproveedor();
		cpresponsableproveedor = p.getCpresponsableproveedor();
		cpestadoproveedor = p.getCpestadoproveedor();
	}

	public void EditarProveedor() {
		try {
			managerProveedor.editarProveedor(cpidproveedor, cpnombreproveedor, cptelefonoproveedor, cpcelularproveedor,
					cpdireccionproveedor, cpcorreoproveedor, cpresponsableproveedor, cpestadoproveedor);
			JSFUtil.crearMensajeInfo("Proveedor " + cpnombreproveedor + " editado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getCpidproveedor() {
		return cpidproveedor;
	}

	public void setCpidproveedor(String cpidproveedor) {
		this.cpidproveedor = cpidproveedor;
	}

	public String getCpnombreproveedor() {
		return cpnombreproveedor;
	}

	public void setCpnombreproveedor(String cpnombreproveedor) {
		this.cpnombreproveedor = cpnombreproveedor;
	}

	public String getCptelefonoproveedor() {
		return cptelefonoproveedor;
	}

	public void setCptelefonoproveedor(String cptelefonoproveedor) {
		this.cptelefonoproveedor = cptelefonoproveedor;
	}

	public String getCpcelularproveedor() {
		return cpcelularproveedor;
	}

	public void setCpcelularproveedor(String cpcelularproveedor) {
		this.cpcelularproveedor = cpcelularproveedor;
	}

	public String getCpdireccionproveedor() {
		return cpdireccionproveedor;
	}

	public void setCpdireccionproveedor(String cpdireccionproveedor) {
		this.cpdireccionproveedor = cpdireccionproveedor;
	}

	public String getCpcorreoproveedor() {
		return cpcorreoproveedor;
	}

	public void setCpcorreoproveedor(String cpcorreoproveedor) {
		this.cpcorreoproveedor = cpcorreoproveedor;
	}

	public String getCpresponsableproveedor() {
		return cpresponsableproveedor;
	}

	public void setCpresponsableproveedor(String cpresponsableproveedor) {
		this.cpresponsableproveedor = cpresponsableproveedor;
	}

	public Boolean getCpestadoproveedor() {
		return cpestadoproveedor;
	}

	public void setCpestadoproveedor(Boolean cpestadoproveedor) {
		this.cpestadoproveedor = cpestadoproveedor;
	}

	public List<TabCpProveedore> getLista() {
		return lista;
	}

	public void setLista(List<TabCpProveedore> lista) {
		this.lista = lista;
	}

}
