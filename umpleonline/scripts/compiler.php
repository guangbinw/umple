<?php
// Copyright: All contributers to the Umple Project
// This file is made available subject to the open source license found at:
// http://umple.org/license
require_once("compiler_config.php");

if (isset($_REQUEST["save"]))
{
  $input = $_REQUEST["umpleCode"];
  $filename = saveFile($input);
  echo $filename;
}
else if (isset($_REQUEST["load"]))
{
  $filename = $_REQUEST["filename"];
  $outputUmple = readTemporaryFile($filename);
  echo $outputUmple;
}
else if (isset($_REQUEST["action"]))
{
  handleUmpleTextChange();
}
else if (isset($_REQUEST["umpleCode"]))
{
  $input = $_REQUEST["umpleCode"];
  $language = $_REQUEST["language"];
  
  if ($language == "javadoc")
  {
     $language = "Java";
     $javadoc = True;
  }
  else
  {
    $javadoc = False;
  }
  
  if ($language == "Simulate")
  {
    $filename = saveFile("generate Php \"./\" --override-all;\n" . $input);
    executeCommand("java -jar umple.jar {$filename}");
    $filename = saveFile("generate Simulate \"./\" --override-all;\n" . $input);
    executeCommand("java -jar umple.jar {$filename}");
    $modelId = getFilenameId($filename);
    echo $modelId;
    return;
  }
  elseif (!in_array($language,array("Php","Java","Ruby","Cpp")))
  {  
    $filename = saveFile($input);
    $sourceCode = executeCommand("java -jar umplesync.jar -generate {$language} {$filename}");
    if ($language != "Json")
    {
      $sourceCode = htmlspecialchars($sourceCode);
    }
    echo $sourceCode;
    return;
  }

  // Generate the Java, PHP, Ruby or Cpp and put it into the right directory
  $filename = saveFile("generate {$language} \"./{$language}/\" --override-all;\n" . $input);
  // $errorOut = saveFile("");
  
  $outputFilename = "{$filename}.output";
  $errorFilename = "{$filename}.erroroutput";
  
  // Clean up any pre-existing java. php, ruby or cpp files
  $thedir = dirname($outputFilename);
  $toRemove = False;
  $rmcommand = "rm -rf ";
  foreach (glob("$thedir/{$language}") as $afile) {
    $rmcommand = $rmcommand . " " . $afile;
    $toRemove = True;
  }    
  if($toRemove) { exec($rmcommand); }
  
  $command = "java -jar umplesync.jar -source {$filename} 1> {$outputFilename} 2> {$errorFilename}";    
  exec($command);
  
  $sourceCode = readTemporaryFile($outputFilename);
  $errorMessage = readTemporaryFile($errorFilename);
  
  $sourceCode = str_replace("<?php","",$sourceCode);
  $sourceCode = str_replace("?>","",$sourceCode);
  $sourceCode = htmlspecialchars($sourceCode);
  
  if ($errorMessage != "")
  {
    $html = "
        // An error occurred interpreting your Umple code, please review it and try again.
        // If the problem persists, please email the Umple code to
        // the umple-help google group: umple-help@googlegroups.com
        // or post an issue at http://bugs.umple.org -- the Umple issues page
        // We are aware that error messages are not useful or nonexistent and
        // we are working to fix that.
        /* {$errorMessage} */"  ;
    echo $html;
  }
  else
  {
    if ($javadoc)
    {
       $thedir = dirname($outputFilename);
       $theurldir = "ump/" . basename($thedir);
       exec("rm -rf " . $thedir . "/javadoc");
       
        $command = "/usr/bin/javadoc -d " . $thedir . "/javadoc -footer \"Generated by Umple\" " ;
              
      foreach (glob("$thedir/{$language}/*/*/*/*.java") as $afile) {
          $command = $command . " " . $afile;
       }
       
       foreach (glob("$thedir/{$language}/*/*/*.java") as $afile) {
          $command = $command . " " . $afile;
       }    
       
       foreach (glob("$thedir/{$language}/*/*.java") as $afile) {
          $command = $command . " " . $afile;
       }    
       
       foreach (glob("$thedir/{$language}/*.java") as $afile) {
          $command = $command . " " . $afile;
       }                  
       
       exec($command);
       exec("cd $thedir; rm javadocFromUmple.zip; /usr/bin/zip -r javadocFromUmple javadoc");
        $html = "<a href=\"umpleonline/$thedir/javadocFromUmple.zip\">Download the following as a zip file</a>
      <iframe width=100% height=1000 src=\"" . $theurldir . "/javadoc/\">This browser does not
      support iframes, so the javadoc cannot be displayed</iframe> 
     ";
       echo $html;
    }
    else // This is where the Java, PHP and other output is placed on the screen
    {
	   exec("cd $thedir; rm {$language}FromUmple.zip; /usr/bin/zip -r {$language}FromUmple {$language}");
	   echo "<a href=\"umpleonline/$thedir/{$language}FromUmple.zip\">Download the following as a zip file</a><p>URL_SPLIT";
       echo $sourceCode;
    }
  }
}
else if (isset($_REQUEST["exampleCode"]))
{
  $filename = "../ump/" . $_REQUEST["exampleCode"];
  $outputUmple = readTemporaryFile($filename);
  echo $outputUmple;
}
else if (isset($_REQUEST["asImage"]))
{
  $asImage = $_REQUEST["asImage"];
  $json = json_decode($asImage);
  showUmlImage($json);
}
else if (isset($_REQUEST["asYuml"]))
{
  showYumlImage($_REQUEST["asYuml"]);
}
else if (isset($_REQUEST["asUI"]))
{ 
  showUserInterface($_REQUEST["asUI"]);
}
else
{
  echo "Invalid use of compiler";
}
?>