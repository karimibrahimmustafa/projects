<?php
// Initialize the session
session_start();
require_once "config.php";
include "functions.php";
$usernames="";
$images="";
// Check if the user is logged in, if not then redirect him to login page

if(true){
    $prof_id = $_GET["id"];

    $sql = "SELECT id, username,color  ,image ,cover FROM users WHERE id = ?";
    if($stmt = mysqli_prepare($link, $sql)){
        mysqli_stmt_bind_param($stmt, "i", $param_id);
        $param_id = $prof_id;
        if(mysqli_stmt_execute($stmt)){
            mysqli_stmt_store_result($stmt);
            if(mysqli_stmt_num_rows($stmt) == 1){                    
                mysqli_stmt_bind_result($stmt, $id, $username,$color,$image,$cover);
                if(mysqli_stmt_fetch($stmt)){
                    $prof_username=$username;
                    $prof_cover=$cover;
                    $prof_color=$color;
                    $prof_img=$image;
                    $_SESSION['prof']=$username;

                }
            }
        }  
    }
}

?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="cs.css">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href= "style.css">
        <style type="text/css">
            @-webkit-keyframes glow {
                from {
                    text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px <?php echo $_SESSION["dark"];?>, 0 0 40px <?php echo $_SESSION["dark"];?>, 0 0 50px <?php echo $_SESSION["dark"];?>, 0 0 60px <?php echo $_SESSION["dark"];?>, 0 0 70px <?php echo $_SESSION["dark"];?>;
                }

                to {
                    text-shadow: 0 0 20px #fff, 0 0 30px <?php echo $_SESSION["color"];?>, 0 0 40px <?php echo $_SESSION["color"];?>, 0 0 50px <?php echo $_SESSION["color"];?>, 0 0 60px <?php echo $_SESSION["color"];?>, 0 0 70px <?php echo $_SESSION["color"];?>, 0 0 80px <?php echo $_SESSION["color"];?>;
                }
            }

            .icons:hover{
                color: <?php echo $_SESSION['color'];?>;
            }
            .icons{
                color: "gray";
            } 

        </style>
    </head>
    <body>

        <div id="container" style="height=200px;">
            <div id="c2" >
                <img src="<?php echo $prof_cover;?>" style="border-radius: 0%; border: 2px solid <?php echo $_SESSION["color"];?>;">
            </div>
            <div id="c3">
                <img  id='imgc3'src="<?php echo $prof_img;?>" style="border: 2px solid <?php echo $_SESSION["color"];?>;">
            </div><div id="c4">
            <h1 class="glow"> <?php echo $prof_username;?></h1>
            </div></div>
        <div id="all">

            <?php select_posts($prof_id); ?>
        </div>
        <script >
            function add_like2(ids , id,likes,i){
                add_like(ids,id);
                if(i==0)
                {var l=likes+1;
                 console.log('<?php echo $_SESSION['color'];?>');
                 document.getElementById("like"+ids).style.color='<?php echo $_SESSION['color'];?>';
                 document.getElementById("num"+ids).innerHTML="("+l+") likes";
                 document.getElementById("like"+ids).setAttribute("onclick",'add_like2('+ids+","+id+","+l+","+1+")");
                }
                else{
                    var l=likes-1;
                    console.log('remove');
                    document.getElementById("like"+ids).style.color="gray";
                    document.getElementById("num"+ids).innerHTML="("+l+") likes";
                    document.getElementById("like"+ids).setAttribute("onclick",'add_like2('+ids+","+id+","+l+","+0+")");


                }
            }

        </script>
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>    

        <script src="script4.js">
        </script>

    </body>
</html>
