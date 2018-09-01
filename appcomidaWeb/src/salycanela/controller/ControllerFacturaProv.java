package salycanela.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import salycanela.model.manager.ManagerProveedores;

@SessionScoped
@ManagedBean
public class ControllerFacturaProv {

	@EJB
	private ManagerProveedores managerProveedor;

	private String cpidfacturaprov, cpidproveedor, cpobservacionesfacturaprov;
	private int cpidmodopago, invidprod, cpcantidaddetalle;
	private Date cpfechaemisionfacprov, cpfechacaducidadfacprov;
	private BigDecimal cpsubtotalcero, cpsubtotaliva, cpivafacturaprov, cpdescuentofacturaprov, cptotalfacturaprov,
			cpunitariodetallefacprov, cptotaldetallefacprov, cpdescuentodetallefacprov;
	private Boolean cpestadofacturaprov, cpivadetallefacprov;

}
