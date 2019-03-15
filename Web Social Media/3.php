<?php

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

    

?>