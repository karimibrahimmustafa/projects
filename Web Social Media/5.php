<?php

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

    

?>