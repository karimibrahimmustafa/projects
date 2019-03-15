<?php 
require_once "config.php";
session_start();
include "functions.php";
$color=$color_err="";
$image_err="";
$image2_err="";
$path="";
$err="";
// Processing form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){
    $param_id = $_SESSION["id"];
    $param_username = $_SESSION["username"];
    $color=check_color_img($_POST["color"]);
    $post_img = $_FILES["image2"]["name"];
    $post_img2 = $_FILES["image3"]["name"];
    $target_dir = "upload/";
    $target_file = $target_dir . basename($post_img);
    $target_filex = $target_dir.$param_username.'/' . basename($post_img2);
    $uploadOk = 1;
    $ext = pathinfo($post_img, PATHINFO_EXTENSION);
    $ext2 = pathinfo($post_img2, PATHINFO_EXTENSION);
    $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
    $imageFileType2 = strtolower(pathinfo($target_filex,PATHINFO_EXTENSION));
    if(isset($_POST["image2"])) {
        $check = getimagesize($post_img);
        $uploadOk=check_img($check,$imageFileType);
    }
    if(isset($_POST["image3"])) {
        $check2 = getimagesize($post_img2);
        $uploadOk=check_img($check2,$imageFileType2);
    }
    if(empty($color_err)&& empty($image_err)){
        $sql = "UPDATE users SET color = ?, image=? , cover=? WHERE id = ?";
        if($stmt = mysqli_prepare($link, $sql)){
            if (move_uploaded_file($_FILES["image2"]["tmp_name"], $target_file)) {
            } else {
                $image_err= "Sorry, there was an error uploading your file.";
            }
            if (move_uploaded_file($_FILES["image3"]["tmp_name"], $target_filex)) {
            } else {
                $image_err= "Sorry, there was an error uploading your file.";
            }
            $target_file2=$target_dir .$param_username."/". basename($_FILES["image2"]["name"]);
            resize($target_file, $target_file2, 150, 150, $ext);
            mysqli_stmt_bind_param($stmt, "sssi",  $param_color,$param_image,$param_image3, $param_id);
            if(basename($_FILES["image2"]["name"])===" "|| basename($_FILES["image2"]["name"])===""){
                $target_file2= $_SESSION["image"] ;  
                $target_file3= $_SESSION["thumb"] ;                            
            }else {
                unlink($target_file) or die("Couldn't delete file");}
            if(basename($_FILES["image3"]["name"])===" "|| basename($_FILES["image3"]["name"])===""){
                $target_filex= $_SESSION["cover"] ;  
            }
            else { }

            $param_image= $target_file2;
            $param_image3= $target_filex;
            $param_color = $color;

            if(mysqli_stmt_execute($stmt)){
                $err= $param_id .$param_color.$param_image;
                $_SESSION["image"] =$target_file2;
                $_SESSION["cover"] =$target_filex;
                $_SESSION["thumb"] =$target_file3;
                $_SESSION["dark"]=darken_color($param_color, $darker=2);
                $_SESSION["reset-color"] = true; 
                header("location: h2.php");

            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }
    $_SESSION["reset"]=1;
    // Close connection
    mysqli_close($link);
}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Reset Color</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
        <link rel="stylesheet" href= "style.css">
        <style type="text/css " >
            img{              float: left;
            }
            body{
                padding-left: 300px;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <img  src="color.png">

            <h2>Reset Color</h2>
            <p>Please fill out this information</p>
            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post" enctype="multipart/form-data" target="_parent">
                <div >
                    <label>Color  </label>
                    <input type="color" id="color" name="color"
                           value="<?php echo $_SESSION["color"] ; ?>">
                    <span class="help-block"><?php echo $color_err; ?></span>
                </div> 
                <label>image</label>
                <br>
                <div class="image2">
                    <input type="file" name="image2" class="form-control-file">
                    <span class="warning-block"><?php echo $image_err; ?></span>
                </div>  
                <div >
                    <label>image3</label>
                    <br>
                    <div class="image3">
                        <input type="file" name="image3" class="form-control-file">
                        <span class="warning-block"><?php echo $image2_err; ?></span>
                    </div>                  </div>
                <div class="submit">
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Reset">
                    </div>
                </div>
            </form>
        </div>    
    </body>
</html>