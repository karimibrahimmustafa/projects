    <?php

    if (isset($_POST['id'])) {

          header("register.php");
session_start();

require_once "config.php";


             $param_id = $_SESSION["id"];
             $param_username = $_SESSION["username"];
        $sql = "UPDATE users SET friends = ? WHERE id = ?";

        if($stmt = mysqli_prepare($link, $sql)){

                                    mysqli_stmt_bind_param($stmt, "si",  $param_q,$param_id);

            $param_q= $_SESSION["friends"].','.$_POST['id'];
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

    

?>


