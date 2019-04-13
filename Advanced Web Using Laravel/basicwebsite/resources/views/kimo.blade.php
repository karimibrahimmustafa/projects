@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Dashboard {{  "  ".$name }}</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif
                    @if ($name==='kimo')
                        <div class="alert alert-success" role="alert">
                            {{'kimo welocoooooooooome   '.$id  }}
                        </div>
                    @else
                    <div class="alert alert-danger" role="alert">
                            {{$name ." is not admin" }}
                        </div>
                    @endif

                    You are logged in!
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
