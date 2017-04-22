package com.happycar.utils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;

import com.happycar.utils.processor.BasicRowProcessor;


public class QueryUtils {
    private static Logger LOG = Logger.getLogger(QueryUtils.class);
	private Connection con;
    private final QueryRunner _g_runner = new QueryRunner();
    private final RowProcessor processor = new BasicRowProcessor();
    private final ColumnListHandler<?> _g_columnListHandler = new ColumnListHandler<Object>() {
        @Override
        protected Object handleRow(ResultSet rs) throws SQLException {
            Object obj = super.handleRow(rs);
            if (obj instanceof BigInteger)
                return ((BigInteger) obj).longValue();
            return obj;
        }

    };
    private final ScalarHandler<?> _g_scaleHandler = new ScalarHandler<Object>() {
        @Override
        public Object handle(ResultSet rs) throws SQLException {
            Object obj = super.handle(rs);
            if (obj instanceof BigInteger)
                return ((BigInteger) obj).longValue();
            return obj;
        }
    };

    private final List<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>() {
        private static final long serialVersionUID = 1L;

        {
            add(Long.class);
            add(Integer.class);
            add(String.class);
            add(java.util.Date.class);
            add(java.sql.Date.class);
            add(java.sql.Timestamp.class);
        }
    };


    public QueryUtils(EntityManager em) throws SQLException {
    	Session session = (Session) em.getDelegate();
    	 SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();
    	 con = sessionFactory.getConnectionProvider().getConnection();

//    	con = em.unwrap(Connection.class);
    }

    private final boolean _IsPrimitive(Class<?> cls) {
        return cls.isPrimitive() || PrimitiveClasses.contains(cls);
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public Connection getConnection() {
        return con;

    }

    /**
     * 读取某个对象
     *
     * @param sql
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T read(Class<T> beanClass, String sql, Object... params) {
        //Long time = System.currentTimeMillis();
        Connection con = getConnection();
        try {
            return (T) _g_runner.query(con, sql,
                    _IsPrimitive(beanClass) ? _g_scaleHandler
                            : new BeanHandler<T>(beanClass, processor), params
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
				con.close();
			} catch (SQLException e) {
				LOG.error("连接关闭异常",e);
			}
        }
    }


    /**
     * 对象查询
     *
     * @param <T>
     * @param beanClass
     * @param sql
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> query(Class<T> beanClass, String sql,
                             Object... params) {
        //Long time = System.currentTimeMillis();
        Connection con = getConnection();
        try {

            return (List<T>) _g_runner.query(con, sql,
                    _IsPrimitive(beanClass) ? _g_columnListHandler
                            : new BeanListHandler<T>(beanClass, processor), params
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	 try {
 				con.close();
 			} catch (SQLException e) {
 				LOG.error("连接关闭异常",e);
 			}
        }
    }

    public List<Map<String, Object>> query(String sql, Object... params) {
        //Long time = System.currentTimeMillis();
        Connection con = getConnection();
        try {
            return _g_runner.query(con, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	 try {
 				con.close();
 			} catch (SQLException e) {
 				LOG.error("连接关闭异常",e);
 			}
        }
    }

    public List<Object[]> queryArrays(String sql, Object... params) {
        //Long time = System.currentTimeMillis();
        Connection con = getConnection();
        try {
            return _g_runner.query(con, sql, new ArrayListHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	 try {
 				con.close();
 			} catch (SQLException e) {
 				LOG.error("连接关闭异常",e);
 			}
        }
    }

    public Map<String, Object> queryMap(String sql, Object... params) {
        //Long time = System.currentTimeMillis();
        Connection con = getConnection();
        try {
            return _g_runner.query(con, sql, new MapHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	 try {
 				con.close();
 			} catch (SQLException e) {
 				LOG.error("连接关闭异常",e);
 			}
        }
    }


    /**
     * 分页查询
     *
     * @param <T>
     * @param beanClass
     * @param sql
     * @param page
     * @param count
     * @param params
     * @return
     */
    public <T> List<T> query_slice(Class<T> beanClass, String sql,
                                   int page, int count, Object... params) {
        if (page < 0 || count < 0)
            throw new IllegalArgumentException(
                    "Illegal parameter of 'page' or 'count', Must be positive.");
        int from = (page - 1) * count;
        count = (count > 0) ? count : Integer.MAX_VALUE;
        return query(beanClass, sql + " LIMIT ?,?", org.apache.commons.lang3.ArrayUtils.addAll(params,
                new Integer[]{from, count}));
    }


    /**
     * 执行统计查询语句，语句的执行结果必须只返回一个数值
     *
     * @param sql
     * @param params
     * @return
     */
    public long stat(String sql, Object... params) {
        Connection con = getConnection();
        //long time = System.currentTimeMillis();
        try {
            Number num = (Number) _g_runner.query(con, sql,
                    _g_scaleHandler, params);
            return (num != null) ? num.longValue() : -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	 try {
 				con.close();
 			} catch (SQLException e) {
 				LOG.error("连接关闭异常",e);
 			}
        }
    }

    /**
     * 执行INSERT/UPDATE/DELETE语句
     *
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object... params) {
        Connection con = getConnection();
        //long time = System.currentTimeMillis();
        try {
            return _g_runner.update(con, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	 try {
 				con.close();
 			} catch (SQLException e) {
 				LOG.error("连接关闭异常",e);
 			}
        }
    }


    /**
     * 批量执行指定的SQL语句
     *
     * @param sql
     * @param params
     * @return
     */
    public int[] batch(String sql, Object[][] params) {
        Connection con = getConnection();
        try {
            return _g_runner.batch(con, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	 try {
 				con.close();
 			} catch (SQLException e) {
 				LOG.error("连接关闭异常",e);
 			}
        }
    }
}
