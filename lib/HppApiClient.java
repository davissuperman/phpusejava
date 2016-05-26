/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  com.nccc.evpos.HppApiClient$MyTrustManager
 */
package com.nccc.evpos;

import com.nccc.evpos.HppApiClient;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class HppApiClient {
    private Map container = new HashMap();
    private String sOnlineIP = "localhost";
    private String sBackupIP = "localhost";
    private String domainNAME = "nccnet-ectest.nccc.com.tw";
    private String requestURL = "/merchant/APIRequest";
    private int iPORT = 9950;
    private int iTimeOut = 405000;
    private Date date = null;
    private SimpleDateFormat sd = null;
    private String sslProviderName = "Default";
    private static final String apiVersion = "HPP-JavaApi_v1.2";
    private boolean debug = true;
    private String connectType = "Internet";
    private String protocol = "TLSv1.2";
    private byte[] zipbuffer = new byte[9000];

    public void setDebug(boolean bl) {
        this.debug = bl;
    }

    public void setMERCHANTID(String string) {
        this.setVariable("MERCHANTID", string);
    }

    public void setTERMINALID(String string) {
        this.setVariable("TERMINALID", string);
    }

    public void setORDERID(String string) {
        this.setVariable("ORDERID", string);
    }

    public void setTRANSMODE(String string) {
        this.setVariable("TRANSMODE", string);
    }

    public void setTRANSAMT(String string) {
        this.setVariable("TRANSAMT", string);
    }

    public void setINSTALLMENT(String string) {
        this.setVariable("INSTALLMENT", string);
    }

    public void setNotifyURL(String string) {
        this.setVariable("NotifyURL", string);
    }

    public void setPrivateData(String string) {
        this.setVariable("PRIVATE_DATA", string);
    }

    public void setBankNo(String string) {
        this.setVariable("BankNo", string);
    }

    public void setTemplate(String string) {
        this.setVariable("TEMPLATE", string);
    }

    public void setCSS_URL(String string) {
        this.setVariable("CSS_URL", string);
    }

    public void setIDNUMBER(String string) {
        this.setVariable("IDNUMBER", string);
    }

    public void setKEY(String string) {
        this.setVariable("KEY", string);
    }

    public String getMERCHANTID() {
        return this.getVariable("MERCHANTID");
    }

    public String getTERMINALID() {
        return this.getVariable("TERMINALID");
    }

    public String getORDERID() {
        return this.getVariable("ORDERID");
    }

    public String getPAN() {
        return this.getVariable("PAN");
    }

    public String getEXPIREDATE() {
        return this.getVariable("EXPIREDATE");
    }

    public String getEXTENNO() {
        return this.getVariable("EXTENNO");
    }

    public String getTRANSCODE() {
        return this.getVariable("TRANSCODE");
    }

    public String getTRANSMODE() {
        return this.getVariable("TRANSMODE");
    }

    public String getTRANSAMT() {
        return this.getVariable("TRANSAMT");
    }

    public String getINSTALLMENT() {
        return this.getVariable("INSTALLMENT");
    }

    public String getNotifyURL() {
        return this.getVariable("NotifyURL");
    }

    public String getPrivateData() {
        return this.getVariable("PRIVATE_DATA");
    }

    public String getBankNo() {
        return this.getVariable("BankNo");
    }

    public String getTemplate() {
        return this.getVariable("TEMPLATE");
    }

    public String getCSS_URL() {
        return this.getVariable("CSS_URL");
    }

    public String getRESPONSECODE() {
        return this.getVariable("RESPONSECODE");
    }

    public String getRESPONSEMSG() {
        return this.getVariable("RESPONSEMSG");
    }

    public String getKEY() {
        return this.getVariable("KEY");
    }

    public String getTRANSDATE() {
        return this.getVariable("TRANSDATE");
    }

    public String getTRANSTIME() {
        return this.getVariable("TRANSTIME");
    }

    public String getAPPROVECODE() {
        return this.getVariable("APPROVECODE");
    }

    public String getINSTALLMENTTYPE() {
        return this.getVariable("INSTALLMENTTYPE");
    }

    public String getFIRSTAMT() {
        return this.getVariable("FIRSTAMT");
    }

    public String getEACHAMT() {
        return this.getVariable("EACHAMT");
    }

    public String getFEE() {
        return this.getVariable("FEE");
    }

    public String getREDEEMTYPE() {
        return this.getVariable("REDEEMTYPE");
    }

    public String getREDEEMUSED() {
        return this.getVariable("REDEEMUSED");
    }

    public String getREDEEMBALANCE() {
        return this.getVariable("REDEEMBALANCE");
    }

    public String getCREDITAMT() {
        return this.getVariable("CREDITAMT");
    }

    public String getRiskMark() {
        return this.getVariable("RISK_MARK");
    }

    public String getForeign() {
        return this.getVariable("FOREIGN");
    }

    public String getSecureStatus() {
        return this.getVariable("SECURE_STATUS");
    }

    public void setURL(String string, String string2) {
        this.domainNAME = string;
        this.requestURL = string2;
        this.connectType = "Internet";
    }

    public String getURL() {
        return "https://" + this.domainNAME + "/" + this.requestURL;
    }

    public void setProvider(String string) {
        this.sslProviderName = string;
    }

    public void setIP(String string, int n, String string2) {
        this.domainNAME = string + ":" + n;
        this.requestURL = string2;
        this.connectType = "Leased";
    }

    private void setVariable(String string, Object object) {
        if (object != null) {
            this.container.put(string, object);
        }
    }

    private String getVariable(String string) {
        Object v = this.container.get(string);
        if (v == null) {
            return "";
        }
        return v.toString();
    }

    private int verifyData() {
        String string = null;
        Object var2_2 = null;
        if (this.getVariable("MERCHANTID").length() <= 0) {
            return -901;
        }
        if (this.getVariable("ORDERID").length() <= 0) {
            return -903;
        }
        if (this.getVariable("TERMINALID").length() <= 0) {
            return -902;
        }
        if (this.getVariable("TRANSAMT").length() <= 0) {
            return -904;
        }
        string = this.getVariable("TRANSMODE");
        if (string.length() <= 0) {
            return -907;
        }
        if ("0".equals(string) || "1".equals(string) || "2".equals(string)) {
            if ("1".equals(string) && this.getVariable("INSTALLMENT").length() <= 0) {
                return -906;
            }
        } else {
            return -907;
        }
        return 0;
    }

    private int verifyCancelData() {
        if (this.getVariable("MERCHANTID").length() <= 0) {
            return -901;
        }
        if (this.getVariable("ORDERID").length() <= 0) {
            return -903;
        }
        return 0;
    }

    private int verifyQueryData() {
        String string = null;
        String string2 = null;
        String string3 = null;
        string = this.getVariable("MERCHANTID");
        string2 = this.getVariable("ORDERID");
        string3 = this.getVariable("KEY");
        if (string3.length() < 10 && string.length() != 10 && string2.length() < 1) {
            this.container.put("RESPONSECODE", "940");
            this.container.put("RESPONSEMSG", "\u4ea4\u6613\u91d1\u9470\u932f\u8aa4");
            return -940;
        }
        return 0;
    }

    public int postTransaction() throws Exception {
        int n;
        block31 : {
            Object var1_1 = null;
            Object var2_2 = null;
            Object var3_3 = null;
            Object var5_4 = null;
            BufferedInputStream bufferedInputStream = null;
            FilterOutputStream filterOutputStream = null;
            String string = null;
            String string2 = null;
            String string3 = null;
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            byte[] arrby = new byte[8192];
            int n2 = 0;
            int n3 = 0;
            n = 9;
            int n4 = 0;
            int n5 = 443;
            Socket socket = null;
            InetAddress inetAddress = null;
            String string4 = "";
            n4 = this.verifyData();
            if (n4 < 0) {
                return n4;
            }
            try {
                if ("Default".equalsIgnoreCase(this.sslProviderName)) {
                    boolean bl = false;
                    try {
                        Security.addProvider((Provider)Class.forName("com.sun.net.ssl.internal.ssl.Provider").newInstance());
                        bl = true;
                    }
                    catch (Exception var23_24) {
                        this.log("USE com.sun.net.ssl.internal.ssl.Provider FAILE");
                    }
                    if (!bl) {
                        Security.addProvider((Provider)Class.forName("com.ibm.jsse.IBMJSSEProvider").newInstance());
                    }
                } else {
                    Security.addProvider((Provider)Class.forName(this.sslProviderName).newInstance());
                }
                string4 = "HPP-JavaApi_v1.2|" + this.connectType + "|" + this.protocol;
                MyTrustManager myTrustManager = new MyTrustManager(this);
                TrustManager[] arrtrustManager = new TrustManager[]{myTrustManager};
                SSLContext sSLContext = SSLContext.getInstance(this.protocol);
                sSLContext.init(null, arrtrustManager, null);
                SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
                SSLSocketFactory sSLSocketFactory2 = sSLContext.getSocketFactory();
                string2 = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?><xmlroot><xmlhead /><xmlbody><group PCODE=\"TOP\"><item BUILDER=\"" + string4 + "\" ACQUIRERID=\"493817\" MERCHANTID=\"" + this.getVariable("MERCHANTID") + "\"  TERMINALID=\"" + this.getVariable("TERMINALID") + "\" ORDERID=\"" + this.getVariable("ORDERID") + "\"  TRANSCODE=\"00\"  TRANSMODE=\"" + this.getVariable("TRANSMODE") + "\" TRANSAMT=\"" + this.getVariable("TRANSAMT") + "\" INSTALLMENT=\"" + this.getVariable("INSTALLMENT") + "\" NotifyURL=\"" + this.getVariable("NotifyURL") + "\" BankNo=\"" + this.getVariable("BankNo") + "\" TEMPLATE=\"" + this.getVariable("TEMPLATE") + "\" CSS_URL=\"" + this.getVariable("CSS_URL") + "\" PRIVATE_DATA=\"" + this.getVariable("PRIVATE_DATA") + "\" IDNUMBER=\"" + this.getVariable("IDNUMBER") + "\" /></group></xmlbody></xmlroot>";
                stringBuffer.setLength(0);
                stringBuffer.append(URLEncoder.encode("MD", "Big5"));
                stringBuffer.append('=');
                stringBuffer.append(URLEncoder.encode("HPP-API", "Big5"));
                stringBuffer.append('&');
                stringBuffer.append(URLEncoder.encode("RequestXML", "Big5"));
                stringBuffer.append('=');
                stringBuffer.append(URLEncoder.encode(string2, "Big5"));
                string = stringBuffer.toString();
                try {
                    n3 = this.domainNAME.indexOf(58);
                    if (n3 > 0) {
                        try {
                            n5 = Integer.parseInt(this.domainNAME.substring(n3 + 1));
                        }
                        catch (Exception var27_29) {
                            n5 = 443;
                        }
                        this.domainNAME = this.domainNAME.substring(0, n3);
                    } else {
                        n5 = 443;
                    }
                    inetAddress = InetAddress.getByName(this.domainNAME);
                    string3 = inetAddress.getHostAddress();
                    socket = (SSLSocket)sSLSocketFactory2.createSocket(string3, n5);
                    socket.setEnabledCipherSuites(socket.getSupportedCipherSuites());
                }
                catch (Exception var27_30) {
                    var27_30.printStackTrace(System.out);
                    this.container.put("RESPONSECODE", "921");
                    this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var27_30.getMessage());
                    this.log("\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var27_30.getMessage());
                    int n6 = -999;
                    if (socket != null) {
                        socket.close();
                    }
                    return n6;
                }
                stringBuffer2.append("POST " + this.requestURL + " HTTP/1.1\r\n");
                stringBuffer2.append("User-Agent: HPP-JavaApi_v1.2\r\n");
                stringBuffer2.append("Host: " + this.domainNAME + "\r\n");
                stringBuffer2.append("Content-Type: application/x-www-form-urlencoded\r\n");
                stringBuffer2.append("Content-Length: " + stringBuffer.toString().getBytes().length + "\r\n\r\n");
                stringBuffer2.append(stringBuffer.toString());
                string = stringBuffer2.toString();
                if (this.debug) {
                    this.log(string);
                }
                socket.setSoTimeout(this.iTimeOut);
                socket.setTcpNoDelay(true);
                try {
                    int n7;
                    filterOutputStream = new BufferedOutputStream(socket.getOutputStream());
                    bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                    filterOutputStream.write(string.getBytes(), 0, string.getBytes().length);
                    filterOutputStream.flush();
                    long l = System.currentTimeMillis() + (long)this.iTimeOut;
                    stringBuffer.setLength(0);
                    while (System.currentTimeMillis() < l) {
                        for (n7 = 0; n7 < 8192; ++n7) {
                            this.zipbuffer[n7] = 0;
                        }
                        n2 = bufferedInputStream.read(this.zipbuffer);
                        if (n2 <= -1) break;
                        stringBuffer.append(new String(this.zipbuffer, 0, n2, "cp950"));
                        if (stringBuffer.indexOf("</xmlroot>") < 0 && stringBuffer.indexOf("</html>") < 0) continue;
                    }
                    string = stringBuffer.toString();
                    if (this.debug) {
                        this.log("Response=" + string);
                    }
                    if (string.length() > 0) {
                        n3 = string.indexOf("<html>");
                        if (n3 >= 0 || (n3 = string.indexOf("<HTML>")) >= 0) {
                            this.container.put("RESPONSEHTML", string.substring(n3));
                            n = 1;
                        } else {
                            this.container.clear();
                            this.parser(string.substring(string.indexOf("<?xml")));
                            n = 2;
                        }
                        break block31;
                    }
                    this.container.put("RESPONSECODE", "921");
                    this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "])\u5931\u6557");
                    n7 = -999;
                    return n7;
                }
                finally {
                    filterOutputStream.close();
                    bufferedInputStream.close();
                }
            }
            catch (Exception var22_23) {
                this.container.put("RESPONSECODE", "921");
                this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var22_23.getMessage());
                this.log("\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var22_23.getMessage());
                var22_23.printStackTrace(System.out);
                throw var22_23;
            }
            finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }
        return n;
    }

    public int postCancel() throws Exception {
        int n;
        block38 : {
            Object var1_1 = null;
            Object var2_2 = null;
            Object var3_3 = null;
            Object var5_4 = null;
            BufferedInputStream bufferedInputStream = null;
            FilterOutputStream filterOutputStream = null;
            String string = null;
            String string2 = null;
            String string3 = null;
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            byte[] arrby = new byte[8192];
            int n2 = 0;
            int n3 = 0;
            n = 9;
            int n4 = 0;
            int n5 = 443;
            Socket socket = null;
            InetAddress inetAddress = null;
            String string4 = "";
            String string5 = null;
            String string6 = null;
            n4 = this.verifyCancelData();
            if (n4 < 0) {
                return n4;
            }
            try {
                if ("Default".equalsIgnoreCase(this.sslProviderName)) {
                    boolean bl = false;
                    try {
                        Security.addProvider((Provider)Class.forName("com.sun.net.ssl.internal.ssl.Provider").newInstance());
                        bl = true;
                    }
                    catch (Exception var25_26) {
                        this.log("USE com.sun.net.ssl.internal.ssl.Provider FAILE");
                    }
                    if (!bl) {
                        Security.addProvider((Provider)Class.forName("com.ibm.jsse.IBMJSSEProvider").newInstance());
                    }
                } else {
                    Security.addProvider((Provider)Class.forName(this.sslProviderName).newInstance());
                }
                string4 = "HPP-JavaApi_v1.2|" + this.connectType + "|" + this.protocol;
                MyTrustManager myTrustManager = new MyTrustManager(this);
                TrustManager[] arrtrustManager = new TrustManager[]{myTrustManager};
                SSLContext sSLContext = SSLContext.getInstance(this.protocol);
                sSLContext.init(null, arrtrustManager, null);
                SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
                SSLSocketFactory sSLSocketFactory2 = sSLContext.getSocketFactory();
                string2 = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?><xmlroot><xmlhead /><xmlbody><group PCODE=\"TOP\"><item BUILDER=\"" + string4 + "\" ACQUIRERID=\"493817\" MERCHANTID=\"" + this.getVariable("MERCHANTID") + "\"  ORDERID=\"" + this.getVariable("ORDERID") + "\"  TRANSCODE=\"01\" /></group></xmlbody></xmlroot>";
                stringBuffer.setLength(0);
                stringBuffer.append(URLEncoder.encode("MD", "Big5"));
                stringBuffer.append('=');
                stringBuffer.append(URLEncoder.encode("HPP-CANCEL", "Big5"));
                stringBuffer.append('&');
                stringBuffer.append(URLEncoder.encode("RequestXML", "Big5"));
                stringBuffer.append('=');
                stringBuffer.append(URLEncoder.encode(string2, "Big5"));
                string = stringBuffer.toString();
                try {
                    n3 = this.domainNAME.indexOf(58);
                    if (n3 > 0) {
                        try {
                            n5 = Integer.parseInt(this.domainNAME.substring(n3 + 1));
                        }
                        catch (Exception var29_31) {
                            n5 = 443;
                        }
                        this.domainNAME = this.domainNAME.substring(0, n3);
                    } else {
                        n5 = 443;
                    }
                    inetAddress = InetAddress.getByName(this.domainNAME);
                    string3 = inetAddress.getHostAddress();
                    socket = (SSLSocket)sSLSocketFactory2.createSocket(string3, n5);
                    socket.setEnabledCipherSuites(socket.getSupportedCipherSuites());
                }
                catch (Exception var29_32) {
                    var29_32.printStackTrace(System.out);
                    this.container.put("RESPONSECODE", "921");
                    this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var29_32.getMessage());
                    this.log("\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var29_32.getMessage());
                    int n6 = -999;
                    if (socket != null) {
                        socket.close();
                    }
                    return n6;
                }
                stringBuffer2.append("POST " + this.requestURL + " HTTP/1.1\r\n");
                stringBuffer2.append("User-Agent: HPP-JavaApi_v1.2\r\n");
                stringBuffer2.append("Host: " + this.domainNAME + "\r\n");
                stringBuffer2.append("Content-Type: application/x-www-form-urlencoded\r\n");
                stringBuffer2.append("Content-Length: " + stringBuffer.toString().getBytes().length + "\r\n\r\n");
                stringBuffer2.append(stringBuffer.toString());
                string = stringBuffer2.toString();
                if (this.debug) {
                    this.log(string);
                }
                socket.setSoTimeout(this.iTimeOut);
                socket.setTcpNoDelay(true);
                try {
                    int n7;
                    filterOutputStream = new BufferedOutputStream(socket.getOutputStream());
                    bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                    filterOutputStream.write(string.getBytes(), 0, string.getBytes().length);
                    filterOutputStream.flush();
                    long l = System.currentTimeMillis() + (long)this.iTimeOut;
                    stringBuffer.setLength(0);
                    while (System.currentTimeMillis() < l) {
                        for (n7 = 0; n7 < 8192; ++n7) {
                            this.zipbuffer[n7] = 0;
                        }
                        n2 = bufferedInputStream.read(this.zipbuffer);
                        if (n2 <= -1) break;
                        stringBuffer.append(new String(this.zipbuffer, 0, n2, "cp950"));
                        if (stringBuffer.indexOf("</xmlroot>") < 0 && stringBuffer.indexOf("</html>") < 0) continue;
                    }
                    string = stringBuffer.toString();
                    if (stringBuffer.length() > 0) {
                        string6 = stringBuffer.toString();
                        if (string6.indexOf("PAN") > 0) {
                            string5 = this.getAttribute(string6, "PAN");
                            if (string5 != null && !"".equals(string5.trim())) {
                                string6 = this.toMaskCardNo(string6, string5);
                                if (this.debug) {
                                    this.log("Response A=" + string6);
                                }
                            } else if (this.debug) {
                                this.log("Response B=" + stringBuffer.toString());
                            }
                        } else if (this.debug) {
                            this.log("Response C=" + stringBuffer.toString());
                        }
                    }
                    if (string.length() > 0) {
                        n3 = string.indexOf("<html>");
                        if (n3 >= 0 || (n3 = string.indexOf("<HTML>")) >= 0) {
                            this.container.put("RESPONSEHTML", string.substring(n3));
                            n = 1;
                        } else {
                            this.container.clear();
                            this.parser(string.substring(string.indexOf("<?xml")));
                            n = 2;
                        }
                        break block38;
                    }
                    this.container.put("RESPONSECODE", "921");
                    this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "])\u5931\u6557");
                    n7 = -999;
                    return n7;
                }
                finally {
                    filterOutputStream.close();
                    bufferedInputStream.close();
                }
            }
            catch (Exception var24_25) {
                this.container.put("RESPONSECODE", "921");
                this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var24_25.getMessage());
                this.log("\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var24_25.getMessage());
                var24_25.printStackTrace(System.out);
                throw var24_25;
            }
            finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }
        return n;
    }

    public int postQuery() throws Exception {
        int n;
        block38 : {
            Object var1_1 = null;
            Object var2_2 = null;
            Object var3_3 = null;
            Object var5_4 = null;
            BufferedInputStream bufferedInputStream = null;
            FilterOutputStream filterOutputStream = null;
            String string = null;
            String string2 = null;
            String string3 = null;
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            byte[] arrby = new byte[8192];
            int n2 = 0;
            int n3 = 0;
            n = 9;
            int n4 = 0;
            int n5 = 443;
            Socket socket = null;
            InetAddress inetAddress = null;
            String string4 = "";
            String string5 = null;
            String string6 = null;
            n4 = this.verifyQueryData();
            if (n4 < 0) {
                return n4;
            }
            try {
                if ("Default".equalsIgnoreCase(this.sslProviderName)) {
                    boolean bl = false;
                    try {
                        Security.addProvider((Provider)Class.forName("com.sun.net.ssl.internal.ssl.Provider").newInstance());
                        bl = true;
                    }
                    catch (Exception var25_26) {
                        this.log("USE com.sun.net.ssl.internal.ssl.Provider FAILE");
                    }
                    if (!bl) {
                        Security.addProvider((Provider)Class.forName("com.ibm.jsse.IBMJSSEProvider").newInstance());
                    }
                } else {
                    Security.addProvider((Provider)Class.forName(this.sslProviderName).newInstance());
                }
                string4 = "HPP-JavaApi_v1.2|" + this.connectType + "|" + this.protocol;
                MyTrustManager myTrustManager = new MyTrustManager(this);
                TrustManager[] arrtrustManager = new TrustManager[]{myTrustManager};
                SSLContext sSLContext = SSLContext.getInstance(this.protocol);
                sSLContext.init(null, arrtrustManager, null);
                SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
                SSLSocketFactory sSLSocketFactory2 = sSLContext.getSocketFactory();
                string2 = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?><xmlroot><xmlhead PERFORM=\"QUERY\" /><xmlbody><group PCODE=\"TOP\"><item BUILDER=\"" + string4 + "\" ACQUIRERID=\"493817\" MERCHANTID=\"" + this.getVariable("MERCHANTID") + "\" ORDERID=\"" + this.getVariable("ORDERID") + "\"  KEY=\"" + this.getVariable("KEY") + "\" /></group></xmlbody></xmlroot>";
                stringBuffer.setLength(0);
                stringBuffer.append(URLEncoder.encode("MD", "Big5"));
                stringBuffer.append('=');
                stringBuffer.append(URLEncoder.encode("ExecuteQuery", "Big5"));
                stringBuffer.append('&');
                stringBuffer.append(URLEncoder.encode("RequestXML", "Big5"));
                stringBuffer.append('=');
                stringBuffer.append(URLEncoder.encode(string2, "Big5"));
                string = stringBuffer.toString();
                try {
                    n3 = this.domainNAME.indexOf(58);
                    if (n3 > 0) {
                        try {
                            n5 = Integer.parseInt(this.domainNAME.substring(n3 + 1));
                        }
                        catch (Exception var29_31) {
                            n5 = 443;
                        }
                        this.domainNAME = this.domainNAME.substring(0, n3);
                    } else {
                        n5 = 443;
                    }
                    inetAddress = InetAddress.getByName(this.domainNAME);
                    string3 = inetAddress.getHostAddress();
                    socket = (SSLSocket)sSLSocketFactory2.createSocket(string3, n5);
                    socket.setEnabledCipherSuites(socket.getSupportedCipherSuites());
                    socket.startHandshake();
                }
                catch (Exception var29_32) {
                    var29_32.printStackTrace(System.out);
                    this.container.put("RESPONSECODE", "921");
                    this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var29_32.getMessage());
                    this.log("\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var29_32.getMessage());
                    int n6 = -999;
                    if (socket != null) {
                        socket.close();
                    }
                    return n6;
                }
                stringBuffer2.append("POST " + this.requestURL + " HTTP/1.1\r\n");
                stringBuffer2.append("Host: " + this.domainNAME + "\r\n");
                stringBuffer2.append("User-Agent: HPP-JavaApi_v1.2\r\n");
                stringBuffer2.append("Content-Type: application/x-www-form-urlencoded\r\n");
                stringBuffer2.append("Content-Length: " + stringBuffer.toString().getBytes().length + "\r\n\r\n");
                stringBuffer2.append(stringBuffer.toString());
                string = stringBuffer2.toString();
                if (this.debug) {
                    this.log(string);
                }
                socket.setSoTimeout(this.iTimeOut);
                socket.setTcpNoDelay(true);
                try {
                    int n7;
                    filterOutputStream = new BufferedOutputStream(socket.getOutputStream());
                    bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                    filterOutputStream.write(string.getBytes(), 0, string.getBytes().length);
                    filterOutputStream.flush();
                    long l = System.currentTimeMillis() + (long)this.iTimeOut;
                    stringBuffer.setLength(0);
                    while (System.currentTimeMillis() < l) {
                        for (n7 = 0; n7 < 8192; ++n7) {
                            this.zipbuffer[n7] = 0;
                        }
                        n2 = bufferedInputStream.read(this.zipbuffer);
                        if (n2 <= -1) break;
                        stringBuffer.append(new String(this.zipbuffer, 0, n2, "cp950"));
                        if (stringBuffer.indexOf("</xmlroot>") < 0 && stringBuffer.indexOf("</html>") < 0) continue;
                    }
                    string = stringBuffer.toString();
                    if (stringBuffer.length() > 0) {
                        string6 = stringBuffer.toString();
                        if (string6.indexOf("PAN") > 0) {
                            string5 = this.getAttribute(string6, "PAN");
                            if (string5 != null && !"".equals(string5.trim())) {
                                string6 = this.toMaskCardNo(string6, string5);
                                if (this.debug) {
                                    this.log("Response A=" + string6);
                                }
                            } else if (this.debug) {
                                this.log("Response B=" + stringBuffer.toString());
                            }
                        } else if (this.debug) {
                            this.log("Response C=" + stringBuffer.toString());
                        }
                    }
                    if (string.length() > 0) {
                        n3 = string.indexOf("<html>");
                        if (n3 >= 0 || (n3 = string.indexOf("<HTML>")) >= 0) {
                            this.container.put("RESPONSEHTML", string.substring(n3));
                            n = 1;
                        } else {
                            this.container.clear();
                            this.parser(string.substring(string.indexOf("<?xml")));
                            n = 2;
                        }
                        break block38;
                    }
                    this.container.put("RESPONSECODE", "921");
                    this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557");
                    n7 = -999;
                    return n7;
                }
                finally {
                    filterOutputStream.close();
                    bufferedInputStream.close();
                }
            }
            catch (Exception var24_25) {
                this.container.put("RESPONSECODE", "921");
                this.container.put("RESPONSEMSG", "\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var24_25.getMessage());
                this.log("\u9023\u63a5" + this.domainNAME + "[" + string3 + "]\u5931\u6557 " + var24_25.getMessage());
                var24_25.printStackTrace(System.out);
                throw var24_25;
            }
            finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }
        return n;
    }

    private String getAttribute(String string, String string2) throws Exception {
        int n = 0;
        int n2 = 0;
        if (string2.trim().length() == 0) {
            return "";
        }
        n = string.indexOf(string2 + "=");
        if (n >= 0 && (n2 = string.indexOf("\"", n += string2.trim().length() + 2)) >= 0) {
            return string.substring(n, n2);
        }
        return "";
    }

    private void parser(String string) throws Exception {
        String[] arrstring = new String[]{"ACQUIRERID", "MERCHANTID", "TERMINALID", "ORDERID", "PAN", "EXPIREDATE", "EXTENNO", "TRANSCODE", "TRANSMODE", "TRANSDATE", "TRANSTIME", "TRANSAMT", "APPROVECODE", "RESPONSECODE", "RESPONSEMSG", "INSTALLMENTTYPE", "INSTALLMENT", "FIRSTAMT", "EACHAMT", "FEE", "REDEEMTYPE", "REDEEMUSED", "REDEEMBALANCE", "CREDITAMT", "PRIVATE_DATA", "RISK_MARK", "FOREIGN", "SECURE_STATUS", "KEY"};
        for (int i = 0; i < arrstring.length; ++i) {
            this.setVariable(arrstring[i], this.getAttribute(string, arrstring[i]));
        }
    }

    private String toMaskCardNo(String string, String string2) {
        StringBuffer stringBuffer = new StringBuffer();
        int n = 0;
        boolean bl = false;
        stringBuffer.setLength(0);
        if (string == null) {
            return null;
        }
        if (string2 == null || string2.length() < 11) {
            return string;
        }
        try {
            int n2 = string.length();
            n = string.indexOf(string2) + 6;
            if (n > 0) {
                stringBuffer.append(string.substring(0, n));
                if (string2.length() > 11) {
                    stringBuffer.append("******");
                    n += 6;
                } else if (string2.length() == 11) {
                    stringBuffer.append("**");
                    n += 2;
                }
                stringBuffer.append(string.substring(n));
            } else {
                stringBuffer.append(string);
            }
        }
        catch (Exception var6_7) {
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }

    private void log(String string) {
        if (this.date == null) {
            this.date = new Date();
        }
        this.date.setTime(System.currentTimeMillis());
        if (this.sd == null) {
            this.sd = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        }
        System.out.println("ApiClient [" + this.sd.format(this.date) + "] " + string);
    }

    public static void main(String[] arrstring) throws Exception {
        boolean bl = false;
        long l = 0;
        long l2 = 0;
        String string = "EC" + System.currentTimeMillis();
        HppApiClient hppApiClient = new HppApiClient();
        hppApiClient.setMERCHANTID("6600800020");
        hppApiClient.setORDERID("EC1448855995072");
        hppApiClient.setURL("localhost:38181", "/merchant/HPPRequest");
        System.out.println(hppApiClient.postQuery());
        System.out.println("RESPONSECODE=" + hppApiClient.getRESPONSECODE() + " RESPONSEMSG=" + hppApiClient.getRESPONSEMSG() + " \u5171\u4f7f\u7528" + String.valueOf((double)(l2 - l) / 1000.0) + "\u79d2");
    }
}
