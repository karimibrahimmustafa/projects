<?php
// Include config file
require_once "config.php";

// Define variables and initialize with empty values
$username = $password = $confirm_password = "";
$username_err = $password_err = $confirm_password_err = "";
$color=$color_err="";
$image_err="";
$path="";
// Processing form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){

    // Validate username
    if(empty(trim($_POST["username"]))){
        $username_err = "Please enter a username.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE username = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_username);

            // Set parameters
            $param_username = trim($_POST["username"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);

                if(mysqli_stmt_num_rows($stmt) == 1){
                    $username_err = "This username is already taken.";
                } else{
                    $username = trim($_POST["username"]);
                }
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }
    if(empty(trim($_POST["color"]))){
        $color_err = "Please enter a color.";     
    }  else{
        $color = trim($_POST["color"]);
    }
    // Validate password
    if(empty(trim($_POST["password"]))){
        $password_err = "Please enter a password.";     
    } elseif(strlen(trim($_POST["password"])) < 6){
        $password_err = "Password must have atleast 6 characters.";
    } else{
        $password = trim($_POST["password"]);
    }

    // Validate confirm password
    if(empty(trim($_POST["confirm_password"]))){
        $confirm_password_err = "Please confirm password.";     
    } else{
        $confirm_password = trim($_POST["confirm_password"]);
        if(empty($password_err) && ($password != $confirm_password)){
            $confirm_password_err = "Password did not match.";
        }
    }

    $target_dir = "upload/";
    $target_file = $target_dir . basename($_FILES["image2"]["name"]);
    $uploadOk = 1;
    $ext = pathinfo($_FILES['image2']['name'], PATHINFO_EXTENSION);
    $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
    // Check if image file is a actual image or fake image
    if(isset($_POST["image2"])) {
        $check = getimagesize($_FILES["image2"]["tmp_name"]);
        if($check !== false) {
            echo "File is an image - " . $check["mime"] . ".";
            $uploadOk = 1;
        } else {
            echo "File is not an image.";
            $uploadOk = 0;
        }
    }
    // Check file size
    if ($_FILES["image2"]["size"] > 5000000) {
        echo "Sorry, your file is too large.";
        $uploadOk = 0;
    }
    // Allow certain file formats
    // Function for resizing jpg, gif, or png image files

    // Check input errors before inserting in database
    if(empty($username_err) && empty($password_err) && empty($confirm_password_err)&& empty($color_err)&& empty($image_err)){

        // Prepare an insert statement
        $sql = "INSERT INTO users (username, password, color ,image,cover) VALUES (?, ?, ?, ?,?)";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
               && $imageFileType != "gif" ) {
                echo "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
                $uploadOk = 0;
            }
            // Check if $uploadOk is set to 0 by an error
            if ($uploadOk == 0) {
                echo "Sorry, your file was not uploaded.";
                // if everything is ok, try to upload file
            } else {
                if (move_uploaded_file($_FILES["image2"]["tmp_name"], $target_file)) {
                    echo "The file ". basename( $_FILES["image2"]["name"]). " has been uploaded.";
                } else {
                    $image_err= "Sorry, there was an error uploading your file.";
                }
            }
            $wmax = 150;
            $hmax = 150;

            if(!is_dir($target_dir .$param_username."/")){
                //Directory does not exist, so lets create it.
                mkdir($target_dir .$param_username."/", 0755, true);
            }
            $target_file2=$target_dir .$param_username."/". basename($_FILES["image2"]["name"]);


            function resize($target, $newcopy, $w, $h, $ext) {

                list($w_orig, $h_orig) = getimagesize($target);
                $scale_ratio = $w_orig / $h_orig;
                $img = "";
                $ext = strtolower($ext);
                if ($ext == "gif"){ 
                    $img = imagecreatefromgif($target);
                } else if($ext =="png"){ 
                    $img = imagecreatefrompng($target);
                } else { 
                    $img = imagecreatefromjpeg($target);
                }
                $tci = imagecreatetruecolor($w, $h);
                // imagecopyresampled(dst_img, src_img, dst_x, dst_y, src_x, src_y, dst_w, dst_h, src_w, src_h)
                imagecopyresampled($tci, $img, 0, 0, 0, 0, $w, $h, $w_orig, $h_orig);
                imagejpeg($tci, $newcopy, 80);
                echo "resize";
            }
            resize($target_file, $target_file2, 150,150, $ext);

            unlink($target_file) or die("Couldn't delete file");

            mysqli_stmt_bind_param($stmt, "sssss", $param_username, $param_password, $param_color, $param_image, $param_cover);
            // Set parameters
            $param_image= $target_file2;
            $param_cover= 'upload/1.jpg';
            $param_username = $username;
            $param_color = $color;
            $param_password = password_hash($password, PASSWORD_DEFAULT); // Creates a password hash

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                // Redirect to login page
                header("location: login.php");
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Close connection
    mysqli_close($link);
}
?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Sign Up</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
        <link rel="stylesheet" href= "style.css">

    </head>
    <body>
        <div class="wrapper">
            <h2>Sign Up</h2>
            <p>Please fill this form to create an account.</p>
            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post" enctype="multipart/form-data">
                <div class="form-group <?php echo (!empty($username_err)) ? 'has-error' : ''; ?>">
                    <label>Username</label>
                    <input type="text" name="username" class="form-control" value="<?php echo $username; ?>">
                    <span class="help-block"><?php echo $username_err; ?></span>
                </div> 
                <div >
                    <label>Color</label>
                    <input type="color" id="color" name="color"
                           value="#f6b73c">
                    <span class="help-block"><?php echo $color_err; ?></span>
                </div> 
                <div >
                    <label>image</label>
                    <input type="file" name="image2">
                    <span class="help-block"><?php echo $image_err; ?></span>
                </div>  
                <div class="form-group <?php echo (!empty($password_err)) ? 'has-error' : ''; ?>">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" value="<?php echo $password; ?>">
                    <span class="help-block"><?php echo $password_err; ?></span>
                </div>
                <div class="form-group <?php echo (!empty($confirm_password_err)) ? 'has-error' : ''; ?>">
                    <label>Confirm Password</label>
                    <input type="password" name="confirm_password" class="form-control" value="<?php echo $confirm_password; ?>">
                    <span class="help-block"><?php echo $confirm_password_err; ?></span>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="Submit">
                    <input type="reset" class="btn btn-default" value="Reset">
                </div>
                <p>Already have an account? <a href="login.php">Login here</a>.</p>
            </form>
        </div>    
    </body>
</html>