function openNav1() {
    document.getElementById("mySidenav2").style.width = "0";
    document.getElementById("mySidenav1").style.width = "300px";
    document.getElementById("kim").style.marginLeft="300px";
}

/* Set the width of the side navigation to 0 */
function closeNav1() {
    document.getElementById("mySidenav1").style.width = "0";
    document.getElementById("main").style.marginLeft = "0px";
    document.getElementById("kim").style.marginLeft="3px";
}
function openNav2() {
    document.getElementById("mySidenav1").style.width = "0";
    document.getElementById("mySidenav2").style.width = "300px";
    document.getElementById("kim").style.marginLeft="300px";
}

/* Set the width of the side navigation to 0 */
function closeNav2() {
    document.getElementById("mySidenav2").style.width = "0";
    document.getElementById("main").style.marginLeft = "0px";
    document.getElementById("kim").style.marginLeft="3px";
}
function myFunction() {
    var x=document.getElementById("scroll2").value;
    document.getElementById("scroll2").value ='';
    var iframe = document.getElementById('test2');
    var innerDoc = iframe.contentWindow.document;
    var div = document.createElement('div');
    div.className = 'speech-bubble';
    div.innerHTML =
        "<div class='content'>" + x +"</div></div>";

    innerDoc.getElementById('wrap').appendChild(div);
    iframe.contentWindow.scrollTo( 0, 999999 );
    var id2 = document.getElementById('h2').value;
    $.ajax({
        url: '4.php',
        type: 'POST',
        data: {id:id2
               ,content:x,state:4
              },
        success: function(data) {
            console.log(data); 
        }
    });
}
function close1(){

    document.getElementById("kim").style.display="none";;

}
function message(image,name,id) {
    $.ajax({
        url: '4.php',
        type: 'POST',
        data: {id:id
               ,state:5
              },
        success: function(data) {
            console.log(data); 
        }
    });
    document.getElementById('test2').src += '';
    document.getElementById("img").src=image;
    document.getElementById("h1").innerHTML=name;
    document.getElementById("h2").innerHTML=id;
    document.getElementById("kim").style.display="block";
    document.getElementById('test2').src += '';
    var iframe = document.getElementById('test2');
    document.getElementById('n'+id).style.display="none";
}
var add_friend = function(id) {
    var state;
    $.ajax({
        url: '4.php',
        type: 'POST',
        data: {id:id
               ,state :1
              },
        success: function(data) {
            console.log(data); 
        }
    });
    document.getElementById("btn"+id).classList.remove("btn-warning");
    document.getElementById("btn"+id).classList.add("btn-primary");
    document.getElementById("btn"+id).disabled=true;
    document.getElementById("h6"+id).innerHTML="Sent";
    var h='remove_friend(';
    var h2=')';

    document.getElementById("btn"+id).setAttribute("onclick",h+id+h2);
}
var add_like = function(id,current) {
    var state;
    $.ajax({
        url: '4.php',
        type: 'POST',
        data: {id:id,
               fromid:current
               ,state :7
              },
        success: function(data) {
            console.log(data); 
        }
    });
    console.log(id);
}
var accept_friend = function(id) {
    $.ajax({
        url: '4.php',
        type: 'POST',
        data: {id:id
               ,state :11
              },
        success: function(data) {
            console.log(data); 
        }
    });
    document.getElementById("btn"+id).classList.remove("btn-warning");
    document.getElementById("btn"+id).classList.add("btn-danger");
    document.getElementById("h6"+id).innerHTML="Remove";
    var h='remove_friend(';
    var h2=')';

    document.getElementById("btn"+id).setAttribute("onclick",h+id+h2);
}
var remove_friend = function(id) {
    $.ajax({
        url: '4.php',
        type: 'POST',
        data: {id:id,
              state:10
              },
        success: function(data) {
            console.log(data); 
        }
    });
    document.getElementById("btn"+id).classList.remove("btn-danger");
    document.getElementById("btn"+id).classList.add("btn-warning");
    document.getElementById("btn"+id).setAttribute("onclick",'add_friend('+id+')');
    document.getElementById("h6"+id).innerHTML="Request";
}
function text(){
    if(document.getElementById("text2").getAttribute('rows')=="1"){
        document.getElementById("text2").setAttribute('rows',"5");
    }
    else{

        document.getElementById("text2").setAttribute('rows',"1");
        console.log(document.getElementById("text2").height);

    }
}
function resizeIframe() {
    var iframe = document.getElementById('test');
    var innerDoc = iframe.contentWindow.document;
    iframe.style.height =innerDoc.documentElement.scrollHeight+'px';  }
function resizeIframe2(x) {
    var iframe = document.getElementById('test');
    var innerDoc = iframe.contentWindow.document;
    iframe.style.height =innerDoc.documentElement.scrollHeight+'px';
}
function post( img){
    var y=img;
    var id2 = document.getElementById('text2').value;
    if(id2 !=""){
        var x="<div class=\"col\" style=\"float:right; width:100%;\">\r\n <div class=\"col\" style=\"width:500px; float:right; margin-right:150px;\">\r\n <div class=\"panel panel-default\">\r\n <div class=\"panel-body\">\r\n <section class=\"post-heading\">\r\n   <div class=\"row\">\r\n <div class=\"col-md-11\">\r\n <div class=\"media\">\r\n <div class=\"media-left\">\r\n "+                  "<a href=\"#\">\r\n <img class=\"media-object photo-profile\" style=\"    border-radius: 50%;\r\n\" src="+y+" width=\"40\" height=\"40\" alt=\"...\">\r\n  <\/a>\r\n <\/div>\r\n <div class=\"media-body\">\r\n <a href=\"#\" class=\"anchor-username\"><h4 class=\"media-heading\">"+"kimo"+"<\/h4><\/a> \r\n<a href=\"#\" class=\"anchor-time\">51 mins<\/a>\r\n <\/div>\r\n        <\/div>\r\n <\/div>\r\n <div class=\"col-md-1\">\r\n <a href=\"#\"><i class=\"glyphicon glyphicon-chevron-down\"><\/i><\/a>\r\n    <\/div>\r\n <\/div> \r\n  <\/section>\r\n<section class=\"post-body\">\r\n <p class='post-content'>"+id2+ "</p><\/section>\r\n <section class=\"post-footer\">\r\n <hr>\r\n<div class=\"post-footer-option container\">\r\n <ul class=\"list-unstyled\">\r\n <li><a href=\"#\"><i class=\"glyphicon glyphicon-thumbs-up\"><\/i> Like<\/a><\/li>\r\n <li><a href=\"#\"><i class=\"glyphicon glyphicon-comment\"><\/i> Comment<\/a><\/li>\r\n<li><a href=\"#\"><i class=\"glyphicon glyphicon-share-alt\"><\/i> Share<\/a><\/li>\r\n                <\/ul>\r\n<\/div>\r\n <div class=\"post-footer-comment-wrapper\">\r\n <div class=\"comment-form\">\r\n <\/div>\r\n  <div class=\"comment\">\r\n <div class=\"media\">\r\n <div class=\"media-left\">\r\n <a href=\"#\">\r\n     <img class=\"media-object photo-profile\" src="+y+" width=\"32\" height=\"32\" alt=\"...\">\r\n <\/a>\r\n<\/div>\r\n<div class=\"media-body\">\r\n<a href=\"#\" class=\"anchor-username\"><h4 class=\"media-heading\">Media heading<\/h4><\/a> \r\n<a href=\"#\" class=\"anchor-time\">51 mins<\/a>\r\n<\/div>\r\n <\/div>\r\n<\/div>\r\n <\/div>\r\n<\/section>\r\n<\/div>\r\n<\/div>\r\n\t<\/div>\r\n<\/div>";
        document.getElementById('all').innerHTML=x+document.getElementById('all').innerHTML;
        document.getElementById('text2').value="";

        $.ajax({
            url: '4.php',
            type: 'POST',
            data: {id:id2
                   ,state:6
                  },
            success: function(data) {
                console.log(data); 
            }
        });
        document.location.reload(true);
        parent.resizeIframe2(500);

    }
}
function comment(id,user,image){
    console.log(image);
    var text=document.getElementById("text"+id).value;
    console.log(text);
    var f="<\/div><div class=\"comment\"> <div class=\"media\"><div class=\"media-left\"><a href=\"#\"><img class=\"media-object photo-profile\" src=\""+image+"\" width=\"32\" height=\"32\" alt=\"...\"><\/a><\/div><div class=\"media-body\"><a href=\"#\" class=\"anchor-username\"><h4 class=\"media-heading\">"+user+"<\/h4><\/a> <a href=\"#\" class=\"anchor-time\">51 mins<\/a><\/div><p class='par'>"+text+"<p><\/div><\/div>";
    text2=document.getElementById("comment"+id).innerHTML+=f;
    parent.resizeIframe2(500);
    $.ajax({
        url: '4.php',
        type: 'POST',
        data: {id:id
               ,state:8
               ,image:image,
               content:text
              },

        success: function(data) {
            console.log(data); 
        }
    });

}
function comment_now(id){
    var text=document.getElementById('comments'+id);
    if(text.style.display==='none'){
        text.style.display='block';
        document.getElementById('comment'+id).style.display='block';
    }
    else 
    {            text.style.display='none';
     document.getElementById('comment'+id).style.display='none';
    }
    parent.resizeIframe2(500);


}
function text3(id){

    textarea = document.getElementById("text"+id);
    textarea.addEventListener("keypress", function() {
        if(textarea.scrollTop != 0){
            textarea.style.height = (textarea.scrollHeight + 5)+"px";
        }

    }, false);}
function share(id,content,fromid,date,pics){
        $.ajax({
            url: '4.php',
            type: 'POST',
            data: {id:id,
                   content:content,
                   fromid:fromid,
                   date:date,
                   pics:pics,
                   state:12
                  },
            success: function(data) {
                console.log(data); 
            }
        });
        document.location.reload(true);
        parent.resizeIframe2(500);
    
}