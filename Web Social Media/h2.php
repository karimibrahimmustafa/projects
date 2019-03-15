<?php
// Initialize the session
session_start();
require_once "config.php";
include "functions.php";

// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: login.php");
    exit;
}
if(true){
    $_SESSION["messages"]=array();
    login($link);
    read();
}
$usernames="";
$images="";
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../../../favicon.ico">

        <title>Starter Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href= "style.css">
        <!-- Custom styles for this template -->
        <style type="text/css">
            .starter-template iframe{
                position: absolute;
                top: 0;
                left: 0;

            }
            .messages{
                position: fixed;

            }
            .badge {
                position: relative;
                top: -10px;
                right: 0px;
                left: 0px;;
                float: left;
                border-radius: 50%;
                background: red;
                color: white;
            }
            .starter-template {
                padding: 3rem 1.5rem;
                text-align: center;
            }
            .kim {
                transition: 0.2s;
                margin-left: 3px;
                float: left;
                background-color: <?php echo htmlspecialchars($_SESSION['color']); ?>;
                position: fixed;
                left: 0;
                margin-top: 32%;
                height: 42.5%;
                border: 2px solid <?php echo htmlspecialchars($_SESSION['dark']); ?>;;
                display: none;
            }
            .kim iframe{
                margin-left: 3px;
                margin-top: 45px;
                margin-right: 3px;
                overflow: scroll;
                height: 72%;
            }
            .scroll2::-webkit-scrollbar {
                display: none;
            }
            .scroll2{
                float: left;
                margin-left: 2px;
                margin-bottom: 5px;
                width: 250px;
            }
            .kim .closebtn {
                position: absolute;
                top: 40;
                font-size: 36px;
                margin-top: 0;
                padding-bottom: 0;
                padding-top: 0;
                margin-left: 290px;
                top:0%;
                color: aliceblue;
            }
            .kim .closebtn :hover{
                color: gray;
            }
            .kim img{

                float: right; width: 60px; margin-right: 5px; margin-top: 0px;
                position: absolute;
                top: 0;

            }
            .kim h1{

                float: right; width: 60px; margin-left: 60px; margin-top: 0px;
                position: absolute;
                top:0;
                font-size: 28px;
                color: aliceblue;
            }
            .link :hover {
                background-color: <?php echo htmlspecialchars($_SESSION['dark']); ?>;

            }
            .link2 :hover {
                background-color: <?php echo htmlspecialchars($_SESSION['dark']); ?>;

            }

            .list :hover{
                background-color: white;
            }
            .dropdown-item :hover{
                background-color: <?php echo htmlspecialchars($_SESSION['dark']); ?>;
            }
            .profile_pic  :hover{
                border: 2px solid <?php echo htmlspecialchars($_SESSION['dark']); ?>;
            }
        </style>
    </head>


    <body>
        <nav class="navbar navbar-expand-md  fixed-top" style="  background-color: <?php echo htmlspecialchars($_SESSION['color']); ?>;border: 1px solid black;height:6.2%;">
            <div id="mySidenav1" class="sidenav" style="background-color: <?php echo htmlspecialchars($_SESSION['color']); ?>;">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav1()" style="padding-top: 35px;">&times;</a>
                <div  style=" margin-top:15px;"><div class='link'><a target="test" href="welcome.php">My Profile</a></div>
                    <div class='link'><a target="test" href="reset-color.php" id="car">Reset Color</a></div>
                    <div class='link'><a target="test" href="reset-password.php">Reset Password</a></div>
                    <div class='link'> <a href="#">Contact</a></div>
                </div>
            </div>
            <div id="mySidenav2" class="sidenav" style="background-color: <?php echo htmlspecialchars($_SESSION['color']); ?>;">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav2()" style="padding-top: 35px;">&times;</a>
                <h1 style="text-allign:center;">FRIENDS</h1>
                <?php
                select();
                ?>  

            </div>
            <div class="collapse navbar-collapse" id="navbarsDefault" style="  background-color: <?php echo htmlspecialchars($_SESSION['color']); ?>; height:6.2%; ">
                <ul class="navbar-nav mr-auto" style=" height:6.2%; position:absolute; top:0; background-color: <?php echo htmlspecialchars($_SESSION['color']); ?>;"> <div class="link">
                    <li class="nav-item active">
                        <a  class="nav-link" onclick="openNav1()" style="padding-right:30px; margin-top:5px;">Reset Information <span class="sr-only">(current)</span></a>
                    </li>
                    </div>
                    <div class="link">
                        <li  class="nav-item active">
                            <a   class="nav-link"  onclick="openNav2()" style="padding-right:30px;margin-top:5px;   text-align: center;">Friends<span class="sr-only">(current)</span></a>
                        </li>
                    </div>
                    <div class="link">
                        <li  class="nav-item active" class="nav-item">
                            <a  class="nav-link"  style="padding-right:30px;margin-top:5px;">Link</a>
                        </li>
                    </div>
                    <div class="link">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="https://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"style='margin-top:5px;'>Dropdown</a>
                            <div class="list">
                                <div class="dropdown-menu" aria-labelledby="dropdown01">
                                    <a class="dropdown-item"  href="Logout.php"
                                       target="_parent"     >Logout</a>
                                    <a class="dropdown-item" href="#">Another action</a>
                                    <a class="dropdown-item" href="#">Something else here</a>
                                </div></div>
                        </li>
                    </div>
                </ul>
                <div  >
                    <a href="#" class="profile_pic" style='float:right;'>          <img  src="<?php echo htmlspecialchars($_SESSION['image']); ?>" height="35px" width="35px" style="margin-right:15px;float:right; position:absolute; left:70%; top:0; margin-top:1%;">
                    </a>
                </div>
                <form class="form-inline my-2 my-lg-0" style="float:right;" >
                    <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" style="position:absolute; left:75%; top:0; margin-top:1%;">
                    <button class="btn btn-outline-success " type="submit"style="position:absolute; left:92%; top:0; margin-top:1%;">Search</button>
                </form>
            </div>
        </nav>
        <main role="main" class="container">

            <div class="starter-template" id="main">

                <iframe src="welcome.php" name="test" id='test' width='100%'frameborder="0" scrolling="no" onload="resizeIframe() " base target="_parent"></iframe>



            </div>
            <div class="messages">
                <div class="kim" id='kim'>
                    <a href="javascript:void(0)" id='closebtn2'class="closebtn" onclick="close1()" style="margin-top:0;text-decoration:none">&times;</a><div >
                    <img  id='img' src="<?php echo $_SESSION["image"];?>" style=" width :40px; float : left ; margin-left:15px;">
                    <h1 id='h1'><?php echo $_SESSION["username"];?></h1>
                    <textarea id='h2' style="display:none;"></textarea>
                    </div>
                    <iframe src="message.php" id="test2" width='320'frameborder="0"  height="350" base target="_parent"></iframe>
                    <div id="auto-text-area">
                        <form method="post"  name="myform">

                            <button type="button" id='send' class="btn btn-info" onclick="myFunction()" style="margin-left:7px;margin-top:0px;">Send</button>
                            <textarea class ="scroll2" id ="scroll2" name="txtarea" form="myform" style="resize: none; height:40px;"></textarea>
                        </form>
                    </div>
                </div>
            </div>

        </main><!-- /.container -->


        <!-- Bootstrap core JavaScript
================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script>

        </script>
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>    
        <script src="script4.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>