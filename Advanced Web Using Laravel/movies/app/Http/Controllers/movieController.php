<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Movie;
use App\Movie_user;
use App\User;
use DateTime;

require('simple_html_dom.php');
class movieController extends Controller
{
function index(){
    $controller = new movieController();
    // $st = isset(Movie::all()[0])) ? Movie::all()[0] : false;
    // if($st)
    // $dates=Movie::all()[0]->created_at;
    // $c=date('Y-m-d H:i:s');
    // $s=new DateTime( $dates);
    // $s2=new DateTime( $c);
    // $diff = $s2->diff($s);
    // $days=$diff->d;
    // $minutes=$diff->i;   
    $controller::arabseed(21); 
    $controller::egybest(21); 
    $controller::akoam(21);
    $controller::cimaclub(21);
    $akoams=Movie::where('site','Akoam')->orderBy('updated_at')->get();
    $arabseeds=Movie::where('site','ArabSeed')->orderBy('updated_at')->get();
    $egybests=Movie::where('site','EgyBest')->orderBy('updated_at')->get();
    $cimaclubs=Movie::where('site','CimaClub')->orderBy('updated_at')->get();
$arr=['cimaclub'=>$cimaclubs,'akoam'=>$akoams,'egybest'=>$egybests,'arabseed'=>$arabseeds];
return view('movies.all',$arr);


}

 private function arabseed($f){
    $html = file_get_html('https://arabseed.tv');
    $videos=array();
    $i=0;
        $f=$f+1;
    foreach ($html->find('div.container-wrap div.media-block') as $video) {
        if ($i > $f) {
            break;
        }
        if($i<2){
            $i++;
            continue;
    
        }
        $videoUrl = $video->find('img')[0]->getAttribute('data-src');
        $videot = $video->find('a h3')[0];
        $videot2 = $video->find('a')[0]->href;
        $videot=substr($videot, 4, -5);
        $results = Movie::where('link', $videot2)->first();
        if(!$results){
        $new = new Movie();
        $new->img=$videoUrl;
        $new->name=$videot;
        $new->link=$videot2;
        $new->site='ArabSeed';
        $new->save();
    }        else break;
        $i++;
    }
    }
    private function akoam($f)
       {$i=1;
           $html = file_get_html('https://akoam.net');
        foreach ($html->find('div.subject_box a') as $video) {
            if ($i > $f) {
                break;
            }
            $videoDetails = $video->find('img', 0);
            $videoDetails2 = $video->find('span.title', 0);
            if(!$videoDetails){continue;
            
            }
            $videoTitle = $videoDetails->alt;
            $videoUrl = $videoDetails->src;
            $videoUrl2 = $video->href;
            $results = Movie::where('link', $videoUrl2)->first();
        if(!$results){
            $new = new Movie();
            $new->img=$videoUrl;
            $new->name=$videoTitle;
            $new->link=$videoUrl2;
            $new->site='Akoam';
            $new->save();
        }           
        else break;
        $i++;
    }}
    function egybest($f){
        $i=1;
        $html = file_get_html('https://egy.best/movies/');
        foreach ($html->find('div.movies a.movie') as $video) {
            if ($i > $f) {
                break;
            }
            $videoDetails = $video->find('  img', 0);
            $videoDetails2 = $video->find('span.title', 0);
        
            $videoTitle = $videoDetails2->innertext;
            $url='https://egy.best'.$video->href;
            $videoUrl = $videoDetails->src;
            $results = Movie::where('link', $url)->first();
        if(!$results){
            $new = new Movie();
            $new->name=$videoTitle;
            $new->img=$videoUrl;
            $new->link=$url;
            $new->site='EgyBest';
            $new->save();
        }
        else break;
        $i++;

    }if($i<$f){
        $html = file_get_html('https://egy.best/movies/?page=2');
        foreach ($html->find('div.movies a.movie') as $video) {
            if ($i > $f) {
                break;
            }
            $videoDetails = $video->find('  img', 0);
            $videoDetails2 = $video->find('span.title', 0);
        
            $videoTitle = $videoDetails2->innertext;
            $url='https://egy.best'.$video->href;
            $videoUrl = $videoDetails->src;
            $results = Movie::where('link', $url)->first();
        if(!$results){
            $new = new Movie();
            $new->name=$videoTitle;
            $new->img=$videoUrl;
            $new->link=$url;
            $new->site='EgyBest';
            $new->save();
    }
    else break;
$i++;
    }
}}
function cimaclub($f){
    $i=0;
    $html = file_get_html('http://cimaclub.com');
foreach ($html->find('div.moviesBlocks div.movie') as $video) {
    if ($i > $f) {
        break;
    }
    $videoDetails = $video->find('img', 0);
    $videoDetails2 = $video->find('boxcontentFilm h2', 0);
    $videoTitle = $videoDetails->alt;
    $url=$video->find('a', 0)->href;
    $videoUrl = $videoDetails->src;
    $results = Movie::where('link', $url)->first();
    if(!$results){
        $new = new Movie();
        $new->name=$videoTitle;
        $new->img=$videoUrl;
        $new->link=$url;
        $new->site='CimaClub';
        $new->save();
}
else break;
$i++;
}

}
public function many(){
$new=new Movie_user();
$new->movie_id=1;
$new->user_id=1;
$new->save();
return view('movies.many');
}
public function add(){
    
    $new=new Movie_user();
    $new->movie_id=$_POST['mid'];
    $new->user_id=$_POST['uid'];
    $new->save();
    }
    public function remove(){
        
            User::find($_POST['uid'])->movies()->detach($_POST['mid']);

    }
    
}

