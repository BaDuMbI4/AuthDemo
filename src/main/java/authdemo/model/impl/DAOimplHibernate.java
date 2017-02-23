package authdemo.model.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import authdemo.hibernate.HibernateUtil;
import authdemo.hibernate.entity.UserEntity;
import authdemo.model.DAO;

public class DAOimplHibernate implements DAO {

	public String getPassword(String name) {
		Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserEntity.class);
		criteria.add(Restrictions.eq("name", name));
		List<UserEntity> lst = criteria.list();
		for(UserEntity ue : lst) {
			if(ue.getName().equals(name)) {
				return ue.getPassword();
			}
		}
		return null;
	}

}
