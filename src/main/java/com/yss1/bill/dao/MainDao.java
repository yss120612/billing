package com.yss1.bill.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;


import com.yss1.bill.util.ApplicationContextUtil;

@Repository
public class MainDao {
//	@Autowired
//	@Qualifier( "MySqlDataSource1")
//	private DataSource pgDS;
	//private JdbcTemplate pgDT;
	
	@PostConstruct
	public void init() {
		//pgDS=(DataSource)ApplicationContextUtil.getApplicationContext().getBean("MySqlDataSource1");
		//JdbcTemplate pgDT=new JdbcTemplate((DataSource)ApplicationContextUtil.getApplicationContext().getBean("MySqlDataSource1"),true);
	}
	
//	public void getTelCount() {
//		JdbcTemplate pgDT=new JdbcTemplate(pgDS);
//	}
	
//	public void backup(Man man, long id) {
//		if (id==0) return;
//		if (checkID(id)) return;
//		pgDT.update("insert into public.person (id,fio,insnmb,prnsex,prnbrd,rk,igd,otn_zp) values (?,?,?,?,?,?,?,?)",
//				id,
//				man.getFamily()+" "+man.getName()+" "+man.getOtch(),
//				man.getSNILS(),
//				man.getSex(),
//				man.getBirthDayDate(),
//				man.getKoeffFix(),
//				man.getIjdevency(),
//				man.getkSal60());
//		saveStaj(id,man.getStaj(),1);
//		saveStaj(id,man.getStajKonv(),2);
//		savePlatej(id,man.getPlateg20002001());
//		saveVsnosy(id,man.getVsnosy());
//	}
	
	
//	private boolean checkID(long tel) {
//		int count=pgDT.queryForObject("select count(*) from  where tel=?",Integer.class,id);
//		return count>0;
//	}
//	
	
//	id bigint NOT NULL, -- Номер запроса справки
//	vid smallint NOT NULL, -- Тип источника
//	dstart date NOT NULL, -- Дата с
//	dend date NOT NULL, -- Дата по
//	regn character varying(15), -- Рег.номер страхователя
//	predpr_name character varying(100), -- Наименование страхователя
//	vid_deyat character varying(40), -- Вид деятельности
//	cggext character varying(30), -- Территориальные условия
//	cspext character varying(30), -- Выслуга лет
//	ctpext character varying(30), -- Исчесляемый стаж
//	cwcext character varying(30), -- Особые условия
//	dopctpext character varying(60), -- Доп.параметры к исчесляемому стажу
//	dopcspext character varying(60) -- Доп. параметры к выслуги лет
//  сохранение стажа (vid 1-данные работодателя, 2-конвертация)
//	private void saveStaj(long id,List<Staj> stl, int vid) {
//		if (stl==null || stl.isEmpty()) return;
//		for(Staj st:stl) {
//			pgDT.update("insert into public.stag (id,vid,dstart,dend,regn,predpr_name,vid_deyat,cggext,cspext,ctpext,cwcext,dopctpext,dopcspext) values("+
//			id+","+
//			vid+",DATE('"+
//			Utils.getFormattedDate4sql2(st.getStartDate())+"'),DATE('"+
//			Utils.getFormattedDate4sql2(st.getEndDate())+"'),'"+
//			st.getRegNumb()+"','"+
//			st.getPredprName()+"','"+
//			st.getVidDeyat()+"','"+
//			st.getCggext()+"','"+
//			st.getCspext()+"','"+
//			st.getCtpext()+"','"+
//			st.getCwcext()+"','"+
//			st.getDopctpext()+"','"+
//			st.getDopcspext()+"')");
//			pgDT.update("insert into public.stag (id,vid,dstart,dend,regn,predpr_name,vid_deyat,cggext,cspext,ctpext,cwcext,dopctpext,dopcspext) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
//					id,vid,st.getStartDate(),st.getEndDate(),
//					//Utils.getFormattedDate4sql2(st.getStartDate()),
//					//Utils.getFormattedDate4sql2(st.getEndDate()),
//					st.getRegNumb(),
//					st.getPredprName(),
//					st.getVidDeyat(),
//					st.getCggext(),
//					st.getCspext(),st.getCtpext(),st.getCwcext(),
//					st.getDopctpext(),st.getDopcspext());
//
//		}
//	}
	
	
//	id bigint NOT NULL, -- Номер запроса справки
//	datestart date NOT NULL, -- Дата с
//	dateend date NOT NULL, -- Дата по
//	raion integer, -- Район
//	region integer, -- Регион
//	summa real -- Сумма
//	зарплата за 2000-2002 гг. сохранение


	
//	  id bigint NOT NULL, -- Номер запроса справки
//	  dptcod integer NOT NULL, -- Код района
//	  dcinmb bigint NOT NULL, -- Входящий номер ПТК СПУ
//	  year integer, -- Год
//	  ctmcod character varying(10), -- Период
//	  asr real, -- Сумма
//	  cprcod integer, -- Код ЗЛ
//	  regnum character varying(14) -- Номер
//	  сохранение взносов
	
		
//
//
//	    private RowMapper<Platej> plRowMapper = new RowMapper<Platej>() {
//			public Platej mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Platej platej = new Platej();
//				platej.setdStart(rs.getDate("datestart"));
//				platej.setdEnd(rs.getDate("dateend"));
//				platej.setRaion(rs.getInt("raion"));
//				platej.setRaion(rs.getInt("region"));
//				platej.setSumma(rs.getFloat("summa"));
//				return platej;
//			}
//		};
		

	}
