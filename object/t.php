<?php
require_once('http://localhost:8080/JavaBridge/java/Java.inc');
$system = new Java('lib.Test');
//$system = new Java('java.lang.System');
echo "<pre>";
//print_r($system);
echo $system->__call('showNoParam',array( ));