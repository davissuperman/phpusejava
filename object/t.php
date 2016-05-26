<?php
require_once('http://localhost:8080/JavaBridge/java/Java.inc');
$system = new Java('lib.Test');
//$system = new Java('java.lang.System');
echo "<pre>";
//print_r(get_class_methods($system));
echo $system->__call('minFunction',array(6,7));