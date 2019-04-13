<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class newcontroller extends Controller
{
        function index($id,$name)
        {
        
            $arr = Array('id'=>$id,'name'=>$name);
            return view('kimo',$arr);
        }
     function index2()
        {
        
            return view('kimp');
        }
}
