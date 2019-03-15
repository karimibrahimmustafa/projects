<?php
session_start();
require_once "config.php";
include "functions.php";
$sql = "SELECT active FROM users WHERE id = ?";

if($stmt = mysqli_prepare($link, $sql)){
    // Bind variables to the prepared statement as parameters
    mysqli_stmt_bind_param($stmt, "i", $id);

    // Set parameters
    $param_username =$_SESSION["id"];

    // Attempt to execute the prepared statement
    if(mysqli_stmt_execute($stmt)){
        // Store result
        mysqli_stmt_store_result($stmt);

        // Check if username exists, if yes then verify password
        if(mysqli_stmt_num_rows($stmt) == 1){                    
            // Bind result variables
            mysqli_stmt_bind_result($active);
            if(mysqli_stmt_fetch($stmt)){
                $_SESSION["active"] = $active;                            

            }}}}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <style type="text/css">
            body{
                margin: auto;
                width: 100%;
                height: 100%;
                background-color: aliceblue;

            }
            .speech-bubble {
                position: relative;
                background: #00aabb;
                border-radius: .4em;
                width: 200px;
                float: right;
                margin-right: 5px;
                margin-top: 10px;
                padding-bottom: 10px;
            }
            .speech-bubble2 {
                position: relative;
                background: #00aabb;
                border-radius: .4em;
                width: 200px;
                float: left;
                margin-top: 10px;
                padding-bottom: 10px;
                margin-left: 5px;

            }

            .content{

                margin-left: 10px;
                margin-bottom: 15px;
                padding-bottom: 5px;
                font-family: "Arial Black", Gadget, sans-serif;
                font-size: 15px;
                letter-spacing: 0.2px;
                word-spacing: 2.8px;
                color: white;
                font-weight: 700;
                text-decoration: none;
                font-style: normal;
                font-variant: normal;
                text-transform: none;
            }
            .content2{

                margin-left: 10px;
                margin-bottom: 5px;
                padding-bottom: 0px;
            }
        </style>
    </head>
    <body onLoad="window.scroll(0, 15000)">
        <div id='wrap'><?php
            select_messages();
            login($link);
            ?>

        </div>
    </body>
</html>