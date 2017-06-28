/**
  *project demo-l
  *@author changchun.wu
  *2017年6月27日下午3:24:02
  */
package com.jianfei.d.test.mapper;


import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jianfei.d.dao.system.MenuDao;
import com.jianfei.d.entity.common.MenuType;
import com.jianfei.d.entity.system.Menu;

public class MenuMapperTest {
	private static SqlSession sqlSession = null;
	
	@BeforeClass
	public static void doInitTest() throws Exception{
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = sqlSessionFactory.openSession(true);
	}
	
	@Test
	public void insert(){
		MenuDao menuDao = sqlSession.getMapper(MenuDao.class);
		Menu menu = new Menu();
		menu.setName("测试菜单");
		menu.setHref("https://github.com/wosyingjun/beauty_ssm_dubbo");
		menu.setPermission("menu:create");
		menu.setSort(1);
		menu.setType(MenuType.MENU);
		menuDao.insert(menu);
	}
	
	/*@Test
	public void testSelect() throws Exception {
		Menu user = new Menu();
		MenuDao userMapper = sqlSession.getMapper(MenuDao.class);
		
		PageContext.setPageParam(new PageParam());
		Page<User> page = userMapper.selectUser(user);
		
		System.out.println(page);
		
		List<User> users = page.getData();
		
		for(int i=0;i<users.size();i++){
			System.out.println(users.get(i));
		}
	}*/
}
