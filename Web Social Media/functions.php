<?php

function login($link){
    $sql = "SELECT id, username, password, color ,image ,cover,request,send,friends,active FROM users WHERE username = ?";

    if($stmt = mysqli_prepare($link, $sql)){
        // Bind variables to the prepared statement as parameters
        mysqli_stmt_bind_param($stmt, "s", $param_username);

        // Set parameters
        $param_username = htmlspecialchars($_SESSION["username"]);

        // Attempt to execute the prepared statement
        if(mysqli_stmt_execute($stmt)){
            // Store result
            mysqli_stmt_store_result($stmt);

            // Check if username exists, if yes then verify password
            if(mysqli_stmt_num_rows($stmt) == 1){                    
                // Bind result variables
                mysqli_stmt_bind_result($stmt, $id, $username, $hashed_password,$color,$image,$cover,$request,$send,$friends,$active);
                if(mysqli_stmt_fetch($stmt)){

                    $_SESSION["reset"]=0;
                    $_SESSION["loggedin"] = true;
                    $_SESSION["id"] = $id;
                    $_SESSION["username"] = $username;                            
                    $_SESSION["color"] = $color;                            
                    $_SESSION["image"] = $image; 
                    $_SESSION["send"] = $send; 
                    $_SESSION["cover"] = $cover; 
                    $_SESSION["friends"] = $friends;                            
                    $_SESSION["request"] = $request;  
                    $_SESSION["active"] = $active;                            

                }}}}}
function select()

{                                            
    $arr=array();
    $arr2=array();
    $sql = "SELECT id, username, password, color ,image FROM users ";
    $link2 = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, 'kimo');
    if($stmt2 = mysqli_prepare($link2, $sql)){
        // Bind variables to the prepared statement as parameters
        if(mysqli_stmt_execute($stmt2)){
            // Store result
            mysqli_stmt_store_result($stmt2);
            // Bind result variables
            $integerIDs = array_map('intval', explode(',', $_SESSION["friends"]));
            $integerIDs2 = array_map('intval', explode(',', $_SESSION["request"]));
            $integerIDs3 = array_map('intval', explode(',', $_SESSION["send"]));
            mysqli_stmt_bind_result($stmt2, $ids, $usernames, $hashed_passwords,$colors,$images);
            while(mysqli_stmt_fetch($stmt2)){
                $stri='btn'.$ids;
                $arr2[$ids]=$usernames;
                $arr[$ids]=$images;
                if($usernames!==$_SESSION["username"]){
                    if(in_array($ids, $integerIDs)){
                        echo " <div class ='link2'><div class ='friend'><img src='$images'width='75' onclick='message(\"$images\",\"$usernames\",\"$ids\")'><br>";
                        if(array_key_exists($ids, $_SESSION["messages"]))
                            echo " <span class='badge' id='n".$ids."'>".$_SESSION["messages"][$ids]."</span>";
                        echo "<a href='profile.php?id=".$ids."' style='font-size:20px;' target='test'>".$usernames."</a>";
                        echo " <button type= 'button' class='btn btn-danger' onclick='remove_friend($ids)' id='$stri'><h6 id='h6$ids'> Remove </h6></button></div></div>";

                    }
                    else if(in_array($ids, $integerIDs2))
                    {echo " <div class ='link2'><div class ='friend'><img src='$images' width='75'><br> ";
                     echo "<a href='profile.php?id=".$ids." ' style='font-size:20px;   pointer-events: none;
                     ' target='test'>".$usernames."</a>";
                     echo "<button type= 'button' class='btn btn-success' onclick='accept_friend($ids)' id='$stri'><h6 id='h6$ids'> Accept</h6></button></div></div>";}
                    else if(in_array($ids, $integerIDs3))
                    {echo " <div class ='link2'><div class ='friend'><img src='$images' width='75'><br>";
                     echo "<a href=''disabled style='font-size:20px;    pointer-events: none;' target='test'>".$usernames."</a>";
                     echo "<button type= 'button' class='btn btn-primary' disabled onclick='add_friend($ids,1)' id='$stri'><h6 id='h6$ids'> Sent </h6></button></div></div>";}
                    else
                    { echo " <div class ='link2'><div class ='friend'><img src='$images' width='75'><br> ";
                     echo "<a href='profile.php?id=".$ids." ' style='font-size:20px;' target='test'>".$usernames."</a>";
                     echo"<button type= 'button' class='btn btn-warning' onclick='add_friend($ids,1)' id='$stri'><h6 class='link3' id='h6$ids'> Request </h6></button></div></div>";
                    }
                }

            }}

    }
    $_SESSION['fr_img']=$arr;
    $_SESSION['fr_name']=$arr2;

}
function select_posts($id)
{
    $sql = "SELECT id, content, fromid,name ,likes,date,pics,shared FROM posts WHERE fromid=".$id;
    $link2 = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, 'kimo');
    if($stmt2 = mysqli_prepare($link2, $sql)){
        // Bind variables to the prepared statement as parameters
        if(mysqli_stmt_execute($stmt2)){
            // Store result
            $arrx=array();
            mysqli_stmt_store_result($stmt2);
            mysqli_stmt_bind_result($stmt2, $ids,$contents, $fromids,$names,$likes,$dates,$pics,$shared);
            while(mysqli_stmt_fetch($stmt2)){
                $integerIDs = array_map('intval', explode(',', $likes));
                $count=count($integerIDs)-1;
                $style="color:'gray';";
                $comments =getcomments($ids);
                $date=getdate2($dates);
                $img='';
                $shareform='';
                if($pics!=''){
                    $img ="<img class='imgpost' src='$pics' style='border-radius:2px;width: 470px;
    height: 450px;'>";
                }
                if($shared!=0){
                   $shareform= "<a href='profile.php?id=".$shared."' style='color:gray;'>shared from ".$_SESSION['fr_name'][$shared]."</a>";
                }
                if(in_array($_SESSION['id'], $integerIDs)){
                    $style="style='color:".$_SESSION['color'].";'";
                    $x=1;
                }
                else                             $x=0;
                array_push($arrx,
                           "<div class=\"col\" style=\"float:right; width:100%;\">\r\n <div class=\"col\" style=\"width:500px; float:right; margin-right:150px;\">\r\n <div class=\"panel panel-default\">\r\n <div class=\"panel-body\">\r\n <section class=\"post-heading\">\r\n   <div class=\"row\">\r\n <div class=\"col-md-11\">\r\n <div class=\"media\">\r\n <div class=\"media-left\">\r\n "."<a href=\"#\">\r\n <img class=\"media-object photo-profile\" style=\"    border-radius: 50%;\r\n\" src=".$_SESSION['fr_img'][$fromids]." width=\"40\" height=\"40\" alt=\"...\">\r\n  </a>\r\n </div>\r\n <div class=\"media-body\">\r\n <a href=\"#\" class=\"anchor-username\"><h4 class=\"media-heading\">".$_SESSION['prof']."</h4></a> \r\n<a href=\"#\" class=\"anchor-time\">$date</a>\r\n".$shareform." </div>\r\n        </div>\r\n </div>\r\n <div class=\"col-md-1\">\r\n <a href=\"#\"><i class=\"glyphicon glyphicon-chevron-down\"></i></a>\r\n    </div>\r\n </div> \r\n  </section>\r\n<section class=\"post-body\">\r\n <p class='post-content'>".dec_enc('decrypt', $contents). "</p>$img</section>\r\n <section class=\"post-footer\">\r\n <hr>\r\n                                                             <div style='color: gray; ' id='num$ids'>(".$count.") likes</div><div class=\"post-footer-option container\">\r\n <ul class=\"list-unstyled\">\r\n <li style='padding-right: 25px; text-align: center; width: 125px;'><a  id='like$ids' class='icons'".$style." onclick='add_like2($ids,".$_SESSION['id'].",$count,$x)'><i class='fa fa-thumbs-up' aria-hidden='true' style='font-size: 32px;'></i></a></li>\r\n <li style='padding-right: 25px; text-align: center; width: 125px;'><a  class='icons' onclick='comment_now($ids)'><i class='fa fa-comment' aria-hidden='true' style='font-size: 32px;'></i></a></li style='padding-right: 25px; text-align: center; width: 125px;'>\r\n<li><a  class='icons' onclick=\"share( $ids,'$contents', $fromids,'$dates','$pics')\"><i class='fa fa-share-alt' aria-hidden='true' style='font-size: 32px;'></i></a></li>\r\n </ul>\r\n</div>\r\n <div id='comments$ids' style='display:none;'><div class=\"post-footer-comment-wrapper\" id='comment$ids'>\r\n <div class=\"comment-form\" id='commentform$ids' '>\r\n <button class='btn btn-outline-success' onclick =\"comment($ids,'".$_SESSION['username']."','".$_SESSION['image']."')\">comment</button><textarea id='text$ids' class='text3'rows='1'cols='50' placeholder=\"What's in your mind\"onclick ='text3($ids)' style=\"font-family: Tahoma, Verdana, Segoe, sans-serif;font-weight: bold;font-size: 15px;font-style: normal;\"></textarea></div>$comments    </div>\r\n</section>\r\n</div>\r\n</div>\r\n\t</div>\r\n</div>");
            }
            $ii=count($arrx);
            for ($i=$ii-1;$i>=0;$i--){

                echo $arrx[$i];
            }

        }
    }

}
function select_posts_welcome($id)
{
    $sql = "SELECT id, content, fromid ,likes ,date,pics,shared FROM posts WHERE fromid=".$id;
    $link2 = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, 'kimo');
    if($stmt2 = mysqli_prepare($link2, $sql)){
        // Bind variables to the prepared statement as parameters
        if(mysqli_stmt_execute($stmt2)){
            // Store result
            $arrx=array();
            mysqli_stmt_store_result($stmt2);
            mysqli_stmt_bind_result($stmt2, $ids,$contents, $fromids,$likes,$dates,$pics,$shared);
            while(mysqli_stmt_fetch($stmt2)){
                $integerIDs = array_map('intval', explode(',', $likes));
                $style="color:'gray';";
                $count=count($integerIDs)-1; 
                $img='';
                $shareform='';
                if($pics!=''){
                    $img ="<img class='imgpost' src='$pics' style='border-radius:2px;width: 470px;
    height: 450px;'>";
                }
                $date=getdate2($dates);
                $comments =getcomments($ids);
                if($shared!=0){
                   $shareform= "<a href='profile.php?id=".$shared."' style='color:gray;'>shared from ".$_SESSION['fr_name'][$shared]."</a>";
                }
                if(in_array($_SESSION['id'], $integerIDs)){
                    $style="style='color:".$_SESSION['color'].";'";  
                    $x=1;

                }
                else                    
                    $x=0;
                array_push($arrx,
                           "<div class=\"col\" style=\"float:right; width:100%;\">\r\n <div class=\"col\" style=\"width:500px; float:right; margin-right:150px;\">\r\n <div class=\"panel panel-default\">\r\n <div class=\"panel-body\">\r\n <section class=\"post-heading\">\r\n   <div class=\"row\">\r\n <div class=\"col-md-11\">\r\n <div class=\"media\">\r\n <div class=\"media-left\">\r\n "."<a href=\"#\">\r\n <img class=\"media-object photo-profile\" style=\"    border-radius: 50%;\r\n\" src=".$_SESSION['image']." width=\"40\" height=\"40\" alt=\"...\">\r\n  </a>\r\n </div>\r\n <div class=\"media-body\">\r\n <a href=\"#\" class=\"anchor-username\"><h4 class=\"media-heading\">".$_SESSION['username']."</h4></a> \r\n<a href=\"#\" class=\"anchor-time\">$date</a>\r\n ".$shareform."</div>\r\n        </div>\r\n </div>\r\n <div class=\"col-md-1\">\r\n <a href=\"#\"><i class=\"glyphicon glyphicon-chevron-down\"></i></a>\r\n    </div>\r\n </div> \r\n  </section>\r\n<section class=\"post-body\">\r\n <p class='post-content'>".dec_enc('decrypt', $contents). "</p>$img</section>\r\n <section class=\"post-footer\">\r\n <hr>\r\n<div style='color: gray; '  id='num$ids'>(".$count.") likes</div><div class=\"post-footer-option container\">\r\n <ul class=\"list-unstyled\">\r\n <li style='padding-right: 25px; text-align: center; width: 125px;'><a  id='like$ids' class='icons'".$style." onclick='add_like2($ids,".$_SESSION['id'].",$count,$x)'><i class='fa fa-thumbs-up' aria-hidden='true' style='font-size: 32px;'></i></a></li>\r\n <li style='padding-right: 25px; text-align: center; width: 125px;'><a  class='icons' onclick='comment_now($ids)'><i class='fa fa-comment' aria-hidden='true' style='font-size: 32px;'></i></a></li style='padding-right: 25px; text-align: center; width: 125px;'>\r\n<li><a href=\"#\" class='icons'><i class='fa fa-share-alt' aria-hidden='true' style='font-size: 32px;'></i></a></li>\r\n </ul>\r\n</div>\r\n <div id='comments$ids' style='display:none;'><div class=\"post-footer-comment-wrapper\" id='comment$ids'>\r\n <div class=\"comment-form\" id='commentform$ids' '>\r\n <button class='btn btn-outline-success' onclick =\"comment($ids,'".$_SESSION['username']."','".$_SESSION['image']."')\">comment</button><textarea id='text$ids' class='text3'rows='1'cols='50' placeholder=\"What's in your mind\"onclick ='text3($ids)' style=\"font-family: Tahoma, Verdana, Segoe, sans-serif;font-weight: bold;font-size: 15px;font-style: normal;\"></textarea></div>$comments    </div>\r\n</section>\r\n</div>\r\n</div>\r\n\t</div>\r\n</div>");
            }
            $ii=count($arrx);
            for ($i=$ii-1;$i>=0;$i--){

                echo $arrx[$i];
            }

        }
    }

}

function dec_enc($action, $string) {
    $output = false;

    $encrypt_method = "AES-256-CBC";
    $secret_key = 'This is my secret key';
    $secret_iv = 'This is my secret iv';

    // hash
    $key = hash('sha256', $secret_key);

    // iv - encrypt method AES-256-CBC expects 16 bytes - else you will get a warning
    $iv = substr(hash('sha256', $secret_iv), 0, 16);

    if( $action == 'encrypt' ) {
        $output = openssl_encrypt($string, $encrypt_method, $key, 0, $iv);
        $output = base64_encode($output);
    }
    else if( $action == 'decrypt' ){
        $output = openssl_decrypt(base64_decode($string), $encrypt_method, $key, 0, $iv);
    }

    return $output;
}
function select_messages()
{
    $sql = "SELECT num,content, fromid, toid FROM messages";
    $link2 = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, 'kimo');
    if($stmt2 = mysqli_prepare($link2, $sql)){
        // Bind variables to the prepared statement as parameters
        if(mysqli_stmt_execute($stmt2)){
            // Store result
            mysqli_stmt_store_result($stmt2);
            // Bind result variables
            mysqli_stmt_bind_result($stmt2,$f, $contents, $froms, $tos);
            while(mysqli_stmt_fetch($stmt2)){

                if($froms==$_SESSION['id'] && $tos==$_SESSION['active']){
                    echo"<div class=\"speech-bubble\">
            <div class=\"content\">".
                        dec_enc('decrypt', $contents)
                        ."</div></div>";

                }
                else if($froms==$_SESSION['active'] && $tos==$_SESSION['id']){
                    echo"<div class=\"speech-bubble2\">
            <div class=\"content\">".
                        dec_enc('decrypt', $contents)
                        ."</div></div>";
                    seen($f);


                }

            }}

    }

}
function seen($f){
    $sql3 = "UPDATE messages SET seen = ? WHERE num = ?";
    $link3 = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

    if($stmt3 = mysqli_prepare($link3, $sql3)){

        mysqli_stmt_bind_param($stmt3, "ii",  $param_q,$param_id);

        $param_q= 0;
        $param_id=$f;

        // Attempt to execute the prepared statement
        if(mysqli_stmt_execute($stmt3)){
            // Redirect to login page
        } else{
            echo "Something went wrong. Please try again later.";
        }
    }


    mysqli_close($link3);

}
function read()
{
    $sql = "SELECT seen ,content, fromid, toid FROM messages";
    $link2 = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, 'kimo');
    if($stmt2 = mysqli_prepare($link2, $sql)){
        // Bind variables to the prepared statement as parameters
        if(mysqli_stmt_execute($stmt2)){
            // Store result
            $arr=array();
            mysqli_stmt_store_result($stmt2);
            // Bind result variables
            mysqli_stmt_bind_result($stmt2, $seen,$contents, $froms, $tos);
            while(mysqli_stmt_fetch($stmt2)){
                if($tos==$_SESSION['id']&& $seen!=0){
                    if(array_key_exists($froms, $arr))
                        $arr[$froms]+=1;
                    else 
                        $arr[$froms]=1;
                }
                $_SESSION['messages']=$arr;

            }}

    }
}
function welcome()
{
    $sql = "SELECT id, username, password, color ,thumb FROM users ";
    $link2 = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, 'kimo');
    if($stmt2 = mysqli_prepare($link2, $sql)){
        // Bind variables to the prepared statement as parameters
        if(mysqli_stmt_execute($stmt2)){
            // Store result
            mysqli_stmt_store_result($stmt2);
            // Bind result variables
            mysqli_stmt_bind_result($stmt2, $ids, $usernames, $hashed_passwords,$colors,$images);
            while(mysqli_stmt_fetch($stmt2)){
                if($usernames!==$_SESSION["username"])
                    echo "<h2> <b>$usernames </h2><b> <img src='$images'><br>";                  


            }}

    }
}
function check_color_img($post_color){
    if(empty(trim($post_color))){
        $color_err = "Please enter a color.";     
    }  else{
        $color = trim($post_color);
    }
    return $color;
}
function check_img($check,$imageFileType){
    if($check !== false) {
        echo "File is an image - " . $check["mime"] . ".";
        $uploadOk = 1;
    } else {
        echo "File is not an image.";
        $uploadOk = 0;
    }
    if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
       && $imageFileType != "gif" ) {
        echo "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
        $uploadOk = 0;
    }
    // Check if $uploadOk is set to 0 by an error
    if ($uploadOk == 0) {
        echo "Sorry, your file was not uploaded.";
        // if everything is ok, try to upload file
    }  
    return $uploadOk;
}
function resize($target, $newcopy, $w, $h, $ext) {
    if(basename($_FILES["image2"]["name"])===" "|| basename($_FILES["image2"]["name"])===""){}else{
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
        ;}
}
function make_img($tmp,$target){
    if (move_uploaded_file($tmp, $target)) {
    } else {
        $image_err= "Sorry, there was an error uploading your file.";
    }

    $wmax = 150;
    $hmax = 150;

    //Check if the directory already exists.
    $target_file2=$target_dir .$param_username."/". basename($_FILES["image2"]["name"]);
    resize($target_file, $target_file2, $wmax, $hmax, $ext);}
function darken_color($rgb) {
    $darker=2;
    $hash = (strpos($rgb, '#') !== false) ? '#' : '';
    $rgb = (strlen($rgb) == 7) ? str_replace('#', '', $rgb) : ((strlen($rgb) == 6) ? $rgb : false);
    if(strlen($rgb) != 6) return $hash.'000000';
    $darker = ($darker > 1) ? $darker : 1;

    list($R16,$G16,$B16) = str_split($rgb,2);

    $R = sprintf("%02X", floor(hexdec($R16)/$darker));
    $G = sprintf("%02X", floor(hexdec($G16)/$darker));
    $B = sprintf("%02X", floor(hexdec($B16)/$darker));

    return $hash.$R.$G.$B;
}
function getdate2($date1){

    $c=date('Y-m-d H:i:s');
    $s=new DateTime( $date1);
    $s2=new DateTime( $c);
    $diff = $s2->diff($s);
    $years=$diff->y;
    $mons=$diff->m;
    $days=$diff->d;
    $hours=$diff->h;
    $mins=$diff->i;
    if($years>0){
        return $years. ' Year Ago';
    }
    else if($mons>0){
        return $mons. ' Months Ago';
    }
    else if($days>0){
        return $days. ' Days Ago';
    }
    else if($hours>0){
        return $hours. ' Hours Ago';
    }
    return $mins. ' Minute Ago';

}
function getcomments($id){
    $h='';
    $sql = "SELECT postid, username,image,content,date FROM comments ";
    $link2 = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, 'kimo');
    if($stmt2 = mysqli_prepare($link2, $sql)){
        // Bind variables to the prepared statement as parameters
        if(mysqli_stmt_execute($stmt2)){
            // Store result

            mysqli_stmt_store_result($stmt2);
            // Bind result variables
            mysqli_stmt_bind_result($stmt2, $ids, $usernames, $images,$contents,$dates);
            while(mysqli_stmt_fetch($stmt2)){
                if($id==$ids){
                    $date=getdate2($dates);
                    $h=$h."\r\n  <div class=\"comment\">\r\n <div class=\"media\">\r\n <div class=\"media-left\">\r\n <a href=\"#\">\r\n     <img class=\"media-object photo-profile\" src=".$images." width=\"32\" height=\"32\" alt=\"...\">\r\n </a>\r\n</div>\r\n<div class=\"media-body\">\r\n<a href=\"#\" class=\"anchor-username\"><h4 class=\"media-heading\">$usernames</h4></a> \r\n<a href=\"#\" class=\"anchor-time\">$date</a>\r\n</div>\r\n <p class='par'>". $contents. "<p></div>\r\n</div>\r\n";
                }
            }
        }}
    return $h;

}

?>