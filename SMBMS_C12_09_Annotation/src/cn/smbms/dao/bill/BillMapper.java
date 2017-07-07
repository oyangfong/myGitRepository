package cn.smbms.dao.bill;

import java.util.List;

import cn.smbms.pojo.Bill;



public interface BillMapper {
  public List<Bill> getBillByProviderId_foreach_array(int[] providerIds); 
  public List<Bill> getBillByProviderId_foreach_list(List<Integer> providerList); 
}
