package dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dao.Dao;


@Transactional
public abstract class DaoHibernate <K, E> implements Dao<K, E> {
		protected Class<E> entityClass;
	 
		@Autowired
		SessionFactory factory;
		@SuppressWarnings("unchecked")
		public DaoHibernate() {
			ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
			this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
		}
		
		@Override
		public void save(E entity) { factory.getCurrentSession().save(entity); }
		
		@Override
		public void remove(E entity) { factory.getCurrentSession().delete(entity); }
		
		@Override
		public void removeById(K id) {
			factory.getCurrentSession().delete(factory.getCurrentSession().load(entityClass, (Serializable)id));
		}
		
		@Override
		public E load(K id) { 
				return  (E) factory.getCurrentSession().get(entityClass, (Serializable)id); 
		}
	
		
		@Override
		public void update(E entity) { factory.getCurrentSession().merge(entity); }
		
		@Override
		public List<E> loadAll() {
			return factory.getCurrentSession().createQuery("from " + entityClass.getName(), entityClass)
					   .getResultList(); 
		}

		@Override
		public void removeAll(Collection<E> entities) {
			for(E entity : entities) {
				factory.getCurrentSession().delete(entity);
			}
		}

		
}
