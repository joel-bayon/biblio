package dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;





import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.Dao;

@Transactional
public abstract class DaoJpa <K, E> implements Dao<K, E> {
		protected Class<E> entityClass;
	 
		@PersistenceContext(unitName="biblio")
		EntityManager em;
	 
		@SuppressWarnings("unchecked")
		public DaoJpa() {
			ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
			this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
		}
		
		@Override
		public void save(E entity) { em.persist(entity); }
		@Override
		public void remove(E entity) { em
			.remove(em.merge(entity)); }
		
		@Override
		public void removeById(K id) {
			em
			.remove(em.getReference(entityClass, (Serializable)id));
		}
		
		@Override
		public E load(K id) { return em.find(entityClass, id); }
		@Override
		public void update(E entity) { em.merge(entity); }
		
		@Override
		public List<E> loadAll() {
			return em.createQuery("from " + entityClass.getName(), entityClass)
					   .getResultList();
		}

		@Override
		public void removeAll(Collection<E> entities) {
			for(E entity : entities) {
				em
				.remove(em.merge(entity));
			}
		}
}
