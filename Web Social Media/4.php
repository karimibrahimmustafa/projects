<?php
if($_POST['state']==1){

    if (isset($_POST['id'])) {

        session_start();

        require_once "config.php";
        include "functions.php";
        require_once "config.php";
        $sql = "SELECT request FROM users WHERE id = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            mysqli_stmt_bind_param($stmt, "i", $param_id);
            $param_id = $_POST['id'];
            if(mysqli_stmt_execute($stmt)){
                mysqli_stmt_store_result($stmt);
                if(mysqli_stmt_num_rows($stmt) == 1){                    
                    mysqli_stmt_bind_result($stmt,$request);
                    if(mysqli_stmt_fetch($stmt)){


                    }}}}
        mysqli_stmt_close($stmt);
        mysqli_close($link);

    }
    if (isset($_POST['id'])) {        
        $sql2 = "UPDATE users SET request = ? WHERE id = ?";
        $link2 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

        if($stmt2 = mysqli_prepare($link2, $sql2)){

            mysqli_stmt_bind_param($stmt2, "si",  $param_q,$param_id);

            $param_q= $request.','.$_SESSION['id'];
            $param_id=$_POST['id'];

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt2)){
                // Redirect to login page
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement
        $_SESSION['request']=$param_q;

        mysqli_stmt_close($stmt2);

        // Close connection
        mysqli_close($link2);
    }
    if (isset($_POST['id'])) {        
        $sql3 = "UPDATE users SET send = ? WHERE id = ?";
        $link3 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

        if($stmt3 = mysqli_prepare($link3, $sql3)){

            mysqli_stmt_bind_param($stmt3, "si",  $param_q,$param_id);

            $param_q= $_SESSION['send'].','.$_POST['id'];
            $param_id=$_SESSION['id'];

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt3)){
                // Redirect to login page
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement
        $_SESSION['send']=$param_q;
        mysqli_stmt_close($stmt3);

        // Close connection
        mysqli_close($link3);
    }

    // Close connection

}
if($_POST['state']==4)
{


    {

        session_start();

        require_once "config.php";
        include "functions.php";


        $param_id = $_SESSION["id"];
        $param_username = $_SESSION["username"];
        $sql = "INSERT INTO messages (content,fromid,toid,seen) VALUES (?, ?, ?,1)";

        if($stmt = mysqli_prepare($link, $sql)){

            mysqli_stmt_bind_param($stmt, "sii",  $param_q,$param_id,$param_id2);

            $param_q= dec_enc('encrypt', $_POST['content']);
            $param_id = $_SESSION["id"];
            $param_id2 =  $_POST['id'];
            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                // Redirect to login page
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement

        mysqli_stmt_close($stmt);

        // Close connection
        mysqli_close($link);
    }



}
if($_POST['state']==5)
{
    session_start();

    require_once "config.php";
    $param_id = $_SESSION["id"];
    $sql = "UPDATE users SET active = ? WHERE id = ?";

    if($stmt = mysqli_prepare($link, $sql)){

        mysqli_stmt_bind_param($stmt, "ii",  $param_q,$param_id);

        $param_q= $_POST["id"];

        $param_id = $_SESSION["id"];
        $_SESSION["active"]=$_POST["id"];
        echo "my update".$param_q;
        // Attempt to execute the prepared statement
        if(mysqli_stmt_execute($stmt)){
            // Redirect to login page
        } else{
            echo "Something went wrong. Please try again later.";
        }
    }

    // Close statement

    mysqli_stmt_close($stmt);

    // Close connection
    mysqli_close($link);

}
if($_POST['state']==6)
{




    session_start();

    require_once "config.php";
    include "functions.php";
    $c=date('Y-m-d H:i:s');
    if($_POST['id']!=null&&$_POST['id']!=''&&$_POST['id']!=' '){
        $param_id = $_SESSION["id"];
        $param_username = $_SESSION["username"];
        $sql = "INSERT INTO posts (content,fromid,name,date,pics) VALUES (?, ?, ?,?,?)";

        if($stmt = mysqli_prepare($link, $sql)){

            mysqli_stmt_bind_param($stmt, "sisss",  $param_q,$param_id,$param_name,$param_date,$param_img);

            $param_q= dec_enc('encrypt', $_POST['id']);
            $param_id = $_SESSION["id"];
            $param_name = $_SESSION["username"];
            $param_img=$_SESSION["postimg"];
            $param_date=$c;
            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                // Redirect to login page
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement

        mysqli_stmt_close($stmt);

        // Close connection
        mysqli_close($link);
    }
}
if($_POST['state']==7)
{




    session_start();

    require_once "config.php";
    include "functions.php";




    $sql = "SELECT likes FROM posts WHERE id = ?";
    $l="0";
    if($stmt = mysqli_prepare($link, $sql)){
        mysqli_stmt_bind_param($stmt, "i", $param_id);
        $param_id = $_POST['id'];
        if(mysqli_stmt_execute($stmt)){
            mysqli_stmt_store_result($stmt);
            if(mysqli_stmt_num_rows($stmt) == 1){                    
                mysqli_stmt_bind_result($stmt,$likes);
                if(mysqli_stmt_fetch($stmt)){

                    $l=$likes;
                }}}}
    mysqli_stmt_close($stmt);
    mysqli_close($link);
    $integerIDs = array_map('intval', explode(',', $l));
    if (($key = array_search($_POST['fromid'],$integerIDs)) !== false) {
        unset($integerIDs[$key]);
        $sql2 = "UPDATE posts SET likes = ? WHERE id = ?";
        $link2 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);
        $d=implode(',', $integerIDs);
        if(is_null($d)){
            $d=0;
        }
        if($stmt2 = mysqli_prepare($link2, $sql2)){

            mysqli_stmt_bind_param($stmt2, "si",  $param_q,$param_id);

            $param_q= $d;
            $param_id=$_POST['id'];

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt2)){
                // Redirect to login page
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt2);
        mysqli_close($link2);
    }
    else{

        $sql2 = "UPDATE posts SET likes = ? WHERE id = ?";
        $link2 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

        if($stmt2 = mysqli_prepare($link2, $sql2)){

            mysqli_stmt_bind_param($stmt2, "si",  $param_q,$param_id);

            $param_q= $l.','.$_POST['fromid'];
            $param_id=$_POST['id'];

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt2)){
                // Redirect to login page
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt2);
        mysqli_close($link2);
    }
}

if($_POST['state']==8)
{




    session_start();

    require_once "config.php";
    include "functions.php";
    {$c=date('Y-m-d H:i:s');
     $param_id = $_SESSION["id"];
     $param_username = $_SESSION["username"];
     $sql = "INSERT INTO comments (postid,username,image,content,date) VALUES (?,?, ?, ?,?)";

     if($stmt = mysqli_prepare($link, $sql)){

         mysqli_stmt_bind_param($stmt, "issss",  $param_id,$param_name,$param_image,$param_content,$param_date);

         $param_content=  $_POST['content'];
         echo  $_POST['content'];
         $param_id = $_POST["id"];
         $param_name = $_SESSION["username"];
         $param_image = $_POST["image"];
         $param_date=$c;
         // Attempt to execute the prepared statement
         if(mysqli_stmt_execute($stmt)){
             // Redirect to login page
         } else{
             echo "Something went wrong. Please try again later.";
         }
     }

     // Close statement

     mysqli_stmt_close($stmt);

     // Close connection
     mysqli_close($link);
     $c="</div>\r\n  <div class=\"comment\">\r\n <div class=\"media\">\r\n <div class=\"media-left\">\r\n <a href=\"#\">\r\n     <img class=\"media-object photo-profile\" src=".$_SESSION['image']." width=\"32\" height=\"32\" alt=\"...\">\r\n </a>\r\n</div>\r\n<div class=\"media-body\">\r\n<a href=\"#\" class=\"anchor-username\"><h4 class=\"media-heading\">Media heading</h4></a> \r\n<a href=\"#\" class=\"anchor-time\">51 mins</a>\r\n</div>\r\n </div>\r\n</div>\r\n";
    }
}
if($_POST['state']==9)   
{
    $_SESSION['postimg']='';
    if($_SERVER["REQUEST_METHOD"] == "POST" && $_FILES["image2"]!=null&&$_FILES["image2"]["name"]!=''&&$_FILES["image2"]["name"]!=' '){
        $post_img = $_FILES["image2"]["name"];
        $target_dir = "upload/";
        $uploadOk=0;
        $target_file = $target_dir . basename($post_img);
        $target_filex = $target_dir.$_SESSION['username'].'/' . basename($post_img);
        $target_file2 = $target_dir.$_SESSION['username'].'/' . basename($post_img);

        $uploadOk = 1;
        $ext = pathinfo($post_img, PATHINFO_EXTENSION);
        if(isset($_POST["image2"])) {
            $check = getimagesize($post_img);
            $uploadOk=1;
            $uploadOk=check_img($check,$imageFileType);
        }

        if($uploadOk!=0)
            if (move_uploaded_file($_FILES["image2"]["tmp_name"], $target_filex)) {
                $_SESSION['postimg']=$target_filex;
            } else {
                $image_err= "Sorry, there was an error uploading your file.";
            }

    }


}
if($_POST['state']==10){
    if (isset($_POST['id'])) {

        header("register.php");
        session_start();

        require_once "config.php";


        $param_id = $_SESSION["id"];
        $param_fr = $_SESSION["friends"];
        $array = array_map('intval', explode(',', $_SESSION["friends"]));
        if(in_array($_POST['id'], $array)){
            if (($key = array_search($_POST['id'], $array)) !== false) {
                unset($array[$key]);
            }
            $param_username = $_SESSION["username"];
            $sql = "UPDATE users SET friends = ? WHERE id = ?";

            if($stmt = mysqli_prepare($link, $sql)){

                mysqli_stmt_bind_param($stmt, "si",  $param_q,$param_id);

                $param_q= implode(', ', $array);
                $param_id = $_SESSION["id"];

                $_SESSION["friends"]=$param_q;
                // Attempt to execute the prepared statement
                if(mysqli_stmt_execute($stmt)){
                    // Redirect to login page
                } else{
                    echo "Something went wrong. Please try again later.";
                }
            }

            // Close statement

            mysqli_stmt_close($stmt);

            // Close connection
            mysqli_close($link);
        }
        $link2 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);
        $sql2 = "SELECT friends FROM users WHERE id = ?";

        if($stmt2 = mysqli_prepare($link2, $sql2)){
            mysqli_stmt_bind_param($stmt2, "i", $param_id);
            $param_id = $_POST['id'];
            if(mysqli_stmt_execute($stmt2)){
                mysqli_stmt_store_result($stmt2);
                if(mysqli_stmt_num_rows($stmt2) == 1){                    
                    mysqli_stmt_bind_result($stmt2,$friends2);
                    if(mysqli_stmt_fetch($stmt2)){


                    }}}}
        mysqli_stmt_close($stmt2);
        mysqli_close($link2);  
        $link3 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);
        $array2 = array_map('intval', explode(',', $friends2));
        if(in_array($_SESSION['id'], $array2)){
            if (($key = array_search($_SESSION['id'], $array2)) !== false) {
                unset($array2[$key]);
            }
            $param_username = $_SESSION["username"];
            $sql3 = "UPDATE users SET friends = ? WHERE id = ?";

            if($stmt3 = mysqli_prepare($link3, $sql3)){

                mysqli_stmt_bind_param($stmt3, "si",  $param_q,$param_id);

                $param_q= implode(', ', $array2);
                $param_id = $_POST["id"];
                // Attempt to execute the prepared statement
                if(mysqli_stmt_execute($stmt3)){
                    // Redirect to login page
                } else{
                    echo "Something went wrong. Please try again later.";
                }
            }

            // Close statement

            mysqli_stmt_close($stmt3);

            // Close connection
            mysqli_close($link3);
        }

    }

}
if($_POST['state']==11){
    if (isset($_POST['id'])) {

        header("register.php");
        session_start();

        require_once "config.php";

        $sql = "SELECT send ,friends FROM users WHERE id = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            mysqli_stmt_bind_param($stmt, "i", $param_id);
            $param_id = $_POST['id'];
            if(mysqli_stmt_execute($stmt)){
                mysqli_stmt_store_result($stmt);
                if(mysqli_stmt_num_rows($stmt) == 1){                    
                    mysqli_stmt_bind_result($stmt,$send,$friends2);
                    if(mysqli_stmt_fetch($stmt)){


                    }}}}
        mysqli_stmt_close($stmt);
        mysqli_close($link);


        $param_id = $_SESSION["id"];
        $param_fr = $_SESSION["send"];
        $array2 = array_map('intval', explode(',', $send));
        $link2 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

        if(in_array($_SESSION['id'], $array2)){
            if (($key = array_search($_SESSION['id'], $array2)) !== false) {
                unset($array2[$key]);
            }
            $param_username = $_SESSION["username"];
            $sql2 = "UPDATE users SET send = ?, friends =? WHERE id = ?";

            if($stmt2 = mysqli_prepare($link2, $sql2)){

                mysqli_stmt_bind_param($stmt2, "ssi",  $param_q ,$param_friends,$param_id);

                $param_q= implode(', ', $array2);
                $param_id = $_POST["id"];
                $param_friends= $friends2.','.$_SESSION['id'];

                // Attempt to execute the prepared statement
                if(mysqli_stmt_execute($stmt2)){
                    // Redirect to login page
                } else{
                    echo "Something went wrong. Please try again later.";
                }
            }

            // Close statement

            mysqli_stmt_close($stmt2);

            // Close connection
            mysqli_close($link2);
        }

        $param_id = $_SESSION["id"];
        $array3 = array_map('intval', explode(',', $_SESSION["request"]));
        $link3 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

        if(in_array($_POST['id'], $array3)){
            if (($key = array_search($_POST['id'], $array3)) !== false) {
                unset($array3[$key]);
            }
            $param_username = $_SESSION["username"];
            $sql3 = "UPDATE users SET request = ?,friends=? WHERE id = ?";

            if($stmt3 = mysqli_prepare($link3, $sql3)){

                mysqli_stmt_bind_param($stmt3, "ssi",  $param_q,$param_q2,$param_id);

                $param_q= implode(', ', $array3);
                $param_id = $_SESSION["id"];
                $param_q2= $_SESSION['friends'].','.$_POST['id'];

                // Attempt to execute the prepared statement
                if(mysqli_stmt_execute($stmt3)){
                    // Redirect to login page
                } else{
                    echo "Something went wrong. Please try again later.";
                }
            }

            // Close statement
            $_SESSION["request"]=$param_q;
            $_SESSION["friends"]=$param_friends;
            mysqli_stmt_close($stmt3);

            // Close connection
            mysqli_close($link3);

        }


    }



}
if($_POST['state']==12)
{




    session_start();

    require_once "config.php";
    include "functions.php";
    $c=date('Y-m-d H:i:s');
    if($_POST['content']!=null&&$_POST['content']!=''&&$_POST['content']!=' '){
        $param_id = $_SESSION["id"];
        $param_username = $_SESSION["username"];
        $sql = "INSERT INTO posts (content,fromid,name,date,pics,shared) VALUES (?, ?, ?,?,?,?)";

        if($stmt = mysqli_prepare($link, $sql)){

            mysqli_stmt_bind_param($stmt, "sisssi",  $param_q,$param_id,$param_name,$param_date,$param_img,$param_shared);

            $param_q= $_POST['content'];
            $param_id = $_SESSION["id"];
            $param_name = $_SESSION["username"];
            $param_img=$_POST["pics"];
                        $param_shared=$_POST["fromid"];
            $param_date=$c;
            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                // Redirect to login page
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement

        mysqli_stmt_close($stmt);

        // Close connection
        mysqli_close($link);
    }
}
?>