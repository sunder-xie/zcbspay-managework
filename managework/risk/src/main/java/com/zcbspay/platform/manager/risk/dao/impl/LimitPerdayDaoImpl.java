package com.zcbspay.platform.manager.risk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.risk.bean.LimitMemCreditDayBean;
import com.zcbspay.platform.manager.risk.bean.LimitMemCreditMonthBean;
import com.zcbspay.platform.manager.risk.bean.LimitMemDayBean;
import com.zcbspay.platform.manager.risk.bean.LimitMemMonthBean;
import com.zcbspay.platform.manager.risk.bean.LimitPerdayBean;
import com.zcbspay.platform.manager.risk.bean.LimitSingleBean;
import com.zcbspay.platform.manager.risk.dao.LimitPerdayDao;
import com.zcbspay.platform.manager.risk.pojo.PojoLimitPerday;

@Repository
public class LimitPerdayDaoImpl extends HibernateBaseDAOImpl<PojoLimitPerday> implements LimitPerdayDao {
	//--------------------------------------------------------------银行卡单日限次------------------------------------------------------
	   public Map<String, Object> findLimitPerdayByPage(Map<String, Object> variables, int page,int rows) {
			String[] columns = new String[] { 
					 "v_riskver"   ,
				     "v_busicode"  ,
				     "v_nums",
				     "i_no",
				     "i_perno" };
			
			Object[] paramaters = new Object[]{
					variables.get("riskver"),
					variables.get("busicode"),
		    null,
			page,rows}; 
			return executePageOracleProcedure("{CALL PCK_T_LIMIT_PERDAY.sel_t_limit_perday(?,?,?,?,?,?,?)}", columns,
					paramaters, "cursor0","v_total");

		}
	// 风控版本
		public List<?> query_risk_all() {
		    String[] columns = new String[] { "v_in"};
			Object[] paramaters = new Object[1];
			paramaters[0] = 1;
			return executeOracleProcedure(
					"{CALL  PCK_FOR_SELECT.sel_risk_all(?,?)}",columns,
					paramaters, "cursor0");
	    }

	    public String AddOneLimitPerday(LimitPerdayBean limitPerday) {	
			    if(limitPerday==null){
			    	return "操作失败！";
			    }
				Object[] paramaters = new Object[] {limitPerday.getCaseid(),limitPerday.getNums(),limitPerday.getRisklevel(),limitPerday.getNotes(),limitPerday.getRemarks(),limitPerday.getCardtype()};
				String[] columns = new String[] {"v_caseid","v_nums","v_risklevel","v_notes","v_remarks","v_cardtype"};
				Object total =executeOracleProcedure(
						"{CALL PCK_T_LIMIT_PERDAY.ins_t_limit_perday(?,?,?,?,?,?,?)}",
						columns,paramaters, "cursor0").get(0).get("INFO");
				return (String) total;
		       } 
	    public String updateOneLimitPerday(LimitPerdayBean limitPerday) {	
		    if(limitPerday==null){
		    	return "操作失败！";
		    }
			Object[] paramaters = new Object[] {limitPerday.getTId(),limitPerday.getNums(),limitPerday.getRisklevel(),limitPerday.getNotes(),limitPerday.getRemarks(),limitPerday.getCardtype()};
			String[] columns = new String[] {"v_t_id","v_nums","v_risklevel","v_notes","v_remarks","v_cardtype"};
			Object total =executeOracleProcedure(
					"{CALL PCK_T_LIMIT_PERDAY.upt_t_limit_perday(?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	       }      
    public Map<String, Object> queryOneLimitPerday(String tId) {
				String[] columns = new String[] { 
						 "v_tid"  };				
				Object[] paramaters = new Object[]{tId};
				return executeOracleProcedure("{CALL PCK_T_LIMIT_PERDAY.sel_t_limit_perday_date(?,?)}", columns,
						paramaters, "cursor0").get(0);
	    }	
    public String deleteOneLimitPerday(String tId) {
			String[] columns = new String[] { 
					"v_t_id","v_user" };				
			Object[] paramaters = new Object[]{tId,null};
			return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_PERDAY.del_t_limit_perday(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
     }
    public String startOneLimitPerday(String tId) {
			String[] columns = new String[] { 
					 "v_t_id","v_user"  };				
			Object[] paramaters = new Object[]{tId,null};
			return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_PERDAY.start_t_limit_perday(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
    }
  //--------------------------------------------------------------卡类别日累计限额------------------------------------------------------    
    public Map<String, Object> findLimitMemCreditDayByPage(Map<String, Object> variables, int page,int rows) {
			String[] columns = new String[] { 
					 "v_memberid"   ,
				     "v_limit_amount"  ,
				     "v_limit_count",
				     "v_card_type",
				     "i_no",
				     "i_perno" };
			Object[] paramaters = new Object[]{
					variables.get("memberid"),
					null,null,
			        variables.get("card_type"),
			        page,rows}; 
			return executePageOracleProcedure("{CALL PCK_T_LIMIT_MEM_CREDIT_DAY.sel_t_limit_mem_credit_day(?,?,?,?,?,?,?,?)}", columns,
					paramaters, "cursor0","v_total");

		}
    public String AddOneLimitMemCreditDay(LimitMemCreditDayBean Mem) {	
		    if(Mem==null){
		    	return "操作失败！";
		    }
			Object[] paramaters = new Object[] {Mem.getMemberid(),Mem.getLimitAmount(),Mem.getLimitCount(),Mem.getCardType(),Mem.getRisklevel(),Mem.getNotes(),Mem.getRemarks()};
			String[] columns = new String[] {"v_memberid","v_limit_amount","v_limit_count","v_card_type","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL PCK_T_LIMIT_MEM_CREDIT_DAY.ins_t_limit_mem_credit_day(?,?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	       } 
    public Map<String, Object> queryOneLimitMemCreditDay(String tId) {
			String[] columns = new String[] { 
					 "v_in"  };				
			Object[] paramaters = new Object[]{tId};
			return executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_CREDIT_DAY.sel_t_limit_mem_credit_day_one(?,?)}", columns,
					paramaters, "cursor0").get(0);
    }	 
    public String UpdateLimitMemCreditDay(LimitMemCreditDayBean Mem) {	
		    if(Mem==null){
		    	return "操作失败！";
		    }
			Object[] paramaters = new Object[] {Mem.getTId(),Mem.getLimitAmount(),Mem.getLimitCount(),Mem.getCardType(),Mem.getRisklevel(),Mem.getNotes(),Mem.getRemarks()};
			String[] columns = new String[] {"v_t_id","v_limit_amount","v_limit_count","v_card_type","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL PCK_T_LIMIT_MEM_CREDIT_DAY.upt_t_limit_mem_credit_day(?,?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	   }  
    public String deleteLimitMemCreditDay(String tId) {
			String[] columns = new String[] { 
					"v_t_id","v_user" };				
			Object[] paramaters = new Object[]{tId,null};
			return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_CREDIT_DAY.del_t_limit_mem_credit_day(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
    }
   public String startLimitMemCreditDay(String tId) {
			String[] columns = new String[] { 
					 "v_t_id","v_user"  };				
			Object[] paramaters = new Object[]{tId,null};
			return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_CREDIT_DAY.start_t_limit_mem_credit_day(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
   }
   //--------------------------------------------------------------卡类别月累计限额------------------------------------------------------    
   public Map<String, Object> findLimitMemCreditMonthByPage(Map<String, Object> variables, int page,int rows) {
			String[] columns = new String[] { 
					 "v_memberid"   ,
				     "v_limit_amount"  ,
				     "v_limit_count",
				     "v_card_type",
				     "i_no",
				     "i_perno" };
			Object[] paramaters = new Object[]{
					variables.get("memberid"),
					null,null,
			        variables.get("card_type"),
			        page,rows}; 
			return executePageOracleProcedure("{CALL pck_t_limit_mem_credit_month.sel_t_limit_mem_credit_month(?,?,?,?,?,?,?,?)}", columns,
					paramaters, "cursor0","v_total");

		}
   public String AddOneLimitMemCreditMonth(LimitMemCreditMonthBean Mem) {	
		    if(Mem==null){
		    	return "操作失败！";
		    }
			Object[] paramaters = new Object[] {Mem.getMemberid(),Mem.getLimitAmount(),Mem.getLimitCount(),Mem.getCardType(),Mem.getRisklevel(),Mem.getNotes(),Mem.getRemarks()};
			String[] columns = new String[] {"v_memberid","v_limit_amount","v_limit_count","v_card_type","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL pck_t_limit_mem_credit_month.ins_t_limit_mem_credit_month(?,?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	       } 
   public Map<String, Object> queryOneLimitMemCreditMonth(String tId) {
			String[] columns = new String[] { 
					 "v_in"  };				
			Object[] paramaters = new Object[]{tId};
			return executeOracleProcedure("{CALL pck_t_limit_mem_credit_month.sel_t_limit_mem_month_one(?,?)}", columns,
					paramaters, "cursor0").get(0);
   }	 
   public String UpdateLimitMemCreditMonth(LimitMemCreditMonthBean Mem) {	
		    if(Mem==null){
		    	return "操作失败！";
		    } 
			Object[] paramaters = new Object[] {Mem.getTId(),Mem.getLimitAmount(),Mem.getLimitCount(),Mem.getCardType(),Mem.getRisklevel(),Mem.getNotes(),Mem.getRemarks()};
			String[] columns = new String[] {"v_t_id","v_limit_amount","v_limit_count","v_card_type","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL pck_t_limit_mem_credit_month.upt_t_limit_mem_credit_month(?,?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	   }  
   public String deleteLimitMemCreditMonth(String tId) {
			String[] columns = new String[] { 
					"v_t_id","v_user" };				
			Object[] paramaters = new Object[]{tId,null};
			return (String) executeOracleProcedure("{CALL pck_t_limit_mem_credit_month.del_t_limit_mem_credit_month(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
   }
  public String startLimitMemCreditMonth(String tId) {
			String[] columns = new String[] { 
					 "v_t_id","v_user"  };				
			Object[] paramaters = new Object[]{tId,null};
			return (String) executeOracleProcedure("{CALL pck_t_limit_mem_credit_month.start_t_limit_mem_credit_month(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
  }
  //--------------------------------------------------------------卡月累计限额------------------------------------------------------    
  public Map<String, Object> findLimitMemMonthByPage(Map<String, Object> variables, int page,int rows) {

			String[] columns = new String[] { 
					 "v_memberid"   ,
				     "v_limit_amount"  ,
				     "v_limit_count",
				     "i_no",
				     "i_perno" };
			Object[] paramaters = new Object[]{
					variables.get("memberid"),
					null,null, page,rows}; 
			return executePageOracleProcedure("{CALL PCK_T_LIMIT_MEM_MONTH.sel_t_limit_mem_month(?,?,?,?,?,?,?)}", columns,
					paramaters, "cursor0","v_total");

		}
  public String AddOneLimitMemMonth(LimitMemMonthBean Mem) {	
		    if(Mem==null){
		    	return "操作失败！";
		    }
			Object[] paramaters = new Object[] {Mem.getMemberid(),Mem.getLimitAmount(),Mem.getLimitCount(),Mem.getRisklevel(),Mem.getNotes(),Mem.getRemarks()};
			String[] columns = new String[] {"v_memberid","v_limit_amount","v_limit_count","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL PCK_T_LIMIT_MEM_MONTH.ins_t_limit_mem_month(?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	       } 
  public Map<String, Object> queryOneLimitMemMonth(String tId) {
			String[] columns = new String[] { 
					 "v_in"  };				
			Object[] paramaters = new Object[]{tId};
			return executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_MONTH.sel_t_limit_mem_month_one(?,?)}", columns,
					paramaters, "cursor0").get(0);
  }	 
  public String UpdateLimitMemMonth(LimitMemMonthBean Mem) {	
		    if(Mem==null){
		    	return "操作失败！";
		    } 
			Object[] paramaters = new Object[] {Mem.getTId(),Mem.getLimitAmount(),Mem.getLimitCount(),Mem.getRisklevel(),Mem.getNotes(),Mem.getRemarks()};
			String[] columns = new String[] {"v_t_id","v_limit_amount","v_limit_count","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL PCK_T_LIMIT_MEM_MONTH.upt_t_limit_mem_month(?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	   }  
  public String deleteLimitMemMonth(String tId,Long userid) {
			String[] columns = new String[] { 
					"v_t_id","v_user" };				
			Object[] paramaters = new Object[]{tId,userid};
			return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_MONTH.del_t_limit_mem_month(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
  }
 public String startLimitMemMonth(String tId,Long userid) {
			String[] columns = new String[] { 
					 "v_t_id","v_user"  };				
			Object[] paramaters = new Object[]{tId,userid};
			return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_MONTH.start_t_limit_mem_month(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
 }     
 //--------------------------------------------------------------卡类别累计限额------------------------------------------------------    
 public Map<String, Object> findLimitMemDayByPage(Map<String, Object> variables, int page,int rows) {
			String[] columns = new String[] { 
					 "v_memberid"   ,
				     "v_limit_amount"  ,
				     "v_limit_count",
				     "i_no",
				     "i_perno" };
			Object[] paramaters = new Object[]{
					variables.get("memberid"),
					null,null, page,rows}; 
			return executePageOracleProcedure("{CALL PCK_T_LIMIT_MEM_DAY.sel_t_limit_mem_day(?,?,?,?,?,?,?)}", columns,
					paramaters, "cursor0","v_total");

		}
 public String AddOneLimitMemDay(LimitMemDayBean Mem) {	
		    if(Mem==null){
		    	return "操作失败！";
		    }
			Object[] paramaters = new Object[] {Mem.getMemberid(),Mem.getLimitAmount(),Mem.getLimitCount(),Mem.getRisklevel(),Mem.getNotes(),Mem.getRemarks()};
			String[] columns = new String[] {"v_memberid","v_limit_amount","v_limit_count","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL PCK_T_LIMIT_MEM_DAY.ins_t_limit_mem_day(?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	       } 
 public Map<String, Object> queryOneLimitMemDay(String tId) {
			String[] columns = new String[] { 
					 "v_in"  };				
			Object[] paramaters = new Object[]{tId};
			return executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_DAY.sel_t_limit_mem_day_one(?,?)}", columns,
					paramaters, "cursor0").get(0);
 }	 
 public String UpdateLimitMemDay(LimitMemDayBean Mem) {	
		    if(Mem==null){
		    	return "操作失败！";
		    }
			Object[] paramaters = new Object[] {Mem.getTId(),Mem.getLimitAmount(),Mem.getLimitCount(),Mem.getRisklevel(),Mem.getNotes(),Mem.getRemarks()};
			String[] columns = new String[] {"v_t_id","v_limit_amount","v_limit_count","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL PCK_T_LIMIT_MEM_DAY.upt_t_limit_mem_day(?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	   }  
 public String deleteLimitMemDay(String tId,Long userid) {
			String[] columns = new String[] { 
					"v_t_id","v_user" };				
			Object[] paramaters = new Object[]{tId,null};
			return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_DAY.del_t_limit_mem_day(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
 }
public String startLimitMemDay(String tId,Long userid) {
			String[] columns = new String[] { 
					 "v_t_id","v_user"  };				
			Object[] paramaters = new Object[]{tId,null};
			return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_MEM_DAY.start_t_limit_mem_day(?,?,?)}", columns,
					paramaters, "cursor0").get(0).get("INFO");
}
	//--------------------------------------------------------------单笔限额------------------------------------------------------
public Map<String, Object> findLimitSingleByPage(Map<String, Object> variables, int page,int rows) {
		String[] columns = new String[] { 
				 "v_riskver"   ,
				 "v_busicode",
			     "v_max_amount"  ,
			     "v_min_amount",
			     "i_no",
			     "i_perno" };
		
		Object[] paramaters = new Object[]{
				variables.containsKey("riskver") ? variables.get("riskver") : null,
			    variables.get("caseid"), null,null,
		        page,rows}; 
		return executePageOracleProcedure("{CALL PCK_T_LIMIT_SINGLE.sel_t_limit_single(?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0","v_total");

	}
 public String AddOneLimitSingle(LimitSingleBean limitSingle) {	
		    if(limitSingle==null){
		    	return "操作失败！";
		    }
			Object[] paramaters = new Object[] {limitSingle.getCaseid(),limitSingle.getMaxAmount(),limitSingle.getMinAmount(),limitSingle.getRisklevel(),limitSingle.getNotes(),limitSingle.getRemarks()};
			String[] columns = new String[] {"v_caseid","v_max_amount","v_min_amount","v_risklevel","v_notes","v_remarks"};
			Object total =executeOracleProcedure(
					"{CALL PCK_T_LIMIT_SINGLE.ins_t_limit_single(?,?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	       } 
 public String updateOneLimitSingle(LimitSingleBean limitSingle) {	
	    if(limitSingle==null){
	    	return "操作失败！";
	    }
	    Object[] paramaters = new Object[] {limitSingle.getTId(),limitSingle.getMaxAmount(),limitSingle.getMinAmount(),limitSingle.getRisklevel(),limitSingle.getNotes(),limitSingle.getRemarks()};
		String[] columns = new String[] {"v_t_id","v_max_amount","v_min_amount","v_risklevel","v_notes","v_remarks"};
		Object total =executeOracleProcedure(
				"{CALL PCK_T_LIMIT_SINGLE.upt_t_limit_single(?,?,?,?,?,?,?)}",
				columns,paramaters, "cursor0").get(0).get("INFO");
		return (String) total;
    }      
public Map<String, Object> queryOneLimitSingle(String tId) {
			String[] columns = new String[] { 
					 "v_in"  };				
			Object[] paramaters = new Object[]{tId};
			return executeOracleProcedure("{CALL PCK_T_LIMIT_SINGLE.sel_t_limit_single_one(?,?)}", columns,
					paramaters, "cursor0").get(0);
 }	
public String deleteOneLimitSingle(String tId) {
		String[] columns = new String[] { 
				"v_t_id","v_user" };				
		Object[] paramaters = new Object[]{tId,null};
		return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_SINGLE.del_t_limit_single(?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("INFO");
 }
public String startOneLimitSingle(String tId) {
		String[] columns = new String[] { 
				 "v_t_id","v_user"  };				
		Object[] paramaters = new Object[]{tId,null};
		return (String) executeOracleProcedure("{CALL PCK_T_LIMIT_SINGLE.start_t_limit_single(?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("INFO");
}   

}
