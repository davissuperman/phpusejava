<?php
require_once("http://localhost:8080/JavaBridge/java/Java.inc");

use java\lang\String as JString;
use java\util\ArrayList as JList;

class String extends JString {
    function toString () {
        return "hello " . parent::toString();
    }
}
$str = new String("Java");

$list = new JList();
$list->add (java_closure($str));
$list->add ("from PHP");
$ar = java_values ($list->toArray());

foreach($ar as $entry) echo "$entry<br>\n"
?>