<?php
require_once('http://localhost:8080/JavaBridge/java/Java.inc');
$apiClient = new Java('com.nccc.evpos.HppApiClient');

$apiClient->__call('setMERCHANTID',array("6600800020"));
$apiClient->__call('setTERMINALID',array("70502876"));
$apiClient->__call('setORDERID',array("1000005"));
$apiClient->__call('setTRANSMODE',array("0"));
$apiClient->__call('setINSTALLMENT',array("0"));
$apiClient->__call('setTRANSAMT',array("1"));
$apiClient->__call('setNotifyURL',array("www.baidu.com"));
$apiClient->__call('setURL' ,array("nccnet-ectest.nccc.com.tw", "/merchant/HPPRequest"));
//echo $apiClient->__call("getMERCHANTID",array());
//echo $apiClient->__call("getURL",array());

$res = $apiClient->__call('postTransaction',array());
$responseCode=$apiClient->__call('getRESPONSECODE',array());//apiClient.getRESPONSECODE();
$key=$apiClient->__call('getKEY',array());//apiClient.getRESPONSECODE();
echo $key;
//print_r($responseCode);