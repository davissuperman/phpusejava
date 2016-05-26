<?php
require_once("http://localhost:8080/JavaBridge/java/Java.inc");
$System = java("java.lang.System");
echo 'Java version=' . $System->getProperty('java.version') . '<br />';
echo 'Java vendor=' . $System->getProperty('java.vendor') . '<br />';
echo 'OS=' . $System->getProperty('os.name') . ' ' .
    $System->getProperty('os.version') . ' on ' .
    $System->getProperty('os.arch') . ' <br />';

// java.util.Date example
$formatter = new Java('java.text.SimpleDateFormat',
    "EEEE, MMMM dd, yyyy 'at' h:mm:ss a zzzz");

echo $formatter->format(new Java('java.util.Date'));