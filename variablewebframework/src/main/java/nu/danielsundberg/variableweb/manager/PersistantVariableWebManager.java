package nu.danielsundberg.variableweb.manager;

import java.io.IOException;

import nu.danielsundberg.variableweb.component.html.VariableWebComponent;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public class PersistantVariableWebManager implements VariableWebManager {

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional(readOnly=false)
	public void addContextRoot(String contextRootId, VariableWebComponent contextRootComponent) {
		nu.danielsundberg.variableweb.manager.persistance.ComponentEntity entity = new nu.danielsundberg.variableweb.manager.persistance.ComponentEntity();
		try {
			entity.setComponent(contextRootComponent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entity.setComponentId(contextRootId);
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public VariableWebComponent fetchContextRoot(String contextRootId) {
		nu.danielsundberg.variableweb.manager.persistance.ComponentEntity entity = hibernateTemplate.get(nu.danielsundberg.variableweb.manager.persistance.ComponentEntity.class, contextRootId);
		VariableWebComponent component = null;
		try {
			 component = (VariableWebComponent) entity.getComponent();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return component;
	}

}
