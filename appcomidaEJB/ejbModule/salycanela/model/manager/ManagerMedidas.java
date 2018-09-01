package salycanela.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import salycanela.model.entities.TabInvMedida;

/**
 * Session Bean implementation class ManagerMedidas
 */
@Stateless
@LocalBean
public class ManagerMedidas {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerMedidas() {
		// TODO Auto-generated constructor stub
	}

	public void agregarMedida(String cpnombremedida) throws Exception {
		if (cpnombremedida == null || cpnombremedida.length() == 0)
			throw new Exception("Debe especificar el nombre de la unidad de medida.");
		TabInvMedida m = new TabInvMedida();
		m.setCpnombremedida(cpnombremedida);
		em.persist(m);
	}

	public TabInvMedida findMedidaById(Integer cpidmedida) throws Exception {
		TabInvMedida m = em.find(TabInvMedida.class, cpidmedida);
		return m;
	}

	public void editarMedida(Integer cpidmedida,String cpnombremedida) throws Exception {
		TabInvMedida m = findMedidaById(cpidmedida);
		if (m == null)
			throw new Exception("No existe la medida especificada.");
		m.setCpnombremedida(cpnombremedida);
		em.merge(m);
	}

	public List<TabInvMedida> findAllMedidas() {
		Query q;
		List<TabInvMedida> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT m FROM TabCpMedida m ORDER BY m.cpnombremedida";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}
}