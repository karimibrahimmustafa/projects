function many(movie_id,user_id){
    $.ajaxSetup({
        headers: {
          'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
      });
    $.ajax({
        type:'POST',
        url:'/addRelation',
        data:{mid:movie_id,uid:user_id},
        success:function(data) {
           $("#movie"+movie_id).html("delete from favourite ☑");
           $("#movie"+movie_id).attr('style', 'color:crimson');
           $("#movie"+movie_id).attr('onclick',"remove("+movie_id+","+user_id+")");

        }
     });
  }
  function remove(movie_id,user_id){
    $.ajaxSetup({
        headers: {
          'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
      });
    $.ajax({
        type:'POST',
        url:'/removeRelation',
        data:{mid:movie_id,uid:user_id},
        success:function(data) {
           $("#movie"+movie_id).html("add to favourite ☐ ");
           $("#movie"+movie_id).attr('style', 'color:gold');
           $("#movie"+movie_id).attr('onclick',"many("+movie_id+","+user_id+")");


        }
     });
  }

