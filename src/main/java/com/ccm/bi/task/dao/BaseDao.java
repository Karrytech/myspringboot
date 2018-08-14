package com.ccm.bi.task.dao;

//import org.springframework.test.context.transaction.TransactionConfiguration;




//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseDao<T>
{
	/*private Class<T> entityClass;

	@Autowired
	@PersistenceContext(unitName="jpa")
	private EntityManager entityManager;

	public BaseDao()
	{
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	
	public T find(Object primaryKey)
	{
		return (T) getEntityManager().find(entityClass, primaryKey);
	}

	
	public void save(T entity)
	{
		getEntityManager().persist(entity);
	}

	
	public void remove(T entity)
	{
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	
	public void remove(Map<String, Object> params)
	{
		String hql = "delete from " + entityClass.getSimpleName();
		Query q = null;
		if (!params.isEmpty())
		{
			hql += " where "+getRrerequisiteCriteria(params);
			q = getEntityManager().createQuery(hql);
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext())
			{
				String key = (String) it.next();
				Object value = (Object) params.get(key);
				q.setParameter(key, value);
			}
			q.executeUpdate();
		}
		else
		{
			q = getEntityManager().createQuery(hql);
			q.executeUpdate();
		}
	}
	
	private String getRrerequisiteCriteria(Map params)
	{
		String str = "";
		
		Iterator it = params.keySet().iterator();
		while(it.hasNext())
		{
			if(str.length()>0)
			{
				str += " and ";
			}
			String key = (String)it.next();
			if((params.get(key)).equals("is null"))
			{
				str += key + " " + params.get(key);
			}
			else if((params.get(key)).equals("is not null"))
			{
				str += key + " " + params.get(key);
			}
			else
			{
				str += key + "=:" + key;
			}
		}
		
		return str;
	}
	
	private String getRrerequisiteValue(Map params)
	{
		String str = "";
		
		Iterator it = params.keySet().iterator();
		while(it.hasNext())
		{
			if(str.length()>0)
			{
				str += ",";
			}
			str += (String)it.next() + "=" + params.get(it.next());
		}
		
		return str;
	}

	
	public void removeForPrimaryKey(Object primaryKey)
	{
		getEntityManager().remove(getEntityManager().getReference(entityClass, primaryKey));
	}

	
	public void update(T entity)
	{
		getEntityManager().merge(entity);
	}
	
	public void update(Map values,Map params)
	{
		String hql = "";
	}

	public EntityManager getEntityManager()
	{
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	
	public List<T> findByParams(Map params)
	{
		String hql = "from " + entityClass.getSimpleName();
		Query q = null;
		if (params!=null && !params.isEmpty())
		{
			hql += " where " + getRrerequisiteCriteria(params);
			q = getEntityManager().createQuery(hql);
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext())
			{
				String key = (String) it.next();
				Object value = (Object) params.get(key);
				if(!value.equals("is null") && !value.equals("is not null"))
				{
					q.setParameter(key, value);
				}	
			}
		}
		else
		{
			q = getEntityManager().createQuery(hql);
		}
		
		return q.getResultList();
	}

	
	public List<T> findByHQL(String hql)
	{
		Assert.hasText(hql);
		return entityManager.createQuery(hql).setMaxResults(10000).getResultList();
	}

	
	public List<T> find(String hql, Map params)
	{
		Assert.hasText(hql);
		Query q = entityManager.createQuery(hql);
		if (!params.isEmpty())
		{
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext())
			{
				String key = (String) it.next();
				Object value = (Object) params.get(key);
				q.setParameter(key, value);
			}
		}
		return q.getResultList();
	} 

	
	public Page pagedQuery(String hql, int pageNo, int pageSize,Map params)
	{
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) "+ removeSelect(removeOrders(hql));
		//String countQueryString = " select count (*) from (select aa.* from ("+ hql+" )aa )";
		Query qq = getEntityManager().createQuery(countQueryString);
		Iterator<String> it = params.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = (Object) params.get(key);
			qq.setParameter(key, value);
		}

		List countlist = qq.getResultList();
		long totalCount = (Long) countlist.get(0);

		if (totalCount < 1)
			return new Page();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, params);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).getResultList();

		return new Page(pageNo, totalCount, pageSize, list);
	}
	public Page pagedQuery2(String hql, int pageNo, int pageSize,Map params)
	{
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) "+ removeSelect2(removeOrders(hql));
		Query qq = getEntityManager().createQuery(countQueryString);
		Iterator<String> it = params.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = (Object) params.get(key);
			qq.setParameter(key, value);
		}

		List countlist = qq.getResultList();
		long totalCount = (Long) countlist.get(0);

		if (totalCount < 1)
			return new Page();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, params);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).getResultList();

		return new Page(pageNo, totalCount, pageSize, list);
	}
	
	public Query createQuery(String hql, Map params)
	{
		Assert.hasText(hql);
		Query query = getEntityManager().createQuery(hql);
		Iterator<String> it = params.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = (Object) params.get(key);
			query.setParameter(key, value);
		}
		return query;
	}
	
	private static String removeSelect(String hql)
	{
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	
	private static String removeSelect2(String hql)
	{
		Assert.hasText(hql);
		String[] beginPos = hql.split("from");
		return " from "+beginPos[beginPos.length -1];
	}
	

	private static String removeOrders(String hql)
	{
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find())
		{
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}*/
}
