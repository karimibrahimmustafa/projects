<?php
session_start();
require_once "config.php";
include "functions.php";
$usernames="";
$images="";
// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: login.php");
    exit;
}

$sql = "SELECT ID FROM posts ";
$link2 = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, 'kimo');
if($stmt2 = mysqli_prepare($link2, $sql)){
    // Bind variables to the prepared statement as parameters
    if(mysqli_stmt_execute($stmt2)){
        // Store result
        $arrx=array();
        mysqli_stmt_store_result($stmt2);
        mysqli_stmt_bind_result($stmt2, $ids);
        while(mysqli_stmt_fetch($stmt2)){
            $_SESSION["postnum"]=$ids+1;
        }}}

if($_SESSION["reset"]===1){
    $sql = "SELECT id, username, password, color ,image FROM users WHERE username = ?";
    if($stmt = mysqli_prepare($link, $sql)){
        mysqli_stmt_bind_param($stmt, "s", $param_username);
        $param_username = htmlspecialchars($_SESSION["username"]);
        if(mysqli_stmt_execute($stmt)){
            mysqli_stmt_store_result($stmt);
            if(mysqli_stmt_num_rows($stmt) == 1){                    
                mysqli_stmt_bind_result($stmt, $id, $username, $hashed_password,$color,$image);
                if(mysqli_stmt_fetch($stmt)){
                    $_SESSION["reset"]=0;
                    $_SESSION["loggedin"] = true;
                    $_SESSION["id"] = $id;
                    $_SESSION["username"] = $username;                            
                    $_SESSION["color"] = $color;                            
                    $_SESSION["image"] = $image;                            

                }
            }
        }  
    }
}
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


?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="cs.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
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

        </style>
    </head>
    <body>

        <div id="container" style="height=200px;">
            <div id="c2" >
                <img src="<?php echo $_SESSION["cover"];?>" style="border-radius: 0%; border: 2px solid <?php echo $_SESSION["color"];?>;">
            </div>
            <div id="c3">
                <img  id='imgc3'src="<?php echo $_SESSION["image"];?>" style="border: 2px solid <?php echo $_SESSION["color"];?>;">
            </div><div id="c4">
            <h1 class="glow"> <?php echo $_SESSION["username"];?></h1>
            </div>


        </div>
        <div class='box' >
            <section>
                <div class="text">
                    <img src="<?php echo $_SESSION["image"];?>"/>
                    <textarea id='text2' placeholder="What's in your mind" onclick="text()" rows="1"></textarea>
                    <button onclick="post('<?php echo $_SESSION["image"];?>')">       submit   </button>            
                    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post" enctype="multipart/form-data" id='form1'   class="md-form">
                        <div class="file-field"><div style="">
                            <div class="btn btn-primary" style="">
                                <span >Choose img</span>
                                <input type="file" name="image2" onchange='kiko()' id="sortpic">
                            </div></div>
                            <div class="file-path-wrapper" style="">
                                <input class="file-path validate" disabled type="text" placeholder="Upload your file" style="">
                            </div>
                        </div>
                    </form>     </div>
            </section>
        </div>
        <div id="all">

            <?php select_posts_welcome($_SESSION["id"]); ?>
        </div>
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>    
        <script>
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
            function kiko(){
                var file_data = $("#sortpic").prop('files')[0];   
                var form_data = new FormData();                  
                form_data.append('image2', file_data);
                console.log(form_data);                             
                $.ajax({
                    url: 'image.php',
                    dataType: 'text',  // what to expect back from the PHP script, if anything
                    cache: false,
                    contentType: false,
                    processData: false,
                    data: form_data,                         
                    type: 'post',
                    success: function(php_script_response){
                        console.log(php_script_response); // display response from the PHP script, if any
                    }
                });
            }    




        </script>
        <script src="script4.js">
            
        </script>
        <script type="text/javascript" src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/compiled-4.7.3.min.js"></script>
    </body>
</html>
