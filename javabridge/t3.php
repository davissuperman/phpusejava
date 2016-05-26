<?php require_once("http://localhost:8080/JavaBridge/java/Java.inc");

$world = new java("HelloWorld");
echo $world->hello(array("from PHP"));
?>