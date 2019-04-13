<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
    use App\Photo;

class photoController extends Controller
{
    //
    function photo(){
    $photo=Photo::all();
    $arr=Array('photo'=>$photo);
    return view('photo.view',$arr);
    }
    function add(Request $request){
        if($request->isMethod('post')){
        $new = new Photo();
        $new->name=$request->input('name');
        $new->from=$request->input('from');
        $new->to=$request->input('from');
        $new ->save();
                }
        
        
    
    return view('photo.add');
    }
}
