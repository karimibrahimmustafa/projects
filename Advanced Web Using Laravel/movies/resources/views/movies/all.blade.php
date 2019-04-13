<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="{{ asset('logo.png') }}">
    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

    <title>Kimo</title>

    <!-- Scripts -->
    <script src="{{ asset('js/app.js') }}" defer></script>
    <script  src="{{ asset('js/jquery-1.4.2.min.js') }}" ></script>
    <script  src="{{ asset('js/jquery-func.js') }} "></script>
    <script  src="{{ asset('js/new.js') }} "></script>
    <!-- Fonts -->
    <link rel="dns-prefetch" href="//fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet" type="text/css">

    <!-- Styles -->
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
    <link rel="stylesheet" href="{{ asset('css/style2.css') }}"  />

</head>
<body><div id="shell">
    <div id="header">
      <h1 id="logo"><a href="{{Request::url()}}"><img src="{{ asset('logo.png') }}"></a></h1>
      <div class="social"> <span>FOLLOW US :</span>
        <ul>
          <li><a class="twitter" href="#">twitter</a></li>
          <li><a class="facebook" href="#">facebook</a></li>
          <li><a class="vimeo" href="#">vimeo</a></li>
          <li><a class="rss" href="#">rss</a></li>
        </ul>
      </div>
      <div id="navigation">
        <ul>
          <li><a class="active" href="#">HOME</a></li>
          <li><a href="#">NEWS</a></li>
          <li><a href="#">IN THEATERS</a></li>
          <li><a href="#">COMING SOON</a></li>
          <li><a href="#">CONTACT</a></li>
          <li><a href="#">ADVERTISE</a></li>
        </ul>
      </div>
      <div id="sub-navigation">
        <ul>
          <li><a href="#">SHOW ALL</a></li>
          <li><a href="#">LATEST TRAILERS</a></li>
          <li><a href="#">TOP RATED</a></li>
          <li><a href="#">MOST COMMENTED</a></li>
        </ul>
          <br>
          <div id='user'><li class="nav-item dropdown">
            <a id="navbarDropdown" class="nav-link2 dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                {{ Auth::user()->name }} <span class="caret"></span>
            </a>

            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="{{ route('logout') }}"
                   onclick="event.preventDefault();
                                 document.getElementById('logout-form').submit();">
                    {{ __('Logout') }}
                </a><a class="dropdown-item" href="{{ route('logout') }}"
                onclick="event.preventDefault();
                              document.getElementById('logout-form').submit();">
                 {{ __('My Favourite') }}
             </a>
                <form id="logout-form" action="{{ route('logout') }}" method="POST" style="display: none;">
                    @csrf
                </form>
            </div>
        </li></div>

      </div>
    </div>
    <div id="main">
      <div id="content">
        <div class="box">
          <div class="head">
            <h1>Akoam</h1>
          </div>
          @for ($i = 0; $i < 18; $i++) 
          <div class="movie">
            <div class="movie-image"> <a href="{{$akoam[$i]->link}}"><span class="play"><span class="name">{{$akoam[$i]->name}}</span></span> <img src="{{$akoam[$i]->img}}" alt="" /> </a></div>
            <div class="rating">
              <div class="rating">
                @if(Auth::user()->movies->find($akoam[$i]->id))
                <h2>{{$akoam[$i]->site}}</h2><a id='movie{{$akoam[$i]->id}}' style="color:crimson;" onclick ="remove({{$akoam[$i]->id}},{{Auth::user()->id }})"> delete from favourite ☑</a>
                </div> 
                @else
                <h2>{{$akoam[$i]->site}}</h2><a id='movie{{$akoam[$i]->id}}' style="color:gold;" onclick ="many({{$akoam[$i]->id}},{{Auth::user()->id }})"> add to favourite ☐ </a>
                </div> 
                @endif    
            </div>
          </div>
          @endfor
          <div class="cl">&nbsp;</div>
              </div>
              <hr style="border-top: 1px solid #ccc;">
        <div id="main">
          <div id="content">
            <div class="box">
              <div class="head">
                <h1>ArabSeed</h1>
              </div>
              @for ($i = 0; $i < 18; $i++) 
              <div class="movie">
                <div class="movie-image"> <a href="{{$arabseed[$i]->link}}"><span class="play"><span class="name">{{$arabseed[$i]->name}}</span></span> <img src="{{$arabseed[$i]->img}}" alt="" /> </a></div>
                <div class="rating">
                  <div class="rating">
                      @if(Auth::user()->movies->find($arabseed[$i]->id))
                      <h2>{{$arabseed[$i]->site}}</h2><a id='movie{{$arabseed[$i]->id}}' style="color:crimson;" onclick ="remove({{$arabseed[$i]->id}},{{Auth::user()->id }})"> delete from favourite ☑</a>
                      </div> 
                      @else
                      <h2>{{$arabseed[$i]->site}}</h2><a id='movie{{$arabseed[$i]->id}}' style="color:gold;" onclick ="many({{$arabseed[$i]->id}},{{Auth::user()->id }})"> add to favourite ☐ </a>
                      </div> 
                      @endif    </div>
              </div>
              @endfor 
              <div class="cl">&nbsp;</div>
              </div>
              <hr style="border-top: 1px solid #ccc;">
            <div id="main">
              <div id="content">
                <div class="box">
                  <div class="head">
                    <h1>CimaClub</h1>
                  </div>
                  @for ($i = 0; $i < 18; $i++) 
                  <div class="movie">
                    <div class="movie-image"> <a href="{{$cimaclub[$i]->link}}"><span class="play"><span class="name">{{$cimaclub[$i]->name}}</span></span> <img src="{{$cimaclub[$i]->img}}" alt="" /> </a></div>
                    <div class="rating">
                        <div class="rating">
                            @if(Auth::user()->movies->find($cimaclub[$i]->id))
                            <h2>{{$cimaclub[$i]->site}}</h2><a id='movie{{$cimaclub[$i]->id}}' style="color:crimson;" onclick ="remove({{$cimaclub[$i]->id}},{{Auth::user()->id }})"> delete from favourite ☑</a>
                            </div> 
                            @else
                            <h2>{{$cimaclub[$i]->site}}</h2><a id='movie{{$cimaclub[$i]->id}}' style="color:gold;" onclick ="many({{$cimaclub[$i]->id}},{{Auth::user()->id }})"> add to favourite ☐ </a>
                            </div> 
                            @endif    </div>
                    </div>
                  @endfor
                  <div class="cl">&nbsp;</div>
              </div>
              <hr style="border-top: 1px solid #ccc;">     
                <div id="main">
                    <div id="content">
                      <div class="box">
                        <div class="head">
                          <h1>Egybest</h1>
                        </div>
                        @for ($i = 0; $i < 18; $i++) 
                        <div class="movie">
                          <div class="movie-image"> <a href="{{$egybest[$i]->link}}"><span class="play"><span class="name">{{$egybest[$i]->name}}</span></span> <img src="{{$egybest[$i]->img}}" alt="" /> </a></div>
                          <div class="rating">
                              <div class="rating">
                                  @if(Auth::user()->movies->find($egybest[$i]->id))
                                  <h2>{{$egybest[$i]->site}}</h2><a id='movie{{$egybest[$i]->id}}' style="color:crimson;" onclick ="remove({{$egybest[$i]->id}},{{Auth::user()->id }})"> delete from favourite ☑</a>
                                  </div> 
                                  @else
                                  <h2>{{$egybest[$i]->site}}</h2><a id='movie{{$egybest[$i]->id}}' style="color:gold;" onclick ="many({{$egybest[$i]->id}},{{Auth::user()->id }})"> add to favourite ☐ </a>
                                  </div> 
                                  @endif    </div>
                          </div>
                        @endfor
                        <div class="cl">&nbsp;</div>
      
                        </div>
        
   
    <div id="footer">
      <p class="lf">Copyright &copy; 2010 <a href="#">SiteName</a> - All Rights Reserved</p>
      <p class="rf">Design by <a href="http://chocotemplates.com/">ChocoTemplates.com</a></p>
      <div style="clear:both;"></div>
    </div>
  </div>
  </body>
  </html>