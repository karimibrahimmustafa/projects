<?php
session_start();

require_once "config.php";
include "functions.php";
$files = glob("upload/posts/".$_SESSION['postnum'] . '/*');

//Loop through the file list.
foreach($files as $file){
    //Make sure that this is a file and not a directory.
    if(is_file($file)){
        //Use the unlink function to delete the file.
        unlink($file);
    }
}
if($_SERVER["REQUEST_METHOD"] == "POST" && $_FILES["image2"]!=null&&$_FILES["image2"]["name"]!=''&&$_FILES["image2"]["name"]!=' '){
    $post_img = $_FILES["image2"]["name"];
    $target_dir = "upload/posts/";
    $uploadOk=0;
    if(!is_dir($target_dir.$_SESSION['postnum'].'/')){
        //Directory does not exist, so lets create it.
        mkdir($target_dir.$_SESSION['postnum'].'/', 0755, true);
    }
    $target_file = $target_dir . basename($post_img);
    $target_filex = $target_dir.$_SESSION['postnum'].'/' . basename($post_img);
    $target_file2 = $target_dir.$_SESSION['postnum'].'/' . basename($post_img);

    $uploadOk = 1;
    $ext = pathinfo($post_img, PATHINFO_EXTENSION);
    if(isset($_POST["image2"])) {
        $check = getimagesize($post_img);
        $uploadOk=1;
        $uploadOk=check_img($check,$imageFileType);
    }

    if($uploadOk!=0)
        if (move_uploaded_file($_FILES["image2"]["tmp_name"], $target_filex)) {
            $_SESSION['postimg']=$target_dir.$_SESSION['postnum'].'/' . basename($post_img);;
        } else {
            $image_err= "Sorry, there was an error uploading your file.";
        }

}
?>
