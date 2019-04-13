<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class Movies extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('movies', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->string('name');
            $table->string('link')->unique();
            $table->string('site');
            $table->string('img');
            $table->rememberToken();
            $table->timestamps();    });
        }
    public function down()
    {
        //       
         Schema::dropIfExists('movies');

    }
}
