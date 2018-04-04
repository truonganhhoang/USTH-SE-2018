<?php
 
namespace App\Http\Controllers;
 
use App\User;
use App\Http\Controllers\Controller;
 
class HelloworldController extends Controller
{
    /**
     * Show the profile for the given user.
     *
     * @return Response
     */
    public function HelloWorld($id)
    {
        return view('helloworld');
    }
}
