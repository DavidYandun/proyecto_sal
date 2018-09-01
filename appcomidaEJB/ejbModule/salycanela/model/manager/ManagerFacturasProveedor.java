package salycanela.model.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import salycanela.model.entities.TabCpFacturasProveedore;
import salycanela.model.entities.TabCpDetalleFacturasProveed;
import salycanela.model.entities.TabCpModosPago;
import salycanela.model.entities.TabCpProveedore;
import salycanela.model.entities.TabInvProducto;
import salycanela.model.entities.TabParametro;

@Stateless
@LocalBean
public class ManagerFacturasProveedor {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;
	private int iddetalleTempo = 1;

	public ManagerFacturasProveedor() {
		// TODO Auto-generated constructor stub
	}

	public TabCpFacturasProveedore crearFacturaTmp(String cpidfacturaprov, String cpidproveedor, Integer cpidmodopago,
			Date cpfechaemisionfacturaprov, Date cpfechacaducidadfacprov, String cpobservacionesfacturaprov) {
		TabCpFacturasProveedore facturaTmp = new TabCpFacturasProveedore();

		TabCpProveedore proveedor = em.find(TabCpProveedore.class, cpidproveedor);
		TabCpModosPago pago = em.find(TabCpModosPago.class, cpidmodopago);
		facturaTmp.setCpidfacturaprov(cpidfacturaprov);
		facturaTmp.setTabCpProveedore(proveedor);
		facturaTmp.setTabCpModosPago(pago);
		facturaTmp.setCpfechaemisionfacturaprov(cpfechaemisionfacturaprov);
		facturaTmp.setCpfechacaducidadfacprov(cpfechacaducidadfacprov);
		facturaTmp.setCpobservacionesfacturaprov(cpobservacionesfacturaprov);
		facturaTmp.setCpestadofacturaprov(true);
		facturaTmp.setTabCpDetalleFacturasProveeds(new ArrayList<TabCpDetalleFacturasProveed>());

		return facturaTmp;
	}

	public void agregarDetalleFacturaTmp(TabCpFacturasProveedore facturaTmp, Integer invidprod,
			Integer cpcantidaddetallefacprov, BigDecimal cpunitariodetallefacprov, BigDecimal cpdescuentodetallefacprov,
			Boolean cpivadetallefacprov) throws Exception {
		double valorTotal = 0;

		// sentencias de error de ingreso
		if (facturaTmp == null)
			throw new Exception("Error primero debe crear una nueva Factura.");
		if (invidprod == null || invidprod.intValue() < 0)
			throw new Exception("Error debe especificar el codigo del producto.");
		if (cpcantidaddetallefacprov == null || cpcantidaddetallefacprov.intValue() <= 0)
			throw new Exception("Error debe especificar la cantidad del producto.");

		// buscamos el producto:
		TabInvProducto p = em.find(TabInvProducto.class, invidprod);
		// creamos un nuevo detalle y llenamos sus propiedades:
		TabCpDetalleFacturasProveed d = new TabCpDetalleFacturasProveed();
		d.setCpiddetallefacprov(iddetalleTempo);
		d.setTabInvProducto(p);
		d.setCpcantidaddetallefacprov(cpcantidaddetallefacprov);
		d.setCpunitariodetallefacprov(cpunitariodetallefacprov);
		d.setCpdescuentodetallefacprov(cpdescuentodetallefacprov);
		d.setCpivadetallefacprov(cpivadetallefacprov);

		// valorTotal = (cpcantidaddetallefacprov.doubleValue() *
		// cpunitariodetallefacprov.doubleValue())
		// + cpivadetallefacprov.doubleValue() -
		// cpdescuentodetallefacprov.doubleValue();
		valorTotal = cpcantidaddetallefacprov.doubleValue() * cpunitariodetallefacprov.doubleValue();
		d.setCptotaldetallefacprov(new BigDecimal(valorTotal));

		facturaTmp.getTabCpDetalleFacturasProveeds().add(d);
		// verificamos los campos calculados:

		calcularFacturaTmp(facturaTmp);
		iddetalleTempo++;
	}

	public void eliminarDetalleFacturaTmp(TabCpFacturasProveedore facturaTmp, Integer iddp) throws Exception {
		if (facturaTmp == null)
			throw new Exception("Error primero debe crear una nueva Factura.");
		facturaTmp.getTabCpDetalleFacturasProveeds().remove(iddp);
		// verificamos los campos calculados:
		calcularFacturaTmp(facturaTmp);
	}

	public void guardarFacturaTemporal(TabCpFacturasProveedore facturaTmp) throws Exception {

		if (facturaTmp == null)
			throw new Exception("Debe crear una factura primero.");
		if (facturaTmp.getTabCpDetalleFacturasProveeds() == null
				|| facturaTmp.getTabCpDetalleFacturasProveeds().size() == 0)
			throw new Exception("Debe ingresar los productos en factura.");

		for (TabCpDetalleFacturasProveed det : facturaTmp.getTabCpDetalleFacturasProveeds()) {
			det.setCpiddetallefacprov(null);
		}

		// obtenemos el numero del nuevo pedido: int contPedidos; contPedidos =
		// getContPedidos(); contPedidos++; pedidoTmp.setIdpedido(contPedidos);

		// verificamos los campos calculados:
		calcularFacturaTmp(facturaTmp);
		sumarStock(facturaTmp);

		// guardamos el pedido completa en la bdd:
		em.persist(facturaTmp);

		// actualizamos los parametros contadores del pedido:
		// actualizarContPedidos(contPedidos);
		facturaTmp = null;
		iddetalleTempo = 1;

	}

	public void sumarStock(TabCpFacturasProveedore facturaTmp) {
		for (TabCpDetalleFacturasProveed det : facturaTmp.getTabCpDetalleFacturasProveeds()) {
			TabInvProducto p = em.find(TabInvProducto.class, det.getTabInvProducto().getInvidprod());
			p.setInvstockprod(p.getInvstockprod() + det.getCpcantidaddetallefacprov());
			em.merge(p);
		}
	}

	private void calcularFacturaTmp(TabCpFacturasProveedore facturaTmp) {
		double sumaSubTotalCero = 0;
		double sumaSubTotalIva = 0;
		double sumaDescuentos = 0;
		double iva = 0;
		double Total = 0;
		for (TabCpDetalleFacturasProveed det : facturaTmp.getTabCpDetalleFacturasProveeds()) {
			if (det.getCpivadetallefacprov())
				sumaSubTotalIva += det.getCptotaldetallefacprov().doubleValue();
			if (!det.getCpivadetallefacprov())
				sumaSubTotalCero += det.getCptotaldetallefacprov().doubleValue();
			sumaDescuentos += det.getCpdescuentodetallefacprov().doubleValue();
		}
		TabParametro imp = em.find(TabParametro.class, "IVA");
		iva = sumaSubTotalIva * imp.getValorparametro().doubleValue();
		Total = sumaSubTotalCero + sumaSubTotalIva + sumaDescuentos + iva;
		facturaTmp.setCpsubtotal0facturaprov(new BigDecimal(sumaSubTotalCero));
		facturaTmp.setCpsubtotal12facturaprov(new BigDecimal(sumaSubTotalIva));
		facturaTmp.setCpdescuentofacturaprov(new BigDecimal(sumaDescuentos));
		facturaTmp.setCpivafacturaprov(new BigDecimal(iva));
		facturaTmp.setCptotalfacturaprov(new BigDecimal(Total));
	}

}
