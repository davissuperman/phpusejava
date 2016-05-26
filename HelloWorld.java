import com.nccc.evpos.HppApiClient;

public class HelloWorld {
  public static void main(String args[]) throws Exception {
         HppApiClient apiClient=new HppApiClient();
         apiClient.setMERCHANTID("6600800020");
         apiClient.setTERMINALID("70502876");
         apiClient.setORDERID("1000002");
         apiClient.setTRANSMODE("0");
         apiClient.setINSTALLMENT("0");
         apiClient.setTRANSAMT("1");
         apiClient.setNotifyURL("www.baidu.com");
         apiClient.setURL("nccnet-ectest.nccc.com.tw", "/merchant/HPPRequest");
         int rtnCode=apiClient.postTransaction();
         System.out.println(rtnCode);
         String responseCode=apiClient.getRESPONSECODE();
         if ("00".equals(responseCode)){
         try {
            System.out.println(apiClient.getKEY());
         } catch (Exception ex){}
         } else {
         System.out.println("Error");
         }
    }
  }
